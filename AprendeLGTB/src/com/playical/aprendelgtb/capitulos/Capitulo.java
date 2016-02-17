package com.playical.aprendelgtb.capitulos;

import android.app.Activity;
import android.os.Bundle;

import com.playical.aprendelgtb.actividades.Actividad;
import com.playical.aprendelgtb.historias.Historia;

import java.util.List;

/**
 * Created by Natalia on 18/02/2015.
 */
public abstract class Capitulo extends Activity {

    Historia H;

    List<Actividad> Actividades;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    };

    protected abstract void cargarActividad(Actividad A);
}
