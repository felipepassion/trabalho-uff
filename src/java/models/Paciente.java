/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author natyn
 */
public class Paciente {
    
    private String autorizado;
    private String idtipoplano;
    
    public Paciente(String id, String nome, String cpf, String senha, String autorizado, String tipoplano){
        this.autorizado = autorizado;
        this.idtipoplano = tipoplano;
    }
    
    /**
     * Construtor utilizado para o primeiro registro do paciente.
     * Este registro precisa de autorização do administrador para prosseguir com certos procedimentos. 
     * @param nome do usuário.
     * @param cpf do usuário.
     * @param senha do usuário.
     * @param autorizado status de autorizado a acessar serviços da clínica.
     * @param tipoplano id do plano de saúde utilizado.
     */
    public Paciente(String nome, String cpf, String senha, String tipoplano){
        this.autorizado = "N";
        this.idtipoplano = tipoplano;
    }
    
    public String getAutorizado(){
        return this.autorizado;
    }
    
    public String getIdtipoPlano(){
        return this.idtipoplano;
    }
    
    public void setAutorizado(String autorizado){
        this.autorizado = autorizado;
    }
    
    public void setIdTipoPlano(String idtipoplano){
        this.idtipoplano = idtipoplano;
    }

}
