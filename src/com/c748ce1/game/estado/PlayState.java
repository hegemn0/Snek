package com.c748ce1.game.estado;

import com.c748ce1.framework.util.Util;
import com.c748ce1.game.main.Game;
import com.c748ce1.game.modelo.Snek;
import com.c748ce1.game.modelo.Apple;
import com.c748ce1.game.modelo.ParteSnek;
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
    
    private Snek snek;
    private Apple apple;
    
    @Override
    public void init() {                  
        snek = new Snek(20);
        apple = new Apple(snek);
    }; 
    
    @Override
    public void atualizar() {
        tempo -= Game.deltaT;
        if(tempo <= 0) {
            tempo = intervaloMovimentos;
            apple.atualizar();            
            snek.atualizar();
            detectarColisoes();            
        }
    }    
    
    @Override
    public void renderizar() {         
        renderizarBackground();
        apple.renderizar();        
        snek.renderizar();
    };
    
    private void renderizarBackground() {
        Game.tela.getGraphics().setColor(Color.BLACK);
        Game.tela.getGraphics().fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    }    
    
    @Override
    public void onKeyPress(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP: {
                snek.setDirecao(CIMA);
            } break;
            case KeyEvent.VK_DOWN: {
                snek.setDirecao(BAIXO);
            } break;
            case KeyEvent.VK_RIGHT: {
                snek.setDirecao(DIREITA);
            } break;
            case KeyEvent.VK_LEFT: {
                snek.setDirecao(ESQUERDA);
            } break;                                                            
        }
    };
    
    @Override
    public void onKeyRelease(KeyEvent e) {};    

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    private void detectarColisoes() {
        detectarColisaoBordas();
        detectarColisaoPartes();
        detectarColisaoMaca();
    }

    private void detectarColisaoBordas() {
        if(snek.getPosicaoX() >= Game.WIDTH) {
            snek.setPosicao(new Point(0, snek.getPosicaoY()));
        }
        
        if(snek.getPosicaoY() >= Game.HEIGHT) {
            snek.setPosicao(new Point(snek.getPosicaoX(), 0));
        }
        
        if(snek.getPosicaoX() < 0) {
            snek.setPosicao(new Point(Game.WIDTH - snek.getVelocidade(), snek.getPosicaoY()));
        }
        
        if(snek.getPosicaoY() < 0) {
            snek.setPosicao(new Point(snek.getPosicaoX(), Game.HEIGHT - snek.getVelocidade()));
        } 
    }

    private void detectarColisaoPartes() {
        for(ParteSnek parte : snek.getPartes()) {
            if(snek.getPosicao().equals(parte.getPosicao())) {
                System.out.println("Colisão com partes!");
                snek.reset();
            }
        }
    }

    private void detectarColisaoMaca() {
        if(snek.getRect().intersects(apple.getRect())) {
            apple.setPosicao(Util.gerarPosicaoMaca(snek));
            snek.setTamanho(snek.getTamanho()+1);
        }
    }
}
