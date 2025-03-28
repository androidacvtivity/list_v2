package com.bancusoft.listv2.Views;
import static com.bancusoft.listv2.Helpers.Utils_2.showInfoDialog_help_en_vw_3;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.bancusoft.listv2.Retrofit.Scientistvw;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.Toolbar;

import com.bancusoft.listv2.Helpers.Utils;
import com.bancusoft.listv2.R;
public class help_vw_en extends BaseActivity{

    private Scientistvw receivedScientist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_envw);

        Toolbar toolbar = findViewById(R.id.aboutUsToolBarn_en_vw);
        setSupportActionBar(toolbar);

//        findViewById(R.id.mBackArrowh_en).setOnClickListener(v -> finish());

        findViewById(R.id.mBackArrowh_en).setOnClickListener( v -> showInfoDialog_help_en_vw_3(this,
                "Attention!", "Are you sure you want to exit? We have translate help in romanian and russian.") );

        // Register the onBackPressed callback
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Show the info dialog
                showInfoDialog_help_en_vw_3(help_vw_en.this,
                        "Attention", "Are you sure you want to exit? We have translate help in romanian and russian.");
            }
        };

        // Add the callback to the dispatcher
        getOnBackPressedDispatcher().addCallback(this, callback);

    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.helpvw, menu);
        return true;

    }

    /**
     * When a menu item is selected we want to navigate to the appropriate page
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        int id = item.getItemId();

        if (id==R.id.rolang_vw){
            Utils.sendScientistvwToActivity(this,receivedScientist,help_vw.class);
            finish();
            return true;
        }
        else

        if (id==R.id.enlang_vw){
            Utils.sendScientistvwToActivity(this,receivedScientist,help_vw_en.class);
            finish();
            return true;

        }

        else
        if (id==R.id.rulang_vw){
            Utils.sendScientistvwToActivity(this,receivedScientist,help_vw_ru.class);
            finish();
            return true;
        }


        else

        if (id == android.R.id.home){

            Intent intent;
            intent = new Intent(this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);
            return true;

        }

        else
        if (id == R.id.link1_vw) {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(com.bancusoft.listv2.Helpers.Utils.youtube_level_stat));

            startActivity(browserIntent);


        }


        return super.onOptionsItemSelected(item);
    }

    public void setReceivedScientist(Scientistvw receivedScientist) {
        this.receivedScientist = receivedScientist;
    }
}
