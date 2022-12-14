/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author felip
 */
public class Medico extends Usuario {

    private String crm;
    private String estadocrm;

    private int idespecialidade;

    public Medico() {
        super();
        this.crm="";
        this.estadocrm="";
    }

    public Medico(String nome, String cpf, String senha, String crm, String estadocrm, int idespecialidade, String autorizado) {
        super(nome, cpf, senha, autorizado);
        this.crm = crm;
        this.estadocrm = estadocrm;
        this.idespecialidade = idespecialidade;
    }

    public Medico(String cpf_user, String senha_user) {
        setCpf(cpf_user);
        setSenha(senha_user);
    }

    public String getCrm() {
        return this.crm;
    }

    public String getEstadoCrm() {
        return this.estadocrm;
    }

    public int getIdEspecialidade() {
        return this.idespecialidade;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setEstadoCrm(String estadocrm) {
        this.estadocrm = estadocrm;
    }

    public void setIdEspecialidade(int idespecialidade) {
        this.idespecialidade = idespecialidade;
    }
}
