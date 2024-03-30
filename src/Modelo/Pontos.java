package Modelo;

import java.io.Serializable;

public class Pontos extends IconesMenu implements Serializable{

    public int valor;

    public Pontos(int valor) {
        super("black.png");
        this.valor = valor;
    }

    public void alterarIcone(){
        String endereco = "numeros/" + valor + ".png";
        super.alteraIcone(endereco);
    }

    @Override
    public void autoDesenho() {
        alterarIcone();
        super.autoDesenho();
    }

}
