package com.HishaTech.android.teachercalc.base;

public class TC_QuestionCount {

    public TC_QuestionCount() {

    }

    public static Integer GetQuestionValue(Integer QuestionCount) {

        Integer QuestionValue;
        Double RawAnswer;
        double QuizValue = 100.0;

        RawAnswer = QuizValue / QuestionCount;

        QuestionValue = (int) (RawAnswer + 0.5);

        return QuestionValue;

    }

}
