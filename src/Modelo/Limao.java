package Modelo;

import java.io.Serializable;

public class Limao extends Comida implements Serializable{

    public Limao() {
        super("comidas/limao.png");
        this.pontos = 15;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }    
}
