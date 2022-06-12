
package practica1;

import Logica.Conexion;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Logica.Producto;
import Logica.Proveedor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import personalizaciones.BotonCrea;
import personalizaciones.BotonEdita;
import personalizaciones.BotonElimina;
import personalizaciones.JTablaEditada;

/**
 *
 * @author Erick Martinez
 */
public class Registro extends JFrame{
    JTablaEditada jt;
    Principal jpadre;
    DefaultTableModel dft;
    Connection con=null;
    public Registro(Principal padre) throws SQLException
    {
    super("Registro");
      this.setSize(600, 600);
      this.setLocationRelativeTo(null);
      this.setIconImage(new ImageIcon(Practica1.class.getResource("verduras.png")).getImage());
      this.jpadre=padre;
      
      
      Container contenedor = this.getContentPane();
      contenedor.setLayout(null);
      contenedor.setBackground(new Color(230,255,255));
      
      JLabel tit = new JLabel("Registro de Productos");
      tit.setFont(new Font("Verdana", Font.PLAIN+Font.BOLD, 18));    
      contenedor.add(tit);
      tit.setBounds(40, 30, 250, 25);
      
      
      JLabel lcla = new JLabel("Clave:");
      lcla.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lcla);
      lcla.setBounds(40, 80, 250, 25);
      
      JTextField tcla= new JTextField();
      contenedor.add(tcla);
      tcla.setBounds(120, 80, 250, 25);
      
      JLabel lpro = new JLabel("Producto:");
      lpro.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lpro);
      lpro.setBounds(40, 120, 250, 25);
      
      JTextField tpro= new JTextField();
      contenedor.add(tpro);
      tpro.setBounds(120, 120, 250, 25);
      
      JLabel lpre = new JLabel("Precio:");
      lpre.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lpre);
      lpre.setBounds(40, 160, 250, 25);
      
      JTextField tpre= new JTextField();
      contenedor.add(tpre);
      tpre.setBounds(120, 160, 250, 25);
      
      JLabel lexis = new JLabel("Existencias:");
      lexis.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lexis);
      lexis.setBounds(40, 200, 250, 25);
      
      JTextField texis= new JTextField();
      contenedor.add(texis);
      texis.setBounds(120, 200, 250, 25);
      
      JLabel lprov = new JLabel("Proveedor");
      lprov.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lprov);
      lprov.setBounds(40, 240, 250, 25);
      
      JComboBox jprov = new JComboBox();
      
      Proveedor cu= new Proveedor();
         
        ResultSet rst = cu.obtenerTodos();
        String newTexto;
        while(rst.next()){
            newTexto=rst.getString("nombre");
      jprov.addItem(newTexto);}
      
      
      contenedor.add(jprov);
      jprov.setBounds(120, 240, 250, 25);
      
      BotonCrea bguardar= new BotonCrea();
      contenedor.add(bguardar);
      bguardar.setBounds(380, 80, 150, 25);
      
      bguardar.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String produc= tpro.getText();
                int clave = Integer.parseInt(tcla.getText());
                float precio = Float.parseFloat(tpre.getText());             
                int existencia= Integer.parseInt(texis.getText()); 
                String provedor=jprov.getSelectedItem().toString();
                
                
                Producto productosbase = new Producto();
                productosbase.setClave(clave);
                productosbase.setNombre(produc);
                productosbase.setPrecio(precio);
                productosbase.setExistencias(existencia);
                if(provedor==null)
                {productosbase.setProveedor("No hay");}
                else {productosbase.setProveedor(provedor);}
                
                
                try{
           if(productosbase.insertarProducto()==1){
              
             mostrarProductos();           
               
          JOptionPane.showMessageDialog(null, "El producto se registró con exito :)");     
        }
        }
        catch(Exception e2)
        {
          JOptionPane.showMessageDialog(null, e2.getMessage());
        }
            }
      });
      
      jt= new JTablaEditada();
      dft = (DefaultTableModel)jt.getModel();
      dft.addColumn("CLAVE");
      dft.addColumn("NOMBRE");
      dft.addColumn("PRECIO");
      dft.addColumn("EXISTENCIAS");
      dft.addColumn("PROVEEDOR");
      this.mostrarProductos();
      
      JScrollPane sp=new JScrollPane(jt);  
      contenedor.add(sp);
      
      jt.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e)
                {
                 if(jt.getSelectedRow()!=-1){              
                tcla.setText(jt.getValueAt(jt.getSelectedRow(), 0).toString());
                tpro.setText(jt.getValueAt(jt.getSelectedRow(), 1).toString());
                tpre.setText(jt.getValueAt(jt.getSelectedRow(), 2).toString());
                texis.setText(jt.getValueAt(jt.getSelectedRow(), 3).toString());
                jprov.setSelectedItem(jt.getValueAt(jt.getSelectedRow(), 4).toString());  
                 
                }
            }});
      
      sp.setBounds(40, 270, 500, 250);
      
      BotonElimina borrapro= new BotonElimina();
      contenedor.add(borrapro);
      borrapro.setBounds(380, 120, 150, 25);
      
      borrapro.addActionListener(new ActionListener(){            
                public void actionPerformed(ActionEvent e)
            {        
                if( jt.getSelectedRow()!=-1)
        {
                Producto cu= new Producto();
            cu.setNombre(jt.getValueAt(jt.getSelectedRow(), 0).toString());
            if( cu.deleteProducto()==1)
            {
               JOptionPane.showMessageDialog(null, "El producto se borró con éxito");
               dft.removeRow(jt.getSelectedRow());
             }
        } 
            }
            });
      
      BotonEdita editarpro= new BotonEdita();
      contenedor.add(editarpro);
      editarpro.setBounds(380, 160, 150, 25);
      editarpro.addActionListener(new ActionListener(){            
                public void actionPerformed(ActionEvent e)
            {
                String produc= tpro.getText();
                int clave = Integer.parseInt(tcla.getText());
                float precio = Float.parseFloat(tpre.getText());             
                int existencia= Integer.parseInt(texis.getText()); 
                String provedor=jprov.getSelectedItem().toString();
                
                
                Producto productosbase = new Producto();
                productosbase.setClave(clave);
                productosbase.setNombre(produc);
                productosbase.setPrecio(precio);
                productosbase.setExistencias(existencia);
                productosbase.setProveedor(provedor);
        
        try{
           if(productosbase.editarProducto()==1)
           {
             JOptionPane.showMessageDialog(null, "Actualización exitosa");
             
              jt.setValueAt(clave, jt.getSelectedRow(), 0);
              jt.setValueAt(produc, jt.getSelectedRow(), 1);
              jt.setValueAt(precio, jt.getSelectedRow(), 2);
              jt.setValueAt(existencia, jt.getSelectedRow(), 3);
              jt.setValueAt(provedor, jt.getSelectedRow(), 4);
           }
           else
              JOptionPane.showMessageDialog(null, "No es posible actualizar el producto :("); 
           
        }
        catch(Exception e1)
            
        {
          JOptionPane.showMessageDialog(null, e1.getMessage());
        }
            }
            });
      
      this.setVisible(true);
      this.setResizable(false);
      //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void mostrarProductos()
    {
       for(int i= jt.getRowCount()-1; i>=0;i--)
       {
         dft.removeRow(i);
       }
         Producto cu= new Producto();
         
        ResultSet rst = cu.obtenerTodos();
        try{
        while(rst.next())
        {
          Object[] filita= new Object[5];
          filita[0]= rst.getString("CLAVE");
          filita[1]= rst.getString("NOMBRE");
          filita[2]= rst.getString("PRECIO");
          filita[3]= rst.getString("EXISTENCIAS");
          filita[4]= rst.getString("PROVEEDOR");
          
          dft.addRow(filita);
          
        }
        }
        catch(Exception e2)
        {
         System.out.println(e2.getMessage());
        }}
    }

