package com.ingchristopher.petagramfinal.vista.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ingchristopher.petagramfinal.R;
import com.ingchristopher.petagramfinal.adaptador.MiMascotaAdaptador;
import com.ingchristopher.petagramfinal.pojo.MiMascota;

import java.util.ArrayList;

/**
 * Created by Ing. Christopher on 27/11/2016.
 */

public class FragmentPerfil extends android.support.v4.app.Fragment {
    private ArrayList<MiMascota> miMascota;
    private RecyclerView fotosMascota;
    MiMascotaAdaptador adaptador;


    public FragmentPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        miMascota = new ArrayList<MiMascota>();
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        fotosMascota = (RecyclerView)v.findViewById(R.id.rvMiMascota);

        GridLayoutManager glm = new GridLayoutManager(getActivity(),2);
        glm.setOrientation(GridLayoutManager.HORIZONTAL);

        fotosMascota.setLayoutManager(glm);
        inicializarMascotas();
        inicializarAdaptador();

        return v;
    }

    public void inicializarMascotas(){
        miMascota = new ArrayList<MiMascota>();

        miMascota.add(new MiMascota(R.drawable.perro1,"0"));
        miMascota.add(new MiMascota(R.drawable.perro1,"0"));
        miMascota.add(new MiMascota(R.drawable.perro1,"0"));
        miMascota.add(new MiMascota(R.drawable.perro1,"0"));
        miMascota.add(new MiMascota(R.drawable.perro1,"0"));

    }

    public void inicializarAdaptador(){
        adaptador = new MiMascotaAdaptador(miMascota,getActivity());
        fotosMascota.setAdapter(adaptador);
    }
}
