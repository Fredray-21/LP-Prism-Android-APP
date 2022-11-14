package fr.fredray21.myapplication.tp5_BDD_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import fr.fredray21.myapplication.R;

public class Visualize_BDD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize_bdd);
        TextView txtV_contentBDD = findViewById(R.id.id_txtV_content_bdd_chucknorris);
        txtV_contentBDD.setMovementMethod( new ScrollingMovementMethod());
        Button btn_goBack = findViewById(R.id.id_goback_chuckNorris);

        btn_goBack.setOnClickListener((v) -> {
            finish();
        });

        txtV_contentBDD.setText(String.join("\n",new helperBDD_2(this).getAllBlagues()));
    }
}