package com.ingchristopher.petagramfinal.database;

/**
 * Created by Ing. Christopher on 27/11/2016.
 */

public class ConstantesBaseDatosMascotas {
    public static final String DATABASE_NAME        = "mascotas";
    public static final int DATABASE_VERSION        = 1;
    public static final String TABLE_PET            = "mascota";
    public static final String TABLE_PET_ID         = "id";
    public static final String TABLE_PET_NOMBRE     = "nombre";
    public static final String TABLE_PET_FOTO       = "foto";

    public static final String TABLE_LIKES_PET      = "mascotas_likes";
    public static final String TABLE_LIKES_PET_ID   = "id";
    public static final String TABLE_PET_NUMERO_LIKES = "numero_likes";
    public static final String TABLE_LIKES_PET_ID_PET = "id_mascota";
    public static final String TABLE_LIKES_PET_NAME = "nombre";
    public static final String TABLE_LIKES_PET_IMAGE = "foto";
    public static final int NUM_PUPPIES = 5;
}
