package model;

public class Cliente {

    private int id;
    private String apelido;
    private String email;
    private int senha;
    private String nascimento;

    public Cliente() {

    }

    public Cliente(int id, String apelido, String email, int senha, String nascimento) {
        this.id = id;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
        this.nascimento = nascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
}
