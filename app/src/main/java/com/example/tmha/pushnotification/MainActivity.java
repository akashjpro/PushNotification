package com.example.tmha.pushnotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE_TEXT = "EXTRA_MESSAGE_TEXT";
    private TextView mTxtMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtMessage = findViewById(R.id.txt_message);

        String message = getIntent().getStringExtra(EXTRA_MESSAGE_TEXT);
        if (message != null){
            mTxtMessage.setText(message);
        }
    }
}
