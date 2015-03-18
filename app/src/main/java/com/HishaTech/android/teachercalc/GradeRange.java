package com.HishaTech.android.teachercalc;

import com.HishaTech.android.teachercalc.base.TC_GRCheck;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

@TargetApi(16)
@SuppressWarnings("deprecation")
public class GradeRange extends Activity {

    private EditText txtALow, txtAHigh, txtBLow, txtBHigh, txtCLow,
            txtCHigh, txtDLow, txtDHigh, txtELow, txtEHigh, txtFLow,
            txtFHigh;
    private TextView lblE, lblF;
    private CheckBox cbxEisF;
    private RelativeLayout relative;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graderange);

        // Access various widgets in Application.
        relative = (RelativeLayout) findViewById(R.id.layoutGradeRange);
        txtALow = (EditText) findViewById(R.id.txtALow);
        txtAHigh = (EditText) findViewById(R.id.txtAHigh);
        txtBLow = (EditText) findViewById(R.id.txtBLow);
        txtBHigh = (EditText) findViewById(R.id.txtBHigh);
        txtCLow = (EditText) findViewById(R.id.txtCLow);
        txtCHigh = (EditText) findViewById(R.id.txtCHigh);
        txtDLow = (EditText) findViewById(R.id.txtDLow);
        txtDHigh = (EditText) findViewById(R.id.txtDHigh);
        txtELow = (EditText) findViewById(R.id.txtELow);
        txtEHigh = (EditText) findViewById(R.id.txtEHigh);
        lblE = (TextView) findViewById(R.id.lblE);
        txtFLow = (EditText) findViewById(R.id.txtFLow);
        txtFHigh = (EditText) findViewById(R.id.txtFHigh);
        lblF = (TextView) findViewById(R.id.lblF);
        cbxEisF = (CheckBox) findViewById(R.id.cbxEisF);
        Button btnGRDone = (Button) findViewById(R.id.btnGRDone);

        // Add OnClickListener to the buttons
        btnGRDone.setOnClickListener(mScan);

        // Add onClickListener to the checkbox
        cbxEisF.setOnClickListener(cbxCheck);

        prefs = getSharedPreferences(Utils.pref_name, 0);
        editor = prefs.edit();

        loadSavedPreferences();

    }

    public CheckBox.OnClickListener cbxCheck = new CheckBox.OnClickListener() {

        @Override
        public void onClick(View v) {


            if (cbxEisF.isChecked()) {

                txtFLow.setVisibility(View.GONE);
                txtFHigh.setVisibility(View.GONE);
                lblF.setVisibility(View.GONE);

                editor.putBoolean(Utils.EisF_Key, true);
                editor.commit();

            } else {

                txtFLow.setVisibility(View.VISIBLE);
                txtFHigh.setVisibility(View.VISIBLE);
                lblF.setVisibility(View.VISIBLE);

                editor.putBoolean(Utils.EisF_Key, false);
                editor.commit();

            }

        }

    };

    public Button.OnClickListener mScan = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btnGRDone:

                    boolean EisF = prefs.getBoolean(Utils.EisF_Key,
                            Utils.EisF_Default);

                    TC_GRCheck GRc = new TC_GRCheck();

                    int errorCheck = 0;

                    boolean A100,
                            AValues,
                            BValues,
                            CValues,
                            DValues,
                            EValues,
                            FValues,
                            AtoB,
                            BtoC,
                            CtoD,
                            DtoE,
                            EtoF = false,
                            F0;

                    A100 = GRc.verifyA100(Integer.parseInt(txtAHigh.getText()
                            .toString()));
                    AValues = GRc.verifyAvalues(
                            Integer.parseInt(txtAHigh.getText().toString()),
                            Integer.parseInt(txtALow.getText().toString()));
                    BValues = GRc.verifyBvalues(
                            Integer.parseInt(txtBHigh.getText().toString()),
                            Integer.parseInt(txtBLow.getText().toString()));
                    CValues = GRc.verifyCvalues(
                            Integer.parseInt(txtCHigh.getText().toString()),
                            Integer.parseInt(txtCLow.getText().toString()));
                    DValues = GRc.verifyDvalues(
                            Integer.parseInt(txtDHigh.getText().toString()),
                            Integer.parseInt(txtDLow.getText().toString()));
                    EValues = GRc.verifyEvalues(
                            Integer.parseInt(txtEHigh.getText().toString()),
                            Integer.parseInt(txtELow.getText().toString()));
                    FValues = GRc.verifyFvalues(
                            Integer.parseInt(txtFHigh.getText().toString()),
                            Integer.parseInt(txtFLow.getText().toString()));
                    AtoB = GRc.verifyAtoB(
                            Integer.parseInt(txtALow.getText().toString()),
                            Integer.parseInt(txtBHigh.getText().toString()));
                    BtoC = GRc.verifyBtoC(
                            Integer.parseInt(txtBLow.getText().toString()),
                            Integer.parseInt(txtCHigh.getText().toString()));
                    CtoD = GRc.verifyCtoD(
                            Integer.parseInt(txtCLow.getText().toString()),
                            Integer.parseInt(txtDHigh.getText().toString()));
                    DtoE = GRc.verifyDtoE(
                            Integer.parseInt(txtDLow.getText().toString()),
                            Integer.parseInt(txtEHigh.getText().toString()));

                    if (!EisF) {

                        EtoF = GRc
                                .verifyEtoF(Integer.parseInt(txtEHigh
                                                .getText().toString()),
                                        Integer.parseInt(txtELow.getText()
                                                .toString()), Integer
                                                .parseInt(txtFHigh.getText()
                                                        .toString()), Integer
                                                .parseInt(txtFLow.getText()
                                                        .toString()),
                                        GradeRange.this);
                        F0 = GRc.verifyF0(Integer.parseInt(txtFLow.getText()
                                .toString()));

                    } else {

                        F0 = GRc.verifyF0(Integer.parseInt(txtELow.getText()
                                .toString()));

                    }

                    if (!A100) {
                        txtAHigh.setError("");
                        errorCheck++;
                    }

                    if (!AValues) {
                        txtAHigh.setError("");
                        txtALow.setError("");
                        errorCheck++;
                    }

                    if (!BValues) {
                        txtBHigh.setError("");
                        txtBLow.setError("");
                        errorCheck++;
                    }

                    if (!CValues) {
                        txtCHigh.setError("");
                        txtCLow.setError("");
                        errorCheck++;
                    }

                    if (!DValues) {
                        txtDHigh.setError("");
                        txtDLow.setError("");
                        errorCheck++;
                    }

                    if (!EValues) {
                        txtEHigh.setError("");
                        txtELow.setError("");
                        errorCheck++;
                    }

                    if (!FValues) {
                        txtFHigh.setError("");
                        txtFLow.setError("");
                        errorCheck++;
                    }

                    if (!AtoB) {
                        txtALow.setError("");
                        txtBHigh.setError("");
                        errorCheck++;
                    }

                    if (!BtoC) {
                        txtBLow.setError("");
                        txtCHigh.setError("");
                        errorCheck++;
                    }

                    if (!CtoD) {
                        txtCLow.setError("");
                        txtDHigh.setError("");
                        errorCheck++;
                    }

                    if (!DtoE) {
                        txtDLow.setError("");
                        txtEHigh.setError("");
                        errorCheck++;
                    }

                    if (!EisF) {

                        if (!EtoF) {
                            txtELow.setError("");
                            txtFHigh.setError("");
                            errorCheck++;
                        }

                        if (!F0) {
                            txtFLow.setError("");
                            errorCheck++;
                        }

                    } else {

                        if (!F0) {
                            txtELow.setError("");
                            errorCheck++;
                        }

                    }

                    if (errorCheck == 0) {
                        {


                            editor.putString(Utils.AHigh_Key, txtAHigh.getText()
                                    .toString());
                            editor.putString(Utils.ALow_Key, txtALow.getText()
                                    .toString());
                            editor.putString(Utils.BHigh_Key, txtBHigh.getText()
                                    .toString());
                            editor.putString(Utils.BLow_Key, txtBLow.getText()
                                    .toString());
                            editor.putString(Utils.CHigh_Key, txtCHigh.getText()
                                    .toString());
                            editor.putString(Utils.CLow_Key, txtCLow.getText()
                                    .toString());
                            editor.putString(Utils.DHigh_Key, txtDHigh.getText()
                                    .toString());
                            editor.putString(Utils.DLow_Key, txtDLow.getText()
                                    .toString());
                            editor.putString(Utils.EHigh_Key, txtEHigh.getText()
                                    .toString());
                            editor.putString(Utils.ELow_Key, txtELow.getText()
                                    .toString());

                            if (cbxEisF.isChecked()) {

                                editor.putString(Utils.FHigh_Key, txtEHigh
                                        .getText().toString());
                                editor.putString(Utils.FLow_Key,
                                        txtELow.getText().toString());
                                editor.putBoolean(Utils.EisF_Key, true);

                            } else {

                                editor.putString(Utils.FHigh_Key, txtFHigh
                                        .getText().toString());
                                editor.putString(Utils.FLow_Key,
                                        txtFLow.getText().toString());
                                editor.putBoolean(Utils.EisF_Key, false);

                            }

                            finish();

                        }

                    }
            }
        }
    };

    private void loadSavedPreferences() {

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

        txtAHigh.setText(AHigh);
        txtALow.setText(ALow);
        txtBHigh.setText(BHigh);
        txtBLow.setText(BLow);
        txtCHigh.setText(CHigh);
        txtCLow.setText(CLow);
        txtDHigh.setText(DHigh);
        txtDLow.setText(DLow);
        txtEHigh.setText(EHigh);
        txtELow.setText(ELow);
        txtFHigh.setText(FHigh);
        txtFLow.setText(FLow);

        if (EisF) {
            cbxEisF.setChecked(true);
            txtFLow.setVisibility(View.GONE);
            txtFHigh.setVisibility(View.GONE);
            lblF.setVisibility(View.GONE);
            lblE.setText(R.string.lblEF);
        } else {
            cbxEisF.setChecked(false);
            txtFLow.setVisibility(View.VISIBLE);
            txtFHigh.setVisibility(View.VISIBLE);
            lblF.setVisibility(View.VISIBLE);
        }

        String backGround = prefs.getString(Utils.BGround_Key,
                Utils.BGround_Default);

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

}
