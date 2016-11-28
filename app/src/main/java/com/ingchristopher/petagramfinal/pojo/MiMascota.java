package com.ingchristopher.petagramfinal.pojo;

/**
 * Created by Ing. Christopher on 27/11/2016.
 */

public class MiMascota {
    private int foto;
    private String raiting;

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getRaiting() {
        return raiting;
    }

    public void setRaiting(String raiting) {
        this.raiting = raiting;
    }

    public MiMascota(int foto, String raiting) {
        this.foto = foto;
        this.raiting = raiting;
    }
}
