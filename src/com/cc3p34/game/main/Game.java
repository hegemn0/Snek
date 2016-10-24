package com.cc3p34.game.main;

import com.cc3p34.framework.util.Controle;
import com.cc3p34.framework.util.Escala;
import com.cc3p34.game.estado.State;
import com.cc3p34.game.estado.LoadState;
import static com.cc3p34.game.main.Main.frame;
import static com.cc3p34.game.main.Main.game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
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
    
    private static final int LARGURA_BASE = 800;
    private static final int ALTURA_BASE = 600;
    
    private static int largura = LARGURA_BASE;
    private static int altura = ALTURA_BASE;

    private static Escala escala;
    public static float deltaTempo; 
    public static Graphics2D tela;
    public static Image imagem;   
    private Thread threadJogo;
    private JFrame frame;
    private static Controle controle;
    private static volatile State estado;
    private volatile boolean executando;
    
    public Game() {  
        inicializarGUI();
    }
    
    private void inicializarGUI() {          
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(largura, altura));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus(); 
        
        frame = new JFrame("snek");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);      
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int LARGURA = ((JFrame)e.getSource()).getContentPane().getWidth();
                int ALTURA = ((JFrame)e.getSource()).getContentPane().getHeight();
                                                
                //escala = new Escala(LARGURA, ALTURA);
            }
        });        
    }    
    
    @Override
    public void run() {
        long duracaoAtualizacao = 0;
        long duracaoRepouso = 0;
                
        while(executando) {
            long antesAtualizacao = System.nanoTime();
            deltaTempo = (duracaoAtualizacao + duracaoRepouso) / 1000f;
            
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
        if(imagem == null) {
            imagem = createImage(largura, altura);
        }        
        tela = (Graphics2D)imagem.getGraphics();
        tela.fillRect(0, 0, largura, altura);        
    }
    
    private void renderizarImagem() {
        if(imagem != null) {
            getGraphics().drawImage(imagem, 0, 0, null);
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
    
    public static int getLargura() {
        return largura;
    }
    
    public static int getAltura() {
        return altura;
    }

    public static int getLarguraBase() {
        return LARGURA_BASE;
    }
    
    public static int getAlturaBase() {
        return ALTURA_BASE;
    } 
    
    /*    public static Double getEscalaX() {
    return escala.getX();
    }
    
    public static Double getEscalaY() {
    return escala.getY();
    }*/
}
