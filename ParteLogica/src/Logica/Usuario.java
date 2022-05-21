
package Logica;

import java.io.Serializable;

/**
 *
 * @author marti
 */
public class Usuario implements Serializable{
    private String password;
    private String nombre;
    private String rol;
    public Usuario(){}
    
    public Usuario(String nombre,String password,String rol)
    {setPassword(password);
     setNombre(nombre);
     setRol(rol);}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
