package com.HishaTech.android.teachercalc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Access various widgets in Application.
        TextView About_first = (TextView) findViewById(R.id.About_first);
        TextView About_second = (TextView) findViewById(R.id.About_second);
        TextView About_PlayStore = (TextView) findViewById(R.id.About_PlayStore);

        // Combine appname and appversion for the label
        String appName = getString(R.string.app_name);
        String appVersion = getString(R.string.app_version);
        String copyRight = getString(R.string.app_copyright);

        About_first.setText(appName + " v" + appVersion);
        About_second.setText(copyRight);

        About_PlayStore.setClickable(true);
        About_PlayStore.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='" + getString(R.string.hishatechPlayStoreURL)
                + "'> " + getString(R.string.hishatechString) +
                " </a>";
        About_PlayStore.setText(Html.fromHtml(text));

    }

}