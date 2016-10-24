package com.cc3p34.framework.util;

import java.util.Random;

public class Util {
    
    public static int gerarNumero(int minimo, int maximo) {        
        return new Random().nextInt(((maximo - minimo) + 1) + minimo);
    }
    
}
