
package practica1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * @author Erick Martinez
 */
public class Principal extends JFrame{
    JDesktopPane desktop = new JDesktopPane();       
    Color colores;
    public Principal() {
        super("Mi pantalla principal");
        this.setIconImage(new ImageIcon(Practica1.class.getResource("verduras.png")).getImage());
        Container contenedor = this.getContentPane();
        contenedor.setLayout(new BorderLayout());  
        
        
        //Panel superior (norte)
         JPanel pnorte = new JPanel();
         pnorte.setBackground(colores=new Color(62,95,138));
         pnorte.setLayout(new FlowLayout());
         JLabel titulo = new JLabel("MENÚ PRINCIPAL");
         titulo.setFont(new Font("Elephant", Font.ITALIC+Font.BOLD,30));
         titulo.setForeground(colores=new Color(255,255,255));
         pnorte.add(titulo);
         
        //Panel izquierda (oeste)     
        JPanel poeste = new JPanel();
        poeste.setLayout(new GridLayout(8,1,5,5));
        poeste.setBackground(colores=new Color(159,213,209));
        
        
        // primer objeto
        JLabel img1 = new JLabel("");  
        img1.setHorizontalAlignment(JLabel.CENTER);
        URL rutita1 = Practica1.class.getResource("verduras.png");        
        Image ima1= new ImageIcon(rutita1).getImage();        
        ImageIcon image1 = new ImageIcon( ima1.getScaledInstance(100, 80, Image.SCALE_SMOOTH));                
        img1.setIcon(image1);
        img1.setVisible(true);        
        poeste.add(img1);
        
        //segundo objeto
        JButton b1 = new JButton("VENTAS");
        rutita1 = Practica1.class.getResource("ventas.png");
        Image i1= new ImageIcon(rutita1).getImage();   
        ImageIcon icono = new ImageIcon(i1.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        b1.setIcon(icono);
        b1.setBackground(colores=new Color(159,213,209));
        
        //ACCION DE BOTON VENTAS
        poeste.add(b1);
         b1.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {      
            Ventas();
          }
         }
        );
        
        
        
        //tercer objeto
        JButton b2 = new JButton("INVENTARIO");
        URL rutita2 = Practica1.class.getResource("inventario.png");
        Image i2= new ImageIcon(rutita2).getImage();   
        ImageIcon icono2 = new ImageIcon(i2.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        b2.setIcon(icono2);
        b2.setBackground(colores=new Color(159,213,209));
        poeste.add(b2);
        b2.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {               
                listarProductos();
                
          }
         }
        );
        
        //cuarto objeto
        JButton b3 = new JButton("REGISTRAR");
        URL rutita3 = Practica1.class.getResource("gastos.png");
        Image i3= new ImageIcon(rutita3).getImage();   
        ImageIcon icono3 = new ImageIcon(i3.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        b3.setIcon(icono3);
        b3.setBackground(colores=new Color(159,213,209));
        poeste.add(b3);
        b3.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {               
                registrarProducto();      
          }
         }
        );
        
        //quinto objeto
        JButton b4 = new JButton("CERRAR SESIÓN");
        URL rutita4 = Practica1.class.getResource("salir.png");
        Image i4= new ImageIcon(rutita4).getImage();   
        ImageIcon icono4 = new ImageIcon(i4.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        b4.setIcon(icono4);
        b4.setBackground(colores=new Color(159,213,209));
        
        b4.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea salir?",
                    "¿Salir?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(opcion== JOptionPane.YES_OPTION)
            {
              System.exit(0);
            }
            
          }        
        });
        poeste.add(b4);
        
        
        contenedor.add(pnorte, BorderLayout.NORTH);
        contenedor.add(poeste, BorderLayout.WEST);
        contenedor.add(desktop, BorderLayout.CENTER); 
        
        this.getContentPane().setBackground(colores=new Color(245,255,255));
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }  
    
    public void registrarProducto()
    {
       Registro regis = new Registro();
       regis.setAlwaysOnTop(true);
       regis.setVisible(true);
    }
    
    public void listarProductos()
    {
        String data[][]={ {"Cuchara","$4.00","10 existencias"},    
                          {"Tenedor","$4.00","10 existencias"}, 
                          {"Olla","$35.00","8 existencias"},    
                          {"Sarten","$45.00","8 existencias"},
                          {"Vaso","$5.00","10 existencias"},    
                          {"Bote","$15.00","7 existencias"},
                          {"Plato","$10.00","5 existencias"}};    
    String column[]={"PRODUCTO","PRECIO","EXISTENCIAS"};        
    
    
    JInternalFrame jif = new JInternalFrame("Inventario de productos", false, true, false, false);
    jif.setSize(400, 300);
              Dimension ds = desktop.getSize();
              Dimension dif= jif.getSize();
              
              jif.setLocation((ds.width-dif.width)/2,(ds.height-dif.height)/2);
      
              JTable jt=new JTable(data,column);    
              jt.setBounds(30,40,200,300);          
              JScrollPane sp=new JScrollPane(jt);    
              jif.add(sp);       
            desktop.add(jif);
            jif.setVisible(true);
    }
    
    public void Ventas()
    {  Ventas venta = new Ventas();
       venta.setAlwaysOnTop(true);
       venta.setVisible(true);
    }
}
