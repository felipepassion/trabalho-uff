package DAO;

import DAO.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Exame;

public class ExameDAO {

    public void Inserir(Exame exame) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("INSERT INTO exame (idtipoexame, idconsulta)"
                            + " VALUES (?,?)");
            sql.setInt(1, exame.getIdTipoExame());
            sql.setInt(2, exame.getIdConsulta());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Exame getExame(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Exame exame = new Exame();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM exames WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    exame.setId(Integer.parseInt(resultado.getString("ID")));
                    exame.setIdTipoExame(resultado.getInt("IDTIPOEXAME"));
                    exame.setIdConsulta(resultado.getInt("IDCONSULTA"));
                }
            }
            return exame;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Exame Exame) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("UPDATE exames SET idtipoexame = ?, idconsulta = ? WHERE ID = ? ");
            sql.setInt(1, Exame.getIdTipoExame());
            sql.setInt(2, Exame.getIdConsulta());
            sql.setInt(3, Exame.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Exame Exame) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM exames WHERE ID = ? ");
            sql.setInt(1, Exame.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Exame> ListaDeExames() {
        ArrayList<Exame> meusExames = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM exames order by IDTIPOEXAME";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Exame exame = new Exame(resultado.getInt("IDTIPOEXAME"),
                            resultado.getInt("IDCONSULTA"));
                    exame.setId(Integer.parseInt(resultado.getString("id")));
                    meusExames.add(exame);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeExames) incorreta - " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return meusExames;
    }
}