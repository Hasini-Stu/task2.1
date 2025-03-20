package com.example.task21final;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.ArrayAdapter;


import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerSource, spinnerDestination;
    EditText valueInput;
    Button convertButton;
    TextView convertedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        valueInput = findViewById(R.id.editText);
        spinnerSource = findViewById(R.id.spinnersource);
        spinnerDestination = findViewById(R.id.spinnerdestination);
        convertedValue = findViewById(R.id.textView);
        convertButton = findViewById(R.id.button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSource.setAdapter(adapter);
        spinnerDestination.setAdapter(adapter);

        //button correction
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void convertUnits() {
        String inputString = valueInput.getText().toString();
        if (inputString.isEmpty()) {
            convertedValue.setText("Please enter a value");
            return;
        }

        double value;
        try {
            value = Double.parseDouble(inputString);
        } catch (NumberFormatException e) {
            convertedValue.setText("Invalid number format");
            return;
        }

        String sourceUnit = spinnerSource.getSelectedItem().toString();
        String destinationUnit = spinnerDestination.getSelectedItem().toString();

        if (sourceUnit.equals(destinationUnit)) {
            convertedValue.setText(String.valueOf(value));
            return;
        }

        double result;

        // recorectionnn as Navneet said used the same if-else loop thing in every section

        if (sourceUnit.equals("Inch") && destinationUnit.equals("Foot")) {
            result = value / 12;
        } else if (sourceUnit.equals("Foot") && destinationUnit.equals("Inch")) {
            result = value * 12;
        } else if (sourceUnit.equals("Yard") && destinationUnit.equals("Foot")) {
            result = value * 3;
        } else if (sourceUnit.equals("Foot") && destinationUnit.equals("Yard")) {
            result = value / 3;
        } else if (sourceUnit.equals("Mile") && destinationUnit.equals("Kilometer")) {
            result = value * 1.60934;
        } else if (sourceUnit.equals("Kilometer") && destinationUnit.equals("Mile")) {
            result = value / 1.60934;
        } else if (sourceUnit.equals("Pound") && destinationUnit.equals("Kilogram")) {
            result = value * 0.453592;
        } else if (sourceUnit.equals("Kilogram") && destinationUnit.equals("Pound")) {
            result = value / 0.453592;
        } else if (sourceUnit.equals("Celsius") && destinationUnit.equals("Fahrenheit")) {
            result = (value * 9/5) + 32;
        } else if (sourceUnit.equals("Fahrenheit") && destinationUnit.equals("Celsius")) {
            result = (value - 32) * 5/9;
        } else {
            convertedValue.setText("Converstion faild");
            return;
        }
// recorrection
        convertedValue.setText(String.valueOf(result));
    }
}
