package com.ingchristopher.petagramfinal.adaptador;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingchristopher.petagramfinal.R;
import com.ingchristopher.petagramfinal.pojo.MiMascota;

import java.util.ArrayList;

/**
 * Created by Ing. Christopher on 27/11/2016.
 */

public class MiMascotaAdaptador extends RecyclerView.Adapter<MiMascotaAdaptador.MiMascotaViewHolder>{

    ArrayList<MiMascota> mascotas;
    Activity activity;

    public MiMascotaAdaptador(ArrayList<MiMascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MiMascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (LayoutInflater.from(parent.getContext())).inflate(R.layout.fotos_mascota,parent,false);

        return new MiMascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MiMascotaViewHolder holder, int position) {
        final MiMascota miMascota = mascotas.get(position);
        holder.foto.setImageResource(miMascota.getFoto());
        holder.raiting.setText(miMascota.getRaiting());
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MiMascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView foto;
        private TextView raiting;

        public MiMascotaViewHolder(View itemView) {
            super(itemView);
            foto = (ImageView)itemView.findViewById(R.id.imgMiMascota);
            raiting = (TextView)itemView.findViewById(R.id.tvMiRaiting);
        }
    }
}
