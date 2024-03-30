package Modelo;

import java.io.Serializable;

public class Cereja extends Comida implements Serializable{

    public Cereja() {
        super("comidas/cereja.png");
        this.pontos = 13;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }    
}
