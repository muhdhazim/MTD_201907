package mtd319.muhammadhazim.moviebooking.adapters;

import android.annotation.SuppressLint;
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
import com.cinema.moviebooking.databinding.LayoutItemMovieListingBinding;
import mtd319.muhammadhazim.moviebooking.models.MovieDetails;

import java.util.List;

public class RecyclerAdapterMovies extends RecyclerView.Adapter<RecyclerAdapterMovies.ViewHolder> {
    private Activity activity;
    private List<MovieDetails> movies;

    public RecyclerAdapterMovies(Activity activity, List<MovieDetails> movies) {
        this.activity = activity;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder((LayoutItemMovieListingBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.layout_item_movie_listing,viewGroup,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding.tvMovieTitle.setText(movies.get(i).getMovieName());
        viewHolder.binding.tvLanguage.setText(movies.get(i).getLanguage());
        viewHolder.binding.ivMovie.setImageResource(movies.get(i).getResource());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LayoutItemMovieListingBinding binding;
        ViewHolder(LayoutItemMovieListingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.rl.setOnClickListener(new View.OnClickListener() {
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
}
