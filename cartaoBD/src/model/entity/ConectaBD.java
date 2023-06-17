package model.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBD {

    private Connection conexao;
        
    public ConectaBD() {
        String bancoDados = "cartaodb";
        String url = "jdbc:mariadb://localhost:3306/"+bancoDados;
        String usuario= "root";
        String senha = "120558";
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao realizar conexão.");
        }
    }

    public Connection getConexao() {
        
        return conexao;
    }
    
}
