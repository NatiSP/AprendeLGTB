package com.playical.aprendelgtb;

import com.playical.aprendelgtb.actividades.CreacionDePersonaje;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Eleccion extends ActionBarActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elegir);
		
        Bundle b = getIntent().getExtras();
        int value = b.getInt("election", 1);

        final String PREFS_NAME = "MisPreferencias";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final SharedPreferences.Editor editor = settings.edit();
        final int actualExp = settings.getInt("exp", 0);
        int expEarned = 0;
        final String nombre = settings.getString("nombre","Pelusa");
        int animal = settings.getInt("animal", -1);
        
        ImageButton imagebutton1 = (ImageButton) findViewById(R.id.imageButtonChoice1);
        ImageButton imagebutton2 = (ImageButton) findViewById(R.id.imageButtonChoice2);
        ImageView animalView = (ImageView) findViewById(R.id.imageView1);
        TextView texto = (TextView) findViewById(R.id.textView1);
        
        switch(animal){
        case 0:{
        	animalView.setBackgroundResource(R.drawable.gato_1);
        	break;
        }
        case 1:{
        	animalView.setBackgroundResource(R.drawable.perro_1);
        	break;
        }
    }
        
        switch(value){
	        case 1:{
	        	imagebutton1.setBackgroundResource(R.drawable.car_toy);
	        	imagebutton2.setBackgroundResource(R.drawable.doll_house);
	        	break;
	        }
	        case 2:{
	        	texto.setText("¿Quién es tu pareja?");
	        	imagebutton2.setBackgroundResource(R.drawable.maxi);
	        	imagebutton1.setBackgroundResource(R.drawable.kira);
	        	break;
	        }
        }
        
        final int election = value;
        
        imagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Eleccion.this);
                builder.setMessage("¡Una vez tomes esta decisión no la podrás cambiar!")
                        .setTitle("¿Confirmar elección?")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            	int exp = 150;
                            	if(election == 1){
                                    editor.putString("juguete", "el coche teledirigido");
                                    editor.putInt("cap_actual", 3);
                                    exp = 150;
                            	}
                            	else if(election == 2){
                                    editor.putString("gusta", "Kira");
                                    editor.putString("orientacion", "las chicas");
                                    editor.putString("orientacion_contraria", "los chicos");
                                    editor.putInt("cap_actual", 4);	
                                    exp = 250;
                            	}

                            	editor.putInt("exp", actualExp + exp);	
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "¡Has ganado " + exp + " EXP!", Toast.LENGTH_LONG).show();
                                //Toast.makeText(getApplicationContext(), "¡Has desbloqueado el objeto 'X'!", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                AlertDialog newFragment = builder.create();

                newFragment.show();
            }
        });
        
        imagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Eleccion.this);
                builder.setMessage("¡Una vez tomes esta decisión no la podrás cambiar!")
                        .setTitle("¿Confirmar elección?")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        	int exp = 150;
                            public void onClick(DialogInterface dialog, int id) {
                            	if(election == 1){
                                    editor.putString("juguete", "la casa de muñecas");
                                    editor.putInt("cap_actual", 3);
                                    exp = 150;
                            	}
                            	else if(election == 2){
                                    editor.putString("gusta", "Maxi");
                                    editor.putString("orientacion", "los chicos");
                                    editor.putString("orientacion_contraria", "las chicas");
                                    editor.putInt("cap_actual", 4);	
                                    exp = 250;
                            	}
                            	editor.putInt("exp", actualExp + exp);	
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "¡Has ganado " + exp + " EXP!", Toast.LENGTH_LONG).show();
                                //Toast.makeText(getApplicationContext(), "¡Has desbloqueado el objeto 'Y'!", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
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
		getMenuInflater().inflate(R.menu.eleccion, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
