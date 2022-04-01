package mtd319.muhammadhazim.moviebooking.adapters;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.moviebooking.R;
import mtd319.muhammadhazim.moviebooking.fragments.ShowTimingsFragment;
import mtd319.muhammadhazim.moviebooking.models.ShowTimingModel;
import mtd319.muhammadhazim.moviebooking.activities.BookingActivity;
import mtd319.muhammadhazim.moviebooking.activities.MovieDetailActivity;
import com.cinema.moviebooking.databinding.LayoutItemShowTimingsBinding;
import mtd319.muhammadhazim.moviebooking.fragments.CinemaDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterShowTimings extends RecyclerView.Adapter<RecyclerAdapterShowTimings.ViewHolder> {

    private Activity activity;
    private List<ShowTimingModel> list;
    private List<ShowTimingModel> temp;
    private ShowTimingsFragment fragment;

    public RecyclerAdapterShowTimings(ShowTimingsFragment fr, Activity activity, List<ShowTimingModel> list) {
        this.activity = activity;
        this.fragment = fr;
        this.list = list;
        this.temp=new ArrayList<>();
        this.temp.addAll(this.list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder((LayoutItemShowTimingsBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext())
                , R.layout.layout_item_show_timings,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding.tvCinemaName.setText(list.get(i).getCinema());
        if(viewHolder.binding.flow.getChildCount()==0) {
            String[] timings = list.get(i).getTimings().split(",");
            for (String text : timings) {
                String[] availability = text.split("\\|");
                TextView textView = buildLabel(availability[0], availability[1], i);
                viewHolder.binding.flow.addView(textView);
            }
        }
    }


    private TextView buildLabel(final String text, final String availability, final int pos) {
        TextView textView = new TextView(activity);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setPadding((int)dpToPx(10), (int)dpToPx(2), (int)dpToPx(10), (int)dpToPx(2));
        textView.setBackgroundResource(R.drawable.bg_rect_corner);
        textView.setTextColor(ContextCompat.getColor(activity, android.R.color.holo_green_dark));
        if(availability.equalsIgnoreCase("A")) {
            textView.setTextColor(ContextCompat.getColor(activity, android.R.color.holo_green_dark));
        }
        else if(availability.equalsIgnoreCase("U")) {
            textView.setTextColor(ContextCompat.getColor(activity, android.R.color.darker_gray));
        }
        else {
            textView.setTextColor(ContextCompat.getColor(activity, android.R.color.holo_orange_light));
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(availability.equalsIgnoreCase("U")) {
                    Toast.makeText(activity, "Show Packed!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(activity, BookingActivity.class);
                    intent.putExtra("movieName", ((MovieDetailActivity) activity).binding.tvMovieTitle.getText().toString().trim());
                    intent.putExtra("cinemaName", list.get(pos).getCinema());
                    intent.putExtra("showTime", text);
                    intent.putExtra("date",fragment.selectedDate);
                    activity.startActivity(intent);
                }
            }
        });
        return textView;
    }

    private float dpToPx(float dp){
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, activity.getResources().getDisplayMetrics());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LayoutItemShowTimingsBinding binding;
        ViewHolder(final LayoutItemShowTimingsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

//            binding.flow.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(activity,BookingActivity.class);
//                    intent.putExtra("movieName",((MovieDetailActivity) activity).binding.tvMovieTitle.getText().toString().trim());
//                    intent.putExtra("cinemaName",list.get(getAdapterPosition()).getCinema());
//                    intent.putExtra("showTime",te)
//                    activity.startActivity(intent);
//                }
//            });

            binding.tvCinemaName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("cinemaName",list.get(getAdapterPosition()).getCinema());
                    bundle.putInt("cinemaImage",list.get(getAdapterPosition()).getCinemaRes());
                    bundle.putDouble("lat",list.get(getAdapterPosition()).getLat());
                    bundle.putDouble("lng",list.get(getAdapterPosition()).getLng());
                    bundle.putString("address",list.get(getAdapterPosition()).getCompleteAddress());
                    CinemaDetailFragment cinemaDetailFragment = new CinemaDetailFragment();
                    cinemaDetailFragment.setArguments(bundle);
                    FragmentManager fragmentManager = ((MovieDetailActivity)activity).getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.addToBackStack(null);
                    ft.replace(((MovieDetailActivity) activity).binding.fl.getId(),cinemaDetailFragment);
                    ft.commit();
                }
            });
        }
    }


    public void filter(String s) {
        list.clear();
        for(int i=0;i<temp.size();i++) {
            if(temp.get(i).getCinema().toLowerCase().contains(s.toLowerCase())) {
                list.add(temp.get(i));
            }
        }
        notifyDataSetChanged();
    }
}
