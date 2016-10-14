package com.c748ce1.game.estado;

import com.c748ce1.framework.util.Recursos;
import com.c748ce1.game.main.Game;
import java.awt.Color;
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
        System.out.println("Renderizar MenuState!");
        Game.tela.getGraphics().setColor(Color.red);
        Game.tela.getGraphics().drawImage(Recursos.icone, 100, 100, null);
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
