
package practica1;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import logica.Proveedor;

/**
 *
 * @author marti
 */
public class RegistroProvee extends JFrame{
    JTable jt;
    Principal jpadre;
    DefaultTableModel dft;
    int index= -1;
    
    public RegistroProvee(Principal padre)
    {
    super("Registro");
      this.setSize(650, 600);
      this.setLocationRelativeTo(null);
      this.setIconImage(new ImageIcon(Practica1.class.getResource("verduras.png")).getImage());
      this.jpadre=padre;
      
      Container contenedor = this.getContentPane();
      contenedor.setLayout(null);
      
      JLabel tit = new JLabel("Registro de Proveedores");
      tit.setFont(new Font("Verdana", Font.PLAIN+Font.BOLD, 18));    
      contenedor.add(tit);
      tit.setBounds(20, 30, 250, 25);
      
      
      JLabel lcla = new JLabel("Clave:");
      lcla.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lcla);
      lcla.setBounds(20, 80, 250, 25);
      
      JTextField tcla= new JTextField();
      contenedor.add(tcla);
      tcla.setBounds(170, 80, 250, 25);
      
      JLabel lpro = new JLabel("Nombre del proveedor:");
      lpro.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lpro);
      lpro.setBounds(20, 120, 250, 25);
      
      JTextField tpro= new JTextField();
      contenedor.add(tpro);
      tpro.setBounds(170, 120, 250, 25);
      
      JButton creapro= new JButton("Crear Proveedor");
      contenedor.add(creapro);
      creapro.setBounds(430, 80, 150, 25);
      
      creapro.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String provee= tpro.getText();
                int clave = Integer.parseInt(tcla.getText());               
                
                
                Proveedor proveedorbase = new Proveedor();
                proveedorbase.setId(clave);
                proveedorbase.setNombre(provee);
                
                try{
                jpadre.listaproveedor.add(proveedorbase);
                 Object[] fila = new Object[2];
            
                fila[0]= String.valueOf(proveedorbase.getId());
                fila[1]= String.valueOf(proveedorbase.getNombre());        
                dft.addRow(fila);
                
                jpadre.guardarProveedores();
                }catch(Exception e1)
                {
                  JOptionPane.showMessageDialog(null, "Error al registrar: "+e1.getMessage());
                }
                
                JOptionPane.showMessageDialog(null, "Se registró con exito el proveedor: "+provee);
            }
      });
      
      jt= new JTable();
      dft = (DefaultTableModel)jt.getModel();
      dft.addColumn("CLAVE");
      dft.addColumn("NOMBRE");
      
      for(Proveedor provee2: jpadre.listaproveedor)
        {
            Object[] fila = new Object[2];
            
          fila[0]= String.valueOf(provee2.getId());
          fila[1]= String.valueOf(provee2.getNombre());  
          dft.addRow(fila);
        }
      
      JScrollPane sp=new JScrollPane(jt);  
      contenedor.add(sp);
      
      jt.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e)
                {
                int row=jt.rowAtPoint(e.getPoint());                
                 
                index=  Integer.parseInt(jt.getValueAt(row, 0).toString());
                
                tcla.setText(jt.getValueAt(row, 0).toString());
                tpro.setText(jt.getValueAt(row, 1).toString());                               
                }
            });
      
      sp.setBounds(50, 230, 500, 300);
      
      JButton borraprov= new JButton("Eliminar proveedor");
      contenedor.add(borraprov);
      borraprov.setBounds(430, 120, 150, 25);
      borraprov.addActionListener(new ActionListener(){            
                public void actionPerformed(ActionEvent e)
            {
                if(index!=-1)
                {
                  if(jt.getSelectedRow()!=-1)
                  {
                    try{
                        int conta=0;
                        for(Proveedor pro: jpadre.listaproveedor)
                        {
                            if(pro.getId()==index){
                                jpadre.listaproveedor.remove(conta);                                
                               break;   
                            }                            
                            }
                               dft.removeRow( jt.getSelectedRow());                        
                            jpadre.guardarProveedores(); 
                            JOptionPane.showMessageDialog(null,"Proveedor eliminado corretcamente");
                      }  
                    catch(Exception er)
                    {
                      JOptionPane.showMessageDialog(null, er.getMessage());
                    }
                  }
                }                
            }
            });
      
      JButton editarprov= new JButton("Editar proveedor");
      contenedor.add(editarprov);
      editarprov.setBounds(430, 160, 150, 25);
      editarprov.addActionListener(new ActionListener(){            
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
                           Proveedor proves= jpadre.listaproveedor.get(pos);     
                           
                            String proveed= tpro.getText();
                            int clave = Integer.parseInt(tcla.getText());
                
                            proves.setId(clave);
                            proves.setNombre(proveed);
                            
                            jpadre.listaproveedor.set(pos, proves);
                            
                            dft.setValueAt( clave , pos, 0);
                            dft.setValueAt( proveed , pos, 1);
                              JOptionPane.showMessageDialog(null, "Se actualizó correctamente el proveedor con clave "+clave);
                            
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