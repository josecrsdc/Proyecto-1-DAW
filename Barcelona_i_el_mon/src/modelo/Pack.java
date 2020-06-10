package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import vistas.PageHomeController;


public class Pack {

    private PageHomeController homeController;
    private LocalDateTime fechaValidez;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private List<DetallePack> listaDetallePack = new ArrayList<>();
    private Double precioTotal = 0.0;
    private String dni;
    private String nombre;
    private int idPack;
    private int numDetallesPack;

    public Pack() {
    }

    public Pack(int idPack, String nombre, String dni) {
        this.idPack = idPack;
        this.nombre = nombre;
        this.dni = dni;
    }

    public void setHomeController(PageHomeController homeController) {
        this.homeController = homeController;
    }

    public int getIdPack() {
        return idPack;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public Double getPrecioTotal() {
        precioTotal = (double) Math.round(precioTotal * 100d) / 100d;
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaValidez() {
        return fechaValidez;
    }

    public void setFechaValidez(LocalDateTime fechaValidez) {
        this.fechaValidez = fechaValidez;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<DetallePack> getListaDetallePack() {
        return listaDetallePack;
    }

    public int getNumDetallesPack() {
        return numDetallesPack;
    }

//    METODOS
    public void añadirDetallePack(DetallePack detallePack) {
        listaDetallePack.add(detallePack);
        precioTotal += detallePack.getPrecioTotalActividad();
        precioTotal = (double) Math.round(precioTotal * 100d) / 100d;

        numDetallesPack = listaDetallePack.size();
        homeController.setCarritoCantidadString(String.valueOf(numDetallesPack));
    }

    public void eliminarDetallePack(DetallePack detallePack) {
        listaDetallePack.remove(detallePack);
        numDetallesPack = listaDetallePack.size();
        homeController.setCarritoCantidadString(String.valueOf(numDetallesPack));
    }
    
    public void descontarPrecio(Double precio){
        this.precioTotal = precioTotal - precio;
    }
    

}
