
package logica;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author marti
 */
public class Producto implements Serializable 
{ int clave;
  String nombre;
  float precio;
  int existencias;
  String proveedor;
    public Producto() {}
  
  
  Producto(int clave, String nombre,float precio, int exis,String proveedor)
  {this.clave=clave;
   this.nombre=nombre;
   this.precio=precio;
   this.existencias=exis;
   this.proveedor=proveedor;
   }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
  
    @Override
    public boolean equals(Object o)
    {
      if( this==o) return true;
      if(o==null || getClass()!=o.getClass()) return false;      
      Producto pro= (Producto)o;      
      return Integer.compare( pro.clave, clave )==0;
    }
    
    @Override
    public int hashCode()
    {
      return Objects.hash(clave);
    }
}
