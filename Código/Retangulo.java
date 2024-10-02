import java.awt.Color;
import java.awt.Graphics;

public class Retangulo extends MeuObjeto{

	
	public Retangulo(int x, int y, int altura, int largura) {
		super(x, y, altura, largura);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desenha(Graphics g) {
		g.setColor(Color.white);  
		g.fillRect(x, y, largura, altura);
	}
}