package com.vitin.mycomp.brainteaser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by MyComp on 12/12/2016.
 */

public class ActivityCheat extends Activity {
public static  final String ANSWER_IS_TRUE = "com.vitin.mycomp.brainteaser.answer_is_true";
public static final String ANSWER_SHOWN = "com.vitin.mycomp.brainteaser.answer_shown";

    private boolean answerIsTrue;
    private TextView answerTextview;
    private Button showAnswerbutton;



    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheat_activity);



        answerIsTrue = getIntent().getBooleanExtra(ANSWER_IS_TRUE, false);

        answerTextview = (TextView) findViewById(R.id.answerTextview);

        showAnswerbutton = (Button) findViewById(R.id.showAnswerbutton);
        showAnswerbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (answerIsTrue) {

                    answerTextview.setText(R.string.true_button);
                    setAnswerShownResult(true);

                }else {
                    answerTextview.setText(R.string.false_button);
                    setAnswerShownResult(false);
                }
            }
        });
    }
}
