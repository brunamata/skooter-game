package Controler;

import Modelo.*;

import java.io.Serializable;

public class Fase3 extends Fase implements Serializable {

    Fase3(){
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
        m1.setPosicao(5, 7);
        this.addPersonagem(m1);

        Monstro m2 = new Monstro("Verde");
        m2.setPosicao(7, 14);
        this.addPersonagem(m2);

        Monstro m3 = new Monstro("Rosa");
        m3.setPosicao(7, 0);
        this.addPersonagem(m3);

        Monstro m4 = new Monstro("Azul");
        m4.setPosicao(14, 8);
        this.addPersonagem(m4);

        Caveira c = new Caveira();
        c.setPosicao(1,0);
        this.addPersonagem(c);

        Caveira c2 = new Caveira();
        c2.setPosicao(4,1);
        this.addPersonagem(c2);

        Caveira c3 = new Caveira();
        c3.setPosicao(10,1);
        this.addPersonagem(c3);

        Caveira c4 = new Caveira();
        c4.setPosicao(13,0);
        this.addPersonagem(c4);



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




        int[] linhas = new  int[]{2,2,2,2,6,6,6,9,9,9,12,12,12,12};
        int[] colunas = new int[]{3,5,9,11,4,7,10,4,7,10,3,5,9,11};

        for(int i=0; i < linhas.length; i++){
            BlocoVermelho b = new BlocoVermelho();
            b.setPosicao(linhas[i], colunas[i]);
            this.addBloco(b);
        }

        int[] linhas2 = new  int[]{2,2,2,2,2,2,2,2,2,7,7,8,8,12};
        int[] colunas2 = new int[]{0,1,2,6,7,8,12,13,14,6,8,6,8,7};

        for(int i=0; i < linhas2.length; i++){
            BlocoVerdeFurado b = new BlocoVerdeFurado();
            b.setPosicao(linhas2[i], colunas2[i]);
            this.addBloco(b);
        }

        int[] linhas3 = new  int[]{1,1,3,3,11,11,13,13};
        int[] colunas3 = new int[]{4,10,4,10,4,10,4,10};

        for(int i=0; i < linhas3.length; i++){
            BlocoVerde b = new BlocoVerde();
            b.setPosicao(linhas3[i], colunas3[i]);
            this.addBloco(b);
        }


        Limao l = new Limao();
        l.setPosicao(2,4);
        frutas.add(l);

        Tangerina t = new Tangerina();
        t.setPosicao(2,10);
        frutas.add(t);

        Pirulito p = new Pirulito();
        p.setPosicao(12,4);
        frutas.add(p);

        Pirulito p2 = new Pirulito();
        p2.setPosicao(12,10);
        frutas.add(p2);

        Cereja c = new Cereja();
        c.setPosicao(7,7);
        frutas.add(c);

        Cereja c2 = new Cereja();
        c2.setPosicao(8,7);
        frutas.add(c2);

        Hamburguer h = new Hamburguer();
        h.setPosicao(14,7);
        frutas.add(h);

    }





}
