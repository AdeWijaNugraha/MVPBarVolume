package com.awn.app.mvpbarvolume;

import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView {
    private EditText edtWidth, edtLength, edtHeight;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = (EditText) findViewById(R.id.edt_width);
        edtHeight = (EditText) findViewById(R.id.edt_height);
        edtLength = (EditText) findViewById(R.id.edt_length);
        tvResult = (TextView) findViewById(R.id.tv_result);
        Button btnCalculate = (Button) findViewById(R.id.btn_calculate);

        final MainPresenter presenter = new MainPresenter(this);
        btnCalculate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String width = edtWidth.getText().toString().trim();
                String height = edtHeight.getText().toString().trim();
                String length = edtLength.getText().toString().trim();

                boolean isEmptyFileds = false;
                if (TextUtils.isEmpty(width)){
                    isEmptyFileds = true;
                    edtWidth.setError("Field ini tidak boleh kosong");
                }
                if (TextUtils.isEmpty(height)){
                    isEmptyFileds = true;
                    edtHeight.setError("Field ini tidak boleh kosong");
                }
                if (TextUtils.isEmpty(length)){
                    isEmptyFileds = true;
                    edtLength.setError("Field ini tidak boleh kosong");
                }

                if (!isEmptyFileds){
                    double w = Double.parseDouble(width);
                    double h = Double.parseDouble(height);
                    double l = Double.parseDouble(length);

                    presenter.hitungVolume(l,w,h);
                }
            }
        });
    }

    @Override
    public void tampilVolume(MainModel model) {
        tvResult.setText(String.valueOf(model.getVolume()));
    }
}
