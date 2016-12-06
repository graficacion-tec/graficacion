package graficacion;

public class PuntoPlano {
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void trasladar(int tx,int ty){
		x=x+tx;
		y=y+ty;
	}
	public void rotar(int g, PuntoPlano p){
		x=(int) (p.getX()+(x-p.getX())*Math.cos(Math.toRadians(g))-(y-p.getY())*Math.sin(Math.toRadians(g)));
		y=(int) (p.getY()+(y-p.getY())*Math.cos(Math.toRadians(g))+(x-p.getX())*Math.sin(Math.toRadians(g)));
	}
	public void reflejar(int x, int y){
		int difx = x-this.x;
		int dify = y-this.y;
		this.x=x+difx;
		this.y=y+dify;
	}
	public void escalar(int sx, int sy){
		x=x*sx;
		y=y*sy;
	}
	
	public PuntoPlano(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		PuntoPlano other = (PuntoPlano) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PuntoPlano [x=" + x + ", y=" + y + "]";
	}
	public int getDistance(PuntoPlano p){
		int dx = x-p.getX();
		int dy = y-p.getY();
		return (int) Math.abs(Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2)));
	}
}
