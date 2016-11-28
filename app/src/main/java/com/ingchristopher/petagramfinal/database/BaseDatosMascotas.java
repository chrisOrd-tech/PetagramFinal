package com.ingchristopher.petagramfinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ingchristopher.petagramfinal.R;
import com.ingchristopher.petagramfinal.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Ing. Christopher on 27/11/2016.
 */

public class BaseDatosMascotas extends SQLiteOpenHelper {
    private Context context;
    public BaseDatosMascotas(Context context) {
        super(context, ConstantesBaseDatosMascotas.DATABASE_NAME, null, ConstantesBaseDatosMascotas.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //Crea la estructura
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatosMascotas.TABLE_PET + "("+
                ConstantesBaseDatosMascotas.TABLE_PET_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatosMascotas.TABLE_PET_NOMBRE + " TEXT, " +
                ConstantesBaseDatosMascotas.TABLE_PET_FOTO   + " INTEGER " +
                ")";
        String queryCrearTablaFavsMascotas = "CREATE TABLE " + ConstantesBaseDatosMascotas.TABLE_LIKES_PET + "(" +
                ConstantesBaseDatosMascotas.TABLE_LIKES_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatosMascotas.TABLE_LIKES_PET_ID_PET + " INTEGER, " +
                ConstantesBaseDatosMascotas.TABLE_LIKES_PET_IMAGE + " INTEGER, " +
                ConstantesBaseDatosMascotas.TABLE_LIKES_PET_NAME + " TEXT, " +
                ConstantesBaseDatosMascotas.TABLE_PET_NUMERO_LIKES + " INTEGER" +
                ")";

        sqLiteDatabase.execSQL(queryCrearTablaMascota);
        sqLiteDatabase.execSQL(queryCrearTablaFavsMascotas);
        inicializarFavs(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatosMascotas.TABLE_PET);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatosMascotas.TABLE_LIKES_PET);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Mascota> obtenerTodasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatosMascotas.TABLE_PET;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryLikes = "SELECT COUNT(" + ConstantesBaseDatosMascotas.TABLE_PET_NUMERO_LIKES + ") as likes " +
                    " FROM " + ConstantesBaseDatosMascotas.TABLE_LIKES_PET +
                    " WHERE " + ConstantesBaseDatosMascotas.TABLE_LIKES_PET_ID_PET + "=" + mascotaActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if(registrosLikes.moveToNext()){
                mascotaActual.setRaiting(registrosLikes.getInt(0));
            }
            else {
                mascotaActual.setRaiting(0);
            }

            mascotas.add(mascotaActual);
        }

        db.close();

        return mascotas;
    }

    public void insertarMascotas(ContentValues contentValues){ //Inserta objetos a la tabla de mascotas en general
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatosMascotas.TABLE_PET, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){ //Inserta objetos a la tabla de favs
        SQLiteDatabase db = this.getWritableDatabase();
        //db.insert(ConstantesBaseDatosMascotas.TABLE_LIKES_PET, null, contentValues);

        int i = db.update(ConstantesBaseDatosMascotas.TABLE_LIKES_PET,
                contentValues,
                ConstantesBaseDatosMascotas.TABLE_LIKES_PET_ID + "=" + contentValues.getAsInteger(ConstantesBaseDatosMascotas.TABLE_LIKES_PET_ID),
                null);
        if (i<1){
            long n = insertPuppy(contentValues);
            if (n>ConstantesBaseDatosMascotas.NUM_PUPPIES){
                Mascota mascota = new Mascota((int)n-ConstantesBaseDatosMascotas.NUM_PUPPIES, 0, "");
                deletePuppy(mascota);
            }
        }

        db.close();
    }

    private int deletePuppy(Mascota mascota) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i =db.delete(ConstantesBaseDatosMascotas.TABLE_LIKES_PET,
                ConstantesBaseDatosMascotas.TABLE_LIKES_PET_ID + "=" + mascota.getId(),
                null);
        db.close();
        return i;
    }

    private long insertPuppy(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        long i = db.insert(ConstantesBaseDatosMascotas.TABLE_LIKES_PET,
                null,
                contentValues);
        db.close();
        return i;
    }

    public int obtenerLikesMascotaDB(Mascota mascota){
        int likes = 0;

        String query = "SELECT COUNT(" + ConstantesBaseDatosMascotas.TABLE_PET_NUMERO_LIKES + ")" +
                " FROM " + ConstantesBaseDatosMascotas.TABLE_LIKES_PET +
                " WHERE " + ConstantesBaseDatosMascotas.TABLE_LIKES_PET_ID_PET + "=" + mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }

    public ArrayList<Mascota> obtenerFavs() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatosMascotas.TABLE_LIKES_PET;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()){
            Mascota mascota = new Mascota(registros.getInt(2), registros.getString(3), registros.getInt(4));

            mascotas.add(mascota);
        }
        db.close();
        return mascotas;
    }

    public void inicializarFavs(SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_LIKES_PET_ID, 1);
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_LIKES_PET_IMAGE, R.drawable.perro1);
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_LIKES_PET_NAME, "Tasha");
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_NUMERO_LIKES, 1);

        db.insert(ConstantesBaseDatosMascotas.TABLE_LIKES_PET, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_LIKES_PET_ID, 2);
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_LIKES_PET_IMAGE, R.drawable.perro1);
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_LIKES_PET_NAME, "Yo");
        contentValues.put(ConstantesBaseDatosMascotas.TABLE_PET_NUMERO_LIKES, 1);

        db.insert(ConstantesBaseDatosMascotas.TABLE_LIKES_PET, null, contentValues);
    }
}
