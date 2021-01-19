package com.example.wanusa;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanusa.R;
import com.example.wanusa.QuestionAdapter;
import com.example.wanusa.QuizDBHelper;
import com.example.wanusa.Question;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.wanusa.CategoryAdapter.CATEGORY_COLOR;
import static com.example.wanusa.CategoryAdapter.CATEGORY_ID;

/**
 * Created by Azhar Rivaldi on 10/07/2019.
 */

public class QuestionActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private QuestionAdapter mAdapter;
    private ArrayList<Question> mQuestionList;
    private QuizDBHelper mDbHelper;

    private ConstraintLayout mParentLayout;
    private TextView mScoreTextView;
    private TextView mRemaningQuestionsTextView;
    private int mTotalQuestions;
    private int mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Bundle categoryBundle = null;
        if (getIntent() != null) {
            categoryBundle = getIntent().getExtras();
        }

        mParentLayout = findViewById(R.id.question_layout);


        mScoreTextView = findViewById(R.id.score);
        mRemaningQuestionsTextView = findViewById(R.id.remaining_questions);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        mDbHelper = new QuizDBHelper(this, categoryBundle);
        if (categoryBundle != null) {
            mQuestionList = mDbHelper.getAllQuestions(categoryBundle.getString(CATEGORY_ID));
            mTotalQuestions = mQuestionList.size();
            mScore = 0;
            displayScore();
        }
        mAdapter = new QuestionAdapter(this, mQuestionList, categoryBundle.getString(CATEGORY_ID));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void displayScore() {
        String scoreString = "Score " + mScore + "/" + mTotalQuestions;
        mScoreTextView.setText(scoreString);
        mRemaningQuestionsTextView.setText("Remaining: " + mTotalQuestions--);
    }

    public void updateScore() {
        mScore++;
    }
}
