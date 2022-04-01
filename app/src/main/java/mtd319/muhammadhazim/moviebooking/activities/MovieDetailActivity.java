package mtd319.muhammadhazim.moviebooking.activities;

import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mtd319.muhammadhazim.moviebooking.fragments.AboutMovieFragment;
import com.cinema.moviebooking.R;
import mtd319.muhammadhazim.moviebooking.fragments.ShowTimingsFragment;
import mtd319.muhammadhazim.moviebooking.adapters.ViewPagerAdapter;
import com.cinema.moviebooking.databinding.ActivityMovieDetailBinding;

public class MovieDetailActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    public ActivityMovieDetailBinding binding;
    private String synopsis;
    public String selectedDate ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        synopsis = getString(R.string.sample);
        binding.tvMovieTitle.setText(getIntent().getStringExtra("movieName"));
        binding.ivMovie.setImageResource(getIntent().getIntExtra("image",R.color.colorAccent));
        synopsis = getIntent().getStringExtra("synopsis");
        setupViewPager(binding.htabViewpager);
        binding.tabs.setupWithViewPager(binding.htabViewpager);

        binding.btBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.htabViewpager.setCurrentItem(1);
            }
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setupViewPager(final ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        AboutMovieFragment fragment = new AboutMovieFragment();
        Bundle bundle = new Bundle();
        bundle.putString("synopsis",synopsis);
        fragment.setArguments(bundle);
        adapter.addFrag(fragment, "About Movie");
        adapter.addFrag(new ShowTimingsFragment(), "Showtimes");
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
        if(i==0) {
            binding.btBook.setVisibility(View.VISIBLE);
        }
        else {
            binding.btBook.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }
}
