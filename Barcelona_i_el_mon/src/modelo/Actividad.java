package modelo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

        
public class Actividad {
    
    private int idActividad;
    private String nombre;
    private String descripcion;
    private String url;
    private String imagen;
    private ImageView imagenEditar = new ImageView(new Image("/recursos/imagenes/icons8_edit_property_26px.png"));
    private String subTipo;
    private String tipo;
    private Double distanciaCentro;
    private int puntuacion;

    
    public Actividad() {
    }

    public Actividad(String nombre, String descripcion, String url, String imagen, String subTipo, Double distanciaCentro, int puntuacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.imagen = imagen;
        this.subTipo = subTipo;
        this.distanciaCentro = distanciaCentro;
        this.puntuacion = puntuacion;
    }

    public Actividad(int idActividad, String nombre, String descripcion, String url, String imagen, String subTipo, Double distanciaCentro, int puntuacion) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.imagen = imagen;
        this.subTipo = subTipo;
        this.distanciaCentro = distanciaCentro;
        this.puntuacion = puntuacion;
    }

    public Actividad(int idActividad, String nombre, String descripcion, String url, String imagen, String subTipo, String tipo, Double distanciaCentro, int puntuacion) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.imagen = imagen;
        this.subTipo = subTipo;
        this.tipo = tipo;
        this.distanciaCentro = distanciaCentro;
        this.puntuacion = puntuacion;
    }
    
    

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getSubTipo() {
        return subTipo;
    }

    public void setSubTipo(String subTipo) {
        this.subTipo = subTipo;
    }

    public Double getDistanciaCentro() {
        return distanciaCentro;
    }

    public void setDistanciaCentro(Double distanciaCentro) {
        this.distanciaCentro = distanciaCentro;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public ImageView getImagenEditar() {
        return imagenEditar;
    }

    public void setImagenEditar(ImageView imagenEditar) {
        this.imagenEditar = imagenEditar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
  
}
