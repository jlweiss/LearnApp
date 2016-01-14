package com.example.jared.geoquiz;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Jared on 12/27/2015.
 */
public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private Scanner input;
    private String question;


    public Question(int TextRedId, boolean AnswerTrue)
    {
        mTextResId = TextRedId;
        mAnswerTrue = AnswerTrue;

    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }



   /* public void readFile()
    {
        Log.d("Tag", "In read file");
        if(input == null)
            openFile();

        if(input.nextLine().equals("True"))
            mAnswerTrue = true;
        else
            mAnswerTrue = false;
        question = input.nextLine();

    }
    public void openFile()
    {
        Log.d("Tag", "in open file");
        final String FILENAME = "questions.txt";
        Scanner input = null;

        try
        {
            input = new Scanner(new File(FILENAME));
        }
        catch(IOException e)
        {
            Log.d("Error File", "Could not read file");
        }

    }
    public String getQuestion()
    {
        return question;
    }*/
}
