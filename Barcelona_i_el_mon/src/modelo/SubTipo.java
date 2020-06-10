package modelo;


public class SubTipo {
    private String codigoSubtipo;
    private String nombre;
    private Tipo codigoTipo;

    public SubTipo() {
    }
    
    
    public SubTipo(String codigoSubtipo, String nombre, Tipo codigoTipo) {
        this.codigoSubtipo = codigoSubtipo;
        this.nombre = nombre;
        this.codigoTipo = codigoTipo;
        
    }

    public String getCodigoSubtipo() {
        return codigoSubtipo;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getCodigoTipo() {
        return codigoTipo;
    }
}
