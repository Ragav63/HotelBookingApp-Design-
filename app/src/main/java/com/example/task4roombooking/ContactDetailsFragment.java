package com.example.task4roombooking;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;


public class ContactDetailsFragment extends Fragment {
    private EditText fNameEdt, lNameEdt, emailEdt, fulladdEdt, postcodeEdt, phnumEdt;

    private AppCompatButton confirmDetailsBtn;

//    private ViewPager viewPager;

    Bundle bundle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_contact_details, container, false);

        // Initialize EditTexts
        fNameEdt = v.findViewById(R.id.fnameEdt);
        lNameEdt = v.findViewById(R.id.lnameEdt);
        emailEdt = v.findViewById(R.id.emailEdt);
        fulladdEdt = v.findViewById(R.id.fulladdEdt);
        postcodeEdt = v.findViewById(R.id.postcodeEdt);
        phnumEdt = v.findViewById(R.id.phnumEdt);
        bundle = getArguments();
//
//        String name = bundle.getString("ChInDate");

        confirmDetailsBtn=v.findViewById(R.id.confirmDetailsBtn);

        confirmDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                viewPager=v.findViewById(R.id.fragmentcontainer);

//                ContactDetailsFragment contactDetailsFragment=new ContactDetailsFragment();
//                contactDetailsFragment.setViewPager(viewPager);
//
//                viewPager=getActivity().findViewById(R.id.fragmentcontainer);
//
//                if (viewPager != null) {

                    // Check if all fields are not empty
                    if (validateFields()) {

                        String firstName =  fNameEdt.getText().toString();
                        String lastName = lNameEdt.getText().toString();
                        String email =  emailEdt.getText().toString();
                        String address=fulladdEdt.getText().toString();
                        String postCode=postcodeEdt.getText().toString();
                        String mobileNo=phnumEdt.getText().toString();

                        Fragment fragment = new ConfirmDetailsFragment();
                        Bundle args = new Bundle();

                        String ChInDate=bundle.getString("chindate");
                        String ChOutDate=bundle.getString("choutdate");

                        Log.e("cdetailsCindate",ChInDate);
                        Log.e("cdetailsCoutndate",ChOutDate);

                        Double noroom = Double.valueOf(bundle.getString("noroom"));
                        Double noperson = Double.valueOf(bundle.getString("rfnoperson"));
                        String duration = bundle.getString("rfduration");
                        String title=bundle.getString("roomfType");
                        String roomDesc=bundle.getString("roomDesc");
                        String price=bundle.getString("roomfPrice");
                        String tax=bundle.getString("roomfTax");
                        String otFacTitle=bundle.getString("roomfOtFacTitle");
                        String otFacPrice=bundle.getString("roomfOtFacPrice");
                        String total=bundle.getString("total");

                        args.putString("chindate", ChInDate);
                        args.putString("choutdate", ChOutDate);

                        args.putString("noroom", String.valueOf(noroom));
                        args.putString("rfduration", String.valueOf(duration));
                        args.putString("rfnoperson", String.valueOf(noperson));

                        args.putString("roomfType", String.valueOf(title));
                        args.putString("roomDesc",roomDesc);
                        args.putString("roomfPrice", String.valueOf(price));
                        args.putString("roomfTax", String.valueOf(tax));
                        args.putString("roomfOtFacTitle",otFacTitle);
                        args.putString("roomfOtFacPrice", String.valueOf(otFacPrice));

                        args.putString("firstName", firstName);
                        args.putString("lastName", lastName);
                        args.putString("email", email);
                        args.putString("address", address);
                        args.putString("postCode", postCode);
                        args.putString("mobileNo", mobileNo);

                        args.putString("total",total);

                        fragment.setArguments(args);
                        AppCompatActivity activity = (AppCompatActivity) getActivity();

                        if (activity != null) {
                            // Access the TabLayout from the activity reference
                            TabLayout tabLayout = activity.findViewById(R.id.include);

                            // Select the desired tab position
                            if (tabLayout != null) {
                                TabLayout.Tab tab = tabLayout.getTabAt(3);
                                if (tab != null) {
                                    tab.select();
                                }
                            }
                        }

                        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentcontainer, fragment);
                        transaction.commit();



                        // Display the details in a default AlertDialog
//                        displayDetailsDialog();
//                        // Set the index of the tab you want to move to
//                        int tabIndexToMoveTo = 3; // For example, move to the second tab (index 1)
//
//                        // Move to the desired tab
//                        viewPager.setCurrentItem(3);
                    } else {
                        // Show an error message if any field is empty
                        Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    }

//                } else {
//                    // Handle the case when ViewPager is not found
//                    Log.e("ContactDetailsFragment", "ViewPager not found");
//                }
            }
        });

        return v;
    }

    private boolean validateFields() {
        // Check if any of the fields are empty
        return !TextUtils.isEmpty(fNameEdt.getText())
                && !TextUtils.isEmpty(lNameEdt.getText())
                && !TextUtils.isEmpty(emailEdt.getText())
                && !TextUtils.isEmpty(fulladdEdt.getText())
                && !TextUtils.isEmpty(postcodeEdt.getText())
                && !TextUtils.isEmpty(phnumEdt.getText());
    }

    private void displayDetailsDialog() {
        // Build the AlertDialog with the entered details
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Contact Details");

        // Construct the message string with the entered details
        StringBuilder message = new StringBuilder();
        message.append("First Name: ").append(fNameEdt.getText().toString()).append("\n");
        message.append("Last Name: ").append(lNameEdt.getText().toString()).append("\n");
        message.append("Email Address: ").append(emailEdt.getText().toString()).append("\n");
        message.append("Full Address: ").append(fulladdEdt.getText().toString()).append("\n");
        message.append("Post Code: ").append(postcodeEdt.getText().toString()).append("\n");
        message.append("Mobile Number: ").append(phnumEdt.getText().toString()).append("\n");

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

//    private void setViewPager(ViewPager viewPager) {
//        this.viewPager=viewPager;
//    }
}