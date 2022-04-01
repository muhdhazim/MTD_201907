package mtd319.muhammadhazim.moviebooking.adapters;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinema.moviebooking.R;
import mtd319.muhammadhazim.moviebooking.activities.MovieDetailActivity;
import com.cinema.moviebooking.databinding.LayoutItemSearchBinding;
import mtd319.muhammadhazim.moviebooking.models.MovieDetails;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapterSearch extends RecyclerView.Adapter<RecyclerAdapterSearch.ViewHolder> {

    private Activity activity;
    private List<MovieDetails> movies;
    private List<MovieDetails> temp;

    public RecyclerAdapterSearch(Activity activity, List<MovieDetails> movieDetails) {
        this.activity = activity;
        this.movies = movieDetails;
        this.temp = new ArrayList<>();
        this.temp.addAll(this.movies);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder((LayoutItemSearchBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.layout_item_search,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding.tvItem.setText(movies.get(i).getMovieName());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LayoutItemSearchBinding binding;
        ViewHolder(LayoutItemSearchBinding binding) {
            super(binding.getRoot());
            this.binding =binding;

            binding.tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, MovieDetailActivity.class);
                    intent.putExtra("movieName",movies.get(getAdapterPosition()).getMovieName());
                    intent.putExtra("image",movies.get(getAdapterPosition()).getResource());
                    intent.putExtra("synopsis",movies.get(getAdapterPosition()).getSynopsis());
                    activity.startActivity(intent);
                }
            });
        }
    }

    public void filter(String s) {
        movies.clear();
        for(int i=0;i<temp.size();i++) {
            if(temp.get(i).getMovieName().toLowerCase().contains(s.toLowerCase())) {
                movies.add(temp.get(i));
            }
        }
        notifyDataSetChanged();
    }
}
