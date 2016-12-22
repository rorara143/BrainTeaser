package com.vitin.mycomp.brainteaser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";

    //define widget variables
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView text_question;
    private Button button_cheat;


     // set up preferences
    private SharedPreferences prefs;


    private FalseTrue[] QuestionBank = new FalseTrue[]{
            new FalseTrue(R.string.question_one, true),
            new FalseTrue(R.string.question_two, false),
            new FalseTrue(R.string.question_three, false),
            new FalseTrue(R.string.question_four, true),
            new FalseTrue(R.string.question_five, true),};

    private int CurrentIndex = 0;
    private boolean mIscheater;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        mIscheater = data.getBooleanExtra(ActivityCheat.ANSWER_SHOWN, false);
    }

    private void updateQuestion() {
        int question = QuestionBank[CurrentIndex].getQuestion();
        text_question.setText(question);
    }

    private void checkAnswer(boolean playerPressedTrue) {
        boolean answerIsTrue = QuestionBank[CurrentIndex].isTrueQuestion();
        int messageAnswer = 0;

        if (mIscheater) {
            messageAnswer = R.string.judgement_message;

        } else {
            if (playerPressedTrue == answerIsTrue) {
                messageAnswer = R.string.correct_toast;
            } else {
                messageAnswer = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageAnswer, Toast.LENGTH_SHORT)
                .show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.main_activity);


        //get reference to the widget
        trueButton = (Button) findViewById(R.id.trueButton);
        falseButton = (Button) findViewById(R.id.falseButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        text_question = (TextView) findViewById(R.id.text_question);
        button_cheat = (Button) findViewById(R.id.button_cheat);


        // set the listeners true
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

            }
        });

        // set the listeners true
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentIndex = (CurrentIndex + 1) % QuestionBank.length;
                mIscheater = false;
                updateQuestion();

            }
        });
        updateQuestion();


        button_cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityCheat.class);
                boolean answerIsTrue = QuestionBank[CurrentIndex].isTrueQuestion();
                i.putExtra(ActivityCheat.ANSWER_IS_TRUE, answerIsTrue);
                startActivityForResult(i, 0);
            }
        });
        updateQuestion();


    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, CurrentIndex);

        if (savedInstanceState != null) {
            CurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.quiz_activity, menu);
        return true;

}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_settings:
                startActivity(new Intent(getApplicationContext(), AboutInformation.class));
                return true;
            case R.id.add_user:
                startActivity(new Intent(getApplicationContext(), MainDB.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

