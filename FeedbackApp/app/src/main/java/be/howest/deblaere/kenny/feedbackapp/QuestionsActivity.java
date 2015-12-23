package be.howest.deblaere.kenny.feedbackapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class QuestionsActivity extends Activity {
    private TextView course;
    private ListView questions;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        course = (TextView) findViewById(R.id.courseTitle);
        questions = (ListView) findViewById(R.id.lstQuestions);
        intent = getIntent();
        course.setText(intent.getStringExtra("course"));
        fillListView();
    }

    private void fillListView(){
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        getResources().getStringArray(R.array.nativeQuestions));
        questions.setAdapter(adapter);
    }

}
