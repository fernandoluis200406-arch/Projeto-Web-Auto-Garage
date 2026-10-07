package org.example.model;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean cadastrar(Usuario usuario){
        String sql = """
        INSERT INTO usuarios
        (login, senha) VALUES(?,?)
        """;
            String senhaHash = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt(12));
            try (Connection conexao = Conexao.conectar();
                PreparedStatement stat =
                        conexao.prepareStatement(sql)){
                stat.setString(1, usuario.getLogin());
                stat.setString(2, senhaHash);
                stat.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
    }
    public boolean inativaUsuario(Usuario usuario){
        String sql = """
                UPDATE usuarios
                SET status = falso
                WHERE id = ?
        """;
        try(Connection conexao = Conexao.conectar();
        PreparedStatement stat =
                conexao.prepareStatement(sql)) {
            stat.setInt(1, usuario.getId());
            int linhasAfetadas = stat.executeUpdate();
            return linhasAfetadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
