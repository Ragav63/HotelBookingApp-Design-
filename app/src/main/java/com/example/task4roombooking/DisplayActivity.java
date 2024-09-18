package com.example.task4roombooking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;

public class DisplayActivity extends AppCompatActivity {
    
    private TextView chInValTv, chOutValTv, noOfRoomValTv, noOfPersonValTv, noOfDaysValTv, 
            roomTitleValTv, roomDescValTv, roomPriceValTv, roomTaxValTv, roomOFPrice, roomTotalAmValTv,
            firstNameValTv, lastNameValTv, emailValTv, addressValTv, postCodeValTv, mobNoValTv, 
            disCouponValTv, disPercentValTv, disAmountValTv, ccNameValTv, ccNumValTv, cvvValTv, expOnValTv, 
            grandTotalValTv;   
    
    private AppCompatButton bookAgainBtn, downloadReceiptBtn;

    // Retrieve the bundle data from the intent
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chInValTv=findViewById(R.id.chInTvVal);
        chOutValTv=findViewById(R.id.chOutTvVal);
        noOfRoomValTv=findViewById(R.id.noOfRoomTvVal);
        noOfPersonValTv=findViewById(R.id.noOfPersonTvVal);
        noOfDaysValTv=findViewById(R.id.noOfDaysTvVal);
        roomTitleValTv=findViewById(R.id.roomTypeTvVal);
        roomDescValTv=findViewById(R.id.roomDescriptionTvVal);
        roomPriceValTv=findViewById(R.id.roomPriceTvVal);
        roomTaxValTv=findViewById(R.id.roomTaxTvVal);
        roomOFPrice=findViewById(R.id.roomOtherFacPriceTvVal);
        roomTotalAmValTv=findViewById(R.id.roomTotalAmTvVal);
        firstNameValTv=findViewById(R.id.firstNameTvVal);
        lastNameValTv=findViewById(R.id.lastNameTvVal);
        emailValTv=findViewById(R.id.emailTvVal);
        addressValTv=findViewById(R.id.addressTvVal);
        postCodeValTv=findViewById(R.id.postCodeTvVal);
        mobNoValTv=findViewById(R.id.mobNumTvVal);
        disCouponValTv=findViewById(R.id.disCouponTvVal);
        disPercentValTv=findViewById(R.id.disPercentTvVal);
        disAmountValTv=findViewById(R.id.disAmountTvVal);
        ccNameValTv=findViewById(R.id.ccNameTvVal);
        ccNumValTv=findViewById(R.id.ccNumberTvVal);
        cvvValTv=findViewById(R.id.cvvTvVal);
        expOnValTv=findViewById(R.id.expOnTvVal);
        
        grandTotalValTv=findViewById(R.id.grandTotalTvVal);
        
        bookAgainBtn=findViewById(R.id.bookAgainbtn);
        downloadReceiptBtn=findViewById(R.id.downloadRecbtn);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            // Retrieve individual data items from the bundle
            String ChInDate=bundle.getString("chindate");
            String ChOutDate=bundle.getString("choutdate");

            String noroom = bundle.getString("noroom");
            String noperson = bundle.getString("rfnoperson");
            String duration = bundle.getString("rfduration");

            String roomfType =bundle.getString("roomfType");
            String roomfPrice = bundle.getString("roomfPrice");
            String roomfTax = bundle.getString("roomfTax");
            String otFacTitle=bundle.getString("roomfOtFacTitle");
            String roomfOtFacPrice =bundle.getString("roomfOtFacPrice");
            String total = bundle.getString("total");

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

            String ccNumber=bundle.getString("ccNumber");
            String ccName=bundle.getString("ccName");
            String cvvNum=bundle.getString("cvvNum");
            String expDate=bundle.getString("expDate");


            // Example: Display the retrieved data in TextViews
            
            chInValTv.setText(ChInDate);
            chOutValTv.setText(ChOutDate);
            noOfRoomValTv.setText(noroom);
            noOfPersonValTv.setText(noperson);
            noOfDaysValTv.setText(duration);
            roomTitleValTv.setText(roomfType);
            roomDescValTv.setText(roomfPrice);
            roomPriceValTv.setText(roomfPrice);
            roomTaxValTv.setText(roomfTax);
            roomOFPrice.setText(roomfOtFacPrice);
            roomTotalAmValTv.setText(total);
            firstNameValTv.setText(firstName);
            lastNameValTv.setText(lastName);
            emailValTv.setText(email);
            addressValTv.setText(address);
            postCodeValTv.setText(postCode);
            mobNoValTv.setText(mobileNo);
            disCouponValTv.setText(selectedCoupon);
            disPercentValTv.setText(discount);
            disAmountValTv.setText(discountAm);
            ccNameValTv.setText(ccName);
            ccNumValTv.setText(ccNumber);
            cvvValTv.setText(cvvNum);
            expOnValTv.setText(expDate);

            grandTotalValTv.setText(grandTotal);

            downloadReceiptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    generatePDF();
                }
            });
            
            
            bookAgainBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an intent to start DisplayActivity
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    Toast.makeText(DisplayActivity.this, "Thank You For Visiting!", Toast.LENGTH_SHORT).show();

                    // Start the DisplayActivity using the intent
                    startActivity(intent);
                }
            });
            

        } else {
            Log.e("ConfirmDetailsFragment", "Bundle is null");
        }
    }

    private void generatePDF() {
        // Create a new PDF document
        try {
            PdfWriter writer = new PdfWriter(new File(getExternalFilesDir(null), "receipt.pdf"));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Add content to the PDF document
            document.add(new Paragraph("Check In Date: " + chInValTv.getText().toString()));
            document.add(new Paragraph("Check Out Date: " + chOutValTv.getText().toString()));


            document.add(new Paragraph("Selected Room: "+noOfRoomValTv.getText().toString()));
            document.add(new Paragraph("No of Person: "+noOfPersonValTv.getText().toString()));
            document.add(new Paragraph("Duration: "+noOfDaysValTv.getText().toString()));

            document.add(new Paragraph("Room Type: "+roomTitleValTv.getText().toString()));
            document.add(new Paragraph("Room Description"+roomDescValTv.getText().toString()));
            document.add(new Paragraph("Room Price: "+roomPriceValTv.getText().toString()));
            document.add(new Paragraph("Other Facility Price: "+roomOFPrice.getText().toString()));
            document.add(new Paragraph("Tax: "+roomTaxValTv.getText().toString()));

            document.add(new Paragraph("Total: "+roomTotalAmValTv.getText().toString()));

            document.add(new Paragraph("First Name: "+firstNameValTv.getText().toString()));
            document.add(new Paragraph("Last Name: "+lastNameValTv.getText().toString()));
            document.add(new Paragraph("Email: "+emailValTv.getText().toString()));
            document.add(new Paragraph("Address: "+addressValTv.getText().toString()));
            document.add(new Paragraph("Postal Code: "+postCodeValTv.getText().toString()));
            document.add(new Paragraph("Mobile Number: "+mobNoValTv.getText().toString()));

            document.add(new Paragraph("Discount Coupon"+disCouponValTv.getText().toString()));
            document.add(new Paragraph("Discount Percent: "+disPercentValTv.getText().toString()));
            document.add(new Paragraph("Discount Amount: "+disAmountValTv.getText().toString()));

            document.add(new Paragraph("Credit Card Number: "+ccNumValTv.getText().toString()));
            document.add(new Paragraph("Credit Card Name: "+ccNameValTv.getText().toString()));
            document.add(new Paragraph("CVV Number: "+cvvValTv.getText().toString()));
            document.add(new Paragraph("Expiry On: "+expOnValTv.getText().toString()));

            document.add(new Paragraph("Grand Total: "+grandTotalValTv.getText().toString()));

            Log.d("FilePath", "PDF File Path: " + new File(getExternalFilesDir(null), "receipt.pdf").getAbsolutePath());

            // Close the document
            document.close();

            Toast.makeText(DisplayActivity.this, "Receipt downloaded successfully!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}