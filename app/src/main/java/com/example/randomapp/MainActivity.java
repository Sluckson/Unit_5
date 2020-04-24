 package com.example.randomapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {
     private static  final String TAG = "bouton";

    private EditText txtNumber = null;
    private Button btnCompare;
    private TextView lblResult;
    private ProgressBar progressBar;
    private TextView lblOutput;

    private  int valueSch;
    private  int Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumber = (EditText) findViewById(R.id.txtNumber);
        btnCompare = (Button)  findViewById(R.id.btnCompare);
        lblResult = (TextView)  findViewById(R.id.lblResult);
        progressBar = (ProgressBar)  findViewById(R.id.progressBar);
        lblOutput  = (TextView)  findViewById(R.id.lblOutput);

        btnCompare.setOnClickListener(btnCompareLisner);
        init();
    }

    private  void init(){
        Score = 0;
        valueSch =(int) (Math.random() * 100) + 1;
        Log.i(TAG, "Bouton cliquer" + valueSch);

        progressBar.setProgress(Score);
        txtNumber.setText("");
        lblOutput.setText("");
        lblResult.setText("");

        txtNumber.requestFocus();
    }

    private void congratulation(){
        lblResult.setText(R.string.cong);
        AlertDialog retry = new AlertDialog.Builder(this).create();
        retry.setTitle(R.string.app_name);
        retry.setMessage(getString(R.string.mesage, Score));
        retry.setButton(AlertDialog.BUTTON_POSITIVE, "oui", new AlertDialog.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        });

        retry.setButton(AlertDialog.BUTTON_NEGATIVE, "non", new AlertDialog.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            finish();
            }
        });
        retry.show();
    }
    private View.OnClickListener btnCompareLisner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i(TAG, "Bouton cliquer");
            String recuper = txtNumber.getText().toString();

            if (recuper.equals("")) return;
            int enterese = Integer.parseInt(recuper);
            lblOutput.append(recuper + "\r\n");
            progressBar.incrementProgressBy(1);
            Score++;


            if (enterese == valueSch){
                congratulation();
            }else if (enterese < valueSch){
                lblResult.setText(R.string.grande);
            }else {
                lblResult.setText(R.string.petite);
            }
            txtNumber.setText("");
            txtNumber.requestFocus();
        }
    };
}
