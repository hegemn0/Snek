package com.cc3p34.game.estado;

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

public class MenuState extends State {

    @Override
    public void init() {
    };

    @Override
    public void atualizar() {
        setEstado(new PlayState());        
    }
    
    @Override
    public void renderizar() {
    };
    
    @Override
    public void onKeyPress(KeyEvent e) {        
    };
    
    @Override
    public void onKeyRelease(KeyEvent e) {};  

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
