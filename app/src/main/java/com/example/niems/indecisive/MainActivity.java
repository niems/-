package com.example.niems.indecisive;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //public static ArrayList<Button> all_choices = new ArrayList<>(); //holds all user options
    public static ArrayList<String> all_choices_string = new ArrayList(); //holds all user options as strings
    private View.OnClickListener button_listener;
    private Dialog add_option_dialog; //used to read the option in from the user
    private static boolean first_run = false; //true after the first execution

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        try{

            //new listview stuff
            String [] list_test = {"test 1", "test 2", "last test" };
            ArrayAdapter<String> list_adapter = new ArrayAdapter<String>(this, R.layout.option_listview_main, all_choices_string);
            ListView options_view = (ListView) findViewById( R.id.option_list );
            options_view.setAdapter( list_adapter );


            Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar_main );
            setSupportActionBar( toolbar );
            toolbar.setTitle(" || ");

           Window window = getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor( getResources().getColor( R.color.colorNotificationBar ) );

        }catch(Exception e){
            Toast.makeText(this, "Error: MainActivity - onCreate()", Toast.LENGTH_SHORT).show();
        }


    }

    public void dialogCancel( View view ){
        this.add_option_dialog.dismiss();
    }

    public void dialogConfirm( View view ){

        try{
            //LinearLayout layout = (LinearLayout) findViewById( R.id.layout_main );
            EditText text = (EditText) this.add_option_dialog.findViewById( R.id.add_option_dialog );

            String option_text = text.getText().toString().toUpperCase();
            all_choices_string.add( option_text );

            this.add_option_dialog.dismiss();

        }catch(Exception e){
            Toast.makeText(this, "Error: MainActivity - dialogConfirm()", Toast.LENGTH_SHORT).show();
        }
    }

    public void addOption(View view){

        try{
            //Button new_option = new Button(this);
            //new_option.setId( View.generateViewId() );

            this.add_option_dialog = new Dialog(this);
            this.add_option_dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
            this.add_option_dialog.setContentView( R.layout.add_option_dialog );

            this.add_option_dialog.show();

        }catch(Exception e){
            Toast.makeText(this, "Error: MainActivity - addOption()", Toast.LENGTH_SHORT).show();
        }
    }

    public void decision( View view ){
        try{
            int min = 0;
            int max = all_choices_string.size() - 1;

            if(max >= 0 ){
                int selection = min + (int)( Math.random() * ((max - min) + 1) );

                Toast toast = Toast.makeText( getApplicationContext(), all_choices_string.get(selection), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                //Toast.makeText(this, all_choices_string.get( selection ), Toast.LENGTH_LONG).show();

                /*
                Toast decision = new Toast( getApplicationContext() );


                decision.setText( all_choices_string.get( selection ) );
                decision.setGravity(Gravity.CENTER, 0, 0);

                decision.show();
                */
            }

            else{
                Toast.makeText(this, "Do not ask me to decide when you have not decided yourself", Toast.LENGTH_LONG).show();
            }

        }catch(Exception e){
            Toast.makeText(this, "Error: MainActivity - decision()", Toast.LENGTH_SHORT).show();
        }
    }
}
