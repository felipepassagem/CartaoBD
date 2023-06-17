package Objetos.entidades;

import java.util.ArrayList;


public class Usuario{
    private String nome;
    private String documento;
    private String data_nascimento;
    private String genero;
    private String telefone;
    
    public Usuario(String nome, String documento, String data_nascimento, String genero, String telefone) {
        this.nome = nome;
        this.documento = documento;
        this.data_nascimento = data_nascimento;
        this.genero = genero;
        this.telefone = telefone;
        
    }

    public Usuario(){
        
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDataNascimento() {
        return data_nascimento;
    }

    public void setDataNascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    
}
