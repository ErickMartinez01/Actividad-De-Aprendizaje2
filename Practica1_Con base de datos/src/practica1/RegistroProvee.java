
package practica1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Logica.Proveedor;
import java.sql.ResultSet;
import personalizaciones.BotonCrea;
import personalizaciones.BotonEdita;
import personalizaciones.BotonElimina;
import personalizaciones.JTablaEditada;

/**
 *
 * @author marti
 */
public class RegistroProvee extends JFrame{
    JTablaEditada jt;
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
      contenedor.setBackground(new Color(230,255,255));
      
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
      
      BotonCrea creapro= new BotonCrea();
      contenedor.add(creapro);
      creapro.setBounds(430, 80, 150, 25);
      
      
      creapro.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                int clave= Integer.parseInt(tcla.getText());
                String nomb= tpro.getText();
    
        Proveedor cu= new Proveedor();
        cu.setId(clave);
        cu.setNombre(nomb);
        
        try{
           if(cu.insertarProveedor()==1){
              
             mostrarProveedores();           
               
          JOptionPane.showMessageDialog(null, "Se registro con exito el proveedor");     
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
      dft.addColumn("ID");
      dft.addColumn("NOMBRE");
      this.mostrarProveedores();
      
      JScrollPane sp=new JScrollPane(jt);  
      contenedor.add(sp);
      
      jt.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e)
                {
                 if(jt.getSelectedRow()!=-1){              
                tcla.setText(jt.getValueAt(jt.getSelectedRow(), 0).toString());
                tpro.setText(jt.getValueAt(jt.getSelectedRow(), 1).toString());}                        
                }
            });
      jt.setVisible(true);
      sp.setBounds(50, 230, 500, 300);
      
      BotonElimina borraprov= new BotonElimina();
      contenedor.add(borraprov);
      borraprov.setBounds(430, 120, 150, 25);
      borraprov.addActionListener(new ActionListener(){            
                public void actionPerformed(ActionEvent e)
            {        
                if( jt.getSelectedRow()!=-1)
        {
                Proveedor cu= new Proveedor();
            cu.setNombre(jt.getValueAt(jt.getSelectedRow(), 1).toString());
            if( cu.deleteProducto()==1)
            {
               JOptionPane.showMessageDialog(null, "se borró con exito el proveedor");
               dft.removeRow(jt.getSelectedRow());
             }
        } 
            }
            });
      
      BotonEdita editarprov= new BotonEdita();
      contenedor.add(editarprov);
      editarprov.setBounds(430, 160, 150, 25);
      editarprov.addActionListener(new ActionListener(){            
                public void actionPerformed(ActionEvent e)
            {
                String usuario= tpro.getText();
            int id= Integer.parseInt(tcla.getText());
        
        Proveedor cu= new Proveedor();
        cu.setId(id);
        cu.setNombre(usuario);
        
        try{
           if(cu.editarProducto()==1)
           {
             JOptionPane.showMessageDialog(null, "Actualizacion con exito");
             
              jt.setValueAt(id, jt.getSelectedRow(), 0);
              jt.setValueAt(usuario, jt.getSelectedRow(), 1);
            
           }
           else
              JOptionPane.showMessageDialog(null, "No es posible actualizar el proveedor :("); 
           
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
    
    public void mostrarProveedores()
    {
       for(int i= jt.getRowCount()-1; i>=0;i--)
       {
         dft.removeRow(i);
       }
         Proveedor cu= new Proveedor();
         
        ResultSet rst = cu.obtenerTodos();
        try{
        while(rst.next())
        {
          Object[] filita= new Object[2];
          filita[0]= rst.getString("ID");
          filita[1]= rst.getString("NOMBRE");
          dft.addRow(filita);
          
        }
        }
        catch(Exception e2)
        {
         System.out.println(e2.getMessage());
        }
    }
    
}