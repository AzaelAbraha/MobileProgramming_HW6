package com.example.homework6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btCount;
    Button btResetCount;
    TextView tvCount;
    int count = 0;
    int incrementAmount = 1;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.homework6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        btCount = findViewById(R.id.count_button);
        btResetCount = findViewById(R.id.reset_count_button);
        tvCount = findViewById(R.id.tv_count);

        count = mPreferences.getInt("COUNT_KEY", 0);
        incrementAmount = mPreferences.getInt("INCREMENT_KEY", 0);
        tvCount.setText("" + count);


        btCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count < 10) {
                    Increment();
                }else{
                    Toast.makeText(getApplicationContext(),"The count has reached the maximum number of 10!" , Toast.LENGTH_LONG).show();
                }
                if(incrementAmount == 0){
                    Toast.makeText(getApplicationContext(), "Please choose an increment amount!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btResetCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                tvCount.setText("" + count);
            }
        });

    }
    @Override
    public void onPause(){
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt("COUNT_KEY", count);
        preferencesEditor.putInt("INCREMENT_KEY", incrementAmount);
        preferencesEditor.apply();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.IncrementBy2) {
            if (incrementAmount != 2){
                incrementAmount = 2;
                count = 0;
                tvCount.setText("" + count);
            }
            return true;
        }
        if (id == R.id.IncrementBy5) {
            if(incrementAmount != 5) {
                incrementAmount = 5;
                count = 0;
                tvCount.setText("" + count);
            }
            return true;
        }
        if (id == R.id.IncrementBy10) {
            if(incrementAmount != 10) {
                incrementAmount = 10;
                count = 0;
                tvCount.setText("" + count);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Increment(){
        count = count + incrementAmount;
        if(count > 10){
            count = 10;
        }
        tvCount.setText("" + count);
    }
}
