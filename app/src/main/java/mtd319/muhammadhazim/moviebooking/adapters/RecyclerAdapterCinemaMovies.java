package mtd319.muhammadhazim.moviebooking.adapters;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cinema.moviebooking.R;
import com.cinema.moviebooking.databinding.LayoutItemCinemaMoviesBinding;

public class RecyclerAdapterCinemaMovies extends RecyclerView.Adapter<RecyclerAdapterCinemaMovies.ViewHolder> {

    private Activity activity;

    public RecyclerAdapterCinemaMovies(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder((LayoutItemCinemaMoviesBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.layout_item_cinema_movies,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LayoutItemCinemaMoviesBinding binding;
        ViewHolder(LayoutItemCinemaMoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

//            binding.rl
        }
    }
}
