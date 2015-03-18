package com.HishaTech.android.teachercalc;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

@TargetApi(16)
@SuppressWarnings("deprecation")
public class QuestionValueAnswer extends Activity {

    SharedPreferences prefs;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionvalueanswer);

		// Get variables passed from Main Activity
		Intent QVAIntent = getIntent();
		Integer QuestionCount = QVAIntent.getIntExtra("QuestionCount", 0);
		Integer QuestionValue = QVAIntent.getIntExtra("QuestionValue", 0);

		// Access various widgets in Application.
		ListView lvQVA = (ListView) findViewById(R.id.lvQuestionValueAnswer);
		LinearLayout linear = (LinearLayout) findViewById(R.id.layoutQVA);

		// Access Shared Preferences
        prefs = getSharedPreferences(Utils.pref_name, 0);

        String AHigh = prefs.getString(Utils.AHigh_Key,
                Utils.AHigh_Default);
        String ALow = prefs.getString(Utils.ALow_Key, Utils.ALow_Default);
        String BHigh = prefs.getString(Utils.BHigh_Key,
                Utils.BHigh_Default);
        String BLow = prefs.getString(Utils.BLow_Key, Utils.BLow_Default);
        String CHigh = prefs.getString(Utils.CHigh_Key,
                Utils.CHigh_Default);
        String CLow = prefs.getString(Utils.CLow_Key, Utils.CLow_Default);
        String DHigh = prefs.getString(Utils.DHigh_Key,
                Utils.DHigh_Default);
        String DLow = prefs.getString(Utils.DLow_Key, Utils.DLow_Default);
        String EHigh = prefs.getString(Utils.EHigh_Key,
                Utils.EHigh_Default);
        String ELow = prefs.getString(Utils.ELow_Key, Utils.ELow_Default);
        String FHigh = prefs.getString(Utils.FHigh_Key,
                Utils.FHigh_Default);
        String FLow = prefs.getString(Utils.FLow_Key, Utils.FLow_Default);
        Boolean EisF = prefs.getBoolean(Utils.EisF_Key, Utils.EisF_Default);
        String backGround = prefs.getString(Utils.BGround_Key,
                Utils.BGround_Default);

		if (backGround.equals("Basic Math")) {
			Drawable basicMath = getResources().getDrawable(
					R.drawable.bg_teachercalc);
			if (Build.VERSION.SDK_INT >= 16) {
				linear.setBackground(basicMath);
			} else {
				linear.setBackgroundDrawable(basicMath);
			}

		}

		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();

		Integer MissedValue = 100;

		map.put("QNumber", getString(R.string.QVA_Column1));
		map.put("Value", getString(R.string.QVA_Column2));
		map.put("Grade", getString(R.string.QVA_Column3));
		mylist.add(map);

		for (int i = 0; i < QuestionCount + 1; i++) {

			map = new HashMap<String, String>();
			map.put("QNumber", String.valueOf(i));

			if (!EisF) {

				if (MissedValue < 0) {
					map.put("Value", "0");
					map.put("Grade", "F");
				} else {
					map.put("Value", MissedValue.toString());
				}

				if (MissedValue >= Integer.parseInt(ALow)
						&& MissedValue <= Integer.parseInt(AHigh)) {
					map.put("Grade", "A");
				} else if (MissedValue >= Integer.parseInt(BLow)
						&& MissedValue <= Integer.parseInt(BHigh)) {
					map.put("Grade", "B");
				} else if (MissedValue >= Integer.parseInt(CLow)
						&& MissedValue <= Integer.parseInt(CHigh)) {
					map.put("Grade", "C");
				} else if (MissedValue >= Integer.parseInt(DLow)
						&& MissedValue <= Integer.parseInt(DHigh)) {
					map.put("Grade", "D");
				} else if (MissedValue >= Integer.parseInt(ELow)
						&& MissedValue <= Integer.parseInt(EHigh)) {
					map.put("Grade", "E");
				} else if (MissedValue >= Integer.parseInt(FLow)
						&& MissedValue <= Integer.parseInt(FHigh)) {
					map.put("Grade", "F");
				}

			} else {

				if (MissedValue < 0) {
					map.put("Value", "0");
					map.put("Grade", "E/F");
				} else {
					map.put("Value", MissedValue.toString());
				}

				if (MissedValue >= Integer.parseInt(ALow)
						&& MissedValue <= Integer.parseInt(AHigh)) {
					map.put("Grade", "A");
				} else if (MissedValue >= Integer.parseInt(BLow)
						&& MissedValue <= Integer.parseInt(BHigh)) {
					map.put("Grade", "B");
				} else if (MissedValue >= Integer.parseInt(CLow)
						&& MissedValue <= Integer.parseInt(CHigh)) {
					map.put("Grade", "C");
				} else if (MissedValue >= Integer.parseInt(DLow)
						&& MissedValue <= Integer.parseInt(DHigh)) {
					map.put("Grade", "D");
				} else if (MissedValue >= Integer.parseInt(ELow)
						&& MissedValue <= Integer.parseInt(EHigh)) {
					map.put("Grade", "E/F");
				}

			}

			mylist.add(map);
			MissedValue = MissedValue - QuestionValue;

		}

		SimpleAdapter mSchedule = new SimpleAdapter(this, mylist,
				R.layout.rowlayout_qv, new String[] { "QNumber", "Value",
						"Grade" }, new int[] { R.id.rl_qv_first,
						R.id.rl_qv_second, R.id.rl_qv_third });

		lvQVA.setAdapter(mSchedule);

	}
}
