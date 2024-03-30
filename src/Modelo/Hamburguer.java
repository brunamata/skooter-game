package Modelo;

import java.io.Serializable;

public class Hamburguer extends Comida implements Serializable{

    public Hamburguer() {
        super("comidas/hamburguer.png");
        this.pontos = 25;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }    
}
