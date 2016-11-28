package com.ingchristopher.petagramfinal.presentador.fragment;

import android.content.Context;

import com.ingchristopher.petagramfinal.database.ConstructorMascotas;
import com.ingchristopher.petagramfinal.pojo.Mascota;
import com.ingchristopher.petagramfinal.vista.fragments.IRecyclerviewFragmentVista;

import java.util.ArrayList;

/**
 * Created by Ing. Christopher on 27/11/2016.
 */

public class RecyclerviewFragmentPresentador implements IRecyclerviewFragmentPresentador{
    private IRecyclerviewFragmentVista iRecyclerviewFragmentVista;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerviewFragmentPresentador(IRecyclerviewFragmentVista iRecyclerviewFragmentVista, Context context) {
        this.context = context;
        this.iRecyclerviewFragmentVista = iRecyclerviewFragmentVista;
        obtenerMascotas();
    }

    @Override
    public void obtenerMascotas() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatosMascotas();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerviewFragmentVista.inicializarAdaptadorRV(iRecyclerviewFragmentVista.crearAdaptadorMascota(mascotas));
        iRecyclerviewFragmentVista.generarLinearLayoutVertical();
    }
}
