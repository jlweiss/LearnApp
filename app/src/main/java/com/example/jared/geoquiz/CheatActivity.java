package com.example.jared.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_KEY = "com.example.jared.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.jared.geoquiz.answer_shown";
    private static final String TAG = "CheatActivity";
    private static final String CHEAT_KEY  = "cheat";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private boolean mAnswerShown;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue)
    {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_KEY, answerIsTrue);
        return i;
    }

    public static boolean wasAnswerShown(Intent result)
    {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Log.d("TAG","cheat activity created");

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_KEY, false);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.True_button);
                else
                    mAnswerTextView.setText(R.string.False_button);
                mAnswerShown = true;
                setAnswerShownResult(true);
            }
        });
        if(savedInstanceState!= null) {
            Log.d(TAG, "saved instance state was not null");
            mAnswerShown = savedInstanceState.getBoolean(CHEAT_KEY);
        }
    }
    /**
     * Sets the intent return data to say that the user entered into the cheat screen
     * then pressed the show answer button
     * @param isAnswerShown
     */
    private void setAnswerShownResult(boolean isAnswerShown)
    {
        Intent data= new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "it saved");
        outState.putBoolean(CHEAT_KEY, mAnswerShown);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "Restore called");
        mAnswerShown = savedInstanceState.getBoolean(CHEAT_KEY);
    }
}
