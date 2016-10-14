package com.c748ce1.game.modelo;

import com.c748ce1.framework.util.Util;
import com.c748ce1.game.main.Game;
import com.c748ce1.framework.util.Recursos;
import java.awt.Rectangle;

public class Apple extends ObjetoJogo { 
    private final Snek snake;
    
    public Apple(Snek snake) {
        this.imagem = Recursos.blocoapple;
        this.snake = snake;
        this.posicao = Util.gerarPosicaoMaca(snake);
        this.rect = new Rectangle(posicao.x, posicao.y, 20, 20);
    }
    
    @Override
    public void renderizar() {
        Game.tela.getGraphics().drawImage(imagem, posicao.x, posicao.y, null);
    }

    @Override
    public void atualizar() {
        atualizarRect();
    }
    
    private void atualizarRect() {
        rect.setBounds(posicao.x, posicao.y, 20, 20);
    }
        
}
