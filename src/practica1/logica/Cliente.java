
package logica;

import java.io.Serializable;

/**
 *
 * @author marti
 */
public class Cliente extends Persona implements Serializable{
    
    String nombre;
    float saldo;
    
    public Cliente(){}
    
    public Cliente(int id,String nombre,float saldo)
    {super(id);
     setNombre(nombre);
     setSaldo(saldo);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    
}
