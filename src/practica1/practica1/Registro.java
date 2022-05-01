
package practica1;

import java.awt.Container;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import logica.Producto;
import logica.Proveedor;

/**
 *
 * @author Erick Martinez
 */
public class Registro extends JFrame{
    JTable jt;
    Principal jpadre;
    DefaultTableModel dft;
    int index= -1;
    public Registro(Principal padre)
    {
    super("Registro");
      this.setSize(650, 600);
      this.setLocationRelativeTo(null);
      this.setIconImage(new ImageIcon(Practica1.class.getResource("verduras.png")).getImage());
      this.jpadre=padre;
      
      Container contenedor = this.getContentPane();
      contenedor.setLayout(null);
      
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
      for(Proveedor prov: jpadre.listaproveedor)
        {
         jprov.addItem(prov.getNombre());
        }
      
      contenedor.add(jprov);
      jprov.setBounds(120, 240, 250, 25);
      
      JButton bguardar= new JButton("Crear Producto");
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
                {productosbase.setProveedor(" ");}
                else {productosbase.setProveedor(provedor);}
                
                
                try{
                jpadre.listaproductos.add(productosbase);
                 Object[] fila = new Object[6];
            
                fila[0]= String.valueOf(productosbase.getClave());
                fila[1]= String.valueOf(productosbase.getNombre());
                fila[2]= String.valueOf(productosbase.getPrecio());
                fila[3]= String.valueOf(productosbase.getExistencias());
                fila[4]= String.valueOf(productosbase.getProveedor());
                
                dft.addRow(fila);
                
                jpadre.guardarProductos();
                
                }catch(Exception e1)
                {
                  JOptionPane.showMessageDialog(null, "Error al registrar: "+e1.getMessage());
                }
                
                JOptionPane.showMessageDialog(null, "Se creo con exito el preducto con la clave: "+clave);
            }
      });
      
      jt= new JTable();
      dft = (DefaultTableModel)jt.getModel();
      dft.addColumn("CLAVE");
      dft.addColumn("NOMBRE");
      dft.addColumn("PRECIO");
      dft.addColumn("EXISTENCIAS");
      dft.addColumn("PROVEEDOR");
      
      
      for(Producto produ2: jpadre.listaproductos)
        {
            Object[] fila = new Object[5];
            
          fila[0]= String.valueOf(produ2.getClave());
          fila[1]= String.valueOf(produ2.getNombre());
          fila[2]= String.valueOf(produ2.getPrecio());
          fila[3]= String.valueOf(produ2.getExistencias());
          fila[4]= String.valueOf(produ2.getProveedor());
          dft.addRow(fila);
        }
      
      JScrollPane sp=new JScrollPane(jt);  
      contenedor.add(sp);
      
      jt.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e)
                {
                int row=jt.rowAtPoint(e.getPoint());                
                 
                index=  Integer.parseInt(jt.getValueAt(row, 0).toString());
                
                texis.setText(jt.getValueAt(row, 3).toString());
                tpre.setText(jt.getValueAt(row, 2).toString());
                tcla.setText(jt.getValueAt(row, 0).toString());
                tpro.setText(jt.getValueAt(row, 1).toString());
                jprov.setSelectedItem(jt.getValueAt(row, 4).toString());                                
                }
            });
      
      sp.setBounds(40, 270, 500, 300);
      
      JButton borrapro= new JButton("Eliminar producto");
      contenedor.add(borrapro);
      borrapro.setBounds(380, 120, 150, 25);
      
      borrapro.addActionListener(new ActionListener(){            
                public void actionPerformed(ActionEvent e)
            {
                if(index!=-1)
                  if(jt.getSelectedRow()!=-1)
                  {
                    try{                                                                                                                                                          
                      int row= jt.getSelectedRow();
                      int clave= Integer.parseInt(jt.getValueAt(row, 0).toString());
                      
                      Producto produ= new Producto();
                      produ.setClave(clave);
                                                              
                      int pos= jpadre.listaproductos.indexOf(produ);                                        
                      if(pos!=-1)
                      {             
                              jpadre.listaproductos.remove(pos);
                               dft.removeRow( jt.getSelectedRow()); 
                            jpadre.guardarProductos();
                            JOptionPane.showMessageDialog(null,"Producto eliminado corretcamente");
                      }                            
                    }
                    catch(Exception e1)
                    {
                      JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
                  }
                }                
            }
            );
      
      JButton editarpro= new JButton("Editar objeto");
      contenedor.add(editarpro);
      editarpro.setBounds(380, 160, 150, 25);
      editarpro.addActionListener(new ActionListener(){            
                public void actionPerformed(ActionEvent e)
            {
                if(index!=-1)
                {
                  if(jt.getSelectedRow()!=-1)
                  {
                     if(tcla.getText()!="")
                     {                       
                       try{
                          int pos= jt.getSelectedRow();
                           Producto pros= jpadre.listaproductos.get(pos);     
                           
                            String produc= tpro.getText();
                            int clave = Integer.parseInt(tcla.getText());
                            float precio = Float.parseFloat(tpre.getText());             
                            int existencia= Integer.parseInt(texis.getText()); 
                            String provedor=jprov.getSelectedItem().toString();
                
                            pros.setClave(clave);
                            pros.setNombre(produc);
                            pros.setPrecio(precio);
                            pros.setExistencias(existencia);
                            pros.setProveedor(provedor);
                            
                            jpadre.listaproductos.set(pos, pros);
                            
                            dft.setValueAt( clave , pos, 0);
                            dft.setValueAt( produc , pos, 1);
                            dft.setValueAt( precio , pos, 2);
                            dft.setValueAt( existencia , pos, 3);
                            dft.setValueAt( provedor , pos, 4);
                              JOptionPane.showMessageDialog(null, "Se actualizó correctamente el producto con clave "+clave);
                            
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
      
      this.setVisible(true);
      this.setResizable(false);
      //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
