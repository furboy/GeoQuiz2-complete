package com.ctech.aleco.geoquiz2;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mFalseButton;
    private Button mTrueButton;
    private ImageButton mNextButton;
    private ImageButton mBackButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionbank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_russia, false),
            new Question(R.string.question_greece, false),
            new Question(R.string.question_america, false),
    };
    private int mCurrentIndex = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = findViewById(R.id.question_text_view);
        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionbank.length;
                updateQuestion();
            }
        });
        mBackButton = findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1);
                updateQuestion();
            }
        });

    }

    private void updateQuestion() {
        int questionResourceId = mQuestionbank[mCurrentIndex].getTextRedId();
        mQuestionTextView.setText(questionResourceId);
    }
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionbank[mCurrentIndex].isAnswerTrue();
        int messageResourceId = 0;
        if (userPressedTrue == answerIsTrue){
            messageResourceId = R.string.correct_toast;
        } else {
            messageResourceId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResourceId, Toast.LENGTH_SHORT).show();
    }
}