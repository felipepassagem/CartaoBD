package Objetos.entidades;

public class Cartao {
    private Usuario titular;
    private String documento;
    private String num_cartao;
    private String data_validade;
    private double limite_credito;
    private String tipo = null;

    public Cartao(Usuario titular, String documento, String num_cartao, String data_validade, double limite_credito) {
        this.titular = titular;
        this.documento = documento;
        this.num_cartao = num_cartao;
        this.data_validade = data_validade;
        this.limite_credito = limite_credito;
    }

    public Usuario getTitular() {
        return titular;
    }

    public void setTitular(Usuario titular) {
        this.titular = titular;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNumCartao() {
        return num_cartao;
    }

    public void setNumCartao(String num_cartao) {
        this.num_cartao = num_cartao;
    }

    public String getDataValidade() {
        return data_validade;
    }

    public void setDataValidade(String data_validade) {
        this.data_validade = data_validade;
    }

    public double getLimiteCredito() {
        return limite_credito;
    }

    public void setLimiteCredito(double limite_credito) {
        this.limite_credito = limite_credito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    
}
