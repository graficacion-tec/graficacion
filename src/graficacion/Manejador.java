package graficacion;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Arrays;

public class Manejador {
	 WritableRaster wr;
	 BufferedImage bi;
	 int [] color={0,0,0};
	 ArrayList<Linea> lineas = new ArrayList<Linea>();
	 ArrayList<Circulo> circulos = new ArrayList<Circulo>();
	 ArrayList<PuntoPlano> PuntosRelleno = new ArrayList<PuntoPlano>();
	 
	 public void addLinea(int x1,int y1,int x2,int y2){
		 lineas.add(new Linea(new PuntoPlano(x1,y1),new PuntoPlano(x2,y2)));
		 lineas.get(lineas.size()-1).setColor(color);
	 }
	 public void addCirculo(int x1,int y1,int x2,int y2){
		 circulos.add(new Circulo(new PuntoPlano(x1,y1),new PuntoPlano(x2,y2)));
		 circulos.get(circulos.size()-1).setColor(color);
	 }
	 public void addRelleno(int x1,int y1){
		 PuntosRelleno.add(new PuntoPlano(x1,y1));
	 }
	 public Manejador(BufferedImage bi) {
		super();
		this.bi=bi;
		this.wr = bi.getRaster();
	}
	public WritableRaster getWr() {
		return wr;
	}
	public void setWr(WritableRaster wr) {
		this.wr = wr;
	}
	public int[] getColor() {
		return color;
	}
	public void setColor(int[] color) {
		this.color = color;
	}
	public void dibujar(){
		dibujarLineas();
		dibujarCirculos();
		dibujarRellenos();
	 }
	private void dibujarCirculos() {
		for (int x=0;x<circulos.size();x++){
			circulos.get(x).dibujar(wr);
		}
	}
	private void dibujarRellenos() {
		for (int x=0;x<PuntosRelleno.size();x++){
			PuntoPlano p =PuntosRelleno.get(x);
			relleno(p.getX(),p.getY(),color,color);
		}
	}
	private void dibujarLineas() {
		for (int x=0;x<lineas.size();x++){
			lineas.get(x).dibujar(wr);
		}
	}
	public void trasladar(int tx, int ty){
		trasladarLineas( tx,  ty);
		trasladarCirculos( tx,  ty);
		trasladarRellenos( tx,  ty);
		dibujar();
	}
	private void trasladarCirculos(int tx, int ty) {
		for (int x=0;x<circulos.size();x++){
			circulos.get(x).trasladar(tx, ty);
		}
	}
	private void trasladarRellenos(int tx, int ty) {
		for (int x=0;x<PuntosRelleno.size();x++){
			PuntosRelleno.get(x).trasladar(tx, ty);
		}
	}
	private void trasladarLineas(int tx, int ty) {
		for (int x=0;x<lineas.size();x++){
			lineas.get(x).trasladar(tx, ty);;
		}
	}
	public void escalar(int tx, int ty){
		escalarLineas( tx,  ty);
		escalarCirculos( tx,  ty);
		escalarRellenos( tx,  ty);
		dibujar();
	}
	private void escalarCirculos(int tx, int ty) {
		for (int x=0;x<circulos.size();x++){
			circulos.get(x).escalar(tx, ty);
		}
	}
	private void escalarRellenos(int tx, int ty) {
		for (int x=0;x<PuntosRelleno.size();x++){
			PuntosRelleno.get(x).escalar(tx, ty);
		}
	}
	private void escalarLineas(int tx, int ty) {
		for (int x=0;x<lineas.size();x++){
			lineas.get(x).escalar(tx, ty);;
		}
	}
	
	public void reflejar(int tx, int ty){
		reflejarLineas( tx,  ty);
		reflejarCirculos( tx,  ty);
		reflejarRellenos( tx,  ty);
		dibujar();
	}
	private void reflejarCirculos(int tx, int ty) {
		for (int x=0;x<circulos.size();x++){
			circulos.get(x).reflejar(tx, ty);
		}
	}
	private void reflejarRellenos(int tx, int ty) {
		for (int x=0;x<PuntosRelleno.size();x++){
			PuntosRelleno.get(x).reflejar(tx, ty);
		}
	}
	private void reflejarLineas(int tx, int ty) {
		for (int x=0;x<lineas.size();x++){
			lineas.get(x).reflejar(tx, ty);;
		}
	}
	public void rotar(int g,int tx, int ty){
		rotarLineas(g,new PuntoPlano(tx, ty));
		rotarCirculos(g,new PuntoPlano(tx, ty));
		rotarRellenos(g,new PuntoPlano(tx, ty));
		dibujar();
	}
	private void rotarCirculos(int g,PuntoPlano p) {
		for (int x=0;x<circulos.size();x++){
			circulos.get(x).rotar(g,p);
		}
	}
	private void rotarRellenos(int g,PuntoPlano p) {
		for (int x=0;x<PuntosRelleno.size();x++){
			PuntosRelleno.get(x).rotar(g,p);
		}
	}
	private void rotarLineas(int g, PuntoPlano p) {
		for (int x=0;x<lineas.size();x++){
			lineas.get(x).rotar(g,p);
		}
	}
	public void relleno(int x1, int y1, int fcol1[], int bcol2[]){
		int color = bi.getRGB(x1, y1);
		int blue = color & 0xff;
		int green = (color & 0xff00) >> 8;
		int red = (color & 0xff0000) >> 16;
		
		int cur[]={red,green,blue};
						
		if((!Arrays.equals(fcol1, cur)) && (!Arrays.equals(bcol2, cur))){
			
			wr.setPixel(x1, y1, fcol1);
			
			relleno(x1+1, y1, fcol1, bcol2);
			relleno(x1-1, y1, fcol1, bcol2);			
			relleno(x1, y1+1, fcol1, bcol2);
			relleno(x1, y1-1, fcol1, bcol2);
			
			/*relleno(x1+1, y1-1, fcol1, bcol2);			
			relleno(x1+1, y1+1, fcol1, bcol2);			
			relleno(x1-1, y1-1, fcol1, bcol2);			
			relleno(x1-1, y1+1, fcol1, bcol2);
			*/
		}
	}
}
