package com.cc3p34.game.modelo;

import com.cc3p34.framework.util.Util;
import com.cc3p34.game.main.Game;
import com.cc3p34.framework.util.Recursos;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Snek extends ObjetoJogo {
    
    private int tamanho, velocidade, direcao, qtdMovimentos;
    private Point posicaoAnterior;
    private ArrayList<ParteSnek> partesSnek;
    private ArrayList<Apple> apples;
    private boolean emMovimento;
    
    public Snek(int velocidade) {
        this.imagem = Recursos.blocosnek;
        this.posicao = Util.gerarPosicaoSnek(velocidade);
        this.tamanho = 0;
        this.velocidade = velocidade;
        this.direcao = 0;
        this.partesSnek = new ArrayList<>();
        this.apples = new ArrayList<>();
        this.qtdMovimentos = 0;
        this.emMovimento = false;        
        this.rect = new Rectangle(posicao.x, posicao.y, 20, 20);
        this.debug = true;       
    }
    
    public Snek(int velocidade, int tamanho) {
        this.imagem = Recursos.blocosnek;
        this.posicao = Util.gerarPosicaoSnek(velocidade);
        this.tamanho = tamanho;
        this.velocidade = velocidade;
        this.direcao = 0;
        this.partesSnek = new ArrayList<>();
        this.apples = new ArrayList<>();
        this.qtdMovimentos = 0;
        this.emMovimento = false;
        this.rect = new Rectangle(posicao.x, posicao.y, 20, 20);
        this.debug = true;       
    }    

    @Override
    public void renderizar() {
        Game.tela.getGraphics().drawImage(imagem, posicao.x, posicao.y, null);
        
        for(ParteSnek parte : partesSnek) {
            Game.tela.getGraphics().drawImage(
                    imagem, parte.getPosicaoX(), parte.getPosicaoY(), null);
        }
    }

    @Override
    public void atualizar() {         
        if(emMovimento) {
            if(tamanho > partesSnek.size() && partesSnek.size() == 0) {
                partesSnek.add(new ParteSnek(posicao));
            } else if(tamanho > partesSnek.size() && partesSnek.size() > 0){
                partesSnek.add(new ParteSnek(posicaoAnterior));
            }
            
            mover();
            moverPartes();            
        }
                     
        atualizarRect();
        
        if(debug) mostrarDebug();
    }
    
    private void atualizarRect() {
        rect.setBounds(posicao.x, posicao.y, 20, 20);
    } 
    
    private void mover() {
        qtdMovimentos++;
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
        
        if(novaDirecao != 0)
            emMovimento = true;
        else
            emMovimento = false;
        
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
    
    public int getVelocidade() {
        return velocidade;
    }
    
    public boolean getEmMovimento() {
        return emMovimento;
    }
    
    public ArrayList<ParteSnek> getPartesSnek() {
        return partesSnek;
    }

    private void mostrarDebug() {
        System.out.println(
                "Em movimento: " + emMovimento + "\n" +
                "Direção: " + direcao + "\n" +
                "Quantidade de Movimentos: " + qtdMovimentos + "\n" +
                "Quantidade de Partes: " + partesSnek.size() + "\n" +            
                "Quantidade de Maças: " + apples.size() + "\n" +                
                "Posição: " + "[" + posicao.x + ", " + posicao.y + "]" + "\n"
        );        
    }

    private void moverPartes() {
        if(partesSnek.size() > 0) {        
            ParteSnek parte = partesSnek.remove(0);
            parte.setPosicao(posicaoAnterior);
            partesSnek.add(parte);            
        }
    }
    
    public void reset() {
        qtdMovimentos = 0;
        tamanho = 0;
        apples = new ArrayList<>();
        partesSnek = new ArrayList<>();
        posicao = new Point(Util.gerarPosicaoSnek(velocidade));
    }

    public void adicionarParte() {
        partesSnek.add(0, new ParteSnek(posicao));
    }
    
}
