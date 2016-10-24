package com.cc3p34.game.estado;

import com.cc3p34.framework.util.Recursos;
import com.cc3p34.game.main.Game;
import com.cc3p34.game.modelo.Snek;
import com.cc3p34.game.modelo.Apple;
import com.cc3p34.game.modelo.ParteSnek;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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

public class PlayState extends State {
        
    private final int CIMA = 1;
    private final int BAIXO = 2;
    private final int DIREITA = 3;
    private final int ESQUERDA = 4;
    private final float intervaloMovimentos = 0.25f;
    
    private float tempo = intervaloMovimentos;
    private int velocidade = 32;
    
    private KeyEvent tecla;
    private Snek snek;
    private Apple apple;
    
    @Override
    public void inicializar() {                  
        snek = new Snek(velocidade, 5);
        apple = new Apple(snek);
    }; 
    
    @Override
    public void atualizar() {
        tempo -= Game.deltaTempo;
        if(tempo <= 0) {            
            resetTempo();
            detectarControle();
            apple.atualizar();            
            snek.atualizar();               
        }
        detectarColisoes(); 
    }    
    
    @Override
    public void renderizar() { 
        renderizarBackground();      
        apple.renderizar();        
        snek.renderizar();
    };
    
    private void renderizarBackground() {
        Game.imagem.getGraphics().setColor(Color.BLACK);
        Game.imagem.getGraphics().fillRect(0, 0, Game.getLargura(), Game.getAltura());
    }    
    
    private void detectarControle() {
        if(tecla != null) {        
            switch(tecla.getKeyCode()) {
                case KeyEvent.VK_UP: {
                    if(snek.getDirecao() !=BAIXO){
                     snek.setDirecao(CIMA);   
                    }                
                } break;
                case KeyEvent.VK_DOWN: {
                    if(snek.getDirecao() !=CIMA){
                      snek.setDirecao(BAIXO);  
                    } 
                } break;
                case KeyEvent.VK_RIGHT: {
                    if(snek.getDirecao() !=ESQUERDA){
                      snek.setDirecao(DIREITA); 
                    }                 
                } break;
                case KeyEvent.VK_LEFT: {
                    if(snek.getDirecao() !=DIREITA){
                     snek.setDirecao(ESQUERDA); 
                    }
                } break;                                                            
            }
        }       
    }
    
    @Override
    public void onKeyPress(KeyEvent tecla) {
        this.tecla = tecla;
    };
    
    @Override
    public void onKeyRelease(KeyEvent e) {};    

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    private void detectarColisoes() {
        detectarColisaoMaca();
        detectarColisaoPartes();
        detectarColisaoBordas();        
    }

    private void detectarColisaoBordas() {
        if(snek.getPosicaoX() >= Game.getLargura()) {
            snek.setPosicao(new Point(0, snek.getPosicaoY()));
        }

        if(snek.getPosicaoX() < 0) {
            snek.setPosicao(new Point(Game.getLargura() - snek.getVelocidade(), snek.getPosicaoY()));
        }
        
        if(snek.getPosicaoY() >= Game.getAltura()) {
            snek.setPosicao(new Point(snek.getPosicaoX(), 0));
        }               
        
        if(snek.getPosicaoY() < 0) {
            snek.setPosicao(new Point(snek.getPosicaoX(), Game.getAltura() - snek.getVelocidade()));
        } 
    }

    private void detectarColisaoPartes() {
        if(snek.getEmMovimento()) {
            for(ParteSnek parte : snek.getPartesSnek()) {
                if(snek.getPosicao().equals(parte.getPosicao())) {
                    Recursos.executarAudioColisaoPartes();
                    snek.reset();
                }
            }
        }
    }

    private void detectarColisaoMaca() {
        if(snek.getRect().contains(apple.getPosicao())) {
            Recursos.executarAudioMordidaMaca();
            apple.reset();
            snek.adicionarParte();        
        }
    }
    
    private void resetTempo() {
        tempo = intervaloMovimentos;
    }
}
