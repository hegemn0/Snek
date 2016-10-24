package com.cc3p34.game.main;

import com.cc3p34.game.modelo.Apple;
import com.cc3p34.game.modelo.ObjetoJogo;
import com.cc3p34.game.modelo.Snek;
import java.util.List;

public class GerenciadorObjetos {
    private List<ObjetoJogo> objetos;
    
    public void atualizar() {
        for(ObjetoJogo objeto : objetos) {
            objeto.atualizar();
        }
    }
    
    public void renderizar() {
        for(ObjetoJogo objeto : objetos) {
            objeto.renderizar();
        }
    }
    
    public void adicionar(ObjetoJogo objeto) {
        objetos.add(objeto);
    }
    
    public void remover(ObjetoJogo objeto) {
        objetos.remove(objeto);
    }
    
    public ObjetoJogo getSnek() {
        return objetos.get((objetos.indexOf(Snek.class)));
    }
    
    public ObjetoJogo getApple() {
        return objetos.get((objetos.indexOf(Apple.class)));
    }
}
