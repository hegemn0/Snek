package com.cc3p34.game.modelo;

import com.cc3p34.game.main.Game;
import com.cc3p34.framework.util.Recursos;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Apple extends ObjetoJogo { 
    private final Snek snake;
    
    public Apple(Snek snake) {
        this.textura = Recursos.apple;
        this.snake = snake;
        this.posicao = gerarPosicao();
        this.rect = new Rectangle(posicao.x, posicao.y, 32, 32);
    }
    
    @Override
    public void renderizar() {
        Game.tela.drawImage(textura, posicao.x, posicao.y, null);        
    }

    @Override
    public void atualizar() {
        atualizarRect();
    }
    
    private void atualizarRect() {
        rect.setBounds(posicao.x, posicao.y, 32, 32);
    }

    public void reset() {
        novaPosicao();
    }
    
    private void novaPosicao() {
        this.posicao = gerarPosicao();
    }
    
    private Point gerarPosicao() {
        Point novaPosicao;
        
        do {
            novaPosicao = new Point(
                    new Random().nextInt(Game.getLargura() / snake.getVelocidade() - 1) * snake.getVelocidade(),
                    new Random().nextInt(Game.getAltura() / snake.getVelocidade() - 1) * snake.getVelocidade());            
        } while(conferirColisoes(novaPosicao));
        
        return novaPosicao;
    }

    private boolean conferirColisoes(Point novaPosicao) {
        if(snake.getRect().contains(novaPosicao)) {
            return true;
        } else {        
            for(ParteSnek parte: snake.getPartesSnek()) {
                if(parte.getRect().contains(novaPosicao)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
