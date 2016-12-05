package graficacion;

import java.awt.image.WritableRaster;

public class Circulo {
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
		this.trazarCircle(color, p1.getX(), p1.getY(), p1.getDistance(p2), wr);
	}
	public Circulo(PuntoPlano p1, PuntoPlano p2) {
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
	public void trazarCircle(int[] color, int x_center, int y_center, int radio,WritableRaster wr)
	 {
		 try 
		 {
			 int P, X, Y;
			 X = 0;
			 Y = radio;
			 P= 3 - 2 * radio;
			 
			 while(X < Y)
			 {
				 Plot_Circle_Point(x_center, y_center, radio, X, Y, color,wr);
				 
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
				 Plot_Circle_Point(x_center, y_center, radio, X, Y, color,wr);
			 
		 }
		 catch (Exception e) {}
	 }
	 
	 public void Plot_Circle_Point (int x_center, int y_center, int radio, int X, int Y, int[] color,WritableRaster wr)
	 { 
	     wr.setPixel(x_center + X, y_center+ Y, color);
	     wr.setPixel(x_center - X, y_center+ Y, color);
	     wr.setPixel(x_center + X, y_center- Y, color);
	     wr.setPixel(x_center - X, y_center- Y, color);
	     wr.setPixel(x_center + Y, y_center+ X, color);
	     wr.setPixel(x_center - Y, y_center+ X, color);
	     wr.setPixel(x_center + Y, y_center- X, color);
	     wr.setPixel(x_center - Y, y_center- X, color);
	 }

}
