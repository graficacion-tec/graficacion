package graficacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelIzq extends JPanel implements MouseListener
{	
	PanelDer copia;
	private BufferedImage bi = null;
	private int [] color = {0,0,0};
	private int figura; //Si Figura = 1 -> Graficar línea. Si figura = 2 -> Graficar círculo.
	private int x1, y1, x2, y2;
	
	// width and height of drawpanel
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    
    // the bounds of the clipping rectangle
    public static final int LEFTEDGE = WIDTH / 4;
    public static final int RIGHTEDGE = WIDTH / 4 * 3;
    public static final int TOPEDGE = HEIGHT / 4;
    public static final int BOTTOMEDGE = HEIGHT / 4 * 3;
    
    // give each edge of clipping rectangle an ID
    public static final byte ID_TOPEDGE = 0;
    public static final byte ID_RIGHTEDGE = 1;
    public static final byte ID_BOTTOMEDGE = 2;
    public static final byte ID_LEFTEDGE = 3;
    
    Vector<Point> polygon;                  // polygon painted by user
    Vector<Point> clippedPoly;              // polygon clipped
    private boolean isPolyClosed = false; 
    Manejador m;
	
	public PanelIzq()
	{
		this.addMouseListener(this);
		bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB); //Inicializa el área de trabajo.
		bi.getGraphics().fillRect(0, 0, bi.getWidth(), bi.getHeight()); //Rellena la pantalla de color blanco.
		polygon = new Vector<Point>();
	    clippedPoly = new Vector<Point>();
		figura = 0;
		x1 = 0;
		x2 = 0;
		y1 = 0;
		y2 = 0;
		m=new Manejador(bi);
	}
	
	public void trazarLinea(int[] color, int x1, int y1, int x2, int y2) 
	{
		WritableRaster wr = bi.getRaster();
		double dx = x2-x1;
		double dy = y2-y1;

		if (Math.abs(y2 - y1) <= Math.abs(x2 - x1)) {

		    if ((x1 == x2) && (y1 == y2)) 
		    {
		       wr.setPixel(x1, y1, color);

		    } else {
		        if (x2 < x1) {
		            int tmp = x2;
		            x2 = x1;
		            x1 = tmp;

		            tmp = y2;
		            y2 = y1;
		            y1 = tmp;
		        }

		        double k = (double)dy/dx; 
		        int cele_y;           
		        double y = (double)y1;

		        for (int x = x1 ; x <= x2 ; x++) {
		            cele_y = (int)Math.round(y);
		            wr.setPixel(x, cele_y, color);
		            y += k;
		        }
		    }
		} else {

		        if (y2 < y1) {
		            int tmp = x2;
		            x2 = x1;
		            x1 = tmp;

		            tmp = y2;
		            y2 = y1;
		            y1 = tmp;
		        }

		        double k = (double)dx/dy;
		        int cele_x;
		        double x = (double)x1;
		        for (int y = y1; y <= y2; y++) {
		            cele_x = (int)Math.round(x);
		            wr.setPixel(cele_x, y, color);
		            x += k;
		        }
		}
		
		repaint();
	}
	
	//Algoritmo DDA.
	/*public void trazarLinea(int[] color, int x1, int y1, int x2, int y2)
	{
		//Pintar de izquierda a derecha. 
		try 
		{
			int dx, dy, steps;
			float x, y, x_increment, y_increment;
			 
			dx = x2 - x1;
			dy = y2 - y1;
			
			if(Math.abs(dx) > Math.abs(dy))
				steps = dx;
			else
				steps = dy;
			
			x_increment = dx/steps;
			y_increment = dy/steps;
			
			x = x1;
			y = y1;
						
			WritableRaster wr = bi.getRaster();
			
			wr.setPixel(Math.round(x), Math.round(y), color); //Pinta el pixel en el punto (x1, y1).
				
			for(int k=1; k<=steps; k++)
			{
				x = x + x_increment;
				y = y + y_increment;
				wr.setPixel(Math.round(x), Math.round(y), color);
			}
				
			repaint();
		
		} catch (Exception e) {}
	}*/
	
	 //Algoritmo Bresenham.
	 public void trazarCircle(int[] color, int x_center, int y_center, int radio)
	 {
		 try 
		 {
			 int P, X, Y;
			 X = 0;
			 Y = radio;
			 P= 3 - 2 * radio;
			 
			 while(X < Y)
			 {
				 Plot_Circle_Point(x_center, y_center, radio, X, Y, color);
				 
				 if(P < 0)
					 P = P + (4 * X) + 6;
				 else
				 {
					 P = P + 4 * (X - Y) + 10;
					 Y = Y - 1;
				 }
				 
				 X = X + 1;
			 }
			 
			 if( X == Y)
				 Plot_Circle_Point(x_center, y_center, radio, X, Y, color);
			 
			 repaint();
		 }
		 catch (Exception e) {}
	 }
	 
	 public void Plot_Circle_Point (int x_center, int y_center, int radio, int X, int Y, int[] color)
	 {
		 WritableRaster wr = bi.getRaster(); 
	     wr.setPixel(x_center + X, y_center+ Y, color);
	     wr.setPixel(x_center - X, y_center+ Y, color);
	     wr.setPixel(x_center + X, y_center- Y, color);
	     wr.setPixel(x_center - X, y_center- Y, color);
	     wr.setPixel(x_center + Y, y_center+ X, color);
	     wr.setPixel(x_center - Y, y_center+ X, color);
	     wr.setPixel(x_center + Y, y_center- X, color);
	     wr.setPixel(x_center - Y, y_center- X, color);
	 }
	 
	 public void reset()
	 {
	    this.polygon.clear();
	    this.clippedPoly.clear();
	    isPolyClosed = false;
	 }
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(bi, 0, 0, null);
		 // draw the clipping rectangle
        g.drawLine(LEFTEDGE, TOPEDGE, RIGHTEDGE, TOPEDGE);           // top edge
        g.drawLine(LEFTEDGE, TOPEDGE, LEFTEDGE, BOTTOMEDGE);        // left edge
        g.drawLine(LEFTEDGE, BOTTOMEDGE, RIGHTEDGE, BOTTOMEDGE);    // bottom edge
        g.drawLine(RIGHTEDGE, TOPEDGE, RIGHTEDGE, BOTTOMEDGE);  
        
        // draw clipped polygon if available, otherwise the original one
        if (clippedPoly != null && clippedPoly.size() > 0)
        {
            g.setColor(Color.red);
        	drawPolygon(g, clippedPoly);
        }
        else
            drawPolygon(g, polygon);
        
        // if polygon is completed or no point is set, work is done
        if (this.isPolyClosed || this.polygon.size() < 1) return;
	}
	 
    /*
     * Draw given polygon
     */
    public void drawPolygon(Graphics g, Vector<Point> v)
    {
        // draw the point rectangles
        Point p;
        for (int i = 0; i < v.size(); i++)
        {
            p = v.get(i);
            g.fillRect(p.x - 2, p.y - 2, 5, 5);
        }
        
        // draw the connecting lines
        if (v.size() < 1) return;
        Point p2;
        for (int i = 0; i < v.size() - 1; i++)
        {
            p = v.get(i);
            p2 = v.get(i+1);
            trazarLinea(color, p.x, p.y, p2.x, p2.y);
        }
    }
    
    /*
     * Calculate the intersection with the given edge of the window
     * edge : edge of the clipping window to calc intersection with
     * p1 : current point
     * p2 : next point
     */
    private Point intersectionPoint(byte EdgeID, Point p1, Point p2)
    {
        //Point resultPoint = new Point();
        if (EdgeID == ID_TOPEDGE)
            return new Point(p1.x + (TOPEDGE - p1.y) * (p2.x - p1.x) / (p2.y - p1.y), TOPEDGE);
        else if (EdgeID == ID_BOTTOMEDGE)
            return new Point(p1.x + (BOTTOMEDGE - p1.y) * (p2.x - p1.x) / (p2.y - p1.y), BOTTOMEDGE);
        else if (EdgeID == ID_RIGHTEDGE)
            return new Point(RIGHTEDGE, p1.y + (RIGHTEDGE - p1.x) * (p2.y - p1.y) / (p2.x - p1.x));
        else if (EdgeID == ID_LEFTEDGE)
            return new Point(LEFTEDGE, p1.y + (LEFTEDGE - p1.x) * (p2.y - p1.y) / (p2.x - p1.x));
        return null;
    }
    
    /*
     * Returns true if given Point p is inside the clipping window considering the given edge of the window
     */
    private boolean isInsideClipWindow(Point p, byte EdgeID, int edgeValue)
    {
        if (EdgeID == ID_TOPEDGE)
            return (p.y >= TOPEDGE);
        else if (EdgeID == ID_BOTTOMEDGE)
            return (p.y <= BOTTOMEDGE);
        else if (EdgeID == ID_LEFTEDGE)
            return (p.x >= LEFTEDGE);
        else if (EdgeID == ID_RIGHTEDGE)
            return (p.x <= RIGHTEDGE);
        else
            return false;           // should never happen!
    }
    
    /*
     * Clip the polygon against the rectangle
     */
    public void clip()
    {
        if (!this.isPolyClosed || polygon.size() < 3) return;
        clippedPoly.clear();
        
        Point p;        // current point
        Point p2;       // next point
        
        // make copy of original polygon to work with
        Vector<Point> workPoly = (Vector<Point>)polygon.clone();
        int[] edgevalues = {TOPEDGE, RIGHTEDGE, BOTTOMEDGE, LEFTEDGE };
        int edge;
        
        // loop through all for clipping edges
        for (byte edgeID = 0; edgeID < 4; edgeID++)
        {
            edge = edgevalues[edgeID];  // get edge value
            clippedPoly.clear();
            for (int i = 0; i < workPoly.size() - 1; i++)
            {
                p = workPoly.get(i);
                p2 = workPoly.get(i+1);
                if (isInsideClipWindow(p, edgeID, edge))
                {
                    if (isInsideClipWindow(p2, edgeID, edge))
                        // here both points are inside the clipping window so add the second one
                        clippedPoly.add(new Point(p2.x, p2.y));
                    else
                    {
                        // the following point is outside so add the intersection point
                        clippedPoly.add(intersectionPoint(edgeID, p, p2));  
                    }
                }
                else
                {
                    // so first point is outside the window here
                    if (isInsideClipWindow(p2, edgeID, edge))
                    {
                        // the following point is inside so add the insection point and also p2
                        clippedPoly.add(intersectionPoint(edgeID, p, p2));  
                        clippedPoly.add(new Point(p2.x, p2.y));
                    }
                }
            }
            // make sure that first and last element are the same, we want a closed polygon
            if (clippedPoly.firstElement() != clippedPoly.lastElement())
                clippedPoly.add(clippedPoly.firstElement());
            // we have to keep on working with our new clipped polygon
            workPoly = (Vector<Point>) clippedPoly.clone();  
        }
        
        repaint();
    }
    
    
    /*
     * Draw the given edge of the clipping window
     */
    public void drawClipLine(Graphics g, int EdgeID)
    {
        switch (EdgeID)
        {
            case ID_TOPEDGE:  g.drawLine(LEFTEDGE, TOPEDGE, RIGHTEDGE, TOPEDGE);  break;
            case ID_LEFTEDGE: g.drawLine(LEFTEDGE, TOPEDGE, LEFTEDGE, BOTTOMEDGE); break;
            case ID_BOTTOMEDGE: g.drawLine(LEFTEDGE, BOTTOMEDGE, RIGHTEDGE, BOTTOMEDGE); break;
            case ID_RIGHTEDGE : g.drawLine(RIGHTEDGE, TOPEDGE, RIGHTEDGE, BOTTOMEDGE); break;
            default: break;
        }
    }

    public void borrar()
	{
		bi.getGraphics().fillRect(0, 0, bi.getWidth(), bi.getHeight());
		m=new Manejador(bi);
		reset();
		repaint();
	}

	public void mousePressed(MouseEvent e) 
	{
		x1 = e.getX();
		y1 = e.getY();
	}

	public void mouseReleased(MouseEvent e) 
	{
		x2 = e.getX();
		y2 = e.getY();
	
		if(figura == 1)
		{
			// if we cannot add more points, bye
            if (isPolyClosed) 
            	return;
            
			trazarLinea(color, x1, y1, x2, y2);
            
            // check if polygon should be closed
            if ((polygon.size() > 2) && (Math.abs(e.getX() - polygon.firstElement().x) < 5) && (Math.abs(e.getY() - polygon.firstElement().y) < 5) )
            {
                isPolyClosed = true;
                polygon.add(polygon.firstElement());
            }
            else
            {
                polygon.add(new Point(e.getX(), e.getY()));
            }
            repaint();
		}
		else if(figura == 2)
		{
			m.addCirculo(x1, y1, x2, y2);
			
		}else if (figura==3){
			m.addLinea(x1, y1, x2, y2);
		}
		else if (figura==4){
			m.addRelleno(x2, y2);
		}
		else if (figura==5){
			copia.setPx(x2+"");
			copia.setPY(y2+"");
		}
		m.dibujar();
		repaint();
	}
	public void setFigura(int fig)
	{
		figura = fig;
	}
	
	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
}
