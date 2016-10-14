package com.c748ce1.game.estado;

import com.c748ce1.game.main.Main;
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

public abstract class State {
    
    public abstract void init();
    
    public abstract void atualizar(); 
    
    public abstract void renderizar();
    
    public abstract void onKeyPress(KeyEvent e);
    
    public abstract void onKeyRelease(KeyEvent e);
    
    public abstract void mousePressed(MouseEvent e);
    
    public abstract void mouseReleased(MouseEvent e);    
    
    public abstract void mouseDragged(MouseEvent e);
    
    public void setEstado(State estado) {
        Main.game.setEstado(estado);
    }
}
