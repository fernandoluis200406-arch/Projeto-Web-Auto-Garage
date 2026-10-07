package org.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USUARIO = "usuario";
    private static final String SENHA = "senha";

    public static Connection conectar()
        throws SQLException{
        return DriverManager.getConnection(URL+USUARIO+SENHA);
    }
}
