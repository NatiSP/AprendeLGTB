package com.playical.aprendelgtb.historias;

import android.media.MediaPlayer;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Natalia on 18/02/2015.
 */
public class Historia {

    int Capitulo;

    String Texto;

    boolean Leida;


    public Historia(int C){
        Capitulo = C;
        Leida = false;
    }

    void cargarPagina(String text){
        Texto = text;
    }

    void mostrarPagina(TextView TV){
        TV.setText(Texto);
    }

    void terminarHistoria(){
        Leida = true;
    }
}
