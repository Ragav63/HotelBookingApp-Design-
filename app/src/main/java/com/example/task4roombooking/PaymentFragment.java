package com.example.task4roombooking;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class PaymentFragment extends Fragment {

    private EditText ccnumEdt, ccNameEdt, cvvnumEdt, exponEdt;

    private DatePickerDialog datePickerDialog;

    private AppCompatButton bookNowBtn;

    private ViewPager viewPager;

    Bundle bundle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_payment, container, false);

       bundle=getArguments();

        if (bundle != null) {
            // Retrieve data from the bundle

        ccnumEdt=v.findViewById(R.id.ccnumEdt);
        ccNameEdt=v.findViewById(R.id.ccNameEdt);
        cvvnumEdt=v.findViewById(R.id.cvvEdt);
        exponEdt=v.findViewById(R.id.expDateEdt);

        bookNowBtn=v.findViewById(R.id.bookNowbtn);

        final Calendar calendar=Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);

        // Set the maximum length of the input
        ccnumEdt.setFilters(new InputFilter[]{new MaxLengthInputFilter(16)});

        // Set the transformation method to display placeholder characters
        ccnumEdt.setTransformationMethod(new PlaceholderTransformationMethod('x'));

        datePickerDialog=new DatePickerDialog(requireContext());

        String ChInDate=bundle.getString("chindate");
        String ChOutDate=bundle.getString("choutdate");

        Double noroom = Double.valueOf(bundle.getString("noroom"));
        Double noperson = Double.valueOf(bundle.getString("rfnoperson"));
        String duration = bundle.getString("rfduration");

        String  roomfType =bundle.getString("roomfType");
        String roomDesc=bundle.getString("roomDesc");
        String roomfPrice = bundle.getString("roomfPrice");
        String roomfTax = bundle.getString("roomfTax");
        String otFacTitle=bundle.getString("roomfOtFacTitle");
        String roomfOtFacPrice =bundle.getString("roomfOtFacPrice");
        String total = bundle.getString("total");

//        Log.d("getTotal",total);
        String firstName = bundle.getString("firstName");
        String lastName = bundle.getString("lastName");
        String email = bundle.getString("email");
        String address = bundle.getString("address");
        String postCode = bundle.getString("postCode");
        String mobileNo = bundle.getString("mobileNo");

        String selectedCoupon = bundle.getString("coupon");
        String discount=bundle.getString("discount");
        String discountAm=bundle.getString("discountAm");
        String grandTotal=bundle.getString("grandTotal");

        exponEdt.setInputType(InputType.TYPE_NULL);
        exponEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog=new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,1);// Set day as 1 to ignore the day component
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
                        String dateString = dateFormat.format(calendar.getTime());
                        exponEdt.setText(dateString);
//                        exponEdt.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                        String ExpOn=exponEdt.getText().toString();
                        if(!ExpOn.isEmpty()) {
                            Toast.makeText(getContext(),"Expiry On Date : "+ExpOn, Toast.LENGTH_SHORT).show();
                        }
                    }
                },year,month,1);

                // set maximum date to be selected as today
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                // Set the date picker mode to MONTH
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getDatePicker().setSpinnersShown(true);

                // show the dialog
                datePickerDialog.show();
            }
        });

        bookNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                viewPager=v.findViewById(R.id.fragmentcontainer);

                // Check if all fields are not empty
                if (validateFields()) {

//                    Fragment fragment = new PaymentFragment();
                    Bundle args = new Bundle();

                    args.putString("chindate", ChInDate);
                    args.putString("choutdate", ChOutDate);

                    args.putString("noroom", String.valueOf(noroom));
                    args.putString("rfduration", String.valueOf(duration));
                    args.putString("rfnoperson", String.valueOf(noperson));

                    args.putString("roomfType", String.valueOf(roomfType));
                    args.putString("roomDesc",roomDesc);
                    args.putString("roomfPrice", String.valueOf(roomfPrice));
                    args.putString("roomfTax", String.valueOf(roomfTax));
                    args.putString("roomfOtFacTitle",otFacTitle);
                    args.putString("roomfOtFacPrice", String.valueOf(roomfOtFacPrice));

                    args.putString("firstName", firstName);
                    args.putString("lastName", lastName);
                    args.putString("email", email);
                    args.putString("address", address);
                    args.putString("postCode", postCode);
                    args.putString("mobileNo", mobileNo);

                    args.putString("total",total);

                    args.putString("coupon",selectedCoupon);
                    args.putString("discount", String.valueOf(discount));
                    args.putString("discountAm", String.valueOf(discountAm));
                    args.putString("grandTotal", String.valueOf(grandTotal));

                    args.putString("ccNumber", ccnumEdt.getText().toString());
                    args.putString("ccName", ccNameEdt.getText().toString());
                    args.putString("cvvNum", cvvnumEdt.getText().toString());
                    args.putString("expDate", exponEdt.getText().toString());

                    // Display the details in a default AlertDialog
                    displayDetailsDialog();

                    // Create an intent to start DisplayActivity
                    Intent intent = new Intent(getActivity(), DisplayActivity.class);

                    // Add the bundle containing the data to the intent
                    intent.putExtras(args);

                    // Start the DisplayActivity using the intent
                    startActivity(intent);
//                    Toast.makeText(getContext(), "Hotel Room Booked", Toast.LENGTH_SHORT).show();
//                    // Set the index of the tab you want to move to
//                    int tabIndexToMoveTo = 3; // For example, move to the second tab (index 1)
//
//                    // Move to the desired tab
//                    viewPager.setCurrentItem(3);
                } else {
                    // Show an error message if any field is empty
                    Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }


            }
        });

    } else {
        Log.e("ConfirmDetailsFragment", "Bundle is null");
    }


        return v;
    }

    private boolean validateFields() {
        // Check if any of the fields are empty
        return !TextUtils.isEmpty(ccnumEdt.getText())
                && !TextUtils.isEmpty(ccNameEdt.getText())
                && !TextUtils.isEmpty(cvvnumEdt.getText())
                && !TextUtils.isEmpty(exponEdt.getText());
    }

    private void displayDetailsDialog() {
        // Build the AlertDialog with the entered details
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Payment Details");

        // Construct the message string with the entered details
        StringBuilder message = new StringBuilder();

        bundle=getArguments();

        String ChInDate=bundle.getString("chindate");
        String ChOutDate=bundle.getString("choutdate");

        Double noroom = Double.valueOf(bundle.getString("noroom"));
        Double noperson = Double.valueOf(bundle.getString("rfnoperson"));
        String duration = bundle.getString("rfduration");

        String  roomfType =bundle.getString("roomfType");
        String roomDesc=bundle.getString("roomDesc");
        String roomfPrice = bundle.getString("roomfPrice");
        String roomfTax = bundle.getString("roomfTax");
        String roomfOtFacPrice =bundle.getString("roomfOtFacPrice");
        String total = bundle.getString("total");

//        Log.d("getTotal",total);
        String firstName = bundle.getString("firstName");
        String lastName = bundle.getString("lastName");
        String email = bundle.getString("email");
        String address = bundle.getString("address");
        String postCode = bundle.getString("postCode");
        String mobileNo = bundle.getString("mobileNo");

        String selectedCoupon = bundle.getString("coupon");
        String discount=bundle.getString("discount");
        String discountAm=bundle.getString("discountAm");
        String grandTotal=bundle.getString("grandTotal");

        message.append("Check In Date: ").append(ChInDate).append("\n");
        message.append("Check Out Date: ").append(ChOutDate).append("\n");

        message.append("Selected Room: ").append(noroom).append("\n");
        message.append("No of Person: ").append(noperson).append("\n");
        message.append("Duration: ").append(duration).append("\n");

        message.append("Room Type: ").append(roomfType).append("\n");
        message.append("Room Price: ").append(roomfPrice).append("\n");
        message.append("Other Facility: ").append(roomfOtFacPrice).append("\n");
        message.append("Tax: ").append(roomfTax).append("\n");

        message.append("Total: ").append(total).append("\n");

        message.append("First Name: ").append(firstName).append("\n");
        message.append("Last Name: ").append(lastName).append("\n");
        message.append("Email: ").append(email).append("\n");
        message.append("Address: ").append(address).append("\n");
        message.append("Postal Code: ").append(postCode).append("\n");
        message.append("Mobile Number: ").append(mobileNo).append("\n");

        message.append("Discount Coupon").append(selectedCoupon).append("\n");
        message.append("Discount: ").append(discount).append("\n");
        message.append("Discount Amount: ").append(discountAm).append("\n");
        message.append("Grand Total: ").append(grandTotal).append("\n");

        message.append("Credit Card Number: ").append(ccnumEdt.getText().toString()).append("\n");
        message.append("Credit Card Name: ").append(ccNameEdt.getText().toString()).append("\n");
        message.append("CVV Number: ").append(cvvnumEdt.getText().toString()).append("\n");
        message.append("Expiry On: ").append(exponEdt.getText().toString()).append("\n");


        // Set the message of the AlertDialog
        builder.setMessage(message.toString());

        // Add a button to close the dialog
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Show the AlertDialog
        builder.create().show();
    }
}