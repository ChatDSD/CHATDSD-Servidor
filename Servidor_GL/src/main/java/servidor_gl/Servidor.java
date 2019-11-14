package servidor_gl;


import DAO.ServidorDAO;
import com.google.gson.Gson;
import model.Login;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.sql.SQLException;

public class Servidor {

    public String run(String json ) throws SQLException, ClassNotFoundException {
        String retur  = "";
        ServidorDAO db = new ServidorDAO();
        String opção  = json.substring(0,json.indexOf("{"));
        String conteudo = json.substring(json.indexOf("{"), json.indexOf("}"));
        conteudo +="}" ;
        System.out.println(opção);
        System.out.println(json.indexOf("{"));
        switch (opção) {
            case "authentication":
                JSONObject login = new JSONObject(conteudo);
           retur = db.authentication(login.getString("login"),login.getInt("senha"));
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
