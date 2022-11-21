package models;

import DAO.TipoPlanoDAO;
import java.util.ArrayList;

public class Paciente extends Usuario {
    
    private int idTipoPlano;
    
    public int getIdTipoPlano() {
        return idTipoPlano;
    }
    public void setIdTipoPlano(int idTipoPlano) {
        this.idTipoPlano = idTipoPlano;
    }
    public Paciente() {
        super();
    }
    public Paciente(String nome, String cpf, String senha, int idTipoPlano){
        super(nome, cpf, senha);
        this.idTipoPlano = idTipoPlano;
    }

    public String getTipoPlano(){
        TipoPlanoDAO tipoPlanoDAO = new TipoPlanoDAO();
        ArrayList<TipoPlano> tipoPlanos = tipoPlanoDAO.ListaDeTipoPlanos();
            for(TipoPlano tipoPlano : tipoPlanos){
            if(tipoPlano.getId() == this.getIdTipoPlano()){
                return tipoPlano.getDescricao();
            }
        }
        return null;
    }
}
