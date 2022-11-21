/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author natyn
 */
public class Medico {
    
    private String crm;
    private String estadocrm;
    private String autorizado;
    private String idespecialidade;
    
    public Medico(String id, String nome, String crm, String estadocrm, String cpf, String senha, String autorizado, String especialidade){
        this.crm = crm;
        this.estadocrm = estadocrm;
        this.autorizado = autorizado;
        this.idespecialidade = especialidade;
    }
    
    public Medico(String nome, String crm, String estadocrm, String cpf, String senha, String autorizado, String especialidade){
        this.crm = crm;
        this.estadocrm = estadocrm;
        this.autorizado = autorizado;
        this.idespecialidade = especialidade;
    }
    
    public String getCrm(){
        return this.crm;
    }
    public String getEstadoCrm(){
        return this.estadocrm;
    }
    
    public String getAutorizado(){
        return this.autorizado;
    }
    
    public String getIdEspecialidade(){
        return this.idespecialidade;
    }
    
    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setEstadoCrm(String estadocrm) {
        this.estadocrm = estadocrm;
    }

    public void setAutorizado(String autorizado) {
        this.autorizado = autorizado;
    }

    public void setIdEspecialidade(String idespecialidade) {
        this.idespecialidade = idespecialidade;
    }    
}
