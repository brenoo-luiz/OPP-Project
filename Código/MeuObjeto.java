import java.awt.Graphics;

public abstract class MeuObjeto {
	protected int x;
	protected int y;
	protected int altura;
	protected int largura;
	
	public MeuObjeto(int x, int y, int altura, int largura) {	
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.largura = largura;
	}
	
	public abstract void desenha(Graphics g);	
}