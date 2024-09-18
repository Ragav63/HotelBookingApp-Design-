package com.example.task4roombooking;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;


public class ConfirmDetailsFragment extends Fragment {

    //    private ViewPager viewPager;
    Bundle bundle;
    private EditText nameEdt, numberEdt, roomTypeEdt, roomNos, roomPriceEdt, taxEdt, exChEdt, discountEdt, disAmEdt, totalEdt, grandTotalEdt;
    private Spinner disCouponSpinner;
    private AppCompatButton makePaymentBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_confirm_details, container, false);

        bundle = getArguments();

        if (bundle != null) {
            // Retrieve data from the bundle
            String ChInDate = bundle.getString("chindate");
            String ChOutDate = bundle.getString("choutdate");

            Log.d("condetailsCindate", ChInDate);
            Log.d("condetailsCoutndate", ChOutDate);

            String noroom = bundle.getString("noroom");
            String noperson = bundle.getString("rfnoperson");
            String duration = bundle.getString("rfduration");

            String roomfType = bundle.getString("roomfType");
            String roomDesc = bundle.getString("roomDesc");
            String roomfPrice = bundle.getString("roomfPrice");
            String roomfTax = bundle.getString("roomfTax");
            String otFacTitle = bundle.getString("roomfOtFacTitle");
            String roomfOtFacPrice = bundle.getString("roomfOtFacPrice");
            String total = bundle.getString("total");

            String firstName = bundle.getString("firstName");
            String lastName = bundle.getString("lastName");
            String email = bundle.getString("email");
            String address = bundle.getString("address");
            String postCode = bundle.getString("postCode");
            String mobileNo = bundle.getString("mobileNo");

            // Initialize EditTexts
            nameEdt = v.findViewById(R.id.conNameEdt);
            numberEdt = v.findViewById(R.id.conPhnumEdt);
            roomTypeEdt = v.findViewById(R.id.conRoomTypeEdt);
            roomNos = v.findViewById(R.id.conRoomNoEdt);
            roomPriceEdt = v.findViewById(R.id.conRoomPriceEdt);
            taxEdt = v.findViewById(R.id.conTaxEdt);
            exChEdt = v.findViewById(R.id.conExchargeEdt);
            discountEdt = v.findViewById(R.id.conDiscountPerEdt);
            disAmEdt = v.findViewById(R.id.conDiscountAmEdt);
            totalEdt = v.findViewById(R.id.conTotalEdt);
            grandTotalEdt = v.findViewById(R.id.conGrandtotalEdt);

            disCouponSpinner = v.findViewById(R.id.spinner_discoupons);

            makePaymentBtn = v.findViewById(R.id.makePaymentBtn);

            nameEdt.setText(firstName);
            numberEdt.setText(mobileNo);
            roomTypeEdt.setText(roomfType);
            roomNos.setText(noroom);
            roomPriceEdt.setText(roomfPrice);
            taxEdt.setText(roomfTax);
            exChEdt.setText(roomfOtFacPrice);
            totalEdt.setText(total);


            String coupons[] = {"Celeb123", "Normal123", "Festival123", "Event123"};

            int textColor = getResources().getColor(R.color.b_coral);

            // Create a custom adapter and set it to the Spinner
            CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(requireContext(), android.R.layout.simple_spinner_item, coupons, textColor);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            disCouponSpinner.setAdapter(adapter);

            // Remove all non-numeric characters from the string
            //String numericDays = duration.replaceAll("[^\\d.]", "");


            disCouponSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedCoupon = disCouponSpinner.getSelectedItem().toString();

                    // Display a toast message with the selected room
                    Toast.makeText(getContext(), "Selected Coupon: " + coupons[position], Toast.LENGTH_SHORT).show();

                    // Set the discount percentage based on the selected coupon
                    float discountPercentage = 0;
                    switch (selectedCoupon) {


                        case "Celeb123":
                            discountPercentage = 10; // Example discount percentage for "Celeb123"
                            // Set the discount percentage to the discount EditText
                            discountEdt.setText(String.valueOf(discountPercentage));
                            // Calculate discount amount
                            Double discountAmount = (Double.valueOf(discountEdt.getText().toString()) / 100) * Double.valueOf(totalEdt.getText().toString());

                            // Calculate discounted total amount
                            Double discountedTotal = Double.valueOf(totalEdt.getText().toString()) - discountAmount;

                            int disAm = (int) Math.round(discountAmount);
                            int disTot = (int) Math.round(discountedTotal);


                            // Set the calculated values to corresponding EditText fields
                            disAmEdt.setText(String.valueOf(disAm));
                            grandTotalEdt.setText(String.valueOf(disTot));
                            break;


                        case "Normal123":
                            discountPercentage = 5; // Example discount percentage for "Normal123"
                            // Set the discount percentage to the discount EditText
                            discountEdt.setText(String.valueOf(discountPercentage));
                            // Calculate discount amount
                            Double discountAmount1 = (Double.valueOf(discountEdt.getText().toString()) / 100) * Double.valueOf(totalEdt.getText().toString());

                            // Calculate discounted total amount
                            Double discountedTotal1 = Double.valueOf(totalEdt.getText().toString()) - discountAmount1;

                            int disAm1 = (int) Math.round(discountAmount1);
                            int disTot1 = (int) Math.round(discountedTotal1);


                            // Set the calculated values to corresponding EditText fields
                            disAmEdt.setText(String.valueOf(disAm1));
                            grandTotalEdt.setText(String.valueOf(disTot1));
                            break;

                        case "Festival123":
                            discountPercentage = 15; // Example discount percentage for "Normal123"
                            // Set the discount percentage to the discount EditText
                            discountEdt.setText(String.valueOf(discountPercentage));
                            // Calculate discount amount
                            Double discountAmount2 = (Double.valueOf(discountEdt.getText().toString()) / 100) * Double.valueOf(totalEdt.getText().toString());

                            // Calculate discounted total amount
                            Double discountedTotal2 = Double.valueOf(totalEdt.getText().toString()) - discountAmount2;

                            int disAm2 = (int) Math.round(discountAmount2);
                            int disTot2 = (int) Math.round(discountedTotal2);

                            // Set the calculated values to corresponding EditText fields
                            disAmEdt.setText(String.valueOf(disAm2));
                            grandTotalEdt.setText(String.valueOf(disTot2));
                            break;


                        case "Event123":
                            discountPercentage = 25; // Example discount percentage for "Normal123"
                            // Set the discount percentage to the discount EditText
                            discountEdt.setText(String.valueOf(discountPercentage));
                            // Calculate discount amount
                            Double discountAmount3 = (Double.valueOf(discountEdt.getText().toString()) / 100) * Double.valueOf(totalEdt.getText().toString());

                            // Calculate discounted total amount
                            Double discountedTotal3 = Double.valueOf(totalEdt.getText().toString()) - discountAmount3;

                            int disAm3 = (int) Math.round(discountAmount3);
                            int disTot3 = (int) Math.round(discountedTotal3);

                            // Set the calculated values to corresponding EditText fields
                            disAmEdt.setText(String.valueOf(disAm3));
                            grandTotalEdt.setText(String.valueOf(disTot3));
                            break;
                        // Add cases for other coupons as needed
                        default:
                            discountPercentage = 0; // Default discount percentage
                            break;
                    }


                    // Calculate the discount amount and update the total
//                calculateDiscountAndTotal();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

//        String selectedCoupon = disCouponSpinner.getSelectedItem().toString();


//        float discou=5;
//        discountEdt.setText(String.valueOf(discou));

// Check if discountEdt is not empty before parsing its value
            //if (!TextUtils.isEmpty(discountEdt.getText())) {
            // float dis = Float.parseFloat(discountEdt);
//        float discountAmount =0.0f;


            //  } else {
            // Handle the case where discountEdt is empty
//            Toast.makeText(getContext(), "Discount field is empty", Toast.LENGTH_SHORT).show();
            //  }


            makePaymentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Fragment fragment = new PaymentFragment();
                    Bundle args = new Bundle();

                    args.putString("chindate", ChInDate);
                    args.putString("choutdate", ChOutDate);

                    args.putString("noroom", String.valueOf(noroom));
                    args.putString("rfduration", String.valueOf(duration));
                    args.putString("rfnoperson", String.valueOf(noperson));

                    args.putString("roomfType", String.valueOf(roomfType));
                    args.putString("roomDesc", roomDesc);
                    args.putString("roomfPrice", String.valueOf(roomfPrice));
                    args.putString("roomfTax", String.valueOf(roomfTax));
                    args.putString("roomfOtFacTitle", otFacTitle);
                    args.putString("roomfOtFacPrice", String.valueOf(roomfOtFacPrice));

                    args.putString("firstName", firstName);
                    args.putString("lastName", lastName);
                    args.putString("email", email);
                    args.putString("address", address);
                    args.putString("postCode", postCode);
                    args.putString("mobileNo", mobileNo);

                    args.putString("total", total);

                    args.putString("coupon", disCouponSpinner.getSelectedItem().toString());
                    args.putString("discount", discountEdt.getText().toString());
                    args.putString("discountAm", disAmEdt.getText().toString());
                    args.putString("grandTotal", grandTotalEdt.getText().toString());

                    fragment.setArguments(args);

                    // Display the details in a default AlertDialog
                    displayDetailsDialog();


                    AppCompatActivity activity = (AppCompatActivity) getActivity();

                    if (activity != null) {
                        // Access the TabLayout from the activity reference
                        TabLayout tabLayout = activity.findViewById(R.id.include);

                        // Select the desired tab position
                        if (tabLayout != null) {
                            TabLayout.Tab tab = tabLayout.getTabAt(4);
                            if (tab != null) {
                                tab.select();
                            }
                        }
                    }

                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentcontainer, fragment);
                    transaction.commit();

//                viewPager=v.findViewById(R.id.fragmentcontainer);
//
//                ConfirmDetailsFragment confirmDetailsFragment=new ConfirmDetailsFragment();
//                confirmDetailsFragment.setViewPager(viewPager);
//
//                viewPager=getActivity().findViewById(R.id.fragmentcontainer);
//
//                if (viewPager != null) {

                    // Check if all fields are not empty
//                    if (validateFields()) {
                    // Display the details in a default AlertDialog
//                        displayDetailsDialog();
//                        // Set the index of the tab you want to move to
//                        int tabIndexToMoveTo = 3; // For example, move to the second tab (index 1)

                    // Move to the desired tab
//                        viewPager.setCurrentItem(4);
//                    } else {
//                        // Show an error message if any field is empty
//                        Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                    }

//                } else {
//                    // Handle the case when ViewPager is not found
//                    Log.e("ContactDetailsFragment", "ViewPager not found");
//                }
                }
            });

        } else {
            Log.e("ConfirmDetailsFragment", "Bundle is null");
        }

        return v;

    }

    private boolean validateFields() {
        // Check if any of the fields are empty
        return !TextUtils.isEmpty(nameEdt.getText())
                && !TextUtils.isEmpty(roomTypeEdt.getText())
                && !TextUtils.isEmpty(roomPriceEdt.getText())
                && !TextUtils.isEmpty(taxEdt.getText())
                && !TextUtils.isEmpty(exChEdt.getText())
                && !TextUtils.isEmpty(discountEdt.getText())
                && !TextUtils.isEmpty(disAmEdt.getText())
                && !TextUtils.isEmpty(totalEdt.getText())
                && !TextUtils.isEmpty(grandTotalEdt.getText());
    }

//    private void setViewPager(ViewPager viewPager) {
//        this.viewPager=viewPager;
//    }

    private void displayDetailsDialog() {
        // Build the AlertDialog with the entered details
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Payment Details");

        // Construct the message string with the entered details
        StringBuilder message = new StringBuilder();

        bundle = getArguments();

        String ChInDate = bundle.getString("chindate");
        String ChOutDate = bundle.getString("choutdate");

        Double noroom = Double.valueOf(bundle.getString("noroom"));
        Double noperson = Double.valueOf(bundle.getString("rfnoperson"));
        String duration = bundle.getString("rfduration");

        String roomfType = bundle.getString("roomfType");
        String roomDesc = bundle.getString("roomDesc");
        String roomfPrice = bundle.getString("roomfPrice");
        String roomfTax = bundle.getString("roomfTax");
        String roomfOtFacPrice = bundle.getString("roomfOtFacPrice");
        String total = bundle.getString("total");

//        Log.d("getTotal",total);
        String firstName = bundle.getString("firstName");
        String lastName = bundle.getString("lastName");
        String email = bundle.getString("email");
        String address = bundle.getString("address");
        String postCode = bundle.getString("postCode");
        String mobileNo = bundle.getString("mobileNo");

        String selectedCoupon = disCouponSpinner.getSelectedItem().toString();
        String discount = discountEdt.getText().toString();
        String discountAm = disAmEdt.getText().toString();
        String grandTotal = grandTotalEdt.getText().toString();

        message.append("Check In Date: ").append(ChInDate).append("\n");
        message.append("Check Out Date: ").append(ChOutDate).append("\n");

        message.append("Selected Room: ").append(noroom).append("\n");
        message.append("No of Person: ").append(noperson).append("\n");
        message.append("Duration: ").append(duration).append("\n");

        message.append("Room Type: ").append(roomfType).append("\n");
        message.append("Room Description").append(roomDesc).append("\n");
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