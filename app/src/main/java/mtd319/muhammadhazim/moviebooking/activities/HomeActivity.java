package mtd319.muhammadhazim.moviebooking.activities;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import mtd319.muhammadhazim.moviebooking.fragments.BookingsFragment;
import com.cinema.moviebooking.R;
import mtd319.muhammadhazim.moviebooking.adapters.RecyclerAdapterCinemas;
import mtd319.muhammadhazim.moviebooking.adapters.RecyclerAdapterMovies;
import mtd319.muhammadhazim.moviebooking.fragments.SearchFragment;
import com.cinema.moviebooking.databinding.ActivityHomeBinding;
import mtd319.muhammadhazim.moviebooking.models.MovieDetails;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private List<String> list =new ArrayList<>();
    private List<MovieDetails> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        list.add("All");
        list.add("GV Jurong Point");
        list.add("GV VivoCity");
        list.add("Cathay Cineplex Jem");
        list.add("Shaw Theatres Jcube");
        list.add("Cathay Cineplex West Mall");


        binding.rvCinemas.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.rvCinemas.setAdapter(new RecyclerAdapterCinemas(list,this));

        movies.add(new MovieDetails("Terminator: Dark Fate",getString(R.string.terminator),"NC16: Violence & Coarse Language",
                R.drawable.terminator));
        movies.add(new MovieDetails("Joker",getString(R.string.joker),"NC16: Some Violence & Nudity",R.drawable.joker));
        movies.add(new MovieDetails("Maleficent: Mistress Of Evil",getString(R.string.maleficent),"PG: Some Intense Sequences",R.drawable.maleficent));
        movies.add(new MovieDetails("Dora And The Lost City Of Gold",getString(R.string.dora),"PG: Some Intense Sequences",R.drawable.dora));
        movies.add(new MovieDetails("Gemini Man",getString(R.string.geminiman),"PG13: Some Violence & Coarse Language",R.drawable.geminiman));


        binding.rvMovies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.rvMovies.setAdapter(new RecyclerAdapterMovies(this,movies));

        binding.ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("type","movie");
                SearchFragment fragment = new SearchFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(binding.rl.getId(),fragment);
                ft.commit();
            }
        });

        binding.tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("type","location");
                SearchFragment fragment = new SearchFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(binding.rl.getId(),fragment);
                ft.commit();
            }
        });

        binding.tvBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(binding.rl.getId(),new BookingsFragment());
                ft.commit();
            }
        });
    }
}
