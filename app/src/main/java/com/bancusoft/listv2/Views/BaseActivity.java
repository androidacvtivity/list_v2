package com.bancusoft.listv2.Views;
import androidx.appcompat.app.AppCompatActivity;

import com.bancusoft.listv2.Helpers.Utils;



public class BaseActivity  extends AppCompatActivity {
    protected void show(String message){
        Utils.show(this,message);
    }
}

