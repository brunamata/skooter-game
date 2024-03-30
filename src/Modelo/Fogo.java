package Modelo;

import Auxiliar.Desenho;
import Auxiliar.Posicao;

import java.io.Serializable;

public class Fogo extends Personagem implements Serializable{
            
    public Fogo(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bMortal = false;
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
        Posicao aux = new Posicao(pPosicao.getLinha(), pPosicao.getColuna()+1);
        Desenho.acessoATelaDoJogo().verificaMonstroMorte(aux);
        if(!this.moveRightFogo())
            Desenho.acessoATelaDoJogo().getFaseAtual().removePersonagem(this);
    }
    
}
