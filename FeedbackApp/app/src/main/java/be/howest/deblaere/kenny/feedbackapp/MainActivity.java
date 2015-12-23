package be.howest.deblaere.kenny.feedbackapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Spinner courseSpinner;
    private TextView feedbackNumber;
    private ProgressBar feedbackProgress;
    private ImageButton slower, faster;
    private Button questionsButton;
    private boolean imageButtonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        courseSpinner = (Spinner) findViewById(R.id.course_spinner);
        feedbackNumber = (TextView) findViewById(R.id.feedback);
        feedbackProgress = (ProgressBar) findViewById(R.id.courseProgress);
        slower = (ImageButton) findViewById(R.id.slower);
        faster = (ImageButton) findViewById(R.id.faster);
        questionsButton = (Button) findViewById(R.id.questionButton);
        imageButtonClick = true;
        fillSpinner();
        initializeProgressbar();
        addListenerToSpinner();
        initializeProgressbar();
        addListenerToButtons();
        addListenerToQuestionButton();
    }

    private void initializeProgressbar() {
        feedbackProgress.setProgress(50);
    }

    private void addListenerToQuestionButton(){
        final Context context = this;
        questionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuestionsActivity.class);
                intent.putExtra("course", courseSpinner.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }

    private void fillSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(adapter);
    }
    private void addListenerToSpinner(){
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                feedbackNumber.setText(R.string.defaultFeedback);
                initializeProgressbar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private Thread delayForButton(){
        return new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                imageButtonClick = true;
            }
        };
    }

    private View.OnClickListener clickEvent(final int number) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtonClick) {
                    feedbackProgress.setProgress(feedbackProgress.getProgress() + number);
                    int input = feedbackProgress.getProgress() - 50;
                    if (input >= -50 && input <= 50)
                        feedbackNumber.setText(" " + input);
                    imageButtonClick = false;
                    delayForButton().start();
                }
            }
        };
    }

    private void addListenerToButtons(){
        slower.setOnClickListener(clickEvent(1));
        faster.setOnClickListener(clickEvent(-1));
    }
}
