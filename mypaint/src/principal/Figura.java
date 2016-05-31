package principal;


public class Figura {

	public static final int RETANGULO = 1;
	public static final int ELIPSE = 2;
	public static final int LINHA = 3;
	
	public int tipo;
	public int x;
	public int y;
	public int x2;
	public int y2;
	
	public Figura(int tipo, int x, int y, int x2, int y2) {
		this.tipo = tipo;
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}
	
}
