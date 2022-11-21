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
 * @author felip
 */
public class Exame {

    private int id;
    private int idtipoexame;
    private int idconsulta;

    public Exame() {
        super();
    }
    public Exame(int idtipoexame, int idconsulta) {
        this.idtipoexame = idtipoexame;
        this.idconsulta = idconsulta;
    }

    public int getId() {
        return this.id;
    }

    public int getIdTipoExame() {
        return this.idtipoexame;
    }

    public int getIdConsulta() {
        return this.idconsulta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdTipoExame(int idtipoexame) {
        this.idtipoexame = idtipoexame;
    }

    public void setIdConsulta(int idconsulta) {
        this.idconsulta = idconsulta;
    }
}
