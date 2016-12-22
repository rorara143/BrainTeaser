package com.vitin.mycomp.brainteaser;

/**
 * Created by MyComp on 12/11/2016.
 */

public class FalseTrue {
    private int Question;

    private boolean TrueQuestion;

    public FalseTrue(int question, boolean trueQuestion) {
        Question = question;
        TrueQuestion = trueQuestion;
    }
    public int getQuestion() {
        return Question;
    }
    public void setQuestion(int question) {
        Question = question;
    }
    public boolean isTrueQuestion() {
        return TrueQuestion;
    }
    public void setTrueQuestion(boolean trueQuestion) {
        TrueQuestion = trueQuestion;
    }
}
