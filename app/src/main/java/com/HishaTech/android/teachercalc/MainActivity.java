package com.HishaTech.android.teachercalc;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.HishaTech.android.teachercalc.base.TC_QuestionCount;

@TargetApi(16)
@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private EditText txtQuestionCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Access various widgets in Application.
        RelativeLayout relative = (RelativeLayout) findViewById(R.id.layoutMain);
        txtQuestionCount = (EditText) findViewById(R.id.txtQuestionCount);
        Button btnGetAnswer = (Button) findViewById(R.id.btnGetAnswer);

        // Add OnClickListener to the buttons
        btnGetAnswer.setOnClickListener(mScan);

        // Set Focus
        txtQuestionCount.requestFocus();

        // Access Shared Preferences
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        String backGround = sharedPreferences.getString("bGround", "Basic Math");

        if (backGround.equals("Basic Math")) {
            Drawable basicMath = getResources().getDrawable(
                    R.drawable.bg_teachercalc);
            if (Build.VERSION.SDK_INT >= 16) {
                relative.setBackground(basicMath);
            } else {
                relative.setBackgroundDrawable(basicMath);
            }

        }

    }

    public Button.OnClickListener mScan = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btnGetAnswer:

                    Integer QuestionValue = TC_QuestionCount
                            .GetQuestionValue(Integer
                                    .parseInt(txtQuestionCount.getText()
                                            .toString()));

                    Intent QVAIntent = new Intent(MainActivity.this,
                            QuestionValueAnswer.class);
                    QVAIntent.putExtra("QuestionCount", Integer
                            .parseInt(txtQuestionCount.getText().toString()));
                    QVAIntent.putExtra("QuestionValue", QuestionValue);
                    txtQuestionCount.setText("");
                    startActivity(QVAIntent);

            }

        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.About:
                Intent Aboutintent = new Intent(this, AboutActivity.class);
                this.startActivity(Aboutintent);
                break;

            case R.id.Options:
                Intent GradeRangeintent = new Intent(this, GradeRange.class);
                this.startActivity(GradeRangeintent);
                break;

            //case R.id.Preferences:
            //	Intent Prefintent = new Intent(this, Preferences.class);
            //	this.startActivity(Prefintent);
            //	break;

        }
        return false; // should never happen

    }

}
