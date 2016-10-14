package com.cc3p34.framework.util;

import com.cc3p34.game.main.Game;
import com.cc3p34.game.modelo.Snek;
import java.awt.Point;
import java.util.Random;

public class Util {
    
    public static int gerarNumero(int minimo, int maximo) {
        int resultado = 0;
        
        while(resultado < minimo) {
            resultado = new Random().nextInt(maximo);
        }
        
        return resultado;
    }

    public static Point gerarPosicaoSnek(int velocidade) {
        return new Point(
                new Random().nextInt(Game.WIDTH / velocidade - 1) * velocidade,
                new Random().nextInt(Game.HEIGHT / velocidade - 1) * velocidade);        
    }
    
    public static Point gerarPosicaoMaca(Snek snake) {
        Point novaPosicao;
        
        do {
            novaPosicao = new Point(
                    new Random().nextInt(Game.WIDTH / snake.getVelocidade() - 1) * snake.getVelocidade(),
                    new Random().nextInt(Game.HEIGHT / snake.getVelocidade() - 1) * snake.getVelocidade());            
        } while(snake.getPosicao().equals(novaPosicao));
        
        return novaPosicao;        
    }
}
