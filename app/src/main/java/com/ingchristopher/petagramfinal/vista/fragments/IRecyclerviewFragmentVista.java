package com.ingchristopher.petagramfinal.vista.fragments;

import com.ingchristopher.petagramfinal.adaptador.MascotaAdaptador;
import com.ingchristopher.petagramfinal.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Ing. Christopher on 27/11/2016.
 */

public interface IRecyclerviewFragmentVista {
    public void generarLinearLayoutVertical();
    public MascotaAdaptador crearAdaptadorMascota(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorRV (MascotaAdaptador mascotaAdaptador); //RV = Recycler View
}
