package com.cc3p34.game.modelo;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class ObjetoJogo {
    protected Point posicao;
    protected Rectangle rect;
    protected BufferedImage imagem;
    protected boolean debug;
    
    public abstract void renderizar();
    
    public abstract void atualizar();
     
    public void setPosicao(Point posicao) {
        this.posicao = posicao;
    }
    
    public Point getPosicao() {
        return posicao;
    };    
    
    public int getPosicaoX() {
        return posicao.x;
    };
    
    public int getPosicaoY() {
        return posicao.y;
    };
    
    public Rectangle getRect() {
        return rect;
    };    
    
    public BufferedImage getImagem() {
        return imagem;
    }   
}
