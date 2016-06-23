package com.example.niems.indecisive;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static boolean erase_all = false; //if true, all options are erased
    public static ArrayList<String> all_choices_string = new ArrayList(); //holds all user options as strings
    private View.OnClickListener button_listener;
    private Dialog add_option_dialog; //used to read the option in from the user
    private ArrayAdapter<String> list_adapter;

    //animations
    private Animation animation_fade_out;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        try{

            //new listview stuff
            this.list_adapter = new ArrayAdapter<String>(this, R.layout.option_listview_main, all_choices_string);
            ListView options_view = (ListView) findViewById( R.id.option_list );
            options_view.setAdapter( this.list_adapter );


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

            //animation setup
            this.animation_fade_out = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.fade_out );

            this.animation_fade_out.setAnimationListener( new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    MainActivity.erase_all = true; //all options should be erased
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }catch(Exception e){
            Toast.makeText(this, "Error: MainActivity - onCreate()", Toast.LENGTH_SHORT).show();
        }


    }

    protected void onResume(){
        super.onResume();

        if( MainActivity.erase_all ){
            all_choices_string.clear();
            this.list_adapter.notifyDataSetChanged(); //removes all options from view

            MainActivity.erase_all = false; //reset

            Toast.makeText(this, "All words cleared", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.toolbar_menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        try{
            int id = item.getItemId();

            if(id == R.id.toolbar_erase_all){
                ListView listview = (ListView) findViewById( R.id.option_list );
                //Intent intent = new Intent(this, MainActivity.class);
               //finish(); //ends current activity
                listview.startAnimation( this.animation_fade_out ); //starts the fade out animation

            }


        }catch(Exception e){
            Toast.makeText(this, "Error: MainActivity - onOptionsItemsSelected()", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public void dialogCancel( View view ){
        this.add_option_dialog.dismiss();
    }

    public void dialogConfirm( View view ){

        try{
            ListView listview = (ListView) findViewById( R.id.option_list );
            EditText text = (EditText) this.add_option_dialog.findViewById( R.id.add_option_dialog );

            String option_text = text.getText().toString().toUpperCase();
            all_choices_string.add( option_text );

            this.add_option_dialog.dismiss();
            Animation fade_in_animation = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.fade);
            listview.setSelection( this.list_adapter.getCount() - 1);
            listview.startAnimation( fade_in_animation );
           // listview.getChildAt( all_choices_string.size() - 1 ).startAnimation( fade_in_animation );


        }catch(Exception e){
            Toast.makeText(this, "Error: MainActivity - dialogConfirm()", Toast.LENGTH_SHORT).show();
        }
    }

    public void addOption(View view){

        try{

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
            }

            else{
                Toast.makeText(this, "Do not ask me to decide when you have not decided yourself", Toast.LENGTH_LONG).show();
            }

        }catch(Exception e){
            Toast.makeText(this, "Error: MainActivity - decision()", Toast.LENGTH_SHORT).show();
        }
    }
}
