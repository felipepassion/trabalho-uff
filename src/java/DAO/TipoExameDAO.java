package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.TipoExame;

public class TipoExameDAO {

    public void Inserir(TipoExame tipoExame) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO tipoExame (descricao)"
                    + " VALUES (?)");
            sql.setString(1, tipoExame.getDescricao());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public TipoExame getTipoExame(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            TipoExame tipoExame = new TipoExame();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM tipoExame WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    tipoExame.setId(Integer.parseInt(resultado.getString("ID")));
                    tipoExame.setDescricao(resultado.getString("DESCRICAO"));
                }
            }
            return tipoExame;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(TipoExame TipoExame) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE tipoExame SET descricao = ?  WHERE ID = ? ");
            sql.setString(1, TipoExame.getDescricao());
            sql.setInt(2, TipoExame.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(TipoExame TipoExame) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM tipoExame WHERE ID = ? ");
            sql.setInt(1, TipoExame.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<TipoExame> ListaDeTipoExames() {
        ArrayList<TipoExame> meusTipoExames = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM tipoExame order by descricao";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    TipoExame tipoExame = new TipoExame(resultado.getString("DESCRICAO"));
                    tipoExame.setId(Integer.parseInt(resultado.getString("id")));
                    meusTipoExames.add(tipoExame);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeTipoExames) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusTipoExames;
    }
}