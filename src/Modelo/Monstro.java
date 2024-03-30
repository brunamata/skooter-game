package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;

import java.util.Random;

public class Monstro extends Personagem{

    String cor;

    public Monstro(String corComInicialMaiuscula) {
        super("monstros/inimigo" + corComInicialMaiuscula + "Frente.png");
        this.cor = corComInicialMaiuscula;
        this.bMortal = true;
        this.bTransponivel = true;
    }
    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }

    public boolean setPosicao(int linha, int coluna){
        if(this.pPosicao.setPosicao(linha, coluna)){
            if (!Desenho.acessoATelaDoJogo().ehPosicaoValidaMonstro(this.getPosicao(), this)) {
                this.voltaAUltimaPosicao();
            }
            return true;
        }
        return false;
    }

    private boolean validaPosicao(){
        Desenho.acessoATelaDoJogo().verificaMonstroMorte(this.pPosicao);
        if (!Desenho.acessoATelaDoJogo().ehPosicaoValidaMonstro(this.getPosicao(), this)) {
            this.voltaAUltimaPosicao();
            return false;
        }
        return true;
    }

    public void alterarIcone(String endereco){
        super.alteraIcone(endereco);
    }


    public void autoDesenho(){
        Random rand = new Random();
        int iDirecao = rand.nextInt(5) % 4;

        if(iDirecao == 0 && pPosicao.getColuna()+1 < Consts.TAM_JOGO ) {
            String endereco = "monstros/inimigo" + cor + "Direita.png";
            alterarIcone(endereco);
            this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()+1);
            validaPosicao();
        }

        else if(iDirecao == 1 && pPosicao.getLinha()+1 < Consts.TAM_JOGO) {
            String endereco = "monstros/inimigo" + cor + "Frente.png";
            alterarIcone(endereco);
            this.setPosicao(pPosicao.getLinha() + 1, pPosicao.getColuna());
            validaPosicao();
        }
        else if(iDirecao == 2 && pPosicao.getColuna()-1 < Consts.TAM_JOGO){
            String endereco = "monstros/inimigo" + cor + "Esquerda.png";
            alterarIcone(endereco);
            this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()-1);
            validaPosicao();
        }
        else if(iDirecao == 3 && pPosicao.getLinha()-1 < Consts.TAM_JOGO){
            String endereco = "monstros/inimigo" + cor + "Tras.png";
            alterarIcone(endereco);
            this.setPosicao(pPosicao.getLinha()-1, pPosicao.getColuna());
            validaPosicao();
        }

        super.autoDesenho();
    }
}
