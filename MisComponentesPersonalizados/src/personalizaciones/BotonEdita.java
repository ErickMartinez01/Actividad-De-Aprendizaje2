
package personalizaciones;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author marti
 */
public class BotonEdita extends JButton{
    
    public BotonEdita()
    {   this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setFont(new Font("Tahoma", Font.BOLD, 14));
        this.setText("Editar");
        this.setBackground(new Color(240,255,255));
        URL rutita1 = BotonCrea.class.getResource("edita.png");   
        Image imo = new ImageIcon(rutita1).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(imo));
        this.setPreferredSize(new Dimension(150,25));
        this.setSize(150,25);
        
    }

}
