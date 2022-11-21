package models;

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
}
