/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author natyn
 */
public class Exame {
    
    private String id;
    private String idtipoexame;
    private String idconsulta;

    public Exame(String idtipoexame, String idconsulta) {
        this.idtipoexame = idtipoexame;
        this.idconsulta = idconsulta;
    }
    
    public Exame(String id, String idtipoexame, String idconsulta) {
        this.id = id;
        this.idtipoexame = idtipoexame;
        this.idconsulta = idconsulta;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getIdTipoExame(){
        return this.idtipoexame;
    }
        
    public String getIdConsulta(){
        return this.idconsulta;
    }
    
    public void setId(String id) {
        this.id=id;
    }

    public void setIdTipoExame(String idtipoexame) {
        this.idtipoexame=idtipoexame;
    }

    public void setIdConsulta(String idconsulta) {
        this.idconsulta=idconsulta;
    }
}
