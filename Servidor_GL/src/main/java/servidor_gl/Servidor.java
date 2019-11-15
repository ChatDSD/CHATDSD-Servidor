package servidor_gl;


import DAO.ServidorDAO;
import org.json.JSONObject;

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
            case "create_account":
                JSONObject cadastrar = new JSONObject(conteudo);
                retur = db.salvar(cadastrar.getString("apelido"),
                        cadastrar.getString("email"),
                        cadastrar.getInt("senha"),
                        cadastrar.getBoolean("online"),
                        cadastrar.getString("nasci"));
                break;
            case "update_info":
                JSONObject update = new JSONObject(conteudo);
                retur = db.update(update.getString("apelido"),
                        update.getString("email"),
                        update.getString("nasci"));
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
