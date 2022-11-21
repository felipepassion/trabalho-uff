package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.TipoPlano;

public class TipoPlanoDAO {

    public void Inserir(TipoPlano tipoPlano) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO tipoPlano (descricao)"
                    + " VALUES (?)");
            sql.setString(1, tipoPlano.getDescricao());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public TipoPlano getTipoPlano(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            TipoPlano tipoPlano = new TipoPlano();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM tipoPlano WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    tipoPlano.setId(Integer.parseInt(resultado.getString("ID")));
                    tipoPlano.setDescricao(resultado.getString("DESCRICAO"));
                }
            }
            return tipoPlano;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(TipoPlano TipoPlano) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE tipoPlano SET descricao = ?  WHERE ID = ? ");
            sql.setString(1, TipoPlano.getDescricao());
            sql.setInt(5, TipoPlano.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(TipoPlano TipoPlano) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM tipoPlano WHERE ID = ? ");
            sql.setInt(1, TipoPlano.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<TipoPlano> ListaDeTipoPlanos() {
        ArrayList<TipoPlano> meusTipoPlanos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM tipoPlano order by descricao";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    TipoPlano tipoPlano = new TipoPlano(resultado.getString("DESCRICAO"));
                    tipoPlano.setId(Integer.parseInt(resultado.getString("id")));
                    meusTipoPlanos.add(tipoPlano);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeTipoPlanos) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusTipoPlanos;
    }
}