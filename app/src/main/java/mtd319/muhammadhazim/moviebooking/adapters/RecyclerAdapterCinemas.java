package mtd319.muhammadhazim.moviebooking.adapters;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinema.moviebooking.R;
import com.cinema.moviebooking.databinding.LayoutItemCinemaBrowsingBinding;
import mtd319.muhammadhazim.moviebooking.fragments.ShowTimingsFragment;

import java.util.List;

public class RecyclerAdapterCinemas extends RecyclerView.Adapter<RecyclerAdapterCinemas.ViewHolder> {

    private List<String> list;
    private int selectedPos = 0;
    private Fragment fragment;
    private Activity activity;
    private boolean isFrag;

    public RecyclerAdapterCinemas(List<String> list, Fragment fragment) {
        this.list = list;
        this.fragment = fragment;
        isFrag =true;
    }

    public RecyclerAdapterCinemas(List<String> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        isFrag= false;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder((LayoutItemCinemaBrowsingBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.layout_item_cinema_browsing,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding.tvCinema.setText(list.get(i));
        if(isFrag) {
            if (fragment.getActivity() != null) {
                if (i == selectedPos) {
                    viewHolder.binding.tvCinema.setBackground(ContextCompat.getDrawable(fragment.getActivity(), R.drawable.bg_rect_fill));
                    viewHolder.binding.tvCinema.setTextColor(ContextCompat.getColor(fragment.getActivity(), android.R.color.white));
                } else {
                    viewHolder.binding.tvCinema.setBackground(ContextCompat.getDrawable(fragment.getActivity(), R.drawable.bg_rect_corner));
                    viewHolder.binding.tvCinema.setTextColor(ContextCompat.getColor(fragment.getActivity(), R.color.colorAccent));
                }
            }
        }
        else {
            if (i == selectedPos) {
                viewHolder.binding.tvCinema.setBackground(ContextCompat.getDrawable(activity, R.drawable.bg_rect_fill));
                viewHolder.binding.tvCinema.setTextColor(ContextCompat.getColor(activity, android.R.color.white));
            } else {
                viewHolder.binding.tvCinema.setBackground(ContextCompat.getDrawable(activity, R.drawable.bg_rect_corner));
                viewHolder.binding.tvCinema.setTextColor(ContextCompat.getColor(activity, R.color.colorAccent));
            }
        }

        /*if(i==4)
            viewHolder.binding.view.setVisibility(View.GONE);
        else viewHolder.binding.view.setVisibility(View.VISIBLE);*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LayoutItemCinemaBrowsingBinding binding;
        ViewHolder(LayoutItemCinemaBrowsingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.tvCinema.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedPos = getAdapterPosition();
                    if(isFrag)
                        ((ShowTimingsFragment)fragment).selectedDate = list.get(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
