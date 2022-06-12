
package practica1;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author LABCOMP
 */
public class Reloj implements Runnable{
    Thread hiloreloj;
    JLabel etiq;
    public Reloj(JLabel etiqueta){
    
        
        etiq=etiqueta;
        hiloreloj=new Thread(this);
        hiloreloj.setName("Reloj-1");
        hiloreloj.start();
    }
    
@Override   
public void run()
 {System.out.println(hiloreloj.getName());
    while(true){
        etiq.setText(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
     try {
         Thread.sleep(1000);
     } catch (InterruptedException ex) {
         Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
     }
    } 
    }
}


