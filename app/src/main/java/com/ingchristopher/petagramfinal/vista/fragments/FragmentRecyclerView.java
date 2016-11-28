package com.ingchristopher.petagramfinal.vista.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ingchristopher.petagramfinal.R;
import com.ingchristopher.petagramfinal.adaptador.MascotaAdaptador;
import com.ingchristopher.petagramfinal.pojo.Mascota;
import com.ingchristopher.petagramfinal.presentador.fragment.IRecyclerviewFragmentPresentador;
import com.ingchristopher.petagramfinal.presentador.fragment.RecyclerviewFragmentPresentador;

import java.util.ArrayList;

/**
 * Created by Ing. Christopher on 27/11/2016.
 */

public class FragmentRecyclerView extends android.support.v4.app.Fragment implements IRecyclerviewFragmentVista{
    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IRecyclerviewFragmentPresentador presentador;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_recyclerview,container,false);

        listaMascotas = (RecyclerView)v.findViewById(R.id.rvMascotas);
        presentador = new RecyclerviewFragmentPresentador(this, getContext());
        return v;
    }

    /*public void inicializarMascotas(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.perro1,"Tasha",0));
        mascotas.add(new Mascota(R.drawable.perro2,"Reina",0));
        mascotas.add(new Mascota(R.drawable.perro3,"Pocky",0));
        mascotas.add(new Mascota(R.drawable.perro4,"Manchis",0));
        mascotas.add(new Mascota(R.drawable.perro5,"Pitty",0));
        mascotas.add(new Mascota(R.drawable.perro1,"Hachi",0));
        mascotas.add(new Mascota(R.drawable.perro2,"Pulgas",0));
        mascotas.add(new Mascota(R.drawable.perro3,"Solovino",0));
        mascotas.add(new Mascota(R.drawable.perro4,"Spike",0));
        mascotas.add(new Mascota(R.drawable.perro5,"Trueno",0));
    }*/


    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptadorMascota(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador mascotaAdaptador) {
        listaMascotas.setAdapter(mascotaAdaptador);
    }
}
