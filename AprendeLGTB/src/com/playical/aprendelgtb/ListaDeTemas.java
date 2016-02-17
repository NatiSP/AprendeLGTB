package com.playical.aprendelgtb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.playical.aprendelgtb.actividades.Actividad;
import com.playical.aprendelgtb.capitulos.CapituloCinco;
import com.playical.aprendelgtb.capitulos.CapituloCuatro;
import com.playical.aprendelgtb.capitulos.CapituloDos;
import com.playical.aprendelgtb.capitulos.CapituloSeis;
import com.playical.aprendelgtb.capitulos.CapituloTres;
import com.playical.aprendelgtb.capitulos.CapituloUno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class ListaDeTemas extends Activity {

    public static final String PREFS_NAME = "MisPreferencias";
    //ArrayList<String> TopicList = new ArrayList<String>();
    
    ArrayList<Drawable> icons = new ArrayList<Drawable>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_temas);

        String[] NumeroTemas = new String[] {
                "Capítulo 1",
                "Capítulo 2",
                "Capítulo 3",
                "Capítulo 4",
                "Capítulo 5",
                "Capítulo 6"
        };

        String[] NombreTemas = new String[] {
                "Introducción",
                "Homosexualidad",
                "Bisexualidad",
                "Transexualidad",
                "Intersexualidad",
                "Resumen"
        };

        // Get preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String user = settings.getString("usuario", "Desconocido");
        int nivel = settings.getInt("nivel", 1);
        int exp = settings.getInt("exp", 0);
        int animalSelected = settings.getInt("animal", -1);


        TextView userText = (TextView) findViewById(R.id.textUserName);
        TextView userLevel = (TextView) findViewById(R.id.textLevel);
        TextView userExp = (TextView) findViewById(R.id.textExp);
        
        ImageView profile = (ImageView) findViewById(R.id.imageProfile);
        
        icons.add(getResources().getDrawable( R.drawable.gato_1 ));
        icons.add(getResources().getDrawable( R.drawable.perro_1 ));
        
        if (animalSelected != -1)
        	profile.setBackgroundDrawable(icons.get(animalSelected));

        userText.setText(user);
        userLevel.setText("Nivel " + Integer.toString(nivel));
        userExp.setText(exp + " EXP");

        // We get the ListView component from the layout
        ListView lv = (ListView) findViewById(R.id.listView);


        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> TopicList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<6;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("number", NumeroTemas[i]);
            hm.put("topic",NombreTemas[i]);
            TopicList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "number","topic" };

        // Ids of views in listview_layout
        int[] to = { R.id.number,R.id.topic};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SpecialAdapter adapter = new SpecialAdapter(getBaseContext(), TopicList, android.R.layout.simple_list_item_1, from, new int[] {android.R.id.text1}, nivel);


        lv.setAdapter(adapter);
        final int finalNivel = nivel;

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                if (position == 0) {
                    Intent appInfo = new Intent(ListaDeTemas.this, CapituloUno.class);
                    startActivityForResult(appInfo, 1);
                }
                if (position == 1) {
                	if(finalNivel > 1){
	                    Intent appInfo = new Intent(ListaDeTemas.this, CapituloDos.class);
	                    startActivityForResult(appInfo, 2);
                	}
                	else
                		Toast.makeText(getApplicationContext(), "¡Aún no has desbloqueado este capítulo!", Toast.LENGTH_LONG).show();
                }
                if (position == 2) {
                	if(finalNivel > 2){
	                    Intent appInfo = new Intent(ListaDeTemas.this, CapituloTres.class);
	                    startActivityForResult(appInfo, 3);
                    }
                	else
                		Toast.makeText(getApplicationContext(), "¡Aún no has desbloqueado este capítulo!", Toast.LENGTH_LONG).show();
                    
                }
                if (position == 3) {
                	if(finalNivel > 3){
	                    Intent appInfo = new Intent(ListaDeTemas.this, CapituloCuatro.class);
	                    startActivityForResult(appInfo, 4);
                	}
                	else
                		Toast.makeText(getApplicationContext(), "¡Aún no has desbloqueado este capítulo!", Toast.LENGTH_LONG).show();
                }
                if (position == 4) {
                	if(finalNivel > 4){
	                    Intent appInfo = new Intent(ListaDeTemas.this, CapituloCinco.class);
	                    startActivityForResult(appInfo, 5);
                	}
                	else
                		Toast.makeText(getApplicationContext(), "¡Aún no has desbloqueado este capítulo!", Toast.LENGTH_LONG).show();
                }
                if (position == 5) {
                	if(finalNivel > 5){
	                    Intent appInfo = new Intent(ListaDeTemas.this, CapituloSeis.class);
	                    startActivityForResult(appInfo, 6);
                	}
                	else
                		Toast.makeText(getApplicationContext(), "¡Aún no has desbloqueado este capítulo!", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button BackButton = (Button) findViewById(R.id.button);


        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*View listViewChildAt = lv.getChildAt(1);
        listViewChildAt.setBackgroundColor(Color.RED);
        listViewChildAt = lv.getChildAt(2);
        listViewChildAt.setBackgroundColor(Color.rgb(255,102,0));
        listViewChildAt = lv.getChildAt(3);
        listViewChildAt.setBackgroundColor(Color.YELLOW);
        listViewChildAt = lv.getChildAt(4);
        listViewChildAt.setBackgroundColor(Color.GREEN);
        listViewChildAt = lv.getChildAt(5);
        listViewChildAt.setBackgroundColor(Color.BLUE);
        listViewChildAt = lv.getChildAt(6);
        listViewChildAt.setBackgroundColor(Color.rgb(128,0,128));*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_de_temas, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            Intent refresh = new Intent(this, ListaDeTemas.class);
            startActivity(refresh);
            this.finish();
    }
}
