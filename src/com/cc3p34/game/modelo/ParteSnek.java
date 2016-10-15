
package com.cc3p34.game.modelo;

import com.cc3p34.game.main.Game;
import com.cc3p34.framework.util.Recursos;
import java.awt.Point;
import java.awt.Rectangle;

public class ParteSnek extends ObjetoJogo {
    
    public ParteSnek(Point posicao) {
        this.imagem = Recursos.blocosnek;
        this.posicao = posicao;
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
