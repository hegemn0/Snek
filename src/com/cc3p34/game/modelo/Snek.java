package com.cc3p34.game.modelo;

import com.cc3p34.framework.util.Util;
import com.cc3p34.game.main.Game;
import com.cc3p34.framework.util.Recursos;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Snek extends ObjetoJogo {
    
    private int tamanho, velocidade, direcao;
    private Point posicaoAnterior;
    private ArrayList<ParteSnek> partes;
    
    public Snek(int velocidade) {
        this.imagem = Recursos.blocosnek;
        this.posicao = Util.gerarPosicaoSnek(velocidade);
        this.tamanho = 0;
        this.velocidade = velocidade;
        this.direcao = 0;
        this.partes = new ArrayList<>();
        this.rect = new Rectangle(posicao.x, posicao.y, 20, 20);
        this.debug = false;       
    }

    @Override
    public void renderizar() {
        Game.tela.getGraphics().drawImage(imagem, posicao.x, posicao.y, null);
        
        for(ParteSnek parte : partes) {
            Game.tela.getGraphics().drawImage(
                    imagem, parte.getPosicaoX(), parte.getPosicaoY(), null);
        }
    }

    @Override
    public void atualizar() {  
        if(tamanho > partes.size()) {
            partes.add(0, new ParteSnek(posicao));
        }
        
        mover();
        moverPartes();
        atualizarRect();
        
        if(debug) {
            mostrarDebug();
        }
    }
    
    private void atualizarRect() {
        rect.setBounds(posicao.x, posicao.y, 20, 20);
    } 
    
    private void mover() {
        posicaoAnterior = new Point(posicao);
        switch(direcao) {
            case 1: {
                posicao.y -= velocidade;
            } break;
            case 2: {
                posicao.y += velocidade;
            } break;
            case 3: {
                posicao.x += velocidade;
            } break;
            case 4: {
                posicao.x -= velocidade;
            } break;
        }        
    }    
    
    public void setDirecao(int novaDirecao) {        
        switch(novaDirecao) {
            case 1: {
                direcao = 1;
            } break;
            case 2: {
                direcao = 2;
            } break;
            case 3: {
                direcao = 3;
            } break;
            case 4: {
                direcao = 4;
            } break;
        }
    }
    
    public int getDirecao(){
        return direcao;
    }
    
    public int getTamanho() {
        return tamanho;
    }
    
    public void setTamanho(int novoTamanho) {
        this.tamanho = novoTamanho;
    }
    
    public int getVelocidade() {
        return velocidade;
    }
    
    public ArrayList<ParteSnek> getPartes() {
        return partes;
    }

    private void mostrarDebug() {
        System.out.println("Direção: " + direcao);
        System.out.println("Posição: " + "[" + posicao.x + ", " + posicao.y + "]");        
    }

    private void moverPartes() {
        if(partes.size() > 0) {
            ParteSnek parte = partes.remove(0);
            parte.setPosicao(posicaoAnterior);
            partes.add(parte);
        }
    }
    
    public void reset() {
        tamanho = 0;
        partes = new ArrayList<>();
        posicao = new Point(Util.gerarPosicaoSnek(velocidade));
    }
}
