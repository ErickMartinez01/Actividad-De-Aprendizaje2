
package practica1;

import java.awt.Container;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Erick Martinez
 */
public class Registro extends JFrame{
    public Registro()
    {
    super("Registro");
      this.setSize(400, 400);
      this.setLocationRelativeTo(null);
      this.setIconImage(new ImageIcon(Practica1.class.getResource("verduras.png")).getImage());

      Container contenedor = this.getContentPane();
      contenedor.setLayout(null);
      
      JLabel tit = new JLabel("Registro de Productos");
      tit.setFont(new Font("Verdana", Font.PLAIN+Font.BOLD, 16));    
      contenedor.add(tit);
      tit.setBounds(40, 30, 250, 25);
      
      
      JLabel lpro = new JLabel("Producto:");
      lpro.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lpro);
      lpro.setBounds(40, 80, 250, 25);
      
      JTextField tpro= new JTextField();
      contenedor.add(tpro);
      tpro.setBounds(120, 80, 250, 25);
      
      JLabel lpre = new JLabel("Precio:");
      lpre.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lpre);
      lpre.setBounds(40, 120, 250, 25);
      
      JTextField tpre= new JTextField();
      contenedor.add(tpre);
      tpre.setBounds(120, 120, 250, 25);
      
      JLabel lgen = new JLabel("Existencias");
      lgen.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lgen);
      lgen.setBounds(40, 160, 250, 25);
      
      JComboBox jgen = new JComboBox();
      jgen.addItem("1");
      jgen.addItem("2");
      jgen.addItem("3");
      jgen.addItem("4");
      jgen.addItem("5");
      jgen.addItem("6");
      jgen.addItem("7");
      jgen.addItem("8");
      jgen.addItem("9");
      jgen.addItem("10");
      
      contenedor.add(jgen);
      jgen.setBounds(120, 160, 250, 25);
      
      
              
      this.setVisible(true);
      this.setResizable(false);
      //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
