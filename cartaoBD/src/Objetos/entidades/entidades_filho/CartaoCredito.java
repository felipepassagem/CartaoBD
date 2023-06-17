package Objetos.entidades.entidades_filho;

import Objetos.entidades.Cartao;
import Objetos.entidades.Usuario;

public class CartaoCredito extends Cartao {

    public CartaoCredito(Usuario titular, String documento, String num_cartao, String data_validade,
            double limite_credito) {
        super(titular, documento, num_cartao, data_validade, limite_credito);
        this.setTipo("credito");
    }
    
}
