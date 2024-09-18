package com.example.task4roombooking;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialog extends Dialog {
    private String title;
    private String message;
    private String linkText;
    private View.OnClickListener linkClickListener;

    public CustomDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);

        // Set title
        TextView titleTextView = findViewById(R.id.dialog_title);
        titleTextView.setText(title);

        // Set message
        TextView messageTextView = findViewById(R.id.dialog_message);
        messageTextView.setText(message);

        // Set link text and click listener
        TextView linkTextView = findViewById(R.id.dialog_link);
        linkTextView.setText(linkText);
        linkTextView.setOnClickListener(linkClickListener);

        Button dialog_button=findViewById(R.id.dialog_button);

        dialog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public void setLinkClickListener(View.OnClickListener linkClickListener) {
        this.linkClickListener = linkClickListener;
    }



}
