package datos.bda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestionBD {

   private String urlBD = "jdbc:mysql://localhost:3306/" + "bcn_v5" + "?serverTimezone=CET";
   private String login = "root";
   private String password = "root";
   private Connection conn;

    public GestionBD() {

    }

    public String getUrlBD() {
        return urlBD;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConn() {
        return conn;
    }

    public String conectarBD() throws SQLException {
        String respuesta = "";
        conn = DriverManager.getConnection(urlBD, login, password);
        if (conn != null) {
            respuesta = "Conexión a base de datos " + urlBD + " ... Ok";
        } else {
            respuesta = "Hubo un problema al intentar conectarse con la base de datos " + urlBD;
        }
        return respuesta;
    }
    
  

}
