package mtd319.muhammadhazim.moviebooking.adapters;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import mtd319.muhammadhazim.moviebooking.models.BookingsModel;
import com.cinema.moviebooking.R;
import com.cinema.moviebooking.databinding.LayoutItemBookingBinding;

import java.util.List;

public class RecyclerAdapterBookings extends RecyclerView.Adapter<RecyclerAdapterBookings.ViewHolder> {

    private List<BookingsModel.Bookings> list;

    public RecyclerAdapterBookings(List<BookingsModel.Bookings> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder((LayoutItemBookingBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.layout_item_booking,viewGroup,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        viewHolder.binding.tvMovieName.setText(list.get(pos).getMovie());
        viewHolder.binding.tvCinemaName.setText(list.get(pos).getCinema());
        viewHolder.binding.tvDateTime.setText(list.get(pos).getDate()+" , "+list.get(pos).getTime());
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<list.get(pos).getSeatNo().size();i++) {
            if(i!=0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(list.get(pos).getSeatNo().get(i));
        }
        viewHolder.binding.tvSeatNo.setText("Seat No : "+stringBuilder.toString());
    }
    
    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LayoutItemBookingBinding binding;
        ViewHolder(LayoutItemBookingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
