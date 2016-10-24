package com.cc3p34.game.modelo;

import com.cc3p34.game.main.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;

public class Butao {
    private String titulo;
    private Point posicao;
    private Dimension resolucao;
    private Color corTexto, background, bordas;
    private Rectangle rect;
    
    public Butao(String titulo, int x, int y, int largura, int altura,
                 Color texto, Color background, Color bordas) {
        this.titulo = titulo;
        this.posicao = new Point(x, y);
        this.resolucao = new Dimension(largura, altura);
        this.corTexto = texto;
        this.background = background;
        this.bordas = bordas;
        this.rect = new Rectangle(x, y, largura, altura);
    }
    
    public void renderizar() {        
        Game.tela.setColor(background);
        Game.tela.fillRect(posicao.x, posicao.y, resolucao.width, resolucao.height);
        
        Game.tela.setColor(bordas);
        Game.tela.drawRect(posicao.x, posicao.y, resolucao.width, resolucao.height);
        
        Game.tela.setColor(corTexto);
        FontMetrics medidas = Game.tela.getFontMetrics();
        Game.tela.drawString(titulo, posicao.x + resolucao.width/2 - medidas.stringWidth(titulo)/2,
                             posicao.y + resolucao.height/2 + medidas.getAscent()/2) ;        
    }
}
