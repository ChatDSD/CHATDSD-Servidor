/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luciano Velho
 */
public class ServidorDAO {

    Connection c = null;
    Statement stmt = null;
    String org = "org.sqlite.JDBC";
    String db = "jdbc:sqlite:servidorGL.db";

    public  void criarTabela(  ) {

        try {
            Class.forName(org);
            c = DriverManager.getConnection(db);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE CLIENTE " +
                    "(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    " LOGIN           TEXT    NOT NULL, " +
                    " EMAIL            TEXT     NOT NULL, " +
                    " ONLINE            BOOLEAN     NOT NULL, " +
                    " SENHA        TEXT NOT NULL, " +
                    " IP        TEXT NOT NULL, " +
                    " PORTA        TEXT NOT NULL, " +
                    " IDADE         TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
    public  void criandoRelacionamento(  ) {

        try {
            Class.forName(org);
            c = DriverManager.getConnection(db);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE CONTATO" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                    " ID_CLIENTE        TEXT NOT NULL, " +
                    " ID_CONTATO         TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }


    public String salvar ( String login, String email, String senha , boolean online , String ip, String porta, String idade) {
        String retur;
        try {
            Class.forName(org);
            c = DriverManager.getConnection(db);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO CLIENTE ( LOGIN ,EMAIL,SENHA,ONLINE,IP,PORTA,IDADE) " +
                    "VALUES ('"+login+"','"+email+"','"+senha+"','"+online+"', '"+ip+"', '"+porta+"', '"+idade+"' );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return retur = "FAIL";
        }
        return retur= "SUCCESS";
    }
    public void buscaAtributos( ) {

        try {
            Class.forName(org);
            c = DriverManager.getConnection(db);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM CLIENTE;" );

            while ( rs.next() ) {
                String apelido = rs.getString("apelido");
                String  email = rs.getString("email");
                int senha  = rs.getInt("senha");
                String  nasci = rs.getString("nasci");

                System.out.println( "Apelido = " + apelido );
                System.out.println( "email = " + email );
                System.out.println( "senha = " + senha );
                System.out.println( "nasci = " + nasci );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    public String update( String login , String email, String idade) throws SQLException, ClassNotFoundException {
            String retur;
            try {
            Class.forName(org);
            c = DriverManager.getConnection(db);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs =  stmt.executeQuery("UPDATE CLIENTE set LOGIN = '"+login+"', IDADE = '"+idade+"'  where EMAIL= '"+email+"';");
                while ( rs.next() ) {
                    String apelido0 = rs.getString("apelido");
                    String  emaill = rs.getString("email");
                    int senha  = rs.getInt("senha");
                    String  nascii = rs.getString("nasci");
                }
            stmt.close();
            c.close();

            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
                return  retur = "FAIL";
            }
        return retur = "SUCCESS";

    }
    public void delete(int idCliente) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DELETE from CONTATO where ID="+idCliente+";";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM CLIENTE;" );

            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int age  = rs.getInt("age");
                String  address = rs.getString("address");
                float salary = rs.getFloat("salary");

                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "AGE = " + age );
                System.out.println( "ADDRESS = " + address );
                System.out.println( "SALARY = " + salary );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    public String authentication(String login, int senhac) throws ClassNotFoundException, SQLException {
           String retu;
           int idCliente = 0;
           int idContato = 0;
           List<String> contatos = new ArrayList<>();
            try{
            Class.forName(org);
            c = DriverManager.getConnection(db);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM CLIENTE WHERE LOGIN = \""+login+"\" " +
                    "AND SENHA = \""+senhac+"\";" );

            while (rs.next()){
                idCliente = rs.getInt("id");
            }
                stmt = c.createStatement();
                String sql = "UPDATE ClIENTE set ONLINE = 'true' where ID="+idCliente+";";
                stmt.executeUpdate(sql);
                c.commit();

                stmt = c.createStatement();
                ResultSet contato = stmt.executeQuery( "SELECT * FROM CONTATO WHERE ID = \""+idCliente+"\";" );

                while (contato.next()){
                    idContato = contato.getInt("ID_CLIENTE");
                }

            } catch ( Exception e ) {
             return e.getClass().getName() + ": " + e.getMessage() ;
             }

            return null;
    }
    public String addContato(String login_cliente, String login_contato){
        int id_cliente = 0;
        int id_contato = 0;
        try {
            Class.forName(org);
            c = DriverManager.getConnection(db);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet cliente = stmt.executeQuery( "SELECT * FROM CLIENTE WHERE LOGIN = '"+login_cliente+"';" );

            while ( cliente.next() ) {
                id_cliente = cliente.getInt("id");
            }
            cliente.close();


            stmt = c.createStatement();
            ResultSet contato = stmt.executeQuery( "SELECT * FROM CLIENTE WHERE LOGIN = '"+login_contato+"';" );

            while ( contato.next() ) {
                id_contato = contato.getInt("id");
            }
            contato.close();

            stmt = c.createStatement();
            String sql = "INSERT INTO CONTATO (ID_CLIENTE, ID_CONTATO ) " +
                    "VALUES ('"+id_cliente+"','"+id_contato+"');";
            stmt.executeUpdate(sql);

            stmt = c.createStatement();
            String sqll = "INSERT INTO CONTATO ( ID_CLIENTE ,ID_CONTATO ) " +
                    "VALUES ('"+id_contato+"','"+id_cliente+"');";
            stmt.executeUpdate(sqll);

            stmt.close();
            c.commit();
            c.close();

        } catch ( Exception e ) {
            return  e.getClass().getName() + ": " + e.getMessage();

        }

        return "SUCESSO";
    }
}
