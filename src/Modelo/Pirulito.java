package Modelo;

import java.io.Serializable;

public class Pirulito extends Comida implements Serializable{

    public Pirulito() {
        super("comidas/pirulito.png");
        this.pontos = 50;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }    
}
