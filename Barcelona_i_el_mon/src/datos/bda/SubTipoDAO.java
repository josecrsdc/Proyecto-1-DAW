package datos.bda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class SubTipoDAO {

    private Connection conn;
    private ResultSet rs = null;
    private Set<String> listaSubTipos = new HashSet<>();
    private PreparedStatement ps;

    public SubTipoDAO() {
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Set<String> hacerConsulta() throws SQLException {
        String codigoSubTipo;
        String consulta = "SELECT codigoSubtipo FROM subtipos;";

        ps = conn.prepareStatement(consulta);
        rs = ps.executeQuery();
        while (rs.next()) {
            codigoSubTipo = rs.getString("codigoSubtipo");
            listaSubTipos.add(codigoSubTipo);
        }

        return listaSubTipos;
    }

}
