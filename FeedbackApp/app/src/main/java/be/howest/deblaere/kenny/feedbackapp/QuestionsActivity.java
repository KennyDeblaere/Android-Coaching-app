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
import android.widget.AdapterView;
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
    private static ListView questions;
    private EditText question;
    private Button ask;
    private static List<String> questionList;
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
        addListenerToListView();
    }

    private void fillListView(){
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        questionList);
        questions.setAdapter(adapter);
    }

    private void addListenerToListView(){
        final Context context = this;
        questions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2 = new Intent(context, ResponseActivity.class);
                intent2.putExtra("question", parent.getItemAtPosition(position).toString());
                startActivity(intent2);
            }
        });
    }

    private void addListenerToAsk(){
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionList.add((String) question.getText().toString());
                Intent broadcast = new Intent();
                broadcast.setAction("be.howest.deblaere.kenny.feedbackapp.CUSTOM_INTENT");
                broadcast.putExtra("question", (String) question.getText().toString());
                sendBroadcast(broadcast);
                //finish();
                //startActivity(getIntent());
            }
        });
    }

    public static void addToListview(String question, Context context){
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(context,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        questionList);
        questions.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
