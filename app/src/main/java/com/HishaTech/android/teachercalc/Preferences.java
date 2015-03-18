package com.HishaTech.android.teachercalc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Preferences extends PreferenceActivity implements
        OnItemSelectedListener {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private Spinner ddlBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        prefs = getSharedPreferences(Utils.pref_name, 0);
        editor = prefs.edit();

        String backGround = prefs.getString(Utils.BGround_Key,
                Utils.BGround_Default);

        // Access various widgets in Application.
        ddlBackground = (Spinner) findViewById(R.id.ddlBackground);

        // Register a callback to be invoked when an item in this AdapterView
        // has been selected.
        // The argument is the callback that will return
        ddlBackground.setOnItemSelectedListener(Preferences.this);
        // Adapter for spinner
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(
                getApplicationContext(), R.array.BackgroundList,
                android.R.layout.simple_spinner_dropdown_item);
        // Sets the layout resource to create the drop down views
        adapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        // The Adapter is used to provide the data which backs this Spinner.
        ddlBackground.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {


        editor.putString(Utils.BGround_Key,
                parent.getItemAtPosition(position).toString());
        editor.commit();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }
}