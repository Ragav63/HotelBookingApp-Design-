package com.example.task4roombooking;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RoomFragment extends Fragment implements RecItemRoomAdapter.OnItemClickListener, RIOtFacRoomAdapter.OnItemClickListener {

    String rPrice, rTax, rOFPrice, rTotal;
    Bundle bundle;
    private RecyclerView recVRoomType, roomRVOtFac;
    private List<RecItemRoom> recItemRoomList;
    //    private List<RecItOccRoom> recItOccRoomList;
    private List<RecItOtFacRoom> recItOtFacRoomList;
    private RecItemRoomAdapter roomRecItemRoomAdapter;
    //    private RIOccRoomAdapter riOccRoomAdapter;
    private RIOtFacRoomAdapter riOtFacRoomAdapter;
    private AppCompatButton selectDetailsBtn;
    //    private ViewPager viewPager;
//    private FragmentManager fragmentManager;
    private int selectedItemPosition;
    private double totalAmount = 0.0;

    String cbox="";

    String price;

     TextView roomType, roomDesc, roomPrice, roomTax, roomOtherFac, roomTotal,roomOFTitle;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_room, container, false);

        // Initialize bundle here
        bundle = getArguments();

        roomType = v.findViewById(R.id.roomTypeVal);
        roomDesc = v.findViewById(R.id.roomDescVal);
        //roomOccType=v.findViewById(R.id.roomOccVal);
        roomPrice = v.findViewById(R.id.roomPriceVal);
        roomTax = v.findViewById(R.id.roomTaxVal);
        roomOFTitle=v.findViewById(R.id.roomOFTitleVal);
        roomOtherFac = v.findViewById(R.id.roomOFVal);
        roomTotal = v.findViewById(R.id.roomTotalVal);

        recVRoomType = v.findViewById(R.id.roomRecView);
        recVRoomType.setLayoutManager(new LinearLayoutManager(getContext()));

//        roomRVOcc=v.findViewById(R.id.roomRVOcc);
//        roomRVOcc.setLayoutManager(new LinearLayoutManager(getContext()));

        roomRVOtFac = v.findViewById(R.id.roomRVFac);
        roomRVOtFac.setLayoutManager(new LinearLayoutManager(getContext()));

        selectDetailsBtn = v.findViewById(R.id.selectDetailsBtn);

        // Initialize data for RecyclerView
        recItemRoomList = generateRoomItemList();

//        recItOccRoomList=generateRecItOccRoomList();

        recItOtFacRoomList = generateRoomOtherFacList();

        // Set the item click listener to this fragment
        roomRecItemRoomAdapter = new RecItemRoomAdapter(getContext(), recItemRoomList, this);

        // Set adapter to RecyclerView
        recVRoomType.setAdapter(roomRecItemRoomAdapter);


        // Set the item click listener to this fragment
        riOtFacRoomAdapter = new RIOtFacRoomAdapter(getContext(), recItOtFacRoomList, (RIOtFacRoomAdapter.OnItemClickListener) this);

        // Set adapter to RecyclerView
        roomRVOtFac.setAdapter(riOtFacRoomAdapter);

        // Set ViewPager if it's not null

//        viewPager = requireActivity().findViewById(R.id.fragmentcontainer);


        selectDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedItemPosition >= 0 && selectedItemPosition < recItemRoomList.size()) {

                    String title = roomType.getText().toString();
                    String description = roomDesc.getText().toString();
                    String price = roomPrice.getText().toString();
                    String tax = roomTax.getText().toString();
                    String otFacTitle=roomOFTitle.getText().toString();
                    String otFacPrice = roomOtherFac.getText().toString();
                    String total = roomTotal.getText().toString();

                    Fragment fragment = new ContactDetailsFragment();
                    Bundle args = new Bundle();
                    args.putString("roomfType", title);
                    args.putString("roomDesc", description);
                    args.putString("roomfPrice", price);
                    args.putString("roomfTax", tax);
                    args.putString("roomfOtFacTitle",otFacTitle);
                    args.putString("roomfOtFacPrice", otFacPrice);
                    args.putString("total", total);
                    Log.d("roomFtotal", total);

                    String ChInDate = bundle.getString("ChInDate");
                    String ChOutDate = bundle.getString("ChOutDate");

                    Log.d("roomCindate", ChInDate);
                    Log.d("roomCoutndate", ChOutDate);

                    Double noroom = Double.valueOf(bundle.getString("SeRoom"));
                    Double noperson = Double.valueOf(bundle.getString("SePerson"));
                    String duration = bundle.getString("duration");
                    // Remove all non-numeric characters from the string
                    String numericDays = duration.replaceAll("[^\\d.]", "");

                    args.putString("chindate", ChInDate);
                    args.putString("choutdate", ChOutDate);

                    args.putString("noroom", String.valueOf(noroom));
                    args.putString("rfduration", String.valueOf(duration));
                    args.putString("rfnoperson", String.valueOf(noperson));


                    fragment.setArguments(args);
                    AppCompatActivity activity = (AppCompatActivity) getActivity();

                    if (activity != null) {
                        // Access the TabLayout from the activity reference
                        TabLayout tabLayout = activity.findViewById(R.id.include);

                        // Select the desired tab position
                        if (tabLayout != null) {
                            TabLayout.Tab tab = tabLayout.getTabAt(2);
                            if (tab != null) {
                                tab.select();
                            }
                        }
                    }

                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentcontainer, fragment);
                    transaction.commit();


                } else {
                    Log.e("RoomFragment", "Invalid selectedItemPosition: " + selectedItemPosition);
                }

            }
        });

        return v;
    }


    @Override
    public void onItemClick(RecItemRoom item) {
        // Find the position of the clicked item in the list

//        viewPager=requireActivity().findViewById(R.id.fragmentcontainer);
        selectedItemPosition = recItemRoomList.indexOf(item);

//        RoomFragment roomFragment=new RoomFragment();
//        roomFragment.setViewPager(viewPager);

        // Ensure viewPager is not null before using it
//        if (viewPager != null) {
        // Access viewPager safely
        String title = item.getRoomTitleTv();
        String description = item.getRoomDescriptionTv();
        String price = item.getRoomPriceTv();
        String tax = item.getRoomTaxTv();

        roomType.setText(title);
        roomDesc.setText(description);
        roomPrice.setText(price);
        roomTax.setText(tax);

        rPrice = price;
        rTax = tax;
        roomOtherFac.setText("0");
        calculateTotalAmount();
//            calculateTotalAmount(Double.valueOf(rTotal));
//            roomTotal.setText(rTotal);
//            double roomPrice = Double.parseDouble(price);
//            double roomTax = Double.parseDouble(tax);
//
//            // Calculate total amount for the selected room
//            double totalAmount = calculateTotalAmount(roomPrice, 0, roomTax);

        Log.d("RoomFragment", "Item clicked: " + item.getRoomTitleTv());

        // Toast.makeText(getContext(), title + description + price + tax, Toast.LENGTH_SHORT).show();
//        } else {
//            // Handle the case where viewPager is null
//            Log.e("RoomFragment", "ViewPager is null");
//            // Optionally display a message to the user
//            Toast.makeText(getContext(), "ViewPager is not available", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onItemClick(RecItOtFacRoom item, boolean isChecked) {
        selectedItemPosition = recItOtFacRoomList.indexOf(item);
        // Ensure viewPager is not null before using it
//        if (viewPager != null) {
        // Access viewPager safely

             //   = item.getRoomOFCBox();


      //  rOFPrice = item.getRoomOFPrice();


//            roomOtherFac.setText(price);
        if (item.isChecked()) {
            totalAmount += Double.valueOf(item.getRoomOFPrice());
            cbox += item.getRoomOFCBox() + "\n"; // Add item name to the string
            roomOFTitle.setText(cbox);
            roomOtherFac.setText(String.valueOf(totalAmount));
            calculateTotalAmount();
        } else {
            totalAmount -= Double.valueOf(item.getRoomOFPrice());
            cbox = cbox.replace(item.getRoomOFCBox() + "\n", ""); // Remove item name from the string
            if (!TextUtils.isEmpty(cbox)) {
                roomOFTitle.setText(cbox);
            } else {
                roomOFTitle.setText(""); // Set empty text if cbox is empty
            }
            roomOtherFac.setText(String.valueOf(totalAmount));
        }
        calculateTotalAmount();
//            double otherFacilityPrice = Double.parseDouble(price);
//
//            // Get the tax percentage (assuming it's the same for all facilities)
//            //double taxPercentage = 5.0; // Example tax percentage
//
//            // Calculate total amount for the selected other facility
//            double totalAmount = calculateTotalAmount(0, otherFacilityPrice, 0);

//        Log.d("RoomFragment", "Item clicked: " + item.getRoomOFPrice());
//        price = item.getRoomOFPrice();
//        Toast.makeText(getContext(), price, Toast.LENGTH_SHORT).show();

    }

    private List<RecItemRoom> generateRoomItemList() {
        List<RecItemRoom> recItemRoomArrayList = new ArrayList<>();
        recItemRoomArrayList.add(new RecItemRoom("Standard Room", "Garden View", "1000", "10"));
        recItemRoomArrayList.add(new RecItemRoom("Delux Room", "City View with Breakfast", "2000", "20"));
        recItemRoomArrayList.add(new RecItemRoom("Super Delux Room", "Sea View with Breakfast", "4000", "30"));

        return recItemRoomArrayList;
    }


    private List<RecItOtFacRoom> generateRoomOtherFacList() {
        List<RecItOtFacRoom> recItOtFacRoomArrayList = new ArrayList<>();
        recItOtFacRoomArrayList.add(new RecItOtFacRoom("Breakfast", "250"));
        recItOtFacRoomArrayList.add(new RecItOtFacRoom("Dinner", "400"));
        recItOtFacRoomArrayList.add(new RecItOtFacRoom("AC-Room", "3000"));

        return recItOtFacRoomArrayList;
    }

    public void calculateTotalAmount() {
        double roomPriceValue = Double.parseDouble(roomPrice.getText().toString());
        double taxValue = Double.parseDouble(roomTax.getText().toString());
        double otherFacilityPrice = Double.parseDouble(roomOtherFac.getText().toString());


        // Initialize bundle here
        bundle = getArguments();


        Double noroom = Double.valueOf(bundle.getString("SeRoom"));
        Double noperson = Double.valueOf(bundle.getString("SePerson"));
        String duration = bundle.getString("duration");
        // Remove all non-numeric characters from the string
        String numericDays = duration.replaceAll("[^\\d.]", "");

        // Convert the numeric string to an integer
        Double numberOfDays = Double.valueOf(Integer.parseInt(numericDays));

        // Calculate the tax amount
        double taxAmount = roomPriceValue * (taxValue / 100);

        Double Amount = roomPriceValue + otherFacilityPrice + taxAmount;

        Double totalAmount = Amount * noroom * noperson * numberOfDays;

        roomTotal.setText(String.valueOf(totalAmount));


    }

}