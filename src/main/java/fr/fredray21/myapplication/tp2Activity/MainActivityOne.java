package fr.fredray21.myapplication.tp2Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.fredray21.myapplication.R;

public class MainActivityOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                       traiterResultat(result);
                    }
        });
        setContentView(R.layout.activity_main_one);
        Button btn = findViewById(R.id.id_button_GO);
        EditText et = findViewById(R.id.id_plaintTxt_one);
        et.setHint("La question ?");

         btn.setOnClickListener((view) -> {
             Intent i = new Intent(this, MainActivityTwo.class);
             String value = et.getText().toString();
             i.putExtra("value", value);
             launcher.launch(i);
             et.getText().clear();
         });
    }
    private void traiterResultat(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            String value = data.getStringExtra("value");
            TextView txtVreponse = findViewById(R.id.id_txtV_reponse);
            txtVreponse.setText(value);
        }
    }

}

