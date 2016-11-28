package com.ingchristopher.petagramfinal.adaptador;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingchristopher.petagramfinal.R;
import com.ingchristopher.petagramfinal.database.ConstructorMascotas;
import com.ingchristopher.petagramfinal.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Ing. Christopher on 27/11/2016.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    ArrayList<Mascota>mascotas;
    Activity activity;

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (LayoutInflater.from(parent.getContext())).inflate(R.layout.cardview_mascota,parent,false);

        return new MascotaViewHolder(v);
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgMascota;
        private TextView tvNombreMascota;
        private TextView tvRaiting;
        private ImageButton btnLike;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgMascota          = (ImageView)itemView.findViewById(R.id.imgMascota);
            tvNombreMascota     = (TextView)itemView.findViewById(R.id.tvNombreMascota);
            tvRaiting           = (TextView)itemView.findViewById(R.id.tvRaiting);
            btnLike             = (ImageButton)itemView.findViewById(R.id.btnLike);


        }
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        holder.imgMascota.setImageResource((mascota.getFoto()));
        holder.tvNombreMascota.setText(mascota.getNombre());
        holder.tvRaiting.setText(String.valueOf(mascota.getRaiting()));

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Diste like a " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);
                holder.tvRaiting.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }
}
