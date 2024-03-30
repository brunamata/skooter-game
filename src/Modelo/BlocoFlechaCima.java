package Modelo;

import Auxiliar.Desenho;

import java.io.Serializable;

public class BlocoFlechaCima extends Bloco implements Serializable{


    public BlocoFlechaCima() {
        super("blocos/blocoFlechaCima.png");
        this.bTransponivel = true;
        this.bArrastavel = false;
        this.bQuebravel = false;
    }


    public void andaPersonagem(Personagem p){
        p.moveUp();
    }

    private boolean validaPosicao(Bloco b){
        if (!Desenho.acessoATelaDoJogo().ehPosicaoValidaBloco(this.getPosicao(), this)) {
            this.pPosicao.volta();
            return false;
        }
        return true;
    }

    public void autoDesenho() {
        super.autoDesenho();
        //teste
    }

    public boolean moveUp() {
        if(super.moveUp()) { //se nao saio pra fora da tela
            return validaPosicao(this);
        }
        return false;
    }

    public boolean moveDown() {
        if(super.moveDown()) {
            return validaPosicao(this);
        }
        return false;
    }


    public boolean moveRight() {
        if(super.moveRight()) {
            return validaPosicao(this);
        }
        return false;
    }

    public boolean moveLeft() {
        if(super.moveLeft()) {
            return validaPosicao(this);
        }
        return false;
    }
}
