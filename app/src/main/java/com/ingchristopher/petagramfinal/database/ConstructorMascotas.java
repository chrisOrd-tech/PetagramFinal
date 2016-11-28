package com.ingchristopher.petagramfinal.database;

import android.content.ContentValues;
import android.content.Context;

import com.ingchristopher.petagramfinal.R;
import com.ingchristopher.petagramfinal.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Ing. Christopher on 27/11/2016.
 */

public class ConstructorMascotas {
    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatosMascotas(){
        BaseDatosMascotas db = new BaseDatosMascotas(context);
        insertarMascotas(db);
        return db.obtenerTodasMascotas();
    }

    public ArrayList<Mascota> obtenerFavoritos(){
        BaseDatosMascotas db = new BaseDatosMascotas(context);
        return db.obtenerFavs();
    }

    public void insertarMascotas(BaseDatosMascotas db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_NOMBRE, "Tasha");
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_FOTO, R.drawable.perro1);

        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_NOMBRE, "Reina");
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_FOTO, R.drawable.perro2);

        db.insertarMascotas(contentValues);
        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_NOMBRE, "Pocky");
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_FOTO, R.drawable.perro3);

        db.insertarMascotas(contentValues);
        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_NOMBRE, "Manchis");
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_FOTO, R.drawable.perro4);

        db.insertarMascotas(contentValues);
        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_NOMBRE, "Pitty");
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_FOTO, R.drawable.perro5);

        db.insertarMascotas(contentValues);
        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_NOMBRE, "Hachi");
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_FOTO, R.drawable.perro1);

        db.insertarMascotas(contentValues);
        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_NOMBRE, "Pulgas");
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_FOTO, R.drawable.perro3);

        db.insertarMascotas(contentValues);
    }

    public void darLikeMascota(Mascota mascota){
        BaseDatosMascotas db = new BaseDatosMascotas(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_LIKES_PET_ID_PET, mascota.getId());
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_NUMERO_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatosMascotas db = new BaseDatosMascotas(context);
        return db.obtenerLikesMascotaDB(mascota);
    }
}
