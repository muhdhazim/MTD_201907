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
import mtd319.muhammadhazim.moviebooking.adapters.RecyclerAdapterCinemas;
import mtd319.muhammadhazim.moviebooking.adapters.RecyclerAdapterShowTimings;
import mtd319.muhammadhazim.moviebooking.models.ShowTimingModel;
import com.cinema.moviebooking.databinding.FragmentShowTimingsBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ShowTimingsFragment extends Fragment {

    private FragmentShowTimingsBinding binding;
    private List<String> list = new ArrayList<>();
    private List<ShowTimingModel> model = new ArrayList<>();
    public String selectedDate = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_timings, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date dayAfterTmrw = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date fourthday = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date fifthday = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date sixthday = calendar.getTime();


        list.add(getDate(today));
        list.add(getDate(tomorrow));
        list.add(getDate(dayAfterTmrw));
        list.add(getDate(fourthday));
        list.add(getDate(fifthday));
        list.add(getDate(sixthday));

        selectedDate = getDate(today);
        binding.rvDates.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        binding.rvDates.setAdapter(new RecyclerAdapterCinemas(list,this));


        model.add(new ShowTimingModel("GV Jurong Point","09:30 AM|A,10:30 AM|A,11:45 AM|A,1:20 PM|U,2:30 AM|F,4:50 PM|A,6:10 PM|U,8:20 PM|F"
                ,R.drawable.gv,1.339748,103.706606,"Address: 1 Jurong West Central, #03-26/26A, Jurong Point, Singapore 648886\n\nMRT: Boon Lay (EW27)\nBus: 30, 79, 154, 157, 172, 174, 178, 180, 187, 192, 193, 240, 242, 243, 246, 249, 179, 182"));
        model.add(new ShowTimingModel("GV VivoCity","10:00 AM|A,10:20 AM|A,10:40 AM|A,11:45 AM|A,1:20 PM|F,2:30 PM|F,4:50 PM|U,6:10 PM|U,8:20 PM|U,10:15 PM|F,11:45 PM|A",R.drawable.gv
                ,1.264800,103.822920,"1 HarbourFront Walk, #02-30, VivoCity, Singapore 098585\n\nMRT: HarbourFront Station (NE1/CC29)\nBus: 10, 30, 30e, 97, 97e, 100, 131, 143, 145, 166, 57, 65, 80, 9, 61, 188, 855, 963"));
        model.add(new ShowTimingModel("Cathay Cineplex Jem","10:10 AM|A,11:10 AM|A,11:45 AM|A,1:30 PM|A,2:40 AM|U,3:40 PM|U,6:00 PM|F,8:30 PM|F",R.drawable.cathay
                ,1.333301,103.743391,"Address: 50, Jurong Gateway Rd, #05-04 Jem, Singapore 608549\n\nMRT: Jurong East (NS1/EW24)\nBus: 41, 49, 78A, 143M, 51, 52, 66, 78, 79, 97E, 97, 98M, 98, 105 and 14"));
        model.add(new ShowTimingModel("Shaw Theatres Jcube","09:45 AM|A,10:40 AM|F,11:20 AM|A,11:45 AM|F,1:20 PM|U,2:30 AM|F,4:50 PM|A,6:10 PM|U,8:20 PM|U"
                ,R.drawable.shaw,1.333298,103.740184,"Address: 2 Jurong East Central 1, #04-11 JCube, Singapore 609731\n\nMRT: Jurong East (NS1/EW24)\nBus: 160, 334, 51, 718, 97"));
        model.add(new ShowTimingModel("Cathay Cineplex West Mall","10:30 AM|A,11:45 AM|A,1:20 PM|A,3:30 PM|A,5:50 PM|A,8:10 PM|F,10:20 PM|F",R.drawable.cathay
                ,1.350009,103.749297,"Address: 1 Bukit Batok Central Link, Level 5 West Mall, Singapore 658713\n\nMRT: Bukit Batok (NS2)\nBus: 61, 77, 106, 173, 177, 189, 852, 941, 945, 947"));

        binding.rvShowTimings.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        binding.rvShowTimings.setAdapter(new RecyclerAdapterShowTimings(this,getActivity(),model));


        binding.etSearchCinema.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(binding.rvShowTimings.getAdapter()!=null) {
                    ((RecyclerAdapterShowTimings)binding.rvShowTimings.getAdapter()).filter(editable.toString());
                }
            }
        });
    }

    private String getDate(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM", Locale.getDefault());
        return dateFormat.format(d);
    }



}
