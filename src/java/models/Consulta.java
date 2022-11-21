/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 *
 * @author felip
 */
public class Consulta {
    
    private int id;
    private String data;
    private String descricao;
    private String realizada;
    private int idmedico;
    private int idpaciente;
    
    public Consulta() {
        super();
    }
    public Consulta(String data, String descricao, String realizada, int idmedico, int idpaciente) {
        this.data = data;
        this.descricao = descricao;
        this.realizada = realizada;
        this.idmedico = idmedico;
        this.idpaciente = idpaciente;
    }

    public int getId(){
        return this.id;
    }
    
    public String getData(){
        return this.data;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public String getRealizada(){
        return this.realizada;
    }
    
    public int getIdMedico(){
        return this.idmedico;
    }
        
    public int getIdPaciente(){
        return this.idpaciente;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setData(String data){
        this.data = data;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public void setRealizada(String realizada){
        this.realizada = realizada;
    }
    
    public void setIdMedico(int idmedico){
        this.idmedico = idmedico;
    }
        
    public void setIdPaciente(int idpaciente){
        this.idpaciente = idpaciente;
    }
}
