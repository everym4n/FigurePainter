package com.andreveryman.figurepainter;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import top.defaults.colorpicker.ColorPickerPopup;

public class MainActivity extends AppCompatActivity {

    private ImageView colorOval;

    private PaintView paintView;
    private ColorPickerPopup colorPicker;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paintView = findViewById(R.id.paintView);
        colorOval = findViewById(R.id.color_oval);
        spinner = findViewById(R.id.spinner);

        initSpinner();
        initColorPicking();


        findViewById(R.id.btn_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickColor();
            }
        });

        colorOval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickColor();
            }
        });

        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.clear();
            }
        });
    }

    private void initColorPicking() {

        setPickedColor(paintView.getColor());
        colorPicker = new ColorPickerPopup.Builder(MainActivity.this)
                .initialColor(paintView.getColor())
                .enableBrightness(true)
                .enableAlpha(false) // Enable alpha slider or not
                .okTitle(getResources().getString(R.string.choose_color))
                .cancelTitle(getResources().getString(R.string.cancel))
                .showIndicator(true)
                .showValue(false)
                .build();
    }

    private void initSpinner() {

        ModeAdapter adapter = new ModeAdapter();
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paintView.setMode(PaintMode.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    private void setPickedColor(int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) colorOval.getDrawable();
        gradientDrawable.setColor(color);
    }

    private void pickColor() {
        colorPicker.show(colorOval, new ColorPickerPopup.ColorPickerObserver() {
            @Override
            public void onColorPicked(int color) {
                paintView.setColor(color);
                setPickedColor(color);
            }
        });
    }


}
