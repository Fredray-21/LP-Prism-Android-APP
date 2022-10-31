package fr.fredray21.myapplication.tp2Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.fredray21.myapplication.R;

public class MainActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        Button btn = findViewById(R.id.id_button_back);

        // get value from bundle
        Bundle extras = getIntent().getExtras();
        String value1 = extras.getString("value");
        TextView textV = findViewById(R.id.id_txtV_titre);
        textV.setText(value1);

        btn.setOnClickListener((view) -> {
            TextView et = findViewById(R.id.id_plaintTxt_two);
            String stringResultat = et.getText().toString();
            Intent i = new Intent();
            i.putExtra("value", stringResultat );
            setResult(RESULT_OK, i);
            finish();
        });
    }
}