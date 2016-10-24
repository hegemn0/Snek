package com.cc3p34.framework.util;

import com.cc3p34.game.main.Game;

public class Escala {
    private double x;
    private double y;
    
    public Escala(int largura, int altura) {
        this.x = (double)largura / (double)Game.getLarguraBase();
        this.y = (double)altura / (double)Game.getAlturaBase();         
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
}
