package com.playical.aprendelgtb.actividades;

import java.util.ArrayList;

import com.playical.aprendelgtb.actividades.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.playical.aprendelgtb.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */

public class CreacionDePersonaje extends Activity {
	
    int animalSelected = 0;
    int LIMITE_SUPERIOR = 1;
    int LIMITE_INFERIOR = 0;
    String genderSelected;
    
    ImageButton animalIcon;
    ImageButton leftArrow;
    ImageButton rightArrow;
    ImageButton masButton;
    ImageButton femButton;
    
    ArrayList<Drawable> icons = new ArrayList<Drawable>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_creacion_de_personaje);

        final String PREFS_NAME = "MisPreferencias";
        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final SharedPreferences.Editor editor = settings.edit();

        //final EditText nombre = (EditText) findViewById(R.id.editUser);
        Button okButton = (Button) findViewById(R.id.Aceptar);
        animalIcon = (ImageButton) findViewById(R.id.imageIcon);
        leftArrow = (ImageButton) findViewById(R.id.leftButton);
        rightArrow = (ImageButton) findViewById(R.id.rightButton);
        
        masButton = (ImageButton) findViewById(R.id.imageButtonMas);
        femButton = (ImageButton) findViewById(R.id.imageButtonFem);
             
        icons.add(getResources().getDrawable( R.drawable.gato_1 ));
        icons.add(getResources().getDrawable( R.drawable.perro_1 ));
               
        animalIcon.setOnClickListener(new View.OnClickListener() {
        	
            @Override
            public void onClick(View v) {
//            	playAnimalSound();
            }

        });
        
        leftArrow.setOnClickListener(new View.OnClickListener() {
        	
            @Override
            public void onClick(View v) {
            	changeSelection(-1);
            	refreshIcon();
            }
        });
        
        rightArrow.setOnClickListener(new View.OnClickListener() {
        	
            @Override
            public void onClick(View v) {
            	changeSelection(1);
            	refreshIcon();
            }
        });
        
        masButton.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
            	changeGender(1);
            	masButton.setScaleX(1.2f);
            	masButton.setScaleY(1.2f);
            	femButton.setScaleX(0.8f);
            	femButton.setScaleY(0.8f);
            }
        });
        
        femButton.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
            	changeGender(0);
            	femButton.setScaleX(1.2f);
            	femButton.setScaleY(1.2f);
            	masButton.setScaleX(0.8f);
            	masButton.setScaleY(0.8f);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(CreacionDePersonaje.this);
                builder.setMessage("¡Una vez creado tu personaje no lo podrás cambiar! ¿Quieres dejarlo así?")
                        .setTitle("¿Confirmar personaje?")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            	EditText nombre = (EditText) findViewById(R.id.editUser);
                            	
                                editor.putString("usuario", nombre.getText().toString());
                                editor.putInt("progresion_actual", 1);
                                editor.putInt("exp", 150);
                                editor.putInt("animal", animalSelected);
                                editor.putBoolean("logro_1", true);
//                                editor.putBoolean("logro_2", true);
//                                editor.putBoolean("logro_3", true);
                                if(animalSelected == 0){
                                	editor.putString("raza", "gato");
                                }
                                else if(animalSelected == 1){
                                	editor.putString("raza", "perro");
                                }
                                if(genderSelected.equalsIgnoreCase("femenino")){
                                	editor.putString("sexo", "chica");
                                }
                                else if(genderSelected.equalsIgnoreCase("masculino")){
                                	editor.putString("sexo", "chico");
                                }
                                editor.apply();
                                showLevelUpDialog("¡Completado!", "¡Has ganado 150 EXP!", false);
                                
//                                Toast.makeText(getApplicationContext(), "¡Has ganado 150 EXP!", Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(), "¡Has desbloqueado el logro 'Protagonista'!", Toast.LENGTH_LONG).show();
//                                finish();
                            }
                        })
                        .setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                AlertDialog newFragment = builder.create();

                newFragment.show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_capitulo_uno, menu);
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

    public void playAnimalSound() {
		if(animalIcon.getDrawable().equals(icons.get(0))){
			playSound(R.raw.cat_meow);
		}
		else if(animalIcon.getDrawable().equals(icons.get(1))){
			playSound(R.raw.dog_bark);
		}
	}

	public void playSound(int soundId){
		MediaPlayer player = MediaPlayer.create(this, soundId);
		player.start();
	}
	
	public void changeSelection(int i){
		if(i == -1){
			if(animalSelected > LIMITE_INFERIOR)
				animalSelected = animalSelected + i;
		}
		else if(i == 1){
			if(animalSelected < LIMITE_SUPERIOR)
				animalSelected = animalSelected + i;
		}
		
	}
	
	public void changeGender(int i){
		if(i == 0){
			genderSelected = "femenino";
		}
		else if(i == 1){
			genderSelected = "masculino";
		}
		
	}

	public void refreshIcon(){
		animalIcon.setBackgroundDrawable(icons.get(animalSelected));
	}
	
	public void showLevelUpDialog(String title, String msg, final boolean finish){

		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
        dialogo1.setTitle(title);  
        dialogo1.setMessage(msg);            
        dialogo1.setCancelable(false);  
        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialogo1, int id) {  
            	if(finish)
            		finish();
            	else
            		showLevelUpDialog("¡Logro conseguido!", "¡Has desbloqueado el logro 'Protagonista'!", true);
            }  
        });             
        dialogo1.show();      
	}
	


}
