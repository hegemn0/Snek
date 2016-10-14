package com.cc3p34.framework.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

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

public class Recursos {
    private static final String DIR_IMAGENS = "/recursos/imagem/";
    
    public static BufferedImage icone, blocosnek, blocoapple;
    
    public static void carregar() { 
        icone = carregarImagem("iconesnek.png");
        blocosnek = carregarImagem("blocosnek.png");
        blocoapple = carregarImagem("blocoapple.png");
    }
    
    private static BufferedImage carregarImagem(String nomeArquivo) {
        BufferedImage imagem = null;
        
        try {
            imagem = ImageIO.read(
                    Recursos.class.getResourceAsStream(
                            DIR_IMAGENS + nomeArquivo));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return imagem;
    }
}
