
package com.cc3p34.game.modelo;

import java.awt.Point;
import java.awt.Rectangle;

public class ParteSnek extends ObjetoJogo {
    private int direcao;
    
    public ParteSnek(int x, int y, int direcao) {
        this.posicao = new Point(x, y);
        this.direcao = direcao;
        this.rect = new Rectangle(posicao.x, posicao.y, 32, 32);
    }    
    
    public ParteSnek(Point posicao, int direcao) {
        this.posicao = posicao;
        this.direcao = direcao;
        this.rect = new Rectangle(posicao.x, posicao.y, 32, 32);
    }

    @Override
    public void renderizar() {}

    @Override
    public void atualizar() {        
        atualizarRect();
    }
    
    private void atualizarRect() {
        rect.setBounds(posicao.x, posicao.y, 32, 32);
    }
    
    public int getDirecao() {
        return direcao;
    }
    
    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }
}
