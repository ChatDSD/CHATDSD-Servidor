/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_gl;

import DAO.ServidorDAO;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 *
 * @author Luciano Velho
 */
public class Servidor_GL {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        String login = "Rich-Mond ";
        String senha = "1234";
//        ServidorDAO db = new ServidorDAO();
//        db.salvar("'Mark','Rich-Mond ','true', 1234, '24/01/19'" );

        Servidor servidor = new Servidor();
        String retur = servidor.run( "authentication{"
                + "\"login\":\"" + login +"\","
                + "\"senha\":\"" + senha +"\"}", login ,senha);
        System.out.println(retur);
//        int porta = 56000;
//        ServerSocket server = new ServerSocket(porta);
//        server.setReuseAddress(true);
//        Socket conn = null;
//        PrintWriter out = null;
//        BufferedReader in = null;
//        Servidor servidor = new Servidor();
//
//        while (true) {
//            try {
//                System.out.println("Aguardando conexao de cliente...");
//                conn = server.accept();
//                System.out.println("Conexao estabelecida. " + conn.getInetAddress().getHostAddress() + " Enviando dados...");
//                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                String linha = in.readLine();
//                while (linha == null) {
//                    linha = in.readLine();
//                    System.out.println(linha);
//                }
//                servidor.run(linha);
//                out = new PrintWriter(conn.getOutputStream(), true);
//                out.println("autenticado!");
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                conn.close();
//                if (out != null) {
//                    out.close();
//                }
//                System.out.println("Conexao fechada.");
//            }conn.close();
//        }

    }
    
}
