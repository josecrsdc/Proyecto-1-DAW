package datos.bda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import modelo.Actividad;

public class ActividadDAO {

    private Connection conn;
    private ResultSet rs = null;
    private Actividad actividad;
    private PreparedStatement ps;
    
    private int numFilas;
    private int idActividad; 
    private int puntuacion;
    private Double distanciaCentro;
    private String consulta;
    private String nombre;
    private String descripcion;
    private String codigoSubtipo;
    private String codigoTipo;
    private String imagen;
    private String url;
    private boolean respuesta;

    public ActividadDAO() {
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

 
    public Set<Actividad> hacerConsulta(String tipo) throws SQLException {
        Set<Actividad> listaActividades = new HashSet<>();
        consulta = "SELECT *"
                + "FROM actividades a INNER JOIN subtipos s "
                + "ON a.codigosubtipo = s.codigosubtipo "
                + "WHERE s.codigotipo = ?";

        ps = conn.prepareStatement(consulta);
        ps.setString(1, tipo);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            idActividad = rs.getInt("idActividad");
            nombre = rs.getString("nombre");
            descripcion = rs.getString("descripcion");
            url = rs.getString("url");
            imagen = rs.getString("imagen");
            codigoSubtipo = rs.getString("codigoSubtipo");
            distanciaCentro = rs.getDouble("distanciaCentro");
            puntuacion = rs.getInt("puntuacion");
            actividad = new Actividad(idActividad, nombre, descripcion, url, imagen, codigoSubtipo, distanciaCentro, puntuacion);
            listaActividades.add(actividad);
        }

        return listaActividades;
    }

    public Set<Actividad> hacerConsulta() throws SQLException {
        Set<Actividad> listaActividades = new HashSet<>();
        consulta = "SELECT *"
                + "FROM actividades a INNER JOIN subtipos s "
                + "ON a.codigosubtipo = s.codigosubtipo ";

        ps = conn.prepareStatement(consulta);
        rs = ps.executeQuery();

        while (rs.next()) {
            idActividad = rs.getInt("idActividad");
            nombre = rs.getString("nombre");
            descripcion = rs.getString("descripcion");
            url = rs.getString("url");
            imagen = rs.getString("imagen");
            codigoSubtipo = rs.getString("codigoSubtipo");
            codigoTipo = rs.getString("codigoTipo");
            distanciaCentro = rs.getDouble("distanciaCentro");
            puntuacion = rs.getInt("puntuacion");
            actividad = new Actividad(idActividad, nombre, descripcion, url, 
                    imagen, codigoSubtipo, codigoTipo, distanciaCentro, puntuacion);
            listaActividades.add(actividad);
        }

        return listaActividades;

    }

    public boolean eliminarActividad(int idActividad) throws SQLException {
        numFilas = 0;
        respuesta = false;

        consulta = "DELETE FROM actividades WHERE idActividad = ?";
        ps = conn.prepareStatement(consulta);

        ps.setInt(1, idActividad);
        numFilas = ps.executeUpdate();
        if (numFilas > 0) {
            respuesta = true;
        }
        return respuesta;
    }

    public boolean insertarActividad(Actividad actividad) throws SQLException {
        numFilas = 0;
        respuesta = false;

        consulta = "INSERT INTO actividades (nombre, descripcion, url, imagen, "
                + "codigoSubtipo, distanciaCentro, puntuacion) VALUES (?,?,?,?,?,?,?)";
        ps = conn.prepareStatement(consulta);
        ps.setString(1, actividad.getNombre());
        ps.setString(2, actividad.getDescripcion());
        ps.setString(3, actividad.getUrl());
        ps.setString(4, actividad.getImagen());
        ps.setString(5, actividad.getSubTipo());
        ps.setDouble(6, actividad.getDistanciaCentro());
        ps.setInt(7, actividad.getPuntuacion());
        numFilas = ps.executeUpdate();
        if (numFilas > 0) {
            respuesta = true;
        }
        return respuesta;
    }

    public int ultimoValorId() throws SQLException {
        consulta = "SELECT MAX(idActividad) FROM actividades";
        int respuesta = 0;
        ps = conn.prepareStatement(consulta);
        rs = ps.executeQuery();
        while (rs.next()) {
            respuesta = rs.getInt(1) + 1;
        }
        return respuesta;
    }
    
    public boolean modificarActividad(Integer idActividad,String nombre, String descripcion, 
            String url, String imagen, String subtipo, Double distanciaCentro, 
            Integer puntuacion ) throws SQLException{
        respuesta = false;
        numFilas = 0;
        consulta = "UPDATE actividades "
                + "SET nombre = ?, descripcion = ?, url = ?,"
                + " imagen = ?,codigoSubtipo = ?, distanciaCentro = ?, puntuacion = ?"
                + " WHERE idActividad = ?";  
        ps = conn.prepareStatement(consulta);
        
        ps.setString(1, nombre);
        ps.setString(2, descripcion);
        ps.setString(3, url);
        ps.setString(4, imagen);
        ps.setString(5, subtipo);
        ps.setDouble(6, distanciaCentro);
        ps.setInt(7, puntuacion);
        ps.setInt(8, idActividad);
        
        numFilas = ps.executeUpdate();

        if(numFilas > 0){
            respuesta = true;
        }
            
        return respuesta;
    }
}
