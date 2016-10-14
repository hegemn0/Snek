package com.cc3p34.framework.util;

import com.cc3p34.game.estado.State;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class InputHandler implements KeyListener, MouseListener {
    private State estado;
    
    public void setEstado(State estado) {
        this.estado = estado;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        estado.onKeyPress(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        estado.onKeyRelease(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
    
}
