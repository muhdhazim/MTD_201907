package mtd319.muhammadhazim.moviebooking.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mtd319.muhammadhazim.moviebooking.adapters.RecyclerAdapterBookings;
import mtd319.muhammadhazim.moviebooking.models.BookingsModel;
import mtd319.muhammadhazim.moviebooking.utils.Prefs;
import com.cinema.moviebooking.R;
import com.cinema.moviebooking.databinding.FragmentBookingsBinding;

import java.util.List;

public class BookingsFragment extends Fragment {

    private FragmentBookingsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookings, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        BookingsModel model = Prefs.with(getActivity()).getObject("bookings",BookingsModel.class);
        if(model!=null) {
            binding.tvNoBookings.setVisibility(View.GONE);
            List<BookingsModel.Bookings> bookings = model.getList();
            binding.rvBookings.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            binding.rvBookings.setAdapter(new RecyclerAdapterBookings(bookings));
        }
        else {
            binding.tvNoBookings.setVisibility(View.VISIBLE);
        }


    }
}
