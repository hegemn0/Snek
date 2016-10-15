package com.cc3p34.game.main;

import com.cc3p34.framework.util.Controle;
import com.cc3p34.game.estado.State;
import com.cc3p34.game.estado.LoadState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @autor
 * NOME:    Anderson do Nascimento Silva
 * TURNO:   NOTURNO
 * PERÍODO: 3º
 * TURMA:   CC3P34
 * RA:      C743CE-1
 * E-MAIL:  hegemn0@gmail.com
 * DATA:    03/10/2016
 * 
 */

@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable {
    
    public static final String DIR_IMAGENS = "/recursos/imagem/";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final Point CENTRO = new Point(WIDTH/2, HEIGHT/2);
    public static final Dimension RESOLUCAO = new Dimension(WIDTH, HEIGHT); 
    
    public static float deltaT;    
    public static Image tela;   
    private Thread threadJogo;
    private static Controle controle;
    private static volatile State estado;
    private volatile boolean executando;
    
    public Game() {  
        inicializarGUI();
    }
    
    private void inicializarGUI() {        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus(); 
    }    
    
    @Override
    public void run() {
        long duracaoAtualizacao = 0;
        long duracaoRepouso = 0;
                
        while(executando) {
            long antesAtualizacao = System.nanoTime();
            deltaT = (duracaoAtualizacao + duracaoRepouso) / 1000f;
            
            atualizarERenderizar();
            
            duracaoAtualizacao = (System.nanoTime() - antesAtualizacao) / 1000000L;
            duracaoRepouso = Math.max(2, 17 - duracaoAtualizacao);
            
            try {
                Thread.sleep(duracaoRepouso);     
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }        
        System.exit(0);
    }

    private void atualizarERenderizar() {
        estado.atualizar();
        prepararImagem();
        estado.renderizar();
        renderizarImagem();
    }
    
    private void prepararImagem() {
        if(tela == null) {
            tela = createImage(WIDTH, HEIGHT);
        }
        Graphics2D g = (Graphics2D)tela.getGraphics();
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
    
    private void renderizarImagem() {
        if(tela != null) {
            getGraphics().drawImage(tela, 0, 0, null);
        }
        getGraphics().dispose();        
    }
    
    public static void setEstado(State novoEstado) {
        System.gc();
        novoEstado.inicializar();
        estado = novoEstado;
        controle.setEstado(estado);
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        inicializarControle();
        setEstado(new LoadState());
        inicializarJogo();
    }
    
    private void inicializarJogo() {
        executando = true;
        threadJogo = new Thread(this, "Thread Jogo");
        threadJogo.start();
    }
    
    private void inicializarControle() {
        controle = new Controle();
        addKeyListener(controle);
        addMouseListener(controle);
    }
    
    public void finalizar() {
        executando = false;
    }
    
}
