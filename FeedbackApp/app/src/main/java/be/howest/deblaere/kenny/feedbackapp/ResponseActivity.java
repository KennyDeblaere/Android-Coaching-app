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

public class ResponseActivity extends Activity {
    private TextView questionTitle;
    private ListView answers;
    private EditText answer;
    private Button answerBtn;
    private List<String> answerList;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        questionTitle = (TextView) findViewById(R.id.questionTitle);
        answers = (ListView) findViewById(R.id.lstAnswers);
        answer = (EditText) findViewById(R.id.answer);
        answerBtn = (Button) findViewById(R.id.answerBtn);
        answerList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.strandAnswers)));
        intent = getIntent();
        questionTitle.setText(intent.getStringExtra("question"));
        fillListView();
        addListenerToAnswer();
    }

    private void fillListView(){
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        answerList);
        answers.setAdapter(adapter);
    }

    private void addListenerToAnswer(){
        final Context context = this;
        answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerList.add((String) answer.getText().toString());
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(context,
                                android.R.layout.simple_list_item_1,
                                android.R.id.text1,
                                answerList);
                answers.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
