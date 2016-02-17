package com.playical.aprendelgtb.capitulos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.playical.aprendelgtb.R;
import com.playical.aprendelgtb.SpecialAdapter;
import com.playical.aprendelgtb.actividades.Actividad;
import com.playical.aprendelgtb.historias.Historia;
import com.playical.aprendelgtb.historias.HistoriaActivity;
import com.playical.aprendelgtb.juegos.ntilepuzzle.PuzzleActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CapituloSeis extends Capitulo {

    public static final String PREFS_NAME = "MisPreferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitulo_uno);
        H = new Historia(2);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        String[] NumeroTemas = new String[] {
                "Historia 1: Fin",
                "Actividad 1: Foto de Grupo"
        };

        String[] NombreTemas = new String[] {
                "Fin",
                "Foto de grupo"
        };


        // We get the ListView component from the layout
        ListView lv = (ListView) findViewById(R.id.listView);


        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> TopicList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<2;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("number", NumeroTemas[i]);
            hm.put("topic",NombreTemas[i]);
            TopicList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "number", "topic" };

        // Ids of views in listview_layout
        int[] to = { R.id.number,R.id.topic};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SpecialAdapter adapter = new SpecialAdapter(getBaseContext(), TopicList, android.R.layout.simple_list_item_1, from, new int[] {android.R.id.text1}, 10);


        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                if (position == 0) {
                    Intent appInfo = new Intent(CapituloSeis.this, HistoriaActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("chap", 10); //Your id
                    appInfo.putExtras(b); //Put your id to your next Intent
                    startActivityForResult(appInfo, 1);
                }
                if (position == 1){
                	Intent intent = new Intent(CapituloSeis.this, PuzzleActivity.class);
                	Bundle b = new Bundle();
                    b.putInt("image", 2);
                    b.putInt("dificultad", 3);
                    intent.putExtras(b); //Put your id to your next Intent
                	startActivityForResult(intent, 3);
                }
            }
        });

        Button BotonTerminar = (Button) findViewById(R.id.buttonFinish);

        BotonTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, null);
                finish();
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

    @Override
    public void cargarActividad(Actividad A){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Intent refresh = new Intent(this, CapituloSeis.class);
            startActivity(refresh);
            this.finish();
        }
    }
}