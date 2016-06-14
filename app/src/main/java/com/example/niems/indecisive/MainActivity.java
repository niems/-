package com.example.niems.indecisive;

import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Button> all_choices = new ArrayList<>(); //holds all user options
    private View.OnClickListener button_listener;
    private Dialog add_option_dialog; //used to read the option in from the user
    private static boolean first_run = false; //true after the first execution

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        try{
            /*
            if(!first_run){
                this.button_listener = new View.OnClickListener(){
                    public void onClick(View v){
                        addOption(v);
                    }
                };

                first_run = true;
            } */

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
            LinearLayout layout = (LinearLayout) findViewById( R.id.layout_main );
            EditText text = (EditText) this.add_option_dialog.findViewById( R.id.add_option_dialog );

            Button new_option = new Button(this);
            String option_text = text.getText().toString();

            new_option.setId( View.generateViewId() );
            new_option.setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT ) );
            new_option.setText( option_text );
            new_option.setTextColor( getResources().getColor( R.color.colorPrimaryText ) );
            new_option.setTextSize(18);
            new_option.setBackgroundResource( R.drawable.current_options_main );
            new_option.setOnClickListener(this.button_listener);

            all_choices.add( new_option );
            layout.addView( new_option );

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
}
