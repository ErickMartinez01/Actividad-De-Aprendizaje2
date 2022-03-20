
package practica1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Erick Martinez
 */
public class Ventas extends JFrame{
    JLabel eti1,eti2,eti3;  
    JButton b1;
    Color color;
    public Ventas()
    {
      super("Ventas");
      this.setSize(400,500);
      this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(Practica1.class.getResource("verduras.png")).getImage());
    Container contenedor = this.getContentPane();
      contenedor.setLayout(new BorderLayout());
      
      //primer panel
      JPanel pnorte = new JPanel();
         pnorte.setBackground(color=new Color(0,133,138));
         pnorte.setLayout(new FlowLayout());
         JLabel titulo = new JLabel("Area de venta");
         titulo.setFont(new Font("Elephant", Font.ITALIC+Font.BOLD,30));
         titulo.setForeground(color=new Color(255,255,255));
         pnorte.add(titulo);
         
        //segundo panel 
    String data[][]={  {"PRODUCTO","PRECIO","CANTIDAD"},
                          {"Cuchara","$4.00","5"},   
                          {"Olla","$35.00","1"}, 
                          {"Vaso","$5.00","5"}, 
                          {"Plato","$10.00","3"}};    
    String column[]={"PRODUCTO","PRECIO","CANTIDAD"};
     JTable tabla= new JTable(data,column);
     tabla.setFont(new Font ("Arial",Font.PLAIN,16));
     
     //tercer panel
     JPanel panel2= new JPanel();
     panel2.setLayout(new GridLayout(1,3));
     eti2=new JLabel("Total: ");
     panel2.add(eti2);
     eti2.setFont(new Font ("Arial",Font.PLAIN ,16));
     
     eti3=new JLabel(" $110.00 ");
     panel2.add(eti3);
     eti3.setFont(new Font ("Arial",Font.PLAIN ,12));
     
     //CUARTO PANEL
     b1= new JButton("Cobrar");  
     panel2.add(b1);
     
     contenedor.add(pnorte,BorderLayout.NORTH);
     contenedor.add(tabla);
     contenedor.add(panel2,BorderLayout.SOUTH);
     
     
    }
}
