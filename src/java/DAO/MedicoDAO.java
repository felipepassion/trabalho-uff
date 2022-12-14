package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Medico;
import models.Usuario;

public class MedicoDAO {

    public void Inserir(Medico medico) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement(
                            "INSERT INTO medico (nome, cpf, senha, crm, autorizado, crmestado, idespecialidade, autorizado)"
                            + " VALUES (?,?,?,?,?,?,?)");
            sql.setString(1, medico.getNome());
            sql.setString(2, medico.getCpf());
            sql.setString(3, medico.getSenha());
            sql.setString(4, medico.getCrm());
            sql.setString(5, medico.getEstadoCrm());
            sql.setInt(6, medico.getIdEspecialidade());
            sql.setString(7, medico.getAutorizado());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Medico getMedico(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Medico medico = new Medico();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM medico WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    medico.setId(Integer.parseInt(resultado.getString("ID")));
                    medico.setNome(resultado.getString("NOME"));
                    medico.setCpf(resultado.getString("CPF"));
                    medico.setSenha(resultado.getString("SENHA"));
                    medico.setCrm(resultado.getString("CRM"));
                    medico.setEstadoCrm(resultado.getString("ESTADOCRM"));
                    medico.setIdEspecialidade(resultado.getInt("IDESPECIALIDADE"));
                    medico.setAutorizado(resultado.getString("AUTORIZADO"));
                }
            }
            return medico;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Medico Medico) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement(
                            "UPDATE medico SET nome = ?, cpf = ?, senha = ?, crm = ?, estadocrm = ?, idespecialidade = ?, autorizado = ? WHERE ID = ? ");
            sql.setString(1, Medico.getNome());
            sql.setString(2, Medico.getCpf());
            sql.setString(3, Medico.getSenha());
            sql.setString(4, Medico.getCrm());
            sql.setString(5, Medico.getEstadoCrm());
            sql.setInt(6, Medico.getIdEspecialidade());
            sql.setString(7, Medico.getAutorizado());
            sql.setInt(8, Medico.getId());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Medico Medico) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM medico WHERE ID = ? ");
            sql.setInt(1, Medico.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Medico> ListaDeMedicos() {
        ArrayList<Medico> meusMedicos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM medico order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Medico medico = new Medico(
                            resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            resultado.getString("SENHA"),
                            resultado.getString("CRM"),
                            resultado.getString("ESTADOCRM"),
                            resultado.getInt("IDESPECIALIDADE"),
                            resultado.getString("AUTORIZADO")
                    );
                    medico.setId(Integer.parseInt(resultado.getString("id")));
                    meusMedicos.add(medico);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeMedicos) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return meusMedicos;
    }

    public Usuario Logar(Medico medico) throws SQLException, Exception {
        Conexao conexao = new Conexao();

        PreparedStatement sql = conexao.getConexao()
                .prepareStatement("SELECT * FROM medico WHERE cpf=? and senha =? LIMIT 1");
        sql.setString(1, medico.getCpf());
        sql.setString(2, medico.getSenha());
        ResultSet resultado = sql.executeQuery();
        Usuario medicoObtido = null;
        if (resultado != null) {
            medicoObtido = new Usuario();
            while (resultado.next()) {
                medicoObtido.setId(Integer.parseInt(resultado.getString("ID")));
                medicoObtido.setNome(resultado.getString("NOME"));
                medicoObtido.setCpf(resultado.getString("CPF"));
                medicoObtido.setSenha(resultado.getString("SENHA"));
                medicoObtido.setAutorizado(resultado.getString("AUTORIZADO"));
            }
        }
        if (medicoObtido.getAutorizado().toLowerCase() == "n") {
            throw new Exception("unauthorized");
        }
        return medicoObtido;

    }

}
