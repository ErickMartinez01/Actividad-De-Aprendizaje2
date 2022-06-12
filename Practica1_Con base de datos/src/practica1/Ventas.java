
package practica1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Logica.Producto;
import Logica.Proveedor;
import personalizaciones.BotonCrea;
import personalizaciones.BotonEdita;
import personalizaciones.BotonElimina;
import personalizaciones.JTablaEditada;

/**
 *
 * @author Erick Martinez
 */
public class Ventas extends JFrame{
    JLabel eti1,eti2,eti3;  
    JButton b1;
    Color color;
    JTablaEditada jt;
    Principal jpadre;
    DefaultTableModel dft;
    String nom;
    Producto productoAVenta;
    float precio;
    String index;
    public Ventas(Principal padre)
    {
      super("Ventas");
      this.setSize(500,600);
      this.setLocationRelativeTo(null);
      this.setIconImage(new ImageIcon(Practica1.class.getResource("verduras.png")).getImage());
      this.jpadre=padre;
      
      Container contenedor = this.getContentPane();
      contenedor.setLayout(null);
      contenedor.setBackground(new Color(230,255,255));
      
      //primer panel
     JLabel titulo = new JLabel("Area de venta");
         titulo.setFont(new Font("Elephant", Font.ITALIC+Font.BOLD,30));
         titulo.setForeground(color=new Color(0,133,138));
         contenedor.add(titulo);
         titulo.setBounds(40, 10, 230, 25);
         
        JLabel lcla = new JLabel("Producto:");
      lcla.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lcla);
      lcla.setBounds(40, 80, 230, 25);
      
      JComboBox jprov = new JComboBox();//producto seleccionado
      
      /*for(Producto produ: jpadre.listaproductos)
        {
         jprov.addItem(produ.getNombre());
        }*/
      //String nom= jprov.getSelectedItem().toString() ;//nombre del producto seleccionado
      contenedor.add(jprov);
      jprov.setBounds(120, 80, 230, 25);
      
      JLabel lpro = new JLabel("Cantidad:");
      lpro.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lpro);
      lpro.setBounds(40, 120, 230, 25);
      
      JComboBox jcant = new JComboBox();//cantidad seleccionada
      
          /*int pos= jpadre.listaproductos.indexOf(produ.getNombre().equals(nom));
          jcant.addItem(produ.getExistencias());*/
          jcant.addItem("1");
          jcant.addItem("2");
          jcant.addItem("3");
          jcant.addItem("4");
          jcant.addItem("5");
          jcant.addItem("6");
          jcant.addItem("7");
          jcant.addItem("8");
          jcant.addItem("9");
          jcant.addItem("10");
        
      contenedor.add(jcant);
      jcant.setBounds(120, 120, 250, 25);
      
      BotonCrea creapro= new BotonCrea();
      contenedor.add(creapro);
      creapro.setBounds(40, 160, 150, 25);
      
      /*creapro.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String nomb= jprov.getSelectedItem().toString();
                int cantidad = Integer.parseInt(jcant.getSelectedItem().toString());               
                
                Venta ventabase = new Venta();
                ventabase.setNombre(nomb);
                ventabase.setCantidad(cantidad);
                
                try{
                jpadre.listaVenta.add(ventabase);
                 Object[] fila = new Object[2];
            
                fila[0]= String.valueOf(ventabase.getNombre());
                fila[1]= String.valueOf(ventabase.getCantidad());        
                dft.addRow(fila);
                
                }catch(Exception e1)
                {
                  JOptionPane.showMessageDialog(null, "Error al registrar producto: "+e1.getMessage());
                }
            }
      });*/
      
      jt= new JTablaEditada();
      dft = (DefaultTableModel)jt.getModel();
      dft.addColumn("PRODUCTO");
      dft.addColumn("CANTIDAD");
      
      /*for(Venta provee2: jpadre.listaVenta)
        {
            Object[] fila = new Object[2];
            
          fila[0]= String.valueOf(provee2.getNombre());
          fila[1]= String.valueOf(provee2.getCantidad());  
          dft.addRow(fila);
        }
      */
      
      JScrollPane sp=new JScrollPane(jt);  
      contenedor.add(sp);
      
      jt.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e)
                {
                int row=jt.rowAtPoint(e.getPoint());                
                 
                index=  jt.getValueAt(row, 0).toString();
                
                jprov.setSelectedItem(jt.getValueAt(row, 0).toString());
                jcant.setSelectedItem(jt.getValueAt(row, 1).toString());                               
                }
            });
      
      sp.setBounds(150, 250, 200, 300);
      
      BotonElimina borraprov= new BotonElimina();
      contenedor.add(borraprov);
      borraprov.setBounds(220, 160, 150, 25);
      /*borraprov.addActionListener(new ActionListener(){            
                public void actionPerformed(ActionEvent e)
            {
                if(index!=null)
                  if(jt.getSelectedRow()!=-1)
                  {
                    try{
                        int row= jt.getSelectedRow();
                      String nomb= jt.getValueAt(row, 0).toString();
                      
                      Producto produ= new Producto();
                      produ.setNombre(nomb);
                        index=produ.getNombre();
                        for(Venta pro: jpadre.listaVenta)
                        {
                            if(pro.getNombre()==index){
                                jpadre.listaVenta.remove(row); 
                                dft.removeRow( jt.getSelectedRow());
                               break;   
                            }                            
                            }
                               //jpadre.listaproductos.remove(pos);
                               //dft.removeRow( jt.getSelectedRow());
                      }  
                    catch(Exception er)
                    {
                      JOptionPane.showMessageDialog(null, er.getMessage());
                    }
                  }
                               
            }
            });*/
      
      BotonEdita editarprov= new BotonEdita();
      contenedor.add(editarprov);
      editarprov.setBounds(40, 200, 150, 25);
      /*editarprov.addActionListener(new ActionListener(){            
                public void actionPerformed(ActionEvent e)
            {
                if(index!=null)
                {
                  if(jt.getSelectedRow()!=-1)
                  {
                     if(jprov.getSelectedItem().toString()!="")
                     {                       
                       try{
                          int pos= jt.getSelectedRow();
                           Venta proves= jpadre.listaVenta.get(pos);     
                           
                            String proveed= jprov.getSelectedItem().toString();
                            int clave = Integer.parseInt(jcant.getSelectedItem().toString());
                
                            proves.setNombre(proveed);
                            proves.setCantidad(clave);
                            
                            jpadre.listaVenta.set(pos, proves);
                            
                            dft.setValueAt( clave , pos, 1);
                            dft.setValueAt( proveed , pos, 0);
                        }                     
                        catch(Exception e1)
                        {
                         JOptionPane.showMessageDialog(null, e1.getMessage());
                        }
                  }
                }                
            }
            }
            });
      */
      
      
      this.setVisible(true);
      this.setResizable(false);
     
    }
}
