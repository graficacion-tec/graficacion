package graficacion;

import java.awt.image.WritableRaster;

public class Linea {
	int[] color={0,0,0};
	public int[] getColor() {
		return color;
	}
	public void setColor(int[] color) {
		this.color = color;
	}
	PuntoPlano p1,p2;
	public void trasladar(int tx,int ty){
		p1.trasladar(tx, ty);
		p2.trasladar(tx, ty);
	}
	public void rotar(int g, PuntoPlano p){
		p1.rotar(g,p);
		p2.rotar(g,p);
	}
	public void reflajar(int x, int y){
		 
	}
	public void escalar(int sx, int sy){
		p1.escalar(sx, sy);
		p2.escalar(sx, sy);
	}
	@Override
	public String toString() {
		return "Linea [p1=" + p1 + ", p2=" + p2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linea other = (Linea) obj;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		return true;
	}
	public void dibujar(WritableRaster wr){
		this.trazarLinea(color, p1.getX(), p1.getY(), p2.getX(), p2.getY(), wr);
	}
	public Linea(PuntoPlano p1, PuntoPlano p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}

	public PuntoPlano getP1() {
		return p1;
	}

	public void setP1(PuntoPlano p1) {
		this.p1 = p1;
	}

	public PuntoPlano getP2() {
		return p2;
	}

	public void setP2(PuntoPlano p2) {
		this.p2 = p2;
	}
	public void trazarLinea(int[] color, int x1, int y1, int x2, int y2,WritableRaster wr) 
	{
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
	}
}
