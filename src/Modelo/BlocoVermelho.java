package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;

import java.io.Serializable;

public class BlocoVermelho extends Bloco implements Serializable{

    public BlocoVermelho() {
        super("blocos/blocoVermelho.png");
        this.bTransponivel = false;
        this.bArrastavel = false;
        this.bQuebravel = false;
    }


    public void autoDesenho() {
        super.autoDesenho();
        //teste
    }

    @Override
    public void andaPersonagem(Personagem p) {}
}
