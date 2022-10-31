package fr.fredray21.myapplication.tp4_Services;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import fr.fredray21.myapplication.R;

public class MainActivity_Services extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_services);

        findViewById(R.id.id_btn_start).setOnClickListener(v -> {
            Intent intent = new Intent(this, MusiqueService.class);
            intent.putExtra("pause", true);
            startService(intent);

            Button btn = findViewById(R.id.id_btn_start);
            if (btn.getText().equals("Start")) {
                btn.setText("Pause");
            } else {
                btn.setText("Start");
            }

        });

        findViewById(R.id.id_btn_stop).setOnClickListener(v -> stopService(new Intent(this, MusiqueService.class)));
    }
}