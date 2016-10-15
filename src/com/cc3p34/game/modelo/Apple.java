package com.cc3p34.game.modelo;

import com.cc3p34.framework.util.Util;
import com.cc3p34.game.main.Game;
import com.cc3p34.framework.util.Recursos;
import java.awt.Rectangle;

public class Apple extends ObjetoJogo { 
    private final Snek snake;
    
    public Apple(Snek snake) {
        this.textura = Recursos.apple;
        this.snake = snake;
        this.posicao = Util.gerarPosicaoMaca(snake);
        this.rect = new Rectangle(posicao.x, posicao.y, 20, 20);
    }
    
    @Override
    public void renderizar() {
        Game.tela.getGraphics().drawImage(textura, posicao.x, posicao.y, null);
    }

    @Override
    public void atualizar() {
        atualizarRect();
    }
    
    private void atualizarRect() {
        rect.setBounds(posicao.x, posicao.y, 20, 20);
    }
        
}
