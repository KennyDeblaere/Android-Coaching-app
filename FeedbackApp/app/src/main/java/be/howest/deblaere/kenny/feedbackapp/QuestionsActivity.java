package be.howest.deblaere.kenny.feedbackapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionsActivity extends Activity {
    private TextView course;
    private ListView questions;
    private EditText question;
    private Button ask;
    private List<String> questionList;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        course = (TextView) findViewById(R.id.courseTitle);
        questions = (ListView) findViewById(R.id.lstQuestions);
        question = (EditText) findViewById(R.id.question);
        ask = (Button) findViewById(R.id.ask);
        questionList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.nativeQuestions)));
        intent = getIntent();
        course.setText(intent.getStringExtra("course"));
        fillListView();
        addListenerToAsk();
    }

    private void fillListView(){
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        questionList);
        questions.setAdapter(adapter);
    }

    private void addListenerToAsk(){
        final Context context = this;
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionList.add((String) question.getText().toString());
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(context,
                                android.R.layout.simple_list_item_1,
                                android.R.id.text1,
                                questionList);
                questions.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
