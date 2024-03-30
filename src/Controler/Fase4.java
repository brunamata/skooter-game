package Controler;

import Modelo.*;

import java.io.Serializable;

public class Fase4 extends Fase implements Serializable {

    Fase4(){
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
        skoot.setPosicao(0, 7);
        this.addPersonagem(skoot);

        Monstro m1 = new Monstro("Amarelo");
        m1.setPosicao(2, 7);
        this.addPersonagem(m1);

        Monstro m2 = new Monstro("Verde");
        m2.setPosicao(4, 14);
        this.addPersonagem(m2);

        Monstro m3 = new Monstro("Azul");
        m3.setPosicao(6, 0);
        this.addPersonagem(m3);

        Monstro m4 = new Monstro("Rosa");
        m4.setPosicao(8, 7);
        this.addPersonagem(m4);

        Monstro m5 = new Monstro("Amarelo");
        m5.setPosicao(10, 10);
        this.addPersonagem(m5);


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




        int[] linhas = new  int[]{1,1,1,1,1,1,1,1, 1, 1, 1, 1, 1,  3,3,3,3,3,3,3,3, 3, 3, 3, 3, 5,5,5,5,5,5,5,5,5, 5, 5, 5, 5,  7,7,7,7,7,7,7,7,7, 7, 7, 7, 7, 9,9,9,9,9,9,9,9,9, 9, 9, 9, 9, 11,11,11,11,11,11,11,11,11,11,11,11,11,11};
        int[] colunas = new int[]{0,1,2,3,4,6,7,8,10,11,12,13,14,  0,1,3,4,5,6,8,9,10,11,13,14, 0,1,2,3,5,6,7,8,9,11,12,13,14,  0,2,3,4,5,6,7,8,9,10,11,12,14, 0,1,2,4,5,6,7,8,9,10,12,13,14, 0,1,2,3,4,5,6,8,9,10,11,12,13,14};

        for(int i=0; i < linhas.length; i++){
            BlocoVermelho b = new BlocoVermelho();
            b.setPosicao(linhas[i], colunas[i]);
            this.addBloco(b);
        }

        int[] linhas2 = new  int[]{1,1,3,3,3,5,5,7,7,9,9,11};
        int[] colunas2 = new int[]{5,9,2,7,12,4,10,1,13,3,11,7};

        for(int i=0; i < linhas2.length; i++){
            BlocoVermelhoFurado b = new BlocoVermelhoFurado();
            b.setPosicao(linhas2[i], colunas2[i]);
            this.addBloco(b);
        }


       Tangerina t = new Tangerina();
        t.setPosicao(13,6);
        frutas.add(t);

        Limao l = new Limao();
        l.setPosicao(13,7);
        frutas.add(l);

        Cereja c = new Cereja();
        c.setPosicao(13,8);
        frutas.add(c);

        Cereja c2 = new Cereja();
        c2.setPosicao(14,6);
        frutas.add(c2);

        Hamburguer h = new Hamburguer();
        h.setPosicao(14,7);
        frutas.add(h);

        Tangerina t2 = new Tangerina();
        t2.setPosicao(14,8);
        frutas.add(t2);

    }





}
