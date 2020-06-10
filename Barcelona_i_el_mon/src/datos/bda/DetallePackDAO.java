package datos.bda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import modelo.DetallePack;
import modelo.Pack;

public class DetallePackDAO {

    private Connection conn;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private String consulta;
    private int numFilas;
    private DateTimeFormatter formatter;

    public DetallePackDAO() {
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public boolean insertarBD(Pack pack) throws SQLException {
        int contador = 0;
        boolean respuesta = false;
        Timestamp fechaInicio;
        Timestamp fechaFin;
        Time duracion;
        numFilas = 0;
        
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
        consulta = "INSERT INTO detallepacks VALUES(?,?,?,?,?,?,?,?,?);";

        ps = conn.prepareStatement(this.consulta);
        for (DetallePack detallePack : pack.getListaDetallePack()) {
            fechaInicio = Timestamp.valueOf(formatter.format(detallePack.getFechaHoraInicio()));
            fechaFin = Timestamp.valueOf(formatter.format(detallePack.getFechaHoraFin()));
            ps.setInt(1, detallePack.getIdPack());
            ps.setInt(2, detallePack.getLinea());
            ps.setInt(3, detallePack.getIdActividad());
            ps.setDouble(4, detallePack.getPrecio());
            ps.setTimestamp(5, fechaInicio);
            ps.setTimestamp(6, fechaFin);
            if (detallePack.getDuracion() != null) {
                duracion = Time.valueOf(detallePack.getDuracion());
                ps.setTime(7, duracion);
            }else{
                ps.setTime(7, null);
            }
            ps.setInt(8, detallePack.getNumeroPlazas());
            ps.setDouble(9, 0);
            numFilas += ps.executeUpdate();
            contador++;
        }
        
        if (contador == pack.getListaDetallePack().size()) {
            respuesta = true;
        }

        return respuesta;

    }

}
