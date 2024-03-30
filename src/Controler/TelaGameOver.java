package Controler;

import Modelo.Fundo;
import Modelo.Monstro;
import Modelo.Pontos;
import Modelo.Skoot;

import java.io.Serializable;

public class TelaGameOver extends Fase implements Serializable {

    TelaGameOver(){
        super();
    }

    public void inicializaFase(){
        desenhaLogo();
        atualizaVidasIcone();
        atualizaPontuacao();
        setLetras();
    }

    public void criaPersonagens() {
    }

    public void reiniciaPersonagens() {
        // apagarPersonagens();
        //apagar arrayPersonagens();
        this.personagens.clear();
        criaPersonagens();
    }


    public void criaObjetos() {

        skoot = new Skoot();
        skoot.setPosicao(13,13);
        this.personagens.add(skoot);

        inicializaPontuacao();

        String sRecorde = Integer.toString(recorde);

        while(sRecorde.length() < 4){
            sRecorde = "0"+sRecorde;
        }

        for(int linha = 1; linha<= 15; linha++){
            for(int coluna = 1; coluna <= 20; coluna++){

                if(linha == 14 && coluna > 10 && coluna <15){

                    Pontos p = new Pontos(Integer.parseInt(String.valueOf(sRecorde.charAt(coluna-11))));
                    p.setPosicao(linha-1, coluna-1);
                    pontosArray.set(coluna-11, p);
                    continue;
                }

                String endereco = "telaDerrota/row-" + linha + "-column-" + coluna + ".png";
                Fundo i = new Fundo(endereco);
                i.setPosicao(linha-1, coluna-1);
                this.fundo.add(i);

            }
        }



    }

}
