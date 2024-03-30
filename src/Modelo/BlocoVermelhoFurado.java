package Modelo;

import Auxiliar.Desenho;

import java.io.Serializable;

public class BlocoVermelhoFurado extends Bloco implements Serializable{

    protected int codigo;
    public BlocoVermelhoFurado() {
        super("blocos/blocoVermelhoFurado.png");
        this.bTransponivel = false;
        this.bArrastavel = false;
        this.bQuebravel = true;
    }

    public void setCodigo(int n){
        codigo = n;
    }

    public int getCodigo(){
        return codigo;
    }

    private boolean validaPosicao(int codigo, Bloco b){
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
            return validaPosicao(codigo, this);
        }
        return false;
    }

    public boolean moveDown() {
        if(super.moveDown()) {
            return validaPosicao(codigo, this);
        }
        return false;
    }


    public boolean moveRight() {
        if(super.moveRight()) {
            return validaPosicao(codigo, this);
        }
        return false;
    }

    public boolean moveLeft() {
        if(super.moveLeft()) {
            return validaPosicao(codigo, this);
        }
        return false;
    }

    @Override
    public void andaPersonagem(Personagem p) {}
}
