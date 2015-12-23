package be.howest.deblaere.kenny.feedbackapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by kenny on 23/12/2015.
 */
public class QuestionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //System.out.println((QuestionsActivity)context);
        QuestionsActivity.addToListview(intent.getStringExtra("question"), context);
        //System.out.println(intent.getStringExtra("question"));
    }
}
