package Modelo;

import java.io.Serializable;

public class Tangerina extends Comida implements Serializable{

    public Tangerina() {
        super("comidas/tangerina.png");
        this.pontos = 10;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }    
}
