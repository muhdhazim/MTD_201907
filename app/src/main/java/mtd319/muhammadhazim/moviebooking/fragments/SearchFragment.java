package mtd319.muhammadhazim.moviebooking.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinema.moviebooking.R;
import mtd319.muhammadhazim.moviebooking.adapters.RecyclerAdapterSearch;
import com.cinema.moviebooking.databinding.FragmentSearchBinding;
import mtd319.muhammadhazim.moviebooking.models.MovieDetails;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments()!=null) {
            if(getArguments().getString("type").equalsIgnoreCase("location")) {
                binding.etSearch.setHint("Search locations");
            }
            else {
                binding.etSearch.setHint("Search By Movies");

                List<MovieDetails> movies = new ArrayList<>();
                movies.add(new MovieDetails("Terminator: Dark Fate",getString(R.string.terminator),"English",
                        R.drawable.terminator));
                movies.add(new MovieDetails("Joker",getString(R.string.joker),"English",R.drawable.joker));
                movies.add(new MovieDetails("Maleficent: Mistress Of Evil",getString(R.string.maleficent),"English",R.drawable.maleficent));
                movies.add(new MovieDetails("Dora And The Lost City Of Gold",getString(R.string.dora),"English",R.drawable.dora));
                movies.add(new MovieDetails("Gemini Man",getString(R.string.joker),"English",R.drawable.geminiman));

                binding.rvResults.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                binding.rvResults.setAdapter(new RecyclerAdapterSearch(getActivity(),movies));
            }
        }

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(binding.rvResults.getAdapter()!=null) {
                    ((RecyclerAdapterSearch)binding.rvResults.getAdapter()).filter(editable.toString());
                }
            }
        });


    }
}
