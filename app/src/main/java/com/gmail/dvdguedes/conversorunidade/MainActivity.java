package com.gmail.dvdguedes.conversorunidade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner fromSpinner = (Spinner) findViewById(R.id.spinner_from);
        Spinner toSpinner = (Spinner) findViewById(R.id.spinner_to);

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);


    }
    public void convert(View view) {
        Spinner fromSpinner, toSpinner;
        EditText fromEditText, toEditText;

        fromSpinner = (Spinner) findViewById(R.id.spinner_from);
        toSpinner = (Spinner) findViewById(R.id.spinner_to);
        fromEditText = (EditText) findViewById(R.id.editText_from);
        toEditText = (EditText) findViewById(R.id.editText_to);

        String fromString = (String) fromSpinner.getSelectedItem();
        String toString = (String) toSpinner.getSelectedItem();
        double input = Double.valueOf(fromEditText.getText().toString());

        Conversor.Unit fromUnit = Conversor.Unit.fromString(fromString);
        Conversor.Unit toUnit = Conversor.Unit.fromString(toString);

        Conversor converter = new Conversor(fromUnit, toUnit);
        NumberFormat formatter = new DecimalFormat("###,###.#####");
        double result = converter.convert(input);
        toEditText.setText(String.valueOf(formatter.format(result)));
    }
}