package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Paciente;
import models.Usuario;

public class PacienteDAO {

    public void Inserir(Paciente paciente) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("INSERT INTO paciente (nome, cpf, senha, idtipoplano)"
                            + " VALUES (?,?,?,?)");
            sql.setString(1, paciente.getNome());
            sql.setString(2, paciente.getCpf());
            sql.setString(3, paciente.getSenha());
            sql.setInt(4, paciente.getIdTipoPlano());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexao.closeConexao();
        }
    }

    public Paciente getPaciente(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Paciente paciente = new Paciente();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM paciente WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    paciente.setId(Integer.parseInt(resultado.getString("ID")));
                    paciente.setNome(resultado.getString("NOME"));
                    paciente.setCpf(resultado.getString("CPF"));
                    paciente.setSenha(resultado.getString("SENHA"));
                    paciente.setIdTipoPlano(resultado.getInt("IDTIPOPLANO"));

                }
            }
            return paciente;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Paciente Paciente) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("UPDATE paciente SET nome = ?, cpf = ?, senha = ?, idtipoplano = ?  WHERE ID = ? ");
            sql.setString(1, Paciente.getNome());
            sql.setString(2, Paciente.getCpf());
            sql.setString(3, Paciente.getSenha());
            sql.setInt(4, Paciente.getIdTipoPlano());
            sql.setInt(5, Paciente.getId());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Paciente Paciente) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM paciente WHERE ID = ? ");
            sql.setInt(1, Paciente.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Paciente> ListaDePacientes() {
        ArrayList<Paciente> meusPacientes = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM paciente order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Paciente paciente = new Paciente(resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            resultado.getString("SENHA"),
                            resultado.getInt("IDTIPOPLANO"));
                    paciente.setId(Integer.parseInt(resultado.getString("id")));
                    meusPacientes.add(paciente);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDePacientes) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return meusPacientes;
    }

    public Usuario Logar(Paciente paciente) throws SQLException {
        Conexao conexao = new Conexao();

        PreparedStatement sql = conexao.getConexao()
                .prepareStatement("SELECT * FROM paciente WHERE cpf=? and senha =? LIMIT 1");
        sql.setString(1, paciente.getCpf());
        sql.setString(2, paciente.getSenha());
        ResultSet resultado = sql.executeQuery();
        Usuario pacienteObtido = null;
        if (resultado != null) {
            pacienteObtido = new Paciente();
            while (resultado.next()) {
                pacienteObtido.setId(Integer.parseInt(resultado.getString("ID")));
                pacienteObtido.setNome(resultado.getString("NOME"));
                pacienteObtido.setCpf(resultado.getString("CPF"));
                pacienteObtido.setSenha(resultado.getString("SENHA"));
            }
        }
        return pacienteObtido;

    }

}
