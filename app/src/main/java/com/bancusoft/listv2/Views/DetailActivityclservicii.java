package com.bancusoft.listv2.Views;


import static com.bancusoft.listv2.Helpers.Utils.app_google;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bancusoft.listv2.Helpers.Utils;
import com.bancusoft.listv2.R;
import com.bancusoft.listv2.Retrofit.Cl_servicii;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class DetailActivityclservicii extends AppCompatActivity{

    private TextView CODUL_TV_CSPM, DENUMIRE_TV_CSPM;

    private Cl_servicii receivedCl_servicii;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;


    /**
     * Let's initialize our widgets
     */
    private void initializeWidgets(){
        CODUL_TV_CSPM = findViewById(R.id.CODUL_TV_CSPM);
        DENUMIRE_TV_CSPM = findViewById(R.id.DENUMIRE_TV_CSPM);


        mCollapsingToolbarLayout=findViewById(R.id.mCollapsingToolbarLayoutcspm);
    }



    /**
     * We will now receive and show our data to their appropriate views.
     */
    private void receiveAndShowData(){

        receivedCl_servicii  = Utils.receiveCl_servicii(getIntent(),DetailActivityclservicii.this);

        if(receivedCl_servicii != null){
            CODUL_TV_CSPM.setText(receivedCl_servicii.getCODUL());
            DENUMIRE_TV_CSPM.setText(receivedCl_servicii.getDENUMIRE());

            mCollapsingToolbarLayout.setTitle(receivedCl_servicii.getCODUL());
            mCollapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));

        }

    }

    /**
     * Let's inflate our menu for the detail page
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_page_menu_servicii_cl, menu);
        return true;
    }


    /**
     * When a menu item is selected we want to navigate to the appropriate page
     */


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.action_edit_cl_servicii){
            Utils.sendCl_serviciiToActivity(this,receivedCl_servicii,help_vw.class);
            finish();
            return true;

        }
        else

        if (id==R.id.action_edit_en_cl_servicii){
            Utils.sendCl_serviciiToActivity(this,receivedCl_servicii,help_vw_en.class);
            finish();
            return true;

        }
        else


        if (id==R.id.action_edit_ru_cl_servicii){
            Utils.sendCl_serviciiToActivity(this,receivedCl_servicii,help_vw_ru.class);
            finish();
            return true;

        }
        else

        if (id == android.R.id.home){

            Intent intent;
            intent = new Intent(this,ScientistsActivityclservicii.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);
            return true;

        }

        else
        if (id == R.id.video3_cl_servicii) {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(com.bancusoft.listv2.Helpers.Utils.youtube_level_stat));
            startActivity(browserIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * Let's once again override the attachBaseContext. We do this for our
     * Calligraphy library
     */

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cspm);

        CODUL_TV_CSPM = findViewById(R.id.CODUL_TV_CSPM);
        DENUMIRE_TV_CSPM = findViewById(R.id.DENUMIRE_TV_CSPM);


        Button mBtnShare = findViewById(R.id.btnShare_cspm);

        mBtnShare.setOnClickListener(view -> {
            String s_CODUL_TV_CSPM = CODUL_TV_CSPM.getText().toString();
            String s_DENUMIRE_TV_CSPM = DENUMIRE_TV_CSPM.getText().toString();


            String contentShare = " Codul servicii : " + s_CODUL_TV_CSPM + " - Denumirea codului, \n" +
                    "  :  " + s_DENUMIRE_TV_CSPM

                    + " -- The application -Level Stat - can be downloaded from here "
                    +  app_google;


            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");

            sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Clasificatorul serviciilor în domeniul activităţii economice externe");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, contentShare);

            startActivity(Intent.createChooser(sharingIntent, "Share text via"));
        });

//-------------

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(DetailActivityclservicii.this, ScientistsActivityclservicii.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
            }
        });

//----------------

        initializeWidgets();
        receiveAndShowData();
    }
}
