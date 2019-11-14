package servidor_gl;


import DAO.ServidorDAO;

import java.sql.SQLException;

public class Servidor {

    public String run(String json ,String email, String senha ) throws SQLException, ClassNotFoundException {
        String retur  = "";
        ServidorDAO db = new ServidorDAO();
        String opção  = json.substring(0,json.indexOf("{"));
        String gson = json.substring(json.indexOf("{"), json.indexOf("}"));
        System.out.println(opção);
        System.out.println(json.indexOf("{"));
        switch (opção) {
            case "authentication":
               retur = db.authentication(email, senha);
                break;
            case "Entrar":

                break;
            case "BuscaUser":

                break;
            case "AddUser":

                break;
            case "Atualizar":

                break;
            case "RemoverUser":

                break;
            case "EditarUser":

                break;
            case "Sair":

                break;
            default:
              return retur = "FAIL";
        }
        return retur;
    }


}
