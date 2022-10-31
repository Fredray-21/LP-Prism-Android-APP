package fr.fredray21.myapplication.tp2Quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.fredray21.myapplication.R;

public class MainActivityQuizzQuestion extends AppCompatActivity {
    private static List<Integer> CORRECTION = new ArrayList<Integer>();
    private static List<Integer> RESULT = new ArrayList<Integer>();

    private static List<String> QUESTIONS = new ArrayList<String>();
    private static List<String> RESPONSES = new ArrayList<String>();

    private static int currentQ = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QUESTIONS.add("De quelle ville Billie Eilish est-elle originaire ?");
        QUESTIONS.add("Quel âge Billie Eilish a-t-elle en 2022 ?");
        QUESTIONS.add("Quelle est sa première chanson de Billie Eilish ?");
        RESPONSES.add("{\"btn1\": 'Los Angeles', \"btn2\": 'Liverpool',\"btn3\": 'Londre'}");
        RESPONSES.add("{\"btn1\": '19', \"btn2\": '20',\"btn3\": '21'}");
        RESPONSES.add("{\"btn1\": 'Bad Guy', \"btn2\": 'My Boy',\"btn3\": 'Ocean Eyes'}");
        CORRECTION.add(1);
        CORRECTION.add(2);
        CORRECTION.add(3);

        String JSONtextBTN = RESPONSES.get(currentQ);
        TextView txtVQuestion = findViewById(R.id.id_txtV_question_quizz);

        try {
            txtVQuestion.setText(QUESTIONS.get(currentQ));
        } catch (Exception e) {
            Log.d("UWU", String.valueOf(e));
        }

        RadioButton RB1 = findViewById(R.id.radioButton);
        RadioButton RB2 = findViewById(R.id.radioButton2);
        RadioButton RB3 = findViewById(R.id.radioButton3);

        try {
            JSONObject jObject = new JSONObject(JSONtextBTN);

            String textBTN1 = jObject.getString("btn1");
            String textBTN2 = jObject.getString("btn2");
            String textBTN3 = jObject.getString("btn3");
            RB1.setText(textBTN1);
            RB2.setText(textBTN2);
            RB3.setText(textBTN3);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("UWU", String.valueOf(e));
        }

        setContentView(R.layout.activity_main_quizz_question);
    }
}