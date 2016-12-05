package graficacion;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class Ventana extends JFrame implements ActionListener
{
	private PanelIzq   panelizq;
	private PanelDer   panelder;
	private JSplitPane divisor;
	private JMenuBar   barrademenu;
	private JMenu      archivo, inicio, graficar;
	private JMenuItem  nuevo, abrir, guardar, guardarcomo, salir;
	private JMenuItem  trasladar, escalar, rotar, recortar, rellenar,punto;
	private JMenuItem  linea, circulo,poligono;
	
	public Ventana()
	{
		super("Graficación");
		
		panelizq    = new PanelIzq();
		panelder	= new PanelDer(panelizq);
		panelizq.copia=panelder;
		divisor		= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(panelizq), new JScrollPane(panelder));
		barrademenu = new JMenuBar();
		
		archivo     = new JMenu("Archivo");
		nuevo       = new JMenuItem("Nuevo");
		abrir       = new JMenuItem("Abrir");
		guardar     = new JMenuItem("Guardar");
		guardarcomo = new JMenuItem("Guardar como...");
		salir       = new JMenuItem("Salir");
		
		inicio      = new JMenu("Inicio");		
		trasladar   = new JMenuItem("Mover");
		escalar     = new JMenuItem("Cambiar tamaño");
		rotar       = new JMenuItem("Rotar");
		recortar    = new JMenuItem("Recortar");
		rellenar    = new JMenuItem("Rellenar");
		punto       = new JMenuItem("Punto de referencia");
		
		graficar	= new JMenu("Graficar");
		linea 		= new JMenuItem("Línea");
		circulo 	= new JMenuItem("Círculo");
		poligono 		= new JMenuItem("Poligono");
		
		agregarComponentes();
		asignarOyentes();
	}

	private void asignarOyentes() 
	{
			  nuevo.addActionListener(this);
		      abrir.addActionListener(this);
		    guardar.addActionListener(this);
		guardarcomo.addActionListener(this);
		      salir.addActionListener(this);
		           
		  trasladar.addActionListener(this);
	        escalar.addActionListener(this);
	          rotar.addActionListener(this);
		   recortar.addActionListener(this);
		   rellenar.addActionListener(this);
		   punto.addActionListener(this);
		      linea.addActionListener(this);
		    circulo.addActionListener(this);
	}

	private void agregarComponentes() 
	{
		barrademenu.add(archivo);
		    archivo.add(nuevo);
			archivo.add(abrir);
			archivo.add(guardar);
			archivo.add(guardarcomo);
			archivo.add(salir);
			
		    archivo.setMnemonic('A');
	          nuevo.setMnemonic('N');
	          abrir.setMnemonic('A');
	        guardar.setMnemonic('G');
	    guardarcomo.setMnemonic('O');
	          salir.setMnemonic('S');
	          
	    barrademenu.add(inicio);
	         inicio.add(trasladar);
	         inicio.add(escalar);
	         inicio.add(rotar);
	         inicio.add(recortar);
	     
	         
	         inicio.setMnemonic('I');
	      trasladar.setMnemonic('M');
	        escalar.setMnemonic('C');
	          rotar.setMnemonic('R');
	       recortar.setMnemonic('E');
	       rellenar.setMnemonic('L');
	       punto.setMnemonic('Z');
	    barrademenu.add(graficar);
	       graficar.add(linea);
	       graficar.add(circulo);
	       graficar.add(poligono);
	       graficar.add(rellenar);
	       graficar.add(punto);
	         linea.setMnemonic('L');
	       circulo.setMnemonic('C');
	      
	   divisor.setOneTouchExpandable(true);
	   divisor.setDividerLocation(580);
	         
	   add(barrademenu, BorderLayout.NORTH);
	   add(divisor);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == nuevo)
		{
			
		}
		else if (e.getSource() == abrir)
		{
		
		}
		else if(e.getSource() == guardar)
		{
			
		}
		else if(e.getSource() == guardarcomo)
		{
			
		}
		else if(e.getSource() == trasladar)
		{
			
		}
		else if(e.getSource() == escalar)
		{
		}
		else if(e.getSource() == rotar)
		{
			
		}
		else if(e.getSource() == recortar)
		{
			panelizq.clip();
		}
		else if(e.getSource() == punto)
		{
			panelizq.setFigura(5);
		}
		else if(e.getSource() == rellenar)
		{
			panelizq.setFigura(4);
		}
		else if(e.getSource() == linea)
		{
			panelizq.setFigura(3);
		}
		else if(e.getSource() == circulo)
		{
			panelizq.setFigura(2);
		}
		else if(e.getSource() == poligono)
		{
			panelizq.setFigura(1);
		}
		else //Salir.
		{
			System.exit(0);
		}
	}
}
