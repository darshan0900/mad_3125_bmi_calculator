package com.darshan09200.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText heightInput, massInput;
    private TextView bmiLabel;
    private Button calculateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightInput = findViewById(R.id.heightInput);
        massInput = findViewById(R.id.massInput);
        bmiLabel = findViewById(R.id.bmiLabel);
        calculateBtn = findViewById(R.id.calculateBtn);

        calculateBtn.setOnClickListener(view -> {
            try {
                heightInput.clearFocus();
                massInput.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            bmiLabel.setText("");
            calculateBmi();
        });
    }

    private double getHeight() {
        double convertedHeight = 0;
        try {
            String height = heightInput.getText().toString().trim();
            if (height.isEmpty()) showToast("Please enter height");
            else {
                convertedHeight = Double.parseDouble(height);
                if (convertedHeight <= 0) showToast("Please enter height");
            }
        } catch (NumberFormatException err) {
            showToast("Invalid height entered");
        }
        return convertedHeight;
    }

    private double getMass() {
        double convertedMass = 0;
        try {
            String mass = massInput.getText().toString().trim();
            if (mass.isEmpty()) showToast("Please enter mass");
            else {
                convertedMass = Double.parseDouble(mass);
                if (convertedMass <= 0) showToast("Please enter mass");
            }
        } catch (NumberFormatException err) {
            showToast("Invalid mass entered");
        }
        return convertedMass;
    }

    public void calculateBmi() {
        double height = getHeight();
        double mass = getMass();
        if (height <= 0 || mass <= 0) return;
        double bmi = mass / Math.pow(height, 2) * 10000;
        bmiLabel.setText(String.format("Your BMI is %.3f", bmi));
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}