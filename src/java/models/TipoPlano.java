/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author felip
 */
public class TipoPlano {

    private int id;
    private String descricao;

    public TipoPlano() {
        super();
    }

    public TipoPlano(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
