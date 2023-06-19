package model.entity.entidades_DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public int listarCartoes(int id_usuario){
        ConectaBD con = new ConectaBD();
        
        String sql = "SELECT num_cartao FROM cartao WHERE id_usuario = ?";

        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1, id_usuario);

            ResultSet res = pst.executeQuery();

            if(!res.isBeforeFirst()){
                System.out.println("Usuario não possui nenhum cartão.");
                return -1;
            } else {
                int i = 1;
                System.out.println("Cartões do usuário: \n");
                while(res.next()){
                    System.out.println(i + "- " + res.getString("num_cartao"));
                    
                }
                return 0;
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }


    }
    
}
