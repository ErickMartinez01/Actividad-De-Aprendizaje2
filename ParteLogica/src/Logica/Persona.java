
package Logica;

import java.io.Serializable;

/**
 *
 * @author marti
 */
public class Persona implements Serializable
{ int id;

  public Persona() {}
  
  public Persona(int id)
   {
   setId(id);}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     
 
}
