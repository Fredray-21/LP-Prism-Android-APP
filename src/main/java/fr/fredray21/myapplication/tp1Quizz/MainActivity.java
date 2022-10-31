package fr.fredray21.myapplication.tp1Quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.fredray21.myapplication.R;


public class MainActivity extends AppCompatActivity {
    private static boolean isCorrection = false;
    private static int currentQ = 1;
    private static int progress = 1;
    private static List<Integer> CORRECTION = new ArrayList<Integer>();
    private static List<Integer> RESULT = new ArrayList<Integer>();

    private static List<String> QUESTIONS = new ArrayList<String>();
    private static List<String> RESPONSES = new ArrayList<String>();

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
        setContentView(R.layout.quizz);
    }

    public void click_button(View view) {
        final TextView textView = findViewById(R.id.id_txtV_question);
        final TextView txtVnbQuestion = findViewById(R.id.id_txtVnbQuestion);
        final Button btn1 = findViewById(R.id.button1);
        final Button btn2 = findViewById(R.id.button2);
        final Button btn3 = findViewById(R.id.button3);
        final Button btn_next = findViewById(R.id.id_btn_next);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        int idViewInt = 0;
        if(!isCorrection){
            String idView = getResources().getResourceEntryName(view.getId());
            String number = idView.substring(idView.length() - 1);
            idViewInt = Integer.parseInt(number);
        }


        if (currentQ == 3 && !isCorrection) {
            RESULT.add(idViewInt);
        }

        if (currentQ < 3 && !isCorrection) {
            RESULT.add(idViewInt);

            textView.setText(QUESTIONS.get(currentQ));
            String JSONtextBTN = RESPONSES.get(currentQ);
            try {
                JSONObject jObject = new JSONObject(JSONtextBTN);

                String textBTN1 = jObject.getString("btn1");
                String textBTN2 = jObject.getString("btn2");
                String textBTN3 = jObject.getString("btn3");
                btn1.setText(textBTN1);
                btn2.setText(textBTN2);
                btn3.setText(textBTN3);

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("UWU", String.valueOf(e));
            }
        }
        if (progress < 3) {
            progress++;
            progressBar.setProgress(progress);
        }
        currentQ++;
        if (currentQ <= 3) {
            txtVnbQuestion.setText("Question : ".concat(String.valueOf(currentQ)));
        } else {
            progress = 1;
            progressBar.setProgress(progress);
            progressBar.getProgressDrawable()
                    .setColorFilter(
                            Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
            isCorrection = true;
            currentQ = 1;
            textView.setText(QUESTIONS.get(currentQ));
        }

        if (isCorrection) {
            int nbFaute = 0;
            for (int i = 0; i < 3; i++) {
                if (RESULT.get(i) != CORRECTION.get(i)) {
                    nbFaute++;
                }
            }
            txtVnbQuestion.setText("Correction | Faute : " + nbFaute);

            String question = QUESTIONS.get(currentQ-1);
            textView.setText(question);
            String JSONtextBTN = RESPONSES.get(currentQ-1);

            try {
                JSONObject jObject = new JSONObject(JSONtextBTN);

                String textBTN1 = jObject.getString("btn1");
                String textBTN2 = jObject.getString("btn2");
                String textBTN3 = jObject.getString("btn3");
                btn1.setText(textBTN1);
                btn2.setText(textBTN2);
                btn3.setText(textBTN3);

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("UWU", String.valueOf(e));
            }
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            btn1.setClickable(false);
            btn2.setClickable(false);
            btn3.setClickable(false);
            btn1.setBackgroundColor(Color.parseColor("#FF6200EE"));
            btn2.setBackgroundColor(Color.parseColor("#FF6200EE"));
            btn3.setBackgroundColor(Color.parseColor("#FF6200EE"));

                switch (RESULT.get(currentQ-1)) {
                    case 1:
                        btn1.setBackgroundColor(Color.RED);
                        break;
                    case 2:
                        btn2.setBackgroundColor(Color.RED);
                        break;
                    case 3:
                        btn3.setBackgroundColor(Color.RED);
                        break;
                }

                switch (CORRECTION.get(currentQ-1)) {
                    case 1:
                        btn1.setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        btn2.setBackgroundColor(Color.GREEN);
                        break;
                    case 3:
                        btn3.setBackgroundColor(Color.GREEN);
                        break;
            }
            Log.d("UWU", String.valueOf(currentQ));
            if(currentQ >= 3){
                btn_next.setText("Recommencer");
                btn_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentQ = 1;
                        progress = 1;
                        progressBar.setProgress(progress);
                        isCorrection = false;
                        RESULT.clear();
                        recreate();
                    }
                });
            }
            btn_next.setVisibility(View.VISIBLE);
        }
    }
    }
