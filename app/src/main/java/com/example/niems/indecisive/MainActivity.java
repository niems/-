package com.example.niems.indecisive;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Button> all_choices = new ArrayList<>(); //holds all user options
    private View.OnClickListener button_listener;
    //public static  Toolbar toolbar_main = (Toolbar) findViewById()


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        try{

            getFilesDir();

           // Toolbar toolbar = (Toolbar) findViewById( R.id.title_main );
           // setSupportActionBar( toolbar );
           // toolbar.setTitle("Words");
           // toolbar.setLogo( R.drawable.mind_map );

        }catch(Exception e){
            Toast.makeText(this, "Error: MainActivity - onCreate()", Toast.LENGTH_SHORT).show();
        }


    }

    public void addOption(View view){
        Button new_option = new Button(this);



    }
}
