package com.cc3p34.framework.util;

import java.applet.Applet;
import java.applet.AudioClip;
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
    private static final String DIR_AUDIO = "/recursos/audio/";
    
    public static BufferedImage icone, apple;  
    
    public static BufferedImage[] snekCabeca, snekCorpo, snekCorpoAngulo, snekCauda;   
    
    public static AudioClip[] mordidaMaca, colisaoPartes;
    
    public static void carregar() { 
        icone = carregarImagem("icone.png");
        apple = carregarImagem("apple.png");
                
        snekCabeca = new BufferedImage[] {
            carregarImagem("snekcabeca.png"),
        };
        
        snekCorpo = new BufferedImage[] {
            carregarImagem("snekcorpo_de.png"),
            carregarImagem("snekcorpo_cb.png"),
        };
        
        snekCauda = new BufferedImage[] {
            carregarImagem("snekrabo.png"),
        };
        
        colisaoPartes = new AudioClip[] {
            carregarAudio("colisao_partes01.wav"),
            carregarAudio("colisao_partes02.wav"),
            carregarAudio("colisao_partes03.wav"),
            carregarAudio("colisao_partes04.wav"),
        };
        
        mordidaMaca = new AudioClip[] {
            carregarAudio("mordida_maca01.wav"),
            carregarAudio("mordida_maca02.wav"),
            carregarAudio("mordida_maca03.wav"),
            carregarAudio("mordida_maca04.wav"),          
        };
    }
    
    private static BufferedImage carregarImagem(String nome) {
        BufferedImage imagem = null;
        
        try {
            imagem = ImageIO.read(
                    Recursos.class.getResourceAsStream(DIR_IMAGENS + nome));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return imagem;
    }
    
    private static AudioClip carregarAudio(String nome) {
        return Applet.newAudioClip(
                Recursos.class.getResource(DIR_AUDIO + nome));
    }
    
    public static void executarAudioColisaoPartes() {
        int i = Util.gerarNumero(0, mordidaMaca.length - 1);
        colisaoPartes[i].play();
    }    
    
    public static void executarAudioMordidaMaca() {
        int i = Util.gerarNumero(0, mordidaMaca.length - 1);
        mordidaMaca[i].play();
    }
}
