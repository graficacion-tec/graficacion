package graficacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelDer extends JPanel implements ActionListener
{
	private PanelIzq copia;
	private JLabel etiq, etiq1, etiq2, etiq3, etiq4, etiq5, etiq6, etiq7, etiq8,etiq9;
	private JTextField sx, sy, tx, ty, px, py, g;
	private JButton trasladar, borrar, escalar,rotar;
	int [] color = {0,0,0};
	
	public PanelDer(PanelIzq panel)
	{
		copia     = panel;
		etiq      = new JLabel("traslacion:");
		etiq1     = new JLabel("TX:");
		etiq2     = new JLabel("TY:");
		etiq3     = new JLabel("Escalacion:");
		etiq4     = new JLabel("SX:");
		etiq5     = new JLabel("SY:");
		sx        = new JTextField();
		sy        = new JTextField();
		tx        = new JTextField();
		ty        = new JTextField();
		etiq6	  = new JLabel("Punto fijo:");
		etiq7	  = new JLabel("PX:");
		etiq8	  = new JLabel("PY:");
		etiq9     = new JLabel("Grados:");
		px        = new JTextField();
		py        = new JTextField();
		g     = new JTextField();
		trasladar = new JButton ("Trasladar");
		escalar = new JButton ("Escalar");
		rotar = new JButton ("Rotar");
		borrar    = new JButton ("Borrar");
		
		setLayout(null);
		agregarComponentes();
		asignarOyentes();
	}

	private void asignarOyentes() 
	{
		rotar.addActionListener(this);
		trasladar.addActionListener(this);
		escalar.addActionListener(this);
		   borrar.addActionListener(this);
	}

	private void agregarComponentes() 
	{
		add(etiq);
		add(etiq1);
		add(tx);
		add(etiq2);
		add(ty);
		add(etiq3);
		add(etiq4);
		add(sx);
		add(sy);
		add(etiq5);
		add(etiq6);
		add(etiq7);
		add(etiq8);
		add(px);
		add(py);
		add(etiq9);
		add(g);
		add(trasladar);
		add(escalar);
		add(rotar);
		add(borrar);
		
		etiq.setBounds(85, 0, 80, 30);
		
		etiq1.setBounds(25, 20, 20, 20);
		tx.setBounds(50, 20, 30, 20);
		
		etiq2.setBounds(125, 20, 20, 20);
		ty.setBounds(150, 20, 30, 20);
		
		etiq3.setBounds(85, 30, 80, 30);
		etiq4.setBounds(25, 50, 20, 20);
		sx.setBounds(50, 50, 30, 20);
		
		etiq5.setBounds(125, 50, 20, 20);
		sy.setBounds(150, 50, 30, 20);
		
		etiq6.setBounds(85, 60, 80, 30);
		etiq7.setBounds(25, 80, 20, 20);
		px.setBounds(50, 80, 30, 20);
		
		etiq8.setBounds(125, 80, 20, 20);
		py.setBounds(150, 80, 30, 20);
		
		etiq9.setBounds(125, 110, 20, 20);
		g.setBounds(150, 110, 30, 20);
		trasladar.setBounds(10, 180, 80, 30);
		escalar.setBounds(10, 220, 80, 30);
		rotar.setBounds(10, 250, 80, 30);
		borrar.setBounds(110, 180, 80, 30);
	}
	public void setPx(String v){
		px.setText(v);
	}
	public void setPY(String v){
		py.setText(v);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource()==borrar){
			copia.borrar();
			return;
		}
		if (e.getSource()==trasladar){
			try 
			{
				copia.m.trasladar(Integer.parseInt(tx.getText()),Integer.parseInt(ty.getText()));
			} 
			catch (Exception ex) 
			{
				JOptionPane.showMessageDialog(null, "Campo vacío.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (e.getSource()==escalar){
			try 
			{
				copia.m.escalar(Integer.parseInt(sx.getText()),Integer.parseInt(sy.getText()));
			} 
			catch (Exception ex) 
			{
				JOptionPane.showMessageDialog(null, "Campo vacío.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (e.getSource()==rotar){
			try 
			{
				copia.m.rotar(Integer.parseInt(g.getText()),Integer.parseInt(px.getText()),Integer.parseInt(py.getText()));
			} 
			catch (Exception ex) 
			{
				JOptionPane.showMessageDialog(null, "Campo vacío.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		copia.repaint();
	}
}
