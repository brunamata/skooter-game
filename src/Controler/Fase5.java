package Controler;

import Modelo.*;

import java.io.Serializable;

public class Fase5 extends Fase implements Serializable {

    Fase5(){
        super();
    }

    public void inicializaFase(){
        desenhaLogo();
        atualizaVidasIcone();
        atualizaPontuacao();
        setLetras();
    }

    public void criaPersonagens() {
        skoot = new Skoot();
        skoot.setPosicao(6, 0);
        this.addPersonagem(skoot);

        Monstro m1 = new Monstro("Amarelo");
        m1.setPosicao(3, 11);
        this.addPersonagem(m1);

        Monstro m2 = new Monstro("Rosa");
        m2.setPosicao(6, 6);
        this.addPersonagem(m2);

        Monstro m3 = new Monstro("Verde");
        m3.setPosicao(13, 1);
        this.addPersonagem(m3);

        Monstro m4 = new Monstro("Rosa");
        m4.setPosicao(8, 8);
        this.addPersonagem(m4);

        Monstro m5 = new Monstro("Azul");
        m5.setPosicao(14, 14);
        this.addPersonagem(m5);

        Caveira caveirao = new Caveira();
        caveirao.setPosicao(2,8);
        this.addPersonagem(caveirao);


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



                                      //cruz                             horizontal               "fase1"                 "fase2"                    "fase4"
        int[] linhas = new  int[]{0,1,2,3,4,5,6,7,8,9,10,12,13,14,  7,7,7,7,7,7,7,7, 7, 7, 7, 0,1,1,2,2,3,3,4,4,5,6,  9,9,9,11,11,13,12,12,13,   1,1,1 ,1, 1, 5, 5, 5, 5, 5 };
        int[] colunas = new int[]{7,7,7,7,7,7,7,7,7,7, 7, 7, 7, 7,  0,1,2,3,4,5,8,9,10,12,13, 3,1,5,1,4,1,3,0,3,2,1,  1,3,5, 3, 5, 5, 1, 2, 2,   8,9,10,11,12,10,11,12,13,14};

        for(int i=0; i < linhas.length; i++){
            BlocoVermelho b = new BlocoVermelho();
            b.setPosicao(linhas[i], colunas[i]);
            this.addBloco(b);
        }

        int[] linhas2 = new  int[]{1,7,7, 7, 11};
        int[] colunas2 = new int[]{6,6,11,14, 7};

        for(int i=0; i < linhas2.length; i++){
            BlocoVermelhoFurado b = new BlocoVermelhoFurado();
            b.setPosicao(linhas2[i], colunas2[i]);
            this.addBloco(b);
        }

        int[] linhas3 = new  int[]{12,14,10,10,12,12};
        int[] colunas3 = new int[]{0, 2, 10,12,10,12};

        for(int i=0; i < linhas3.length; i++){
            BlocoVerdeFurado b = new BlocoVerdeFurado();
            b.setPosicao(linhas3[i], colunas3[i]);
            this.addBloco(b);
        }

        int[] linhas4 = new  int[]{11,12,12,13};
        int[] colunas4 = new int[]{11,10,12,11};

        for(int i=0; i < linhas4.length; i++){
            BlocoVerde b = new BlocoVerde();
            b.setPosicao(linhas4[i]-1, colunas4[i]);
            this.addBloco(b);
        }


        Tangerina t = new Tangerina();
        t.setPosicao(3,0);
        frutas.add(t);

        Cereja c = new Cereja();
        c.setPosicao(14,0);
        frutas.add(c);

        Pirulito p = new Pirulito();
        p.setPosicao(11,11);
        frutas.add(p);

        Hamburguer h = new Hamburguer();
        h.setPosicao(0,8);
        frutas.add(h);

    }





}
