package com.example.task4roombooking;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateFragment extends Fragment {

    private EditText dateCheckInEdt, dateCheckOutEdt;

    private DatePickerDialog datePickerDialog;

    private Spinner roomSpinner, personSpinner;

    private AppCompatButton selectRoomBtn;
//    private ViewPager viewPager;
    private FragmentManager fragmentManager;
    private TextView chInValTv, chOutValTv, durValTv, roomValTv, personValTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_date, container, false);

        dateCheckInEdt=v.findViewById(R.id.selectCheckInDate);
        dateCheckOutEdt=v.findViewById(R.id.selectCheckOutDate);

        chInValTv=v.findViewById(R.id.checkInVal);
        chOutValTv=v.findViewById(R.id.checkoutVal);
        durValTv=v.findViewById(R.id.durVal);
        roomValTv=v.findViewById(R.id.roomVal);
        personValTv=v.findViewById(R.id.personVal);

        final Calendar calendar=Calendar.getInstance();

        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);

        datePickerDialog=new DatePickerDialog(requireContext());

        String rooms[]={"1", "2", "3", "4", "5"};

        String persons[]={"1", "2", "3", "4", "5"};

        roomSpinner=v.findViewById(R.id.spinner_rooms);
        personSpinner=v.findViewById(R.id.spinner_persons);
        selectRoomBtn=v.findViewById(R.id.selectRoomBtn);

        int textColor = getResources().getColor(R.color.b_coral);

        // Create a custom adapter and set it to the Spinner
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(requireContext(), android.R.layout.simple_spinner_item, rooms, textColor);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomSpinner.setAdapter(adapter);


        CustomSpinnerAdapter adapter1 = new CustomSpinnerAdapter(requireContext(), android.R.layout.simple_spinner_item, persons, textColor);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        personSpinner.setAdapter(adapter1);

        // Create an ArrayAdapter using the string array and a default spinner layout
      //  ArrayAdapter<String> adapterOfRooms= new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, rooms);
      //  ArrayAdapter<String> adapterOfPersons= new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, persons);

        // Specify the layout to use when the list of choices appears
      //  adapterOfRooms.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

     //   adapterOfPersons.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
      //  roomSpinner.setAdapter(adapterOfRooms);
      //  personSpinner.setAdapter(adapterOfPersons);

//        // Setting a calendar for the EditText of Check In
//        dateCheckInEdt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDatePickerDialog();
//            }
//        });
//
//        // Setting a calendar for the EditText of Check Out
//        dateCheckOutEdt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDatePickerDialog();
//            }
//        });


        //setting a calender for the EditText of Check In
        dateCheckInEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog=new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateCheckInEdt.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                        String CheckIn=dateCheckInEdt.getText().toString();
                        if(!CheckIn.isEmpty()) {
                            Toast.makeText(getContext(),"Check In Date : "+CheckIn, Toast.LENGTH_SHORT).show();
                            calculateDuration();
                        }
                    }
                },year,month,day);

                // set maximum date to be selected as today
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

                // show the dialog
                datePickerDialog.show();
            }
        });


        //setting a calender for the EditText of Check Out
        dateCheckOutEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog=new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateCheckOutEdt.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                        String CheckOut=dateCheckOutEdt.getText().toString();
                        if(!CheckOut.isEmpty()) {
                            Toast.makeText(getContext(),"Check Out Date :"+CheckOut, Toast.LENGTH_SHORT).show();
                            calculateDuration();
                        }
                    }
                },year,month,day);

                // set maximum date to be selected as today
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

                // show the dialog
                datePickerDialog.show();



            }
        });

        roomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRoom = roomSpinner.getSelectedItem().toString();
                roomValTv.setText(selectedRoom);

                // Display a toast message with the selected room
                Toast.makeText(getContext(), "Selected Room: " + rooms[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        personSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Display a toast message with the selected room
                String selectedPerson = personSpinner.getSelectedItem().toString();
                personValTv.setText(selectedPerson);

                Toast.makeText(getContext(), "Selected Person: " + persons[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //ViewPager viewPager=v.findViewById(R.id.fragmentcontainer);
        selectRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                viewPager = v.findViewById(R.id.fragmentcontainer);
//                DateFragment fragment = new DateFragment();
//                fragment.setViewPager(viewPager);

//                viewPager = getActivity().findViewById(R.id.fragmentcontainer);
//                if (viewPager != null) {



                    if(!dateCheckInEdt.getText().toString().isEmpty() && !dateCheckOutEdt.getText().toString().isEmpty() ){

                        String selectedCheckInDate = dateCheckInEdt.getText().toString();
                        String selectedCheckOutDate = dateCheckOutEdt.getText().toString();
                        String selectedRoom = roomSpinner.getSelectedItem().toString();
                        String selectedPerson = personSpinner.getSelectedItem().toString();
                        String duration = durValTv.getText().toString();


                        Fragment fragment = new RoomFragment();
                        Bundle args = new Bundle();
                        args.putString("ChInDate", selectedCheckInDate);
                        args.putString("ChOutDate", selectedCheckOutDate);
                        args.putString("SeRoom", selectedRoom);
                        args.putString("SePerson", selectedPerson);
                        args.putString("duration", duration);


                        fragment.setArguments(args);
                        AppCompatActivity activity = (AppCompatActivity) getActivity();

                        if (activity != null) {
                            // Access the TabLayout from the activity reference
                            TabLayout tabLayout = activity.findViewById(R.id.include);

                            // Select the desired tab position
                            if (tabLayout != null) {
                                TabLayout.Tab tab = tabLayout.getTabAt(1);
                                if (tab != null) {
                                    tab.select();
                                }
                            }
                        }

                        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentcontainer, fragment);
                        transaction.commit();


                    } else {
                        Toast.makeText(getContext(), "Enter all Fields", Toast.LENGTH_SHORT).show();
                    }

//                } else {
//                    // Handle the case when ViewPager is not found
//                    Log.e("DateFragment", "ViewPager not found");
//                }
            }
        });



        return v;
    }

    private void calculateDuration() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date checkInDate = sdf.parse(dateCheckInEdt.getText().toString());
            Date checkOutDate = sdf.parse(dateCheckOutEdt.getText().toString());

            long diff = checkOutDate.getTime() - checkInDate.getTime();
            long days = diff / (24 * 60 * 60 * 1000);

            chInValTv.setText(dateCheckInEdt.getText().toString());
            chOutValTv.setText(dateCheckOutEdt.getText().toString());
            durValTv.setText(String.valueOf(days) + " days");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

//    private void setViewPager(ViewPager viewPager) {
//        this.viewPager = viewPager;
//    }


}