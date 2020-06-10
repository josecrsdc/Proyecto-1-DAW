package datos.bda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TipoDAO {

    private Connection conn;
    private ResultSet rs = null;
    private Set<String> listaTipos = new HashSet<>();
    private PreparedStatement ps;

    public TipoDAO() {

    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Set<String> hacerConsulta() throws SQLException {
        String codigoTipo;
        String consulta = "SELECT codigoTipo FROM tipos;";

        ps = conn.prepareStatement(consulta);
        rs = ps.executeQuery();

        while (rs.next()) {
            codigoTipo = rs.getString("codigoTipo");
            listaTipos.add(codigoTipo);
        }
        return listaTipos;
    }

}
