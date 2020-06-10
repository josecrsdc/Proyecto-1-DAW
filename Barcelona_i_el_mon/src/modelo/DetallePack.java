package modelo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DetallePack {

    static private int contador = 1;

    private int idPack;
    private int linea;
    private int idActividad;
    private String nombreActividad;
    private Double precio;
    private Double precioTotalActividad;
    private LocalDateTime fechaHoraInicio;
    private String fechaHoraInicioFormato;
    private LocalDateTime fechaHoraFin;
    private String fechaHoraFinFormato;
    private LocalTime duracion;
    private int numeroPlazas;
    private int diasActividad;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    
//    CONSTRUCTORES
    
    public DetallePack() {
        this.linea = contador;
        contador++;
    }

    public DetallePack(int idPack, int idActividad, Double precio,
            LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin,
            LocalTime duracion, int numeroPlazas,int diasActividad) {
        this.idPack = idPack;
        this.linea = contador;
        this.idActividad = idActividad;
        this.precio = precio;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.duracion = duracion;
        this.numeroPlazas = numeroPlazas;
        this.fechaHoraInicioFormato = fechaHoraInicio.format(formatter);
        this.fechaHoraFinFormato = fechaHoraFin.format(formatter);
        this.diasActividad = diasActividad;
        calcularPrecioTotalActividad();
        contador++;

    }

    public DetallePack(int idPack, int idActividad, Double precio, 
            LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, 
            int numeroPlazas, int diasActividad) {
        this.idPack = idPack;
        this.linea = contador;
        this.idActividad = idActividad;
        this.precio = precio;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.numeroPlazas = numeroPlazas;
        this.fechaHoraInicioFormato = fechaHoraInicio.format(formatter);
        this.fechaHoraFinFormato = fechaHoraFin.format(formatter);
        this.duracion = null;
        this.diasActividad = diasActividad;
        calcularPrecioTotalActividad();
        contador++;
    }
    

 
///////////////////////////////////////////////////////////////////////////////
///////////////////////    SETTER Y GETTER     ////////////////////////////////
///////////////////////////////////////////////////////////////////////////////   
    public Double getPrecioTotalActividad() {
        precioTotalActividad = (double) Math.round(precioTotalActividad * 100d) / 100d;
        return precioTotalActividad;
    }

    public int getDiasActividad() {
        return diasActividad;
    }

    public void setDiasActividad(int diasActividad) {
        this.diasActividad = diasActividad;
    }
    

    public void setPrecioTotalActividad(Double precioTotalActividad) {
        this.precioTotalActividad = precioTotalActividad;
    }

    public int getIdPack() {
        return idPack;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        DetallePack.contador = contador;
    }

    public int getLinea() {
        return linea;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public Double getPrecio() {
        precio = (double) Math.round(precio * 100d) / 100d;
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public int getNumeroPlazas() {
        return numeroPlazas;
    }

    public void setNumeroPlazas(int numeroPlazas) {
        this.numeroPlazas = numeroPlazas;
    }

    public String getFechaHoraInicioFormato() {
        return fechaHoraInicioFormato;
    }

    public void setFechaHoraInicioFormato(String fechaHoraInicioFormato) {
        this.fechaHoraInicioFormato = fechaHoraInicioFormato;
    }

    public String getFechaHoraFinFormato() {
        return fechaHoraFinFormato;
    }

    public void setFechaHoraFinFormato(String fechaHoraFinFormato) {
        this.fechaHoraFinFormato = fechaHoraFinFormato;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

///////////////////////////////////////////////////////////////////////////////
////////////////////////////    METODOS      //////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

    public Period getPeriod(LocalDateTime dob, LocalDateTime now) {
        return Period.between(dob.toLocalDate(), now.toLocalDate());
    }

    public long[] getTime(LocalDateTime dob, LocalDateTime now) {
        final int MINUTES_PER_HOUR = 60;
        final int SECONDS_PER_MINUTE = 60;
        final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
        LocalDateTime today;
        Duration duration;
        long seconds;
        long hours;
        long minutes;
        long secs;
        
        today = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
        duration = Duration.between(today, now);
        seconds = duration.getSeconds();
        hours = seconds / SECONDS_PER_HOUR;
        minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        secs = (seconds % SECONDS_PER_MINUTE);
        
        return new long[]{hours, minutes, secs};
    }

    private void calcularPrecioTotalActividad() {
        if (this.diasActividad != 0) {
            this.precioTotalActividad = this.precio * this.numeroPlazas * this.diasActividad;
        } else {
            
            this.precioTotalActividad = this.precio * this.numeroPlazas;
        }
    }
   

}