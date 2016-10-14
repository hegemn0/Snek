package com.cc3p34.game.main;

import javax.swing.JFrame;

/**
 * teste 2
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

public class Main {
    public static Game game;
    public static JFrame frame;
    
    public static void main(String[] args) {        
        game = new Game();
        game.setDoubleBuffered(true); 
        
        frame = new JFrame("snek");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);      
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }
}
