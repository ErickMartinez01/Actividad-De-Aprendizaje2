package practica1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import logica.Usuario;

/**
 * @author Erick Martinez
 */
public class Practica1 extends JFrame{
    JLabel eti1,eti2, eti3;  
    JButton b1;
    JPasswordField jt2;
    JTextField jt1;
    Color color;
    ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    public Practica1()
    {super("Acceso al sistema");
    this.setSize(400,300);
    this.setIconImage(new ImageIcon(Practica1.class.getResource("verduras.png")).getImage());
    this.cargarUsuario();
    this.guardarProveedores();
    this.leerProveedores();
     Container contenedor=this.getContentPane();
     contenedor.setLayout(new GridLayout(1,2));
     
        // parte de la izquierda
     
     JPanel panel1=new JPanel(new BorderLayout());
     panel1.setBackground(color=new Color(254,186,173));
     JLabel img= new JLabel("");
     img.setHorizontalAlignment(JLabel.CENTER);
     URL rutita =Practica1.class.getResource("logoPOS.png");
     Image image = new ImageIcon(rutita).getImage().getScaledInstance(210, 155, Image.SCALE_SMOOTH);        
     img.setIcon(new ImageIcon(image));
     img.setVisible(true);
     panel1.add(img,BorderLayout.CENTER);
     
        // parte de la derecha
     JPanel panel2=new JPanel();
     panel2.setLayout(null);
     panel2.setBackground(color=new Color(255,250,250));
     eti1=new JLabel("Inicio de sesión");
     eti1.setFont(new Font ("Arial",Font.BOLD ,16));
     panel2.add(eti1);
     eti1.setBounds(20,30,150,25);
     
     eti2=new JLabel("Usuario");
     eti2.setFont(new Font ("Arial",Font.PLAIN ,12));
     panel2.add(eti2);
     eti2.setBounds(20,70,150,25);

     jt1=new JTextField("Erick");
     panel2.add(jt1);
     jt1.setBounds(20,90,150,25);

     eti3=new JLabel("Contraseña");
     panel2.add(eti3);
     eti3.setFont(new Font ("Arial",Font.PLAIN ,12));
     eti3.setBounds(20,130,150,25);

     jt2=new JPasswordField("123456777");
     panel2.add(jt2);
     jt2.setBounds(20,150,150,25);

     b1= new JButton("Accesar");  
     panel2.add(b1);  
     b1.setBounds(20, 200, 150, 25);
     
     
      b1.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            String nombreu= jt1.getText();
            String passwordu= jt2.getText();
            
            String rol= verificarUsuario(nombreu,passwordu);
            if(rol.length()>0)
            {
             Principal p = new Principal();
             p.setVisible(true);
             JOptionPane.showMessageDialog(null,"Bienvenido: "+rol);
             setVisible(false);
            }
            else                          
             JOptionPane.showMessageDialog(null,"Error: Usuario o password incorrectos");   
           }
        });
     
     contenedor.add(panel1);
     contenedor.add(panel2);
     
      this.setResizable(false);
      this.setLocationRelativeTo(null);

     this.setVisible(true);
     this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        Practica1 p = new Practica1();
    }
    
    public String verificarUsuario(String nu, String pu)
    {
     String rol="";
     for(Usuario usu: listaUsuarios)
     {
       if(usu.getNombre().equals(nu) && usu.getPassword().equals(pu))
        {
           rol= usu.getRol();
        }
     }      
     return rol;
    }
    
    public void cargarUsuario()
    {
      listaUsuarios.add(new Usuario("Erick","123456777","Administrador"));
    }
    public void guardarProveedores()
    {
        FileOutputStream archivo;
        ObjectOutputStream output;
        
      try{
            archivo = new FileOutputStream("usuarios.dat");
            output= new ObjectOutputStream(archivo);
            if(output!=null)
                output.writeObject(listaUsuarios);
            output.close();
          
      
      }catch(Exception e)
      {
         JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
      }
    }
    
    public void leerProveedores()
    {
        FileInputStream archivo;
        ObjectInputStream input;
        
      try{
            archivo = new FileInputStream("usuarios.dat");
            input= new ObjectInputStream(archivo);
            if(input!=null)
                listaUsuarios = (ArrayList<Usuario>)input.readObject();
            
            input.close();
          
      
      }catch(Exception e)
      {
         JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
      }
    }
}
