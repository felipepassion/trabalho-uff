package models;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import DAO.UsuarioDAO;
import Enums.TipoConta;
import java.util.ArrayList;

public class Usuario {

    private int id;
    private String nome;
    private String cpf;
    private String senha;

    public Usuario(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public Usuario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Usuario() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.senha = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMedico() {
        MedicoDAO medicoDAO = new MedicoDAO();
        ArrayList<Medico> medicos = medicoDAO.ListaDeMedicos();
        for (Medico medico : medicos) {
            if (medico.getId() == this.getId()) {
                return medico.getNome();
            }
        }
        return null;
    }

    public String getPaciente() {
        PacienteDAO PacienteDAO = new PacienteDAO();
        ArrayList<Paciente> Pacientes = PacienteDAO.ListaDePacientes();
        for (Paciente paciente : Pacientes) {
            if (paciente.getId() == this.getId()) {
                return paciente.getNome();
            }
        }
        return null;
    }

    public String getUsuario() {
        UsuarioDAO UsuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> Usuarios = UsuarioDAO.ListaDeUsuarios();
        for (Usuario usuario : Usuarios) {
            if (usuario.getId() == this.getId()) {
                return usuario.getNome();
            }
        }
        return null;
    }

    public String getTipoConta() {
        if (getMedico() != null) {
            return TipoConta.Medico;
        }
        if (getPaciente() != null) {
            return TipoConta.Paciente;
        }
        if (getUsuario() != null) {
            return TipoConta.Adm;
        }
        return null;
    }
}
