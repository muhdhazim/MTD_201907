package mtd319.muhammadhazim.moviebooking.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import mtd319.muhammadhazim.moviebooking.models.BookingsModel;
import mtd319.muhammadhazim.moviebooking.utils.Prefs;
import com.cinema.moviebooking.R;
import com.cinema.moviebooking.databinding.ActivityBookingBinding;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityBookingBinding binding;

    String seats = "_UUUUUUAAAAARRRR_/"
            + "_________________/"
            + "UU__AAAARRRRR__RR/"
            + "UU__UUUAAAAAA__AA/"
            + "AA__AAAAAAAAA__AA/"
            + "AA__AARUUUURR__AA/"
            + "UU__UUUA_RRRR__AA/"
            + "AA__AAAA_RRAA__UU/"
            + "AA__AARR_UUUU__RR/"
            + "AA__UUAA_UURR__RR/"
            + "_________________/"
            + "UU_AAAAAAAUUUU_RR/"
            + "RR_AAAAAAAAAAA_AA/"
            + "AA_UUAAAAAUUUU_AA/"
            + "AA_AAAAAAUUUUU_AA/"
            + "_________________/";

    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 100;
    int seatGaping = 10;
    int ticketcost = 10;
    int totalcost = 0;
    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    int STATUS_RESERVED = 3;
    String selectedIds = "";
    int selectedSeats =0;
    List<String> seatNos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking);

        if(getIntent()!=null) {
            binding.tvMovieName.setText(getIntent().getStringExtra("movieName"));
            binding.tvCinemaName.setText(getIntent().getStringExtra("cinemaName"));
            binding.tvDate.setText(getIntent().getStringExtra("date"));
            binding.tvShowTime.setText(getIntent().getStringExtra("showTime"));
        }

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.btBookTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(BookingActivity.this);
                builder.setTitle("Booking Confirmed!");
                StringBuilder sb = new StringBuilder();
                sb.append("No. of seats booked: ");
                sb.append(BookingActivity.this.selectedSeats);
                int seat = BookingActivity.this.selectedSeats;
                totalcost = seat * ticketcost;
                sb.append("\nTotal cost paid: $");
                sb.append(totalcost);
                sb.append("\nMovie: ");
                sb.append(BookingActivity.this.binding.tvMovieName.getText().toString().trim());
                sb.append("\nCinema: ");
                sb.append(BookingActivity.this.binding.tvCinemaName.getText().toString().trim());
                sb.append("\nDate: ");
                sb.append(BookingActivity.this.binding.tvDate.getText().toString().trim());
                sb.append("\nTime: ");
                sb.append(BookingActivity.this.binding.tvShowTime.getText().toString().trim());
                builder.setMessage(sb.toString());
                builder.setCancelable(false);

                //Meant for users to input their email address for reminder/receipt
                /*final EditText input = new EditText(BookingActivity.this);
                input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                input.setPadding(10,10,10,10);
                input.setBackground(getDrawable(R.drawable.bg_rect));

                final FrameLayout container = new FrameLayout(BookingActivity.this);
                FrameLayout.LayoutParams params = new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.dp_20);
                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.dp_20);
                nput.setLayoutParams(params);
                container.addView(input);
                builder.setView(container);*/

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        BookingsModel.Bookings booking = new BookingsModel.Bookings(binding.tvMovieName.getText().toString().trim(),
                                binding.tvCinemaName.getText().toString().trim(),binding.tvDate.getText().toString().trim(),
                                binding.tvShowTime.getText().toString().trim(),seatNos);
                        BookingsModel existing = Prefs.with(BookingActivity.this).getObject("bookings",BookingsModel.class);
                        List<BookingsModel.Bookings> bookings;
                        if(existing!=null)
                            bookings = existing.getList();
                        else bookings = new ArrayList<>();
                        bookings.add(booking);
                        BookingsModel newBookings = new BookingsModel(bookings);
                        Prefs.with(BookingActivity.this).save("bookings",newBookings);
                        Toast.makeText(BookingActivity.this, "Booking list updated!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BookingActivity.this,HomeActivity.class));
                        finishAffinity();
                    }
                });

                builder.show();
            }
        });

        seats = "/" + seats;

        LinearLayout layoutSeat = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
        binding.layoutSeat.addView(layoutSeat);

        LinearLayout layout = null;

        int count = 0;

        for (int index = 0; index < seats.length(); index++) {
            if (seats.charAt(index) == '/') {
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
            } else if (seats.charAt(index) == 'U') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0,0 /*2 * seatGaping*/);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.bg_rect_fill_grey);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_BOOKED);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'A') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 0 /*2 * seatGaping*/);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.bg_rect_corner);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.BLACK);
                view.setTag(STATUS_AVAILABLE);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'R') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 0 /*2 * seatGaping*/);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.bg_rect_fill_grey);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_RESERVED);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == '_') {
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setBackgroundColor(Color.TRANSPARENT);
                view.setText("");
                layout.addView(view);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if ((int) view.getTag() == STATUS_AVAILABLE) {
            if (selectedIds.contains(view.getId() + ",")) {
                selectedIds = selectedIds.replace(+view.getId() + ",", "");
                view.setBackgroundResource(R.drawable.bg_rect_corner);
                selectedSeats--;
                seatNos.remove(String.valueOf(view.getId()));
            } else {
                selectedIds = selectedIds + view.getId() + ",";
                view.setBackgroundResource(R.drawable.bg_rect_fill);
                selectedSeats++;
                seatNos.add(String.valueOf(view.getId()));
            }
            checkSeats();
        } else if ((int) view.getTag() == STATUS_BOOKED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == STATUS_RESERVED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
        }

    }

    private void checkSeats() {
        if(selectedSeats>0) {
            binding.ivScreen.setVisibility(View.GONE);
            binding.btBookTickets.setVisibility(View.VISIBLE);
        }
        else {
            binding.ivScreen.setVisibility(View.VISIBLE);
            binding.btBookTickets.setVisibility(View.GONE);

        }
    }
}
