package model.entity.entidades_filho;


import model.entity.Cartao;
import model.entity.Usuario;

public class CartaoCredito extends Cartao {

    public CartaoCredito(int id_usuario, String documento,  String data_validade,
            double limite_credito) {
        super(id_usuario, documento, data_validade, limite_credito);
        this.setTipo("credito");
    }
    
}
