package com.itemhunter.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Kyle on 15/06/2014.
 */
public class Profile extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public void backToFeed(View view){
        Intent homeIntent = new Intent(this, Home.class);
        startActivity(homeIntent);
    }

    public void showPword(View view){
        RelativeLayout showPass = (RelativeLayout)findViewById(R.id.changePassLay);
        TextView imgB = (TextView)findViewById(R.id.change_email);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)imgB.getLayoutParams();
        if(showPass.isShown()) {
            showPass.setVisibility(View.GONE);
            view.setBackgroundResource(R.drawable.plussign);
            params.setMargins(0,25,0,0);
            imgB.setLayoutParams(params);
        }
        else{
            showPass.setVisibility(View.VISIBLE);
            view.setBackgroundResource(R.drawable.negsign);
            params.setMargins(0,75,0,0);
            imgB.setLayoutParams(params);
        }
    }

    public void showEmail(View view){
        RelativeLayout showEmai = (RelativeLayout)findViewById(R.id.changeEmaiLay);
        TextView imgB = (TextView)findViewById(R.id.change_location);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)imgB.getLayoutParams();
        if(showEmai.isShown()){
            showEmai.setVisibility(View.GONE);
            view.setBackgroundResource(R.drawable.plussign);
            params.setMargins(0,25,0,0);
            imgB.setLayoutParams(params);
        }
        else{
            showEmai.setVisibility(View.VISIBLE);
            view.setBackgroundResource(R.drawable.negsign);
            params.setMargins(0,75,0,0);
            imgB.setLayoutParams(params);
        }
    }

    public void showLoca(View view){
        RelativeLayout showLoca = (RelativeLayout)findViewById(R.id.changeLocaLay);
        if(showLoca.isShown()){
            showLoca.setVisibility(View.GONE);
            view.setBackgroundResource(R.drawable.plussign);
        }
        else{
            showLoca.setVisibility(View.VISIBLE);
            view.setBackgroundResource(R.drawable.negsign);
        }
    }

    public void changePassword(View view){
        EditText ed = (EditText)findViewById(R.id.changePassText);
        String input = ed.getText().toString();
        System.out.println(input);
        //Hash the input string, save to db and update sharedpreferences
    }

    public void changeEmail(View view){
        EditText ed = (EditText)findViewById(R.id.changeEmaiText);
        String input = ed.getText().toString();
        System.out.println(input);
        //Set the input string in db and sharedpreferences
    }

    public void changeLocation(View view){
        EditText ed = (EditText)findViewById(R.id.changeLocaText);
        String input = ed.getText().toString();
        System.out.println(input);
        //Set the input string in db and sharedpreferences
    }

}
