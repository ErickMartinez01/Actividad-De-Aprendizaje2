
package practica1;

import Logica.Usuario;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import personalizaciones.BotonCrea;
import personalizaciones.BotonEdita;
import personalizaciones.BotonElimina;
import personalizaciones.JTablaEditada;

/**
 *
 * @author Erick Martinez
 */
public class RegistroUsers extends JFrame{
    JTablaEditada jt;
    Principal jpadre;
    DefaultTableModel dft;
    int index= -1;
    
    public RegistroUsers(Principal padre)
    {
    super("Registro");
      this.setSize(650, 600);
      this.setLocationRelativeTo(null);
      this.setIconImage(new ImageIcon(Practica1.class.getResource("verduras.png")).getImage());
      this.jpadre=padre;
      
      
      
      Container contenedor = this.getContentPane();
      contenedor.setLayout(null);
      contenedor.setBackground(new Color(230,255,255));
      
      JLabel tit = new JLabel("Registro de Usuarios");
      tit.setFont(new Font("Verdana", Font.PLAIN+Font.BOLD, 18));    
      contenedor.add(tit);
      tit.setBounds(20, 30, 250, 25);
      
      JLabel eti = new JLabel("Id:");
      eti.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(eti);
      eti.setBounds(20, 80, 250, 25);
      
      JLabel nomID = new JLabel(" ");    
      contenedor.add(nomID);
      nomID.setBounds(170, 80, 250, 25);
      
      JLabel lcla = new JLabel("Nombre del usuario:");
      lcla.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lcla);
      lcla.setBounds(20, 120, 250, 25);
      
      JTextField tcla= new JTextField();
      contenedor.add(tcla);
      tcla.setBounds(170, 120, 250, 25);
      
      JLabel lpro = new JLabel("Password:");
      lpro.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(lpro);
      lpro.setBounds(20, 160, 250, 25);
      
      JTextField tpro= new JTextField();
      contenedor.add(tpro);
      tpro.setBounds(170, 160, 250, 25);
      
      JLabel rol1 = new JLabel("Rol:");
      rol1.setFont(new Font("Verdana", Font.PLAIN, 12));    
      contenedor.add(rol1);
      rol1.setBounds(20, 200, 250, 25);
      
      JTextField rol2= new JTextField();
      contenedor.add(rol2);
      rol2.setBounds(170, 200, 250, 25);
      
      BotonCrea creapro= new BotonCrea();
      contenedor.add(creapro);
      creapro.setBounds(430, 80, 150, 25);
      
      
      creapro.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String nomb= tcla.getText();
                String pass= tpro.getText();
                String rol= rol2.getText();
    
        Usuario cu= new Usuario();
        cu.setNombre(nomb);
        cu.setPassword(pass);
        cu.setRol(rol);
        
        try{
           if(cu.insertUsuario()==1){
              
             mostrarUsuarios();           
               
          JOptionPane.showMessageDialog(null, "El usuario se registró con éxito");     
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
      dft.addColumn("USUARIO");
      dft.addColumn("PASSWORD");
      dft.addColumn("ROL");
      this.mostrarUsuarios();
      
      JScrollPane sp=new JScrollPane(jt);  
      contenedor.add(sp);
      
      jt.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e)
                {
                 if(jt.getSelectedRow()!=-1){
                
                nomID.setText(jt.getValueAt(jt.getSelectedRow(), 0).toString());
                tcla.setText(jt.getValueAt(jt.getSelectedRow(), 1).toString());
                tpro.setText(jt.getValueAt(jt.getSelectedRow(), 2).toString());
                rol2.setText(jt.getValueAt(jt.getSelectedRow(), 3).toString());
                 
                 }                        
                }
            });
      jt.setVisible(true);
      sp.setBounds(50, 270, 500, 300);
      
      BotonElimina borraprov= new BotonElimina();
      contenedor.add(borraprov);
      borraprov.setBounds(430, 120, 150, 25);
      borraprov.addActionListener(new ActionListener(){            
                public void actionPerformed(ActionEvent e)
            {      
                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el usuario?",
                    "¿Eliminar?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(opcion== JOptionPane.YES_OPTION)
            {
                if( jt.getSelectedRow()!=-1)
            {
                Usuario cu= new Usuario();
                String clave =jt.getValueAt(jt.getSelectedRow(), 0).toString();
            cu.setId(Integer.parseInt(clave));
            if( cu.delUsuario()==1)
            {
               JOptionPane.showMessageDialog(null, "se borró con exito el usuario");
               dft.removeRow(jt.getSelectedRow());
             }
            else
                JOptionPane.showMessageDialog(null, "No fue posible eliminar");
            } 
            }}
            });
      
      BotonEdita editarprov= new BotonEdita();
      contenedor.add(editarprov);
      editarprov.setBounds(430, 160, 150, 25);
      editarprov.addActionListener(new ActionListener(){            
                public void actionPerformed(ActionEvent e)
            {
                String usuario= tcla.getText();
                String password= tpro.getText();
                String roleo= rol2.getText();                
            int id= Integer.parseInt(nomID.getText());
        
        Usuario cu= new Usuario();
        cu.setId(id);
        cu.setPassword(password);
        cu.setRol(roleo);
        cu.setNombre(usuario);
        
        try{
           if(cu.editarUsuario()==1)
           {
             JOptionPane.showMessageDialog(null, "Actualización con éxito");
             
              jt.setValueAt(id, jt.getSelectedRow(), 0);
              jt.setValueAt(usuario, jt.getSelectedRow(), 1);
              jt.setValueAt(password, jt.getSelectedRow(), 2);
              jt.setValueAt(roleo, jt.getSelectedRow(), 3);
            
           }
           else
              JOptionPane.showMessageDialog(null, "No es posible actualizar el usuario :("); 
           
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
    
    public void mostrarUsuarios()
    {
       for(int i= jt.getRowCount()-1; i>=0;i--)
       {
         dft.removeRow(i);
       }
         Usuario cu= new Usuario();
         
        ResultSet rst = cu.obtenerTodos();
        try{
        while(rst.next())
        {
          Object[] filita= new Object[4];
          filita[0]= rst.getString("ID");
          filita[1]= rst.getString("USUARIO");
          filita[2]= rst.getString("PASSWORD");
          filita[3]= rst.getString("ROL");
          dft.addRow(filita);
          
        }
        }
        catch(Exception e2)
        {
         System.out.println(e2.getMessage());
        }
    }
    
}