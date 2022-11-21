/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author natyn
 */
public class Especialidade {
    
    private String id;
    private String descricao;
    
    public Especialidade(String id, String descricao){
        this.id = id;
        this.descricao =  descricao;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getDescricao(){
        return this.descricao;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
