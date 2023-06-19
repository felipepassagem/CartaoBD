package model.entity.entidades_DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import model.entity.ConectaBD;
import model.entity.Usuario;

public class UsuarioDAO {

    public void cadastrasUsuario(Usuario u){
        ConectaBD con = new ConectaBD();
        con.getConexao();

        String sql = "INSERT INTO usuario (nome, documento, genero, telefone, dataNascimento) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, u.getNome());
            pst.setString(2, u.getDocumento());
            pst.setString(3, u.getGenero());
            pst.setString(4, u.getTelefone());
            pst.setString(5, u.getDataNascimento());

            pst.execute();
            System.out.println("Usuário cadastrado com sucesso.");
            //INSERIR CADASTRO NO LOG
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //INSERIR FALHA NO LOG
        }
    }
    
}
