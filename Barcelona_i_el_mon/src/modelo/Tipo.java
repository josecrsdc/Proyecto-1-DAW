
package modelo;

import java.util.HashSet;
import java.util.Set;

public class Tipo {
    private String nombre;
    private String codigoTipo;
    private Set<SubTipo> listaSubtipo = new HashSet<>();

    public Tipo() {
    }

    public Tipo(String nombre, String codigoTipo) {
        this.nombre = nombre;
        this.codigoTipo = codigoTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoTipo() {
        return codigoTipo;
    }

    public void setCodigoTipo(String codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public Set<SubTipo> getListaSubtipo() {
        return listaSubtipo;
    }

    public void setListaSubtipo(Set<SubTipo> listaSubtipo) {
        this.listaSubtipo = listaSubtipo;
    }
    
    
    
}
