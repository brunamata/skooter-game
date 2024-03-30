package Controler;

import Modelo.*;

import java.io.*;
import java.util.ArrayList;

public abstract class Fase implements Serializable {

    protected ArrayList<Personagem> personagens;
    protected ArrayList<Bloco> blocos;
    protected ArrayList<Comida> frutas;
    protected ArrayList<Life> vidasIcone;
    protected ArrayList<Pontos> pontosArray;
    protected ArrayList<Letra> letras;
    protected ArrayList<Fundo> fundo; //apenas para tela
    protected Skoot skoot;
    protected int pontuacao;
    protected int recorde;
    protected int vidas;

    Fase(){
        this.pontuacao = 0;
        personagens = new ArrayList<>();
        blocos = new ArrayList<>();
        frutas = new ArrayList<>();
        vidasIcone = new ArrayList<>();
        pontosArray = new ArrayList<>();
        letras = new ArrayList<>();
        fundo = new ArrayList<>();
        recorde = leRecorde();

    }

    public void desenhaLogo(){
        for(int linha = 10; linha<15; linha++){
            for(int coluna = 15; coluna<20; coluna++){
                Fundo f = new Fundo("menuDireita/row-"+(linha-9)+"-column-"+(coluna-14)+".png");
                f.setPosicao(linha, coluna);
                fundo.add(f);
            }
        }
    }

    public void inicializaFase(){
        atualizaVidasIcone();
        atualizaPontuacao();
        setLetras();
    }


    //escreve as palavras no menu lateral
    public void setLetras(){
    String pontos = "SCORE";
    for(int i=0, coluna=15; i<pontos.length(); i++, coluna++){
        Letra p = new Letra(pontos.charAt(i));
        p.setPosicao(1, coluna);
        letras.add(p);
    }

      String vida = "VIDAS";
        for(int i=0, coluna=15; i<vida.length(); i++, coluna++){
            Letra p = new Letra(vida.charAt(i));
            p.setPosicao(4, coluna);
            letras.add(p);
        }

    }

    //inicializa a pontuacao com 0 no menu lateral
    public void inicializaPontuacao(){
        Pontos unidade = new Pontos(0);
        unidade.setPosicao(2,19);
        Pontos dezena = new Pontos(0);
        dezena.setPosicao(2, 18);
        Pontos centena = new Pontos(0);
        centena.setPosicao(2,17);
        Pontos milhar = new Pontos(0);
        milhar.setPosicao(2,16);

        pontosArray.add(milhar);
        pontosArray.add(centena);
        pontosArray.add(dezena);
        pontosArray.add(unidade);

    }

    //de acordo com a pontuacao atual, reimprime para contabilizar os pontos
    public void atualizaPontuacao(){
        if(pontosArray.isEmpty()){
            inicializaPontuacao();
        }

        String pontos = Integer.toString(pontuacao);

        while(pontos.length() < 4){
            pontos = "0"+pontos;
        }

        for(int i=0; i<4; i++){
            pontosArray.get(i).valor = Integer.parseInt(String.valueOf(pontos.charAt(i)));
        }

    }


    //escreve o valor da pontuacao recorde na tela inicial e nas finais
    public void escreveRecorde(){
        File f = new File("recorde.txt");

        //se o arquivo ainda n existe (primeira vez jogando, o recorde eh 0)
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            FileWriter arq = new FileWriter("recorde.txt");
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.printf("%d", recorde);
            arq.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    //le o arquivo que armazena a pontuacao recorde do jogador
    public int leRecorde() {
        File f = new File("recorde.txt");

        //se o arquivo ainda n existe (primeira vez jogando, o recorde eh 0)
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                return 0;
            }

        //pega o valor int no arquivo
        } else {
            int input = 0;
            try {
                FileReader fReader = new FileReader("recorde.txt");
                BufferedReader in = new BufferedReader(fReader);
                input = Integer.valueOf(in.readLine());
                fReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return input;
        }
    }

    //atualiza quantos icones de vida serao impressos no menu lateral
    public void atualizaVidasIcone(){
        int coluna = 19;
        for(int i=0; i<vidas; i++){
            Life l = new Life();
            l.setPosicao(5,coluna);
            vidasIcone.add(l);

            coluna--;
        }
    }

    //diminui 1 na vida do personagem
    public void removeVida(){
        if(vidas>0){
            vidasIcone.remove(vidas);
        }
    }


    public int getVidas(){
        return this.vidas;
    }

    public void setVidas(int vida) {
        this.vidas = vida;
        if(this.vidas < 0) {
            this.vidas = 0;
        }
    }

    public abstract void criaObjetos();

    public abstract void reiniciaPersonagens();

    public void addPersonagem(Personagem umPersonagem) {
        personagens.add(umPersonagem);
    }

    public void removePersonagem(Personagem umPersonagem) {
        personagens.remove(umPersonagem);
    }

    public void addBloco(Bloco umBloco) {
        blocos.add(umBloco);
    }

    public void removeBloco(Bloco umBloco) {
        blocos.remove(umBloco);
    }


    public void addFruta(Comida umaFruta) {
        frutas.add(umaFruta);
    }

    public void removeFruta(Comida umaFruta) {
        frutas.remove(umaFruta);
    }

    public boolean isEmpty(){
        return personagens.isEmpty() && blocos.isEmpty() && frutas.isEmpty() && fundo.isEmpty();
    }

    public void clear(){
        personagens.clear();
        blocos.clear();
        frutas.clear();
        fundo.clear();
    }
}
