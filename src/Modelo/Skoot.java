package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Auxiliar.Posicao;

import java.io.Serializable;

public class Skoot extends Personagem implements Serializable{
    protected int direcao;
    public Skoot() {
        super("skoot/skooterFrente.png");
    }

    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }
    
    
    public boolean setPosicao(int linha, int coluna){
        if(this.pPosicao.setPosicao(linha, coluna)){
            if (!Desenho.acessoATelaDoJogo().ehPosicaoValida(this.getPosicao(), direcao)) {
                this.voltaAUltimaPosicao();
            }
            return true;
        }
        return false;
    }

    /*TO-DO: este metodo pode ser interessante a todos os personagens que se movem*/
    private boolean validaPosicao(int direcao){
        if (!Desenho.acessoATelaDoJogo().ehPosicaoValida(this.getPosicao(), direcao)) {
            this.voltaAUltimaPosicao();
            return false;
        }
        return true;
    }
    
    public boolean moveUp() {
        alterarIcone("skoot/skooterTras.png");
        if(super.moveUp()) {
            this.direcao = Consts.CIMA;
            Desenho.acessoATelaDoJogo().verificaMorte(this.pPosicao);
            return validaPosicao(direcao);
        }
        return false;
    }

    public boolean moveDown() {
        alterarIcone("skoot/skooterFrente.png");
        if(super.moveDown()) {
            this.direcao = Consts.BAIXO;
            Desenho.acessoATelaDoJogo().verificaMorte(this.pPosicao);
            return validaPosicao(direcao);
        }
        return false;
    }

    public boolean moveRight() {
        alterarIcone("skoot/skooterDireita.png");
        if(super.moveRight()) {
            this.direcao = Consts.DIREITA;
            Desenho.acessoATelaDoJogo().verificaMorte(this.pPosicao);
            return validaPosicao(direcao);
        }
        return false;
    }

    public boolean moveLeft() {
        alterarIcone("skoot/skooterEsquerda.png");
        if(super.moveLeft()) {
            this.direcao = Consts.ESQUERDA;
            Desenho.acessoATelaDoJogo().verificaMorte(this.pPosicao);
            return validaPosicao(direcao);
        }
        return false;
    }    

    public void breakBlock(){
        switch(direcao){
            case 1: //Right
                Posicao auxRight = new Posicao(this.pPosicao.getLinha(), this.pPosicao.getColuna() + 1);
                Desenho.acessoATelaDoJogo().quebrarBlocoSePossivel(auxRight);
                break;
            case 2: //Left
                Posicao auxLeft = new Posicao(this.pPosicao.getLinha(), this.pPosicao.getColuna() - 1);
                Desenho.acessoATelaDoJogo().quebrarBlocoSePossivel(auxLeft);
                break;
            case 3: //Up
                Posicao auxUp = new Posicao(this.pPosicao.getLinha() - 1, this.pPosicao.getColuna());
                Desenho.acessoATelaDoJogo().quebrarBlocoSePossivel(auxUp);
                break;
            case 4: //Down
                Posicao auxDown = new Posicao(this.pPosicao.getLinha() + 1, this.pPosicao.getColuna());
                Desenho.acessoATelaDoJogo().quebrarBlocoSePossivel(auxDown);
                break;

        }
    }

    public void alterarIcone(String endereco){
        super.alteraIcone(endereco);
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
    }
}
