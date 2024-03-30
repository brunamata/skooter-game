package Controler;

import Modelo.*;

import java.io.Serializable;

public class Fase2 extends Fase implements Serializable {

    Fase2(){
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
        skoot.setPosicao(7, 7);
        this.addPersonagem(skoot);

        Monstro m1 = new Monstro("Amarelo");
        m1.setPosicao(0, 15);
        this.addPersonagem(m1);

        Monstro m2 = new Monstro("Amarelo");
        m2.setPosicao(3, 4);
        this.addPersonagem(m2);

        Monstro m3 = new Monstro("Rosa");
        m3.setPosicao(9, 10);
        this.addPersonagem(m3);

        Monstro m4 = new Monstro("Azul");
        m4.setPosicao(14, 0);
        this.addPersonagem(m4);


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



        for(int i = 5; i < 10; i++) {
            BlocoFlechaDireita bfb = new BlocoFlechaDireita();
            bfb.setPosicao(1, i);
            this.addBloco(bfb);
        }

        for(int i = 5; i < 10; i++) {
            BlocoFlechaCima bfb = new BlocoFlechaCima();
            bfb.setPosicao(i, 1);
            this.addBloco(bfb);
        }

        for(int i = 5; i < 10; i++) {
            BlocoFlechaEsquerda bfb = new BlocoFlechaEsquerda();
            bfb.setPosicao(13, i);
            this.addBloco(bfb);
        }

        for(int i = 5; i < 10; i++) {
            BlocoFlechaBaixo bfb = new BlocoFlechaBaixo();
            bfb.setPosicao(i, 13);
            this.addBloco(bfb);
        }

        int[] colunas = new int[]{3,3,3,3,3,3,3,3,3,4,5,6,7,8,9,10,11,4,11,11,11,11,11,11,11,11,5,6,7,8,9,10};
        int[] linhas = new int[]{4,5,6,7,8,9,10,11,12,4,4,4,4,4,4,4,4,12,5,6,7,8,9,10,11,12,12,12,12,12,12,12};

        for(int i=0; i < linhas.length; i++){
            BlocoVerde b = new BlocoVerde();
            b.setPosicao(linhas[i]-1, colunas[i]);
            this.addBloco(b);
        }

        int[] linhas2 = new int[]{ 8,6,7,7 };
        int[] colunas2 = new int[]{ 7,7,6,8 };

        for(int i=0; i < linhas2.length; i++){
            BlocoVerdeFurado b = new BlocoVerdeFurado();
            b.setPosicao(linhas2[i], colunas2[i]);
            this.addBloco(b);
        }

        Cereja c = new Cereja();
        c.setPosicao(0,7);
        frutas.add(c);

        Cereja c2 = new Cereja();
        c2.setPosicao(7,14);
        frutas.add(c2);

        Tangerina t = new Tangerina();
        t.setPosicao(2,12);
        frutas.add(t);

        Tangerina t2 = new Tangerina();
        t2.setPosicao(14,7);
        frutas.add(t2);

        Limao l = new Limao();
        l.setPosicao(7,0);
        frutas.add(l);

        Limao l2 = new Limao();
        l2.setPosicao(12,12);
        frutas.add(l2);

        Hamburguer h = new Hamburguer();
        h.setPosicao(12,2);
        frutas.add(h);

        Pirulito p = new Pirulito();
        p.setPosicao(2,2);
        frutas.add(p);

    }




}
