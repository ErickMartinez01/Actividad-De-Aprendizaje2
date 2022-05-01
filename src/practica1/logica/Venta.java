
package logica;

/**
 *
 * @author marti
 */
public class Venta {
    String nombre;
    int cantidad;
    
    public Venta(){}
    
    public Venta(String nombre, int cantidad)
    {setNombre(nombre);
     setCantidad(cantidad);}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
