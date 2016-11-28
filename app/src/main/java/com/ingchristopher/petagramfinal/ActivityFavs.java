package com.ingchristopher.petagramfinal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ingchristopher.petagramfinal.adaptador.MascotaAdaptador;
import com.ingchristopher.petagramfinal.database.ConstructorMascotas;
import com.ingchristopher.petagramfinal.pojo.Mascota;

import java.util.ArrayList;

public class ActivityFavs extends AppCompatActivity {

    private Context context;
    private ConstructorMascotas constructorMascotas;
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs);

        listaMascotas = (RecyclerView)findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
        inicializarMascotas();
        inicializarAdaptador();
    }
    public void inicializarMascotas(){
        /*mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.perro1,"Tasha",0));*/

        constructorMascotas = new ConstructorMascotas(this);
        mascotas = constructorMascotas.obtenerFavoritos();
    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);
    }
}
