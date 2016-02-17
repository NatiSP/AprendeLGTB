package com.playical.aprendelgtb;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;


public class PerfilActivity extends Activity {

    public static final String PREFS_NAME = "MisPreferencias";
    
    ArrayList<Drawable> icons = new ArrayList<Drawable>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Get preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String user = settings.getString("usuario", "Desconocido");
        int nivel = settings.getInt("nivel", 1);
        int exp = settings.getInt("exp", 0);
        int animalSelected = settings.getInt("animal", -1);
        int maxExp = calcularMaxExp(nivel);
        boolean logros[] = obtenerLogros();


        TextView userText = (TextView) findViewById(R.id.textUserName);
        TextView userLevel = (TextView) findViewById(R.id.textLevel);
        TextView userExp = (TextView) findViewById(R.id.textExp);
        
        ImageView profile = (ImageView) findViewById(R.id.imageProfile);
        
        ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar1);
        
        icons.add(getResources().getDrawable( R.drawable.gato_1 ));
        icons.add(getResources().getDrawable( R.drawable.perro_1 ));
        
        if (animalSelected != -1)
        	profile.setBackgroundDrawable(icons.get(animalSelected));

        userText.setText(user);
        userLevel.setText("Nivel: " + nivel);
        userExp.setText(exp + " EXP");
        
        mProgress.setMax(maxExp - calcularMaxExp(nivel - 1));
        mProgress.setProgress(exp - calcularMaxExp(nivel - 1));

        Button BotonTerminar = (Button) findViewById(R.id.buttonBack);

        BotonTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        Button Reset = (Button) findViewById(R.id.buttonReset);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	AlertDialog.Builder builder = new AlertDialog.Builder(PerfilActivity.this);
                builder.setMessage("¡Una vez tomes esta decisión no la podrás cambiar!")
                        .setTitle("¿Confirmar borrado de perfil?")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        	
                            public void onClick(DialogInterface dialog, int id) {
                            	final String PREFS_NAME = "MisPreferencias";
                                final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                                final SharedPreferences.Editor editor = settings.edit();
                                editor.clear();
                                editor.commit();
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


    private int calcularMaxExp(int nivel) {
		
    	return (int) (200*Math.pow((nivel),2));
	}
    
    private boolean[] obtenerLogros(){
    	boolean[] result = new boolean[3];
    	// Get preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        for(int i = 0; i < 3; i++){
        	result[i] = settings.getBoolean("logro_" + (i+1), false);
        }
    	
        View view = (View) findViewById(R.layout.activity_perfil); //the layout you set in `setContentView()`
        LinearLayout picLL = (LinearLayout) findViewById(R.id.logrosLayout);
        picLL.layout(0, 0, 0, 0);
        picLL.setLayoutParams(new LayoutParams(400, 100));
        picLL.setOrientation(LinearLayout.HORIZONTAL);
        for(int i = 0; i < 3; i++){
        	if(result[i] == true){
		        ImageView myImage = new ImageView(this);
		        switch(i){
		        case 0:{
		        	myImage.setBackgroundResource(R.drawable.medalla_azul);
		        	break;
		        }
		        case 1:{
		        	myImage.setBackgroundResource(R.drawable.medalla_morada);
		        	break;
		        }
		        case 2:{
		        	myImage.setBackgroundResource(R.drawable.medalla_verde);
		        	break;
		        }
		        }
		        myImage.setLayoutParams(new LayoutParams(60, 100));
		        picLL.addView(myImage);
        	}
        }
    	
    	return result;
    }


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
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
    
    
}
