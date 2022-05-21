/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personalizaciones;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;

/**
 *
 * @author marti
 */
public class JTablaEditada extends JTable{
    public JTablaEditada()
    {
      this.setFocusable(false);
      this.setIntercellSpacing(new Dimension(0,0));
      this.setRowHeight(25);
      this.setSelectionBackground( Color.LIGHT_GRAY);
      this.setShowHorizontalLines(true);
      this.setShowVerticalLines(false);
      this.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
      this.getTableHeader().setForeground(Color.black);
      this.getTableHeader().setBackground(new Color(173,207,230));
      this.setBackground(Color.WHITE);
      this.setFont(new Font("Arial", Font.BOLD, 11));
    }
}
