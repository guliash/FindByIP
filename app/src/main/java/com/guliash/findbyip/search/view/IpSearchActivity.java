package com.guliash.findbyip.search.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.guliash.findbyip.R;

public class IpSearchActivity extends AppCompatActivity {

    private EditText ipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipEditText = (EditText)findViewById(R.id.ip);
    }
}
