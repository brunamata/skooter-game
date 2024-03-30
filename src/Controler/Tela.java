package Controler;

import Modelo.*;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Auxiliar.Posicao;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

    private Skoot skoot;
    public Fase faseAtual;
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;

    private String image;

    public Tela(String image) {
        this.image = image;
        Desenho.setCenario(this);
        initComponents();
        this.addMouseListener(this); /*mouse*/

        this.addKeyListener(this);   /*teclado*/
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.RES_COLUNA * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES_LINHA * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        faseAtual = new Fase0();
        faseAtual.criaObjetos();

    }

    public boolean ehPosicaoValida(Posicao p, int direcao){
        return cj.ehPosicaoValida(this.faseAtual, p, direcao);
    }

    public boolean ehPosicaoValidaBloco(Posicao p, Bloco b){
        return cj.ehPosicaoValidaBloco(this.faseAtual, p, b);
    }

    public boolean ehPosicaoValidaMonstro(Posicao p, Personagem zz) {
        return cj.ehPosicaoValidaMonstro(this.faseAtual, p, zz);
    }

    public void quebrarBlocoSePossivel(Posicao pDirecao){
        for(int i =0 ; i<faseAtual.blocos.size(); i++){
            Bloco pIesimoBloco = faseAtual.blocos.get(i);
            if(pIesimoBloco.isbQuebravel()){
                if(pIesimoBloco.getPosicao().getLinha() == pDirecao.getLinha() && pIesimoBloco.getPosicao().getColuna() == pDirecao.getColuna()){
                    faseAtual.blocos.remove(pIesimoBloco);
                }
            }
        }
    }

    public void verificaMorte(Posicao p){
        if (cj.verificaMorte(this.faseAtual, p)) {
            this.faseAtual.setVidas(this.faseAtual.getVidas() - 1);
            this.faseAtual.reiniciaPersonagens();
            this.faseAtual.removeVida();
        }
    }

    public void verificaMonstroMorte(Posicao p){
        if (this.faseAtual.skoot.getPosicao().igual(p)) {
            this.faseAtual.setVidas(this.faseAtual.getVidas() - 1);
            this.faseAtual.reiniciaPersonagens();
            this.faseAtual.removeVida();
        }
    }

    public Fase getFaseAtual(){
        return faseAtual;
    }


    public Graphics getGraphicsBuffer(){
        return g2;
    }
    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
        /*************Desenha cenário de fundo**************/
        for (int i = 0; i < Consts.RES_LINHA; i++) {
            for (int j = 0; j < Consts.RES_COLUNA; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + image);
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);

                    if(j >= 15){
                        Image menuImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "black.png");
                        g2.drawImage(menuImage,
                                j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (!this.faseAtual.isEmpty()) {
            this.cj.desenhaTudo(faseAtual);
            this.cj.processaTudo(faseAtual); //processar os blocos tbm?
            this.faseAtual = this.cj.verificaGameOver(faseAtual);
            this.faseAtual = this.cj.verificaProximaFase(faseAtual);
            }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    public void go() {
        TimerTask task = new TimerTask() {
            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.PERIOD);
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_P) {
            if (faseAtual instanceof Fase0) {
                cj.limpaArquivo();
                this.faseAtual.fundo.clear();
                faseAtual = new Fase1();
                faseAtual.criaObjetos();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_K) {
            if (faseAtual instanceof Fase0) {
                if(!(new File("fase.txt").length() == 0)){
                    this.faseAtual.clear();
                    faseAtual = cj.leUmaFase();
                }
            }
        } else if(e.getKeyCode() == KeyEvent.VK_R){
            if (faseAtual instanceof TelaVitoria || faseAtual instanceof TelaGameOver){
                this.faseAtual.clear();
                this.faseAtual = new Fase0();
                this.faseAtual.criaObjetos();
            }
        }
            else if (e.getKeyCode() == KeyEvent.VK_UP) {
                this.faseAtual.skoot.moveUp();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                this.faseAtual.skoot.moveDown();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                this.faseAtual.skoot.moveLeft();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.faseAtual.skoot.moveRight();
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                this.faseAtual.skoot.breakBlock();
            }

            this.setTitle("-> Cell: " + (this.faseAtual.skoot.getPosicao().getColuna()) + ", "
                    + (this.faseAtual.skoot.getPosicao().getLinha()));

            //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/


        }

    public void mousePressed(MouseEvent e) {
        /* Clique do mouse desligado*/
         int x = e.getX();
         int y = e.getY();
     
         this.setTitle("X: "+ x + ", Y: " + y +
         " -> Cell: " + (y/Consts.CELL_SIDE) + ", " + (x/Consts.CELL_SIDE));

         this.skoot.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
         
        repaint();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2023-1 - Skooter");
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

}
