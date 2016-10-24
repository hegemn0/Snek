package com.cc3p34.game.modelo;

import com.cc3p34.game.main.Game;
import com.cc3p34.framework.util.Recursos;
import java.applet.AudioClip;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Snek extends ObjetoJogo {
    
    private int tamanho, velocidade, direcao, direcaoAnterior, qtdMovimentos;
    private Point posicaoAnterior;
    private ArrayList<ParteSnek> partesSnek;
    private ArrayList<Apple> apples;
    private boolean emMovimento;
    private BufferedImage texturaCabeca[], texturaCorpo[], texturaCauda[];
    
    public Snek(int velocidade) {
        this.texturaCabeca = Recursos.snekCabeca;
        this.texturaCorpo = Recursos.snekCorpo;
        this.texturaCauda = Recursos.snekCauda;           
        this.tamanho = 0;
        this.velocidade = velocidade;
        this.direcao = gerarDirecao();
        this.posicao = gerarPosicao();
        this.partesSnek = new ArrayList<>();
        this.apples = new ArrayList<>();
        this.qtdMovimentos = 0;
        this.emMovimento = false;        
        this.rect = new Rectangle(posicao.x, posicao.y, 32, 32);
        this.debug = false;
        gerarPosicaoPartes();
    }
    
    public Snek(int velocidade, int tamanho) {
        this.texturaCabeca = Recursos.snekCabeca;
        this.texturaCorpo = Recursos.snekCorpo;
        this.texturaCauda = Recursos.snekCauda;        
        this.tamanho = tamanho;
        this.velocidade = velocidade;
        this.direcao = gerarDirecao();
        this.posicao = gerarPosicao();
        this.partesSnek = new ArrayList<>();
        this.apples = new ArrayList<>();
        this.qtdMovimentos = 0;
        this.emMovimento = false;
        this.rect = new Rectangle(posicao.x, posicao.y, 32, 32);
        this.debug = true;  
        gerarPosicaoPartes();
    }    

    @Override
    public void renderizar() {
        Game.tela.drawImage(texturaCabeca[0], posicao.x, posicao.y, null);
        
        for(ParteSnek parte : partesSnek) {
            if(parte.getDirecao() == 1 || parte.getDirecao() == 2) {
                Game.tela.drawImage(
                        texturaCorpo[0], parte.getPosicaoX(), parte.getPosicaoY(), null);
            } else if(parte.getDirecao() == 3 || parte.getDirecao() == 4) {
                Game.tela.drawImage(
                        texturaCorpo[1], parte.getPosicaoX(), parte.getPosicaoY(), null);                
            }         
        }
    }

    @Override
    public void atualizar() {         
        if(emMovimento) {
            mover();
            moverPartes();            
        }
                     
        atualizarRect();
        
        if(debug) mostrarDebug();
    }
    
    private void atualizarRect() {
        rect.setBounds(posicao.x, posicao.y, 32, 32);
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
        
        direcaoAnterior = direcao;
        
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
            parte.setDirecao(direcaoAnterior);
            partesSnek.add(parte);            
        }
    }
    
    public void reset() {
        qtdMovimentos = 0;
        tamanho = 0;
        apples = new ArrayList<>();
        partesSnek = new ArrayList<>();
        gerarPosicao();
    }

    public void adicionarParte() {
        partesSnek.add(0, new ParteSnek(posicaoAnterior, direcaoAnterior));
    }
    
    public void novaPosicao() {
        this.posicao = gerarPosicao();
    }
    
    private void gerarPosicaoPartes() {
        if(tamanho > 0) {
            for(int i = 0; i < tamanho; i++) {
                switch(direcao) {
                    case 1: {
                        if(this.getPosicaoY() + (-i * velocidade) >= Game.getLargura()) {
                            partesSnek.add(0, new ParteSnek(
                                this.getPosicaoX(), i * velocidade, direcao));
                        } else if(this.getPosicaoY() + (-i * velocidade) < 0) {
                           partesSnek.add(0, new ParteSnek(
                                this.getPosicaoX(), Game.getAltura() + (-i * velocidade), direcao));
                        } else {
                            partesSnek.add(0, new ParteSnek(
                                this.getPosicaoX(), this.getPosicaoY() + (-i * velocidade), direcao));                             
                        }
                    } break;
                    case 2: {
                        if(this.getPosicaoY() + (i * velocidade) >= Game.getLargura()) {
                            partesSnek.add(0, new ParteSnek(
                                this.getPosicaoX(), (i * velocidade), direcao));
                        } else if(this.getPosicaoY() + (i * velocidade) < 0) {
                           partesSnek.add(0, new ParteSnek(
                                this.getPosicaoX(), Game.getAltura() + (i * velocidade), direcao));
                        } else {
                            partesSnek.add(0, new ParteSnek(
                                this.getPosicaoX(), this.getPosicaoY() + (i * velocidade), direcao));                             
                        }                                                
                    } break;
                    case 3: {
                        if(this.getPosicaoX() + (i * velocidade) >= Game.getAltura()) {
                            partesSnek.add(0, new ParteSnek(
                                i * velocidade,  this.getPosicaoY(), direcao));
                        } else if(this.getPosicaoX() + (i * velocidade) < 0) {
                           partesSnek.add(0, new ParteSnek(
                                Game.getAltura() + (i * velocidade), this.getPosicaoY(), direcao));
                        } else {
                            partesSnek.add(0, new ParteSnek(
                                this.getPosicaoX() + (i * velocidade), this.getPosicaoY() , direcao));                             
                        }                        
                    } break;
                    case 4: {
                        if(this.getPosicaoX() + (-i * velocidade) >= Game.getAltura()) {
                            partesSnek.add(0, new ParteSnek(
                                i * velocidade,  this.getPosicaoY(), direcao));
                        } else if(this.getPosicaoX() + (-i * velocidade) < 0) {
                           partesSnek.add(0, new ParteSnek(
                                Game.getAltura() + (i * velocidade), this.getPosicaoY(), direcao));
                        } else {
                            partesSnek.add(0, new ParteSnek(
                                this.getPosicaoX() + (i * velocidade), this.getPosicaoY() , direcao));                             
                        }                         
                    } break;                    
                }                
            }
        }
    }
    
    private Point gerarPosicao() {
        return new Point(
                new Random().nextInt(Game.getLargura() / velocidade - 1) * velocidade,
                new Random().nextInt(Game.getAltura() / velocidade - 1) * velocidade);
    }    
    
    private int gerarDirecao() {
        return new Random().nextInt((4 - 1) + 1) + 1;
    }
}
