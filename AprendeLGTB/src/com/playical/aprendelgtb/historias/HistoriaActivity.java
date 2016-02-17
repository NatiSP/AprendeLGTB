package com.playical.aprendelgtb.historias;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.playical.aprendelgtb.R;

public class HistoriaActivity extends ActionBarActivity {
	
	int nivel;
	String nombre = "el/la protagonista";
	String orientacion_contraria = "los chicos";
    String orientacion = "las chicas";
	final String PREFS_NAME = "MisPreferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        final int value = b.getInt("chap");
        if(value == 7){
        	 setContentView(R.layout.activity_historia_especial);
        }
        else
        	setContentView(R.layout.activity_historia);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final SharedPreferences.Editor editor = settings.edit();
        final int actualExp = settings.getInt("exp", 0);
        nivel = settings.getInt("nivel", 1);
        int expEarned = 0;
        String raza = settings.getString("raza","gato");
        String sexo = settings.getString("sexo","chico");
        nombre = settings.getString("usuario","Pelusa");
        String juguete = settings.getString("juguete","el coche teledirigido");
        String pareja = settings.getString("gusta","Maxi");
        orientacion_contraria = settings.getString("orientacion_contraria","los chicos");
        orientacion = settings.getString("orientacion","las chicas");

        TextView texto = (TextView) findViewById(R.id.textContenidoCapitulo);
        TextView textCap = (TextView) findViewById(R.id.textCapitulo);
        
        Button finish = (Button) findViewById(R.id.buttonFinish);

        switch(value){
            case 1: {
                String textHistory = getString(R.string.capitulo_1_historia_1, nombre, raza, sexo);
                texto.setText(textHistory);
                textCap.setText("Capítulo 1");
                expEarned = 100;
                break;
            }
            case 2: {
                String textHistory = getString(R.string.capitulo_2_historia_1, nombre);
                texto.setText(textHistory);
                textCap.setText("Capítulo 2");
                expEarned = 150;
                break;
            }
            case 3:{
                String textHistory = getString(R.string.capitulo_2_historia_2, nombre, raza, sexo);
                texto.setText(textHistory);
                textCap.setText("Capítulo 2");
                expEarned = 200;
                break;
            }
            case 4:{
            	String textHistory = getString(R.string.capitulo_3_historia_1, nombre);
                texto.setText(textHistory);
                textCap.setText("Capítulo 3");
                expEarned = 250;
                break;
            }
            case 5:{
            	String textHistory = getString(R.string.capitulo_3_historia_2, juguete);
                texto.setText(textHistory);
                textCap.setText("Capítulo 3");
                expEarned = 300;
                break;
            }
            case 6:
            {
            	String textHistory = getString(R.string.capitulo_4_historia_1, nombre);
                texto.setText(textHistory);
                textCap.setText("Capítulo 4");
                expEarned = 400;
                break;
            }
            case 7:
            {
            	String textHistory = getString(R.string.capitulo_4_historia_2, pareja, orientacion_contraria, nombre);
            	finish.setText("Responder pregunta");
                texto.setText(textHistory);
                textCap.setText("Capítulo 4");
                expEarned = 0;
                break;
            }
            
            case 8:
            {
            	String textHistory = getString(R.string.capitulo_5_historia_1);
                texto.setText(textHistory);
                textCap.setText("Capítulo 5");
                expEarned = 600;
                break;
            }
            case 9:
            {
            	String textHistory = getString(R.string.capitulo_5_historia_2);
                texto.setText(textHistory);
                expEarned = 700;
                textCap.setText("Capítulo 5");
                break;
            }
            case 10:
            {
            	String textHistory = getString(R.string.capitulo_6_historia_1, nombre, raza, sexo, orientacion, pareja);
                texto.setText(textHistory);
                textCap.setText("Capítulo 6");
                expEarned = 850;
                break;
            }
            
            case 11:
            {
            	String textHistory = getString(R.string.capitulo_4_historia_2B, orientacion, nombre, pareja);
                texto.setText(textHistory);
                textCap.setText("Capítulo 4");
                expEarned = 500;
                break;
            }
            
            
            default:
                texto.setText("No se ha podido cargar ningún texto. Por favor contacta con la desarrolladora.");
        }

        final int finalExpEarned = expEarned;
        final int actualLevel = nivel;
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            	doQuestion(value, actualExp, finalExpEarned, actualLevel);
                //expEarned = expEarned;
            	//Toast.makeText(getApplicationContext(), "¡Has ganado "+ finalExpEarned + " EXP!", Toast.LENGTH_LONG).show();
//            	int expTotal = actualExp + finalExpEarned;
//            	boolean levelUp = calcularSubidaNivel(expTotal);
//                editor.putInt("exp", expTotal);
//                if(levelUp == true)
//                	editor.putInt("nivel", actualLevel + 1);
//                editor.putInt("progresion_actual", value);
//                editor.apply();
//                setResult(RESULT_OK, null);
//                if(levelUp == true){
//                	showLevelUpDialog("Historia Completada", "¡Has ganado "+ finalExpEarned + " EXP!", false);
//                	showLevelUpDialog("¡Felicidades!", "¡Has subido al nivel "+ (actualLevel + 1) + "!", true);
//                }
//                else
//                	showLevelUpDialog("Historia Completada", "¡Has ganado "+ finalExpEarned + " EXP!", true);
//                finish();
            }
        });


    }
    
    public void doQuestion(final int chap, int actualExp, int ExpEarned, int actualLevel){
    	CharSequence answers[] = null;
    	String question = null;
    	String correctAnswer = null;
    	boolean response;
    	final int Exp = actualExp;
    	final int finalExpEarned = ExpEarned;
    	final int chapter = chap;
    	final int finalLevel = actualLevel;
    	switch(chap){
    	case 1:{
    		answers = new CharSequence[] {"9", "10", "11", "8"};
    		question = "¿Cuántos meses tiene "+ nombre + "?";
    		correctAnswer = "11";
    		break;
    	}
    	case 2:{
    		answers = new CharSequence[] {"Al monte", "Al campo", "A la playa", "A ningún sitio"};
    		question = "¿A dónde se van de excursión?";
    		correctAnswer = "Al campo";
    		break;
    	}
    	case 3:{
    		answers = new CharSequence[] {"Kiko", "Keke", "Keko", "Kaká"};
    		question = "¿Como se llama el hermano de " + nombre + "?";
    		correctAnswer = "Kiko";
    		break;
    	}
    	case 4:{
    		answers = new CharSequence[] {"De un compañero de clase", "De " + nombre , "De Kiko", "De nadie"};
    		question = "¿De quién es el cumpleaños?";
    		correctAnswer = "De " + nombre;
    		break;
    	}
    	case 5:{
    		answers = new CharSequence[] {"Lara", "Mari", "Lili", "Maya"};
    		question = "¿Con quién estaba jugando Tobi?";
    		correctAnswer = "Maya";
    		break;
    	}
    	case 6:{
    		answers = new CharSequence[] {"Kira", "Maxi", "Cotufa", "Tobi"};
    		question = "¿Con quién iba " + nombre + " a casa?";
    		correctAnswer = "Cotufa";
    		break;
    	}
    	case 7:{
    		answers = new CharSequence[] {"No, solo " + orientacion, "Sí, me gustan ambos."};
    		question = nombre + ", ¿te gustan también " + orientacion_contraria + "?";
    		correctAnswer = "";
    		break;
    	}
    	case 11:{
    		answers = new CharSequence[] {"Los chicos", "Las chicas", "Los chicos y las chicas", "Ninguno"};
    		question = "¿Qué le gustaba a Kiko?";
    		correctAnswer = "Los chicos y las chicas";
    		break;
    	}
    	case 8:{
    		answers = new CharSequence[] {"Canelo", "Arthas", "Lolo", "Wolf"};
    		question = "¿Como se llamaba el tío de " + nombre + "?";
    		correctAnswer = "Arthas";
    		break;
    	}
    	case 9:{
    		answers = new CharSequence[] {"La de Maya", "La de Cora", "La de " + nombre, "La de Maxi" };
    		question = "¿Qué familia está compuesta por dos mamás?";
    		correctAnswer = "La de Maya";
    		break;
    	}
    	case 10:{
    		answers = new CharSequence[] {"Muchisimo", "Mucho", "Poco", "Nada"};
    		question = "¿Te ha gustado la historia?";
    		correctAnswer = "";
    		break;
    	}
    	}
    	
    	final CharSequence ans[] = answers; 
    	final String correct = correctAnswer;
    	

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final SharedPreferences.Editor editor = settings.edit();

    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle(question);
    	builder.setItems(answers, new DialogInterface.OnClickListener() {
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {

	            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	            final SharedPreferences.Editor editor = settings.edit();
	            
    	    	if(chap == 10){    	    		 	            
                	int expTotal = Exp + finalExpEarned;
                	boolean levelUp = calcularSubidaNivel(expTotal);
                    editor.putInt("exp", expTotal);
                    if(levelUp == true)
                    	editor.putInt("nivel", finalLevel + 1);
                    editor.putInt("progresion_actual", chapter);
                    editor.putString("valoracion", ans[which].toString());
                    editor.putBoolean("logro_3", true);
                    editor.apply();
                    setResult(RESULT_OK, null);
                    if(levelUp == true){
//                    	showLevelUpDialog("Historia Completada", "¡Has ganado "+ finalExpEarned + " EXP!", false);
                    	showLevelUpDialog("¡Felicidades!", "¡Has ganado "+ finalExpEarned + " EXP, has subido al nivel "+ (finalLevel + 1) + " y has obtenido el logro 'Final Feliz'!", true);
                    }
                    else
                    	showLevelUpDialog("Historia Completada", "¡Has ganado "+ finalExpEarned + " EXP y has obtenido el logro 'Final Feliz'!", true);
    	    	}
    	    	else if(chap == 7){
    	    		if(ans[which].toString().contains("ambos")){
	    	    		editor.putString("orientacion", "los chicos y las chicas");
	                    editor.apply();
    	    		}
                    Intent i = new Intent(HistoriaActivity.this, HistoriaActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("chap", 11); //Your id
                    i.putExtras(b);
                    startActivity(i);
                    finish();
    	    		
    	    	}
    	    	else{
	    	    	if(ans[which].toString().equalsIgnoreCase(correct)){	    	            
	                	int expTotal = Exp + finalExpEarned;
	                	boolean levelUp = calcularSubidaNivel(expTotal);
	                    editor.putInt("exp", expTotal);
	                    if(levelUp == true)
	                    	editor.putInt("nivel", finalLevel + 1);
	                    editor.putInt("progresion_actual", chapter);
	                    editor.apply();
	                    setResult(RESULT_OK, null);
	                    if(levelUp == true){
	//                    	showLevelUpDialog("Historia Completada", "¡Has ganado "+ finalExpEarned + " EXP!", false);
	                    	showLevelUpDialog("¡Felicidades!", "¡Has ganado "+ finalExpEarned + " EXP y has subido al nivel "+ (finalLevel + 1) + "!", true);
	                    }
	                    else
	                    	showLevelUpDialog("Historia Completada", "¡Has ganado "+ finalExpEarned + " EXP!", true);
	    	        }
	    	        else{
	    	        	showLevelUpDialog("¡Respuesta incorrecta!", "Intentalo otra vez", true);
	    	        	
	    	        }
    	    	}
    	        	
    	    }
    	});
    	builder.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_historia, menu);
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
    
	public void showLevelUpDialog(String title, String msg, final boolean finish){

		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
        dialogo1.setTitle(title);  
        dialogo1.setMessage(msg);            
        dialogo1.setCancelable(false);  
        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialogo1, int id) {  
            	if(finish)
            		finish();
            }  
        });             
        dialogo1.show();      
	}
	
	public boolean calcularSubidaNivel(int exp){
		int max = calcularMaxExp(nivel);
		if(max > exp){
			return false;
		}
		else
			return true;
	}
	
    private int calcularMaxExp(int nivel) {
		
    	return (int) (200*Math.pow((nivel),2));
	}
}
