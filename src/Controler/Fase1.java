package Controler;

import Modelo.*;

import java.io.Serializable;

public class Fase1 extends Fase implements Serializable {

    Fase1(){
        super();
        this.vidas = 5;  //Pode trocar as vidas para 5, se achar muito hardcore :)
        atualizaVidasIcone();
        inicializaPontuacao();
        setLetras();
        desenhaLogo();
    }

    public void inicializaFase(){
        desenhaLogo();
        atualizaVidasIcone();
        inicializaPontuacao();
        setLetras();
    }

    public void criaPersonagens() {
        skoot = new Skoot();
        skoot.setPosicao(0, 7);
        this.addPersonagem(skoot);


        // 2,0 0,12  13,2 12,14

        Monstro zz1 = new Monstro("Verde");
        zz1.setPosicao(2, 0);
        this.addPersonagem(zz1);

        Monstro zz2 = new Monstro("Rosa");
        zz2.setPosicao(0, 12);
        this.addPersonagem(zz2);

        Monstro zz3 = new Monstro("Amarelo");
        zz3.setPosicao(13, 2);
        this.addPersonagem(zz3);

        Monstro zz4 = new Monstro("Azul");
        zz4.setPosicao(12, 14);
        this.addPersonagem(zz4);

    }

    public void reiniciaPersonagens() {
        // apagarPersonagens();
        //apagar arrayPersonagens();
        this.personagens.clear();
        criaPersonagens();
    }


    public void criaObjetos() {
        criaPersonagens();
        //SKOOTER TEM QUE SER SEMPRE A POSICAO 0, POR CAUSA DA FUNCAO PROCESSA TUDO EM CJ


        for(int linha = 1; linha < 14; linha = linha + 2){
            for(int coluna = 1; coluna<14; coluna = coluna+2){
                BlocoVermelho b = new BlocoVermelho();
                b.setPosicao(linha, coluna);
                this.addBloco(b);
            }
        }

        // 2,0 0,12  13,2 12,14

        int[] linhas = new int[]{0,2,3,0,1,1,13,13,13,10,12,14,2,5,4,4,4,6,6,7,9,9,7,10,14,14,14,5,7,0,0,12,10   };
        int[] colunas = new int[]{1,1,0,11,12,14,0,2,4,14,13,13,4,6,8,9,14,0,2,4,4,8,12,4,8,9,10,10,10,6,8,8,0   };

        for(int i=0; i < linhas.length; i++){
            BlocoVerdeFurado b = new BlocoVerdeFurado();
            b.setPosicao(linhas[i], colunas[i]);
            this.addBloco(b);
        }

        Limao f1 = new Limao();
        f1.setPosicao(0,0);
        this.addFruta(f1);

        Cereja f2 = new Cereja();
        f2.setPosicao(0,14);
        this.addFruta(f2);

        Cereja f3 = new Cereja();
        f3.setPosicao(14,0);
        this.addFruta(f3);

        Tangerina f4 = new Tangerina();
        f4.setPosicao(14,14);
        this.addFruta(f4);


    }




}
