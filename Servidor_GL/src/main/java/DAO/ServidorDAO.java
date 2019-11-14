/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;

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
                    " APELIDO           TEXT    NOT NULL, " +
                    " EMAIL            TEXT     NOT NULL, " +
                    " ONLINE            BOOLEAN     NOT NULL, " +
                    " SENHA        INT NOT NULL, " +
                    " NASCI         TEXT NOT NULL)";
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
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "TOKEN TEXT ," +
                    "LOGIN TEXT ," +
                    "PORTA TEXT ," +
                    "ONLINE  BOOLEAN NOT NULL, " +
                    "ID_CLIENTE INTENGER,"+
                    "FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTE(ID))";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }


    public void salvar ( String json ) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName(org);
            c = DriverManager.getConnection(db);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO CLIENTE (APELIDO,EMAIL,ONLINE,SENHA,NASCI) " +
                    "VALUES ("+json+" );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
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
    public void update(int idCliente, String atributo) {

        try {
            Class.forName(org);
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE CLIENTE set "+atributo+"  where ID="+idCliente+";";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM Cliente;" );

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
    public void delete(int idCliente) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DELETE from CLIENTE where ID="+idCliente+";";
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
    public String authentication(String emailc, String senhac) throws ClassNotFoundException, SQLException {
        String retu;

            Class.forName(org);
            c = DriverManager.getConnection(db);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT email FROM CLIENTE WHERE EMAIL = \""+emailc+"\" " +
                    "AND SENHA = \""+senhac+"\";" );

            if(rs == null){
                return  retu = "FAIL";
            }
            return retu = "SUCCESS";
    }

}
