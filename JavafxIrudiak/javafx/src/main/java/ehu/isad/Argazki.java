package ehu.isad;

import javafx.scene.image.Image;

public class Argazki {

    private String izena;
    private String irudia;

    public Argazki(String pIzena,String pImage){
        this.izena=pIzena;
        this.irudia=pImage;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getIrudia() {
        return irudia;
    }

    public void setIrudia(String irudia) {
        this.irudia = irudia;
    }

    @Override
    public String toString(){
        return this.izena;
    }
}
