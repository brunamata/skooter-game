package Modelo;

import java.io.Serializable;

public class Letra extends IconesMenu implements Serializable{


    public Letra(char letra){
        super("letras/" + letra + ".png");
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
    }

}
