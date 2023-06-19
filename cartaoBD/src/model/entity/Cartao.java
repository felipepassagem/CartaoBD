package model.entity;

public class Cartao {
    private int id;
    private int id_usuario;
    
    private String num_cartao;
    private String data_validade;
    private double limite_credito;
    private String tipo = null;
    private double credito_usado = 0;

    public Cartao(int id_usuario, String num_cartao, String data_validade, double limite_credito) {
        this.id_usuario = id_usuario;
        this.num_cartao = num_cartao;
        this.data_validade = data_validade;
        this.limite_credito = limite_credito;
    }

    public Cartao() {
        
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setCredito_usado(double credito_usado) {
        this.credito_usado = credito_usado;
    }


    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNum_cartao() {
        return num_cartao;
    }

    public void setNum_cartao(String num_cartao) {
        this.num_cartao = num_cartao;
    }

    public String getData_validade() {
        return data_validade;
    }

    public void setData_validade(String data_validade) {
        this.data_validade = data_validade;
    }

    public double getLimite_credito() {
        return limite_credito;
    }

    public void setLimite_credito(double limite_credito) {
        this.limite_credito = limite_credito;
    }

    public double getCredito_usado() {
        return credito_usado;
    }

    

    
    public int getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
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
