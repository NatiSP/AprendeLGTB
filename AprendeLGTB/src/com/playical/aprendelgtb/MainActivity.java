package com.playical.aprendelgtb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.playical.aprendelgtb.menus.Creditos;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton BotonComenzar = (ImageButton) findViewById(R.id.ButtonStart);
        final ImageButton BotonPerfil = (ImageButton) findViewById(R.id.ButtonProfile);
        final ImageButton BotonCreditos = (ImageButton) findViewById(R.id.ButtonCredits);
        
		setVolumeControlStream(AudioManager.STREAM_MUSIC);

        final String PREFS_NAME = "MisPreferencias";
        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final SharedPreferences.Editor editor = settings.edit();
//        editor.clear();
//        editor.commit();

        BotonComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	playSound(R.raw.click);
                startActivity(new Intent(MainActivity.this, ListaDeTemas.class));
            }
        });
        
        BotonComenzar.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == (MotionEvent.ACTION_UP)){
                    //Do whatever you want after press
                	BotonComenzar.setBackgroundResource(R.drawable.boton_comenzar);
                }
                else{
                	BotonComenzar.setBackgroundResource(R.drawable.boton_comenzar_pulsado);
                }
                return false;
            }
        });
        
        BotonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	playSound(R.raw.click);
                startActivity(new Intent(MainActivity.this, PerfilActivity.class));
            }
        });
        
        BotonPerfil.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == (MotionEvent.ACTION_UP)){
                    //Do whatever you want after press
                	BotonPerfil.setBackgroundResource(R.drawable.boton_perfil);
                }
                else{
                	BotonPerfil.setBackgroundResource(R.drawable.boton_perfil_pulsado);
                }
                return false;
            }
        });
        
        BotonCreditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	playSound(R.raw.click);
                startActivity(new Intent(MainActivity.this, Creditos.class));
            }
        });
        
        BotonCreditos.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == (MotionEvent.ACTION_UP)){
                    //Do whatever you want after press
                	BotonCreditos.setBackgroundResource(R.drawable.boton_creditos);
                }
                else{
                	BotonCreditos.setBackgroundResource(R.drawable.boton_creditos_pulsado);
                }
                return false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
	public void playSound(int soundId)
	{
		MediaPlayer player = MediaPlayer.create(this, soundId);
		player.start();
	}
}
