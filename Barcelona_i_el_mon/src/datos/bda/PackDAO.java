package datos.bda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import modelo.Pack;

public class PackDAO {

    private Connection conn;
    private ResultSet rs = null;
    private String consulta;
    private PreparedStatement ps;

    public PackDAO() {
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public int ultimoValorId() throws SQLException {
        int respuesta = 0;
        consulta = "SELECT MAX(idPack) FROM packs";
        ps = conn.prepareStatement(consulta);
        rs = ps.executeQuery();
        while (rs.next()) {
            respuesta = rs.getInt(1) + 1;
        }
        return respuesta;
    }

    public boolean insertarBD(Pack pack) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
        Timestamp fechaCreacion;
        Timestamp fechaValidez;
        int numFilas;
        boolean respuesta = false;

        consulta = "INSERT INTO packs VALUES (?,?,?,?,?);";
        ps = conn.prepareStatement(consulta);
        fechaCreacion = Timestamp.valueOf(formatter.format(pack.getFechaCreacion()));
        fechaValidez = Timestamp.valueOf(formatter.format(pack.getFechaValidez()));

        ps.setInt(1, pack.getIdPack());
        ps.setString(2, pack.getNombre());
        ps.setTimestamp(3, fechaValidez);
        ps.setTimestamp(4, fechaCreacion);
        ps.setString(5, pack.getDni());
        numFilas = ps.executeUpdate();
        if (numFilas > 0) {
            respuesta = true;
        }

        return respuesta;
    }
}
