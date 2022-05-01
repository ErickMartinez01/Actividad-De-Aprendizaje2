
package logica;

import java.io.Serializable;

/**
 *
 * @author marti
 */
public class Proveedor extends Persona implements Serializable{
    
    String nombre;
    
    public Proveedor(){}
    
    
    public Proveedor(int id, String nombre)
    {super(id);
     setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
