package fr.fredray21.myapplication.tp5_BDD;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.fredray21.myapplication.R;

public class MainActivity_BDD extends AppCompatActivity {

    helperBDD bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bdd);
        bdd = new helperBDD(this);
        EditText pays = findViewById(R.id.id_input_pays);
        EditText devise = findViewById(R.id.id_input_devise);
        TextView contentBDD = findViewById(R.id.id_txtView_contentBDD);
        Button btnAdd = findViewById(R.id.id_btn_stockBDD);

        btnAdd.setOnClickListener((v) -> {
                    bdd.insertPays(pays.getText().toString(), devise.getText().toString());
                    contentBDD.setText(String.join("\n", bdd.getAllPays()));
                }
        );

        Button btnClearBDD = findViewById(R.id.id_btn_clearBDD);
        btnClearBDD.setOnClickListener((v) -> {
            bdd.clearBDD();
            contentBDD.setText(String.join("\n", bdd.getAllPays()));
        });
    }
}