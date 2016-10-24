package com.cc3p34.game.estado;

import com.cc3p34.game.main.Game;
import com.cc3p34.game.modelo.Butao;
import com.cc3p34.game.modelo.MenuPrincipal;
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
    private MenuPrincipal menu;
    private Butao iniciar;

    @Override
    public void inicializar() {
        iniciar = new Butao("INICIAR", 350, 200, 100, 100, Color.RED, Color.CYAN, Color.MAGENTA);
    };

    @Override
    public void atualizar() {

    }
    
    @Override
    public void renderizar() {
        iniciar.renderizar();
    };
    
    @Override
    public void onKeyPress(KeyEvent e) {
        setEstado(new PlayState());
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
