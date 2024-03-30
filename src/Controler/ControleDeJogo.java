package Controler;

import Modelo.*;
import Auxiliar.Posicao;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ControleDeJogo {
    public void desenhaTudo(Fase e) {
        for (int i = 0; i < e.personagens.size(); i++) {
            e.personagens.get(i).autoDesenho();
        }
        for (int i = 0; i < e.blocos.size(); i++) {
            e.blocos.get(i).autoDesenho();
        }

        for (int i = 0; i < e.frutas.size(); i++) {
            e.frutas.get(i).autoDesenho();
        }

        for (int i = 0; i < e.getVidas(); i++) {
            e.vidasIcone.get(i).autoDesenho();
        }
        for (int i = 0; i < 4; i++) {
            e.pontosArray.get(i).autoDesenho();
        }
        for (int i = 0; i < e.letras.size(); i++) {
            e.letras.get(i).autoDesenho();
        }

        for (int i = 0; i < e.fundo.size(); i++) {
            e.fundo.get(i).autoDesenho();
        }

    }

    public void processaTudo(Fase umaFase) {
        Skoot skoot = (Skoot) umaFase.personagens.get(0);
        Personagem pIesimoPersonagem;
        for (int i = 1; i < umaFase.personagens.size(); i++) {
            pIesimoPersonagem = umaFase.personagens.get(i);
            if (skoot.getPosicao().igual(pIesimoPersonagem.getPosicao()))
                if (pIesimoPersonagem.isbTransponivel()) {
                    if (!pIesimoPersonagem.isbMortal()) //FOI ALTERADO, TÁ CERTO?
                        umaFase.personagens.remove(pIesimoPersonagem);
                }
        }

    }

    public Fase verificaProximaFase(Fase umaFase) {

        if (umaFase.pontuacao > umaFase.recorde) {
            umaFase.recorde = umaFase.pontuacao;
            umaFase.escreveRecorde();
        }

        if(!(umaFase instanceof Fase0) && !(umaFase instanceof TelaVitoria) && !(umaFase instanceof TelaGameOver))
            salvaFaseAtual(umaFase);

        if (umaFase.pontuacao == 51 && umaFase instanceof Fase1) {
            umaFase = processaFase(umaFase);
            umaFase = new Fase2();
            umaFase = inicializaFase(umaFase);
        } else if (umaFase.pontuacao == 202 && umaFase instanceof Fase2) {
            umaFase = processaFase(umaFase);
            umaFase = new Fase3();
            umaFase = inicializaFase(umaFase);
        } else if (umaFase.pontuacao == 378 && umaFase instanceof Fase3) {
            umaFase = processaFase(umaFase);
            umaFase = new Fase4();
            umaFase = inicializaFase(umaFase);
        } else if (umaFase.pontuacao == 464 && umaFase instanceof Fase4) {
            umaFase = processaFase(umaFase);
            umaFase = new Fase5();
            umaFase = inicializaFase(umaFase);
        }

        return umaFase;
    }

    public Fase verificaGameOver(Fase fase) {
        if (fase.getVidas() <= 0 && !(fase instanceof Fase0) && !(fase instanceof TelaVitoria)) {

            if (fase.pontuacao > fase.recorde) {
                fase.recorde = fase.pontuacao;
                fase.escreveRecorde();
            }

            fase.clear();
            fase = new TelaGameOver();
            fase.criaObjetos();
            limpaArquivo();

        } else if (fase.pontuacao >= 562) {

            if (fase.pontuacao > fase.recorde) {
                fase.recorde = fase.pontuacao;
                fase.escreveRecorde();
            }

            fase.pontuacao = 0;
            fase.clear();
            fase = new TelaVitoria();
            fase.criaObjetos();
            limpaArquivo();
        }

        return fase;
    }

    public Fase processaFase(Fase umaFase){
        salvaFaseAtual(umaFase);
        umaFase.clear();
        return umaFase;
    }

    public Fase inicializaFase(Fase umaFase){
        Fase f = leUmaFase();
        umaFase.pontuacao = f.pontuacao;
        umaFase.vidas = f.vidas;
        umaFase.inicializaFase();
        umaFase.criaObjetos();
        return umaFase;
    }

    public void limpaArquivo() {
        File f = new File("fase.txt");
        f.delete();
    }

    public void salvaFaseAtual(Fase faseAtual) {
        File f = new File("fase.txt");
        try {
            FileOutputStream g = new FileOutputStream(f);
            GZIPOutputStream zip = new GZIPOutputStream(g);
            ObjectOutputStream serializador = new ObjectOutputStream(zip);
            serializador.writeObject(faseAtual);
            serializador.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Fase leUmaFase() {
        File f = new File("fase.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
            return new Fase0();
        }else {
            try {
                FileInputStream g = new FileInputStream(f);
                GZIPInputStream zip = new GZIPInputStream(g);
                ObjectInputStream deserializador = new ObjectInputStream(zip);
                Fase umaFase = (Fase) deserializador.readObject();
                deserializador.close();
                return umaFase;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /*Retorna true se a posicao p é válida para Skoot com relacao a todos os personagens no array*/
    public boolean ehPosicaoValida(Fase umaFase, Posicao p, int direcao) {

        Personagem pIesimoPersonagem;
        for (int i = 1; i < umaFase.personagens.size(); i++) {
            pIesimoPersonagem = umaFase.personagens.get(i);
            if (!pIesimoPersonagem.isbTransponivel())
                if (pIesimoPersonagem.getPosicao().igual(p))
                    return false;
        }

        Comida pIesimaFruta;
        for(int i=0; i<umaFase.frutas.size(); i++){
            pIesimaFruta = umaFase.frutas.get(i);
            if (pIesimaFruta.getPosicao().igual(p)){
                umaFase.pontuacao += pIesimaFruta.getPontos();
                umaFase.removeFruta(pIesimaFruta);
                umaFase.atualizaPontuacao();
            }
        }

        Bloco pIesimoBloco;
        for (int i = 0; i < umaFase.blocos.size(); i++) {
            pIesimoBloco = umaFase.blocos.get(i);
            if (!pIesimoBloco.isbTransponivel())
                if (pIesimoBloco.isbArrastavel()) {
                    if (pIesimoBloco.getPosicao().igual(p)) {
                        switch (direcao) {
                            case 1:
                                if (pIesimoBloco.moveRight())
                                    return true;
                                break;
                            case 2:
                                if (pIesimoBloco.moveLeft())
                                    return true;
                                break;
                            case 3:
                                if (pIesimoBloco.moveUp())
                                    return true;
                                break;
                            case 4:
                                if (pIesimoBloco.moveDown())
                                    return true;
                                break;
                        }
                    }
                }
            //para bloco flecha
            if(pIesimoBloco.isbTransponivel()){
                if (pIesimoBloco.getPosicao().igual(p)){
                    pIesimoBloco.andaPersonagem(umaFase.skoot);
                    return true;
                }
            }

            if (pIesimoBloco.getPosicao().igual(p))
                return false;

        }


        return true;
    }

    //se um bloco pode andar para essa posicao p
    public boolean ehPosicaoValidaBloco(Fase umaFase, Posicao p, Bloco atual) {

        Personagem pIesimoPersonagem;
        for(int i = 1; i < umaFase.personagens.size(); i++){
            pIesimoPersonagem = umaFase.personagens.get(i);

            if(pIesimoPersonagem.getPosicao().igual(p))
                return false;
        }

        Bloco pIesimoBloco;
        for (int i = 0; i < umaFase.blocos.size(); i++) {
            pIesimoBloco = umaFase.blocos.get(i);
            if (pIesimoBloco.getPosicao().igual(p)) { //eh o bloco que me interessa avaliar?
                if (pIesimoBloco != atual) {
                    return false;
                }
            }

        }

        Comida pIesimaFruta;
        for(int i=0; i<umaFase.frutas.size(); i++){
            pIesimaFruta = umaFase.frutas.get(i);
            if (pIesimaFruta.getPosicao().igual(p)){
                return false;
            }
        }

        return true;
    }

    public boolean ehPosicaoValidaMonstro(Fase umaFase, Posicao p, Personagem atual) {
        Personagem pIesimoPersonagem;
        for (int i = 0; i < umaFase.personagens.size(); i++) {
            pIesimoPersonagem = umaFase.personagens.get(i);
            if (pIesimoPersonagem.getPosicao().igual(p)) { //eh o bloco que me interessa avaliar?
                if (pIesimoPersonagem == umaFase.skoot) {
                    return true;
                }
                if (pIesimoPersonagem != atual) {
                    return false;
                }
            }
        }

        Comida pIesimaFruta;
        for(int i=0; i<umaFase.frutas.size(); i++){
            pIesimaFruta = umaFase.frutas.get(i);
            if (pIesimaFruta.getPosicao().igual(p)){
                return false;
            }
        }

        Bloco pIesimoBloco;
        for (int i = 0; i < umaFase.blocos.size(); i++) {
            pIesimoBloco = umaFase.blocos.get(i);
            //para bloco flecha
            if(pIesimoBloco.isbTransponivel()){
                if (pIesimoBloco.getPosicao().igual(p)){
                    pIesimoBloco.andaPersonagem(atual);
                    return true;
                }
            }
            if (pIesimoBloco.getPosicao().igual(p)) { //eh o bloco que me interessa avaliar?
                return false;
            }
        }

        return true;
    }
//            if(!pIesimoBloco.isbTransponivel()) {
//                if (pIesimoBloco.getPosicao().igual(p))
//                    if(pIesimoBloco instanceof BlocoVerdeFurado && pIesimoBloco.getCodigo() == codigo) //se o bloco eh ele msm
//                        return true;
//                    else return false;
//            }
//        }
//        return true;



    public boolean verificaMorte(Fase umaFase, Posicao pSkooter){

        Personagem pIesimoMonstro;
        for(int i =1; i<umaFase.personagens.size(); i++){
            pIesimoMonstro = umaFase.personagens.get(i);
            if(pIesimoMonstro.getPosicao().igual(pSkooter)){
                return true;
            }
        }

        return false;
    }
}
