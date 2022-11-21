package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Consulta;

public class ConsultaDAO {

    public void Inserir(Consulta consulta) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("INSERT INTO consulta (data, descricao, realizada, idmedico, idpaciente)"
                            + " VALUES (?,?,?,?,?)");
            sql.setString(1, consulta.getData());
            sql.setString(2, consulta.getDescricao());
            sql.setString(3, consulta.getRealizada());
            sql.setInt(4, consulta.getIdMedico());
            sql.setInt(5, consulta.getIdPaciente());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Consulta getConsulta(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Consulta consulta = new Consulta();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM consulta WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    consulta.setId(Integer.parseInt(resultado.getString("ID")));
                    consulta.setDescricao(resultado.getString("Descricao"));
                    consulta.setData(resultado.getString("Data"));
                    consulta.setRealizada(resultado.getString("Realizada"));
                }
            }
            return consulta;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Consulta Consulta) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("UPDATE consulta SET Descricao = ?, Data = ?, Realizada = ?, idmedico = ?, idpaciente = ? WHERE ID = ? ");
            sql.setString(1, Consulta.getDescricao());
            sql.setString(2, Consulta.getData());
            sql.setString(3, Consulta.getRealizada());
            sql.setInt(4, Consulta.getIdMedico());
            sql.setInt(5, Consulta.getIdPaciente());
            sql.setInt(6, Consulta.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Consulta Consulta) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM consulta WHERE ID = ? ");
            sql.setInt(1, Consulta.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Consulta> ListaDeConsultas() {
        ArrayList<Consulta> meusConsultas = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM consulta order by Descricao";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Consulta consulta = new Consulta(resultado.getString("Data"),
                            resultado.getString("Descricao"),
                            resultado.getString("Realizada"),
                            resultado.getInt("idMedico"),
                            resultado.getInt("idPaciente"));
                    consulta.setId(Integer.parseInt(resultado.getString("id")));
                    meusConsultas.add(consulta);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeConsultas) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return meusConsultas;
    }
}