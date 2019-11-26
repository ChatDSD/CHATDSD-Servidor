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
    public String buscaConatotos( String login_cliente ) {
         int id_cliente = 0;
         int aux = 1;
         String contatos = null;
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

            stmt = c.createStatement();
            ResultSet contato = stmt.executeQuery( "SELECT ID_CONTATO FROM CONTATO WHERE ID_CLIENTE = \""+id_cliente+"\";" );

            while (contato.next()){
             contatos+= buscaCliente(contato.getInt("id_contato"), aux);
             aux++;
            }

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            return "FAIL";
        }
        if(contatos!= null) {
            contatos = contatos.replaceAll("null", "");
            contatos = "{" + contatos + "}";
            contatos = contatos.substring(0, contatos.length() - 2);
            contatos += "}";
        }else {
            contatos = "VAZIO";
        }
         return  contatos;

    }
    public String update( String login ,String idade) throws SQLException, ClassNotFoundException {
            String retur;
            int idCliente = 0;
            try {
            Class.forName(org);
            c = DriverManager.getConnection(db);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery( "SELECT * FROM CLIENTE WHERE LOGIN = \""+login+"\";" );

                while (rs.next()){
                    idCliente = rs.getInt("id");
                }

                stmt = c.createStatement();
                String sql = "UPDATE CLIENTE set LOGIN = '"+login+"' where ID= "+idCliente+";";
                stmt.executeUpdate(sql);
                c.commit();
                stmt = c.createStatement();
                String sqll = "UPDATE CLIENTE set IDADE = '"+idade+"' where ID= "+idCliente+";";
                stmt.executeUpdate(sqll);
                c.commit();


            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
                return  retur = "FAIL";
            }
        return retur = "SUCCESS";

    }
    public String delete(String login) {
        int contato = 0 ;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            ResultSet rs = stmt.executeQuery( "SELECT * FROM CLIENTE WHERE LOGIN = '"+login+"';" );

            while ( rs.next() ) {
                 contato = rs.getInt("id");

            }
            rs.close();
            stmt = c.createStatement();
            String sql = "DELETE from CONTATO where ID="+contato+";";
            stmt.executeUpdate(sql);
            c.commit();
        } catch ( Exception e ) {
            return "FAIL";
        }
         return "SUCESSO";
    }
    public String authentication(String login, String senhac) throws ClassNotFoundException, SQLException {
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
                String sql = "UPDATE CLIENTE set ONLINE = 'true' where ID="+idCliente+";";
                stmt.executeUpdate(sql);
                c.commit();
            } catch ( Exception e ) {
             return "FAIL" ;
             }

            return "SUCESSO";
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
         return "FAIL";
        }
        return "SUCESSO";
    }
    public String buscaCliente (int id, int aux){
        String cliente = "";

        try {
            Class.forName(org);
            c = DriverManager.getConnection(db);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM CLIENTE WHERE ID  = "+id+" ;" );

            while ( rs.next() ) {
               String login = rs.getString("login");
               boolean online = rs.getBoolean("online");
               String ip = rs.getString("ip");
               String porta = rs.getString("porta");
               cliente = " contato"+aux+":{\"login\" :  \""+login+"\", \"online\" : \""+online+"\", \"ip\" : \""+ip+"\", \"porta\" : \""+porta+"\"},";
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return "FAIL";
        }
        return cliente;

    }
}
