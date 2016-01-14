package com.example.jared.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private Button mCheatButton;
    private Question mQuestion;
    private TextView tv;
    private boolean mIsCheater;

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;


    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.chicken, false),
            new Question(R.string.beef, true),
            new Question(R.string.bassist, false),
            new Question(R.string.rabbit, true)
    };
    private int mIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "onCreate(Bundle) called");
        //Text View Listener
        tv = (TextView) findViewById(R.id.question_text_view);
        tv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mIndex = (mIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        //True button Listener
        mTrueButton = (Button) findViewById(R.id.trueb);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v)
               {
                    checkAnswer(true);
               }
           }

        );

        //false button listener
        mFalseButton = (Button) findViewById(R.id.falseb);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v)
               {
                   checkAnswer(false);
               }
           }
        );
        //Cheat button listener
        mCheatButton = (Button) findViewById(R.id.cheatb);
        mCheatButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
               // Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                //startActivity(i);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });

        //previous button listener
        mPrevButton = (ImageButton) findViewById(R.id.prevb);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIndex = (mIndex - 1) % mQuestionBank.length;
                if(mIndex < 0) mIndex = mQuestionBank.length - 1;
                updateQuestion();
            }
        });
        //next button listener
        mNextButton = (ImageButton) findViewById(R.id.nextb);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                mIndex = (mIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
               updateQuestion();
            }
        }

        );

        if(savedInstanceState != null)
            mIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mIndex);
    }

    private void checkAnswer(boolean userInput)
    {
        boolean ans = mQuestionBank[mIndex].isAnswerTrue();
        int messageResId = 0;
        if(mIsCheater)
            messageResId = R.string.judgement_toast;
        else if(userInput == ans)
            messageResId = R.string.correct_toast;
        else
            messageResId = R.string.incorrect_toast;

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion()
    {
        int question = mQuestionBank[mIndex].getTextResId();
        tv.setText(question);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode != Activity.RESULT_OK)
            return;
        if(requestCode == REQUEST_CODE_CHEAT)
        {
            if(data == null) return;
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
