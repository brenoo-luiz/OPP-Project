import java.awt.Graphics;

public class RectanguloPreenchido extends MeuObjeto{

	
	public RectanguloPreenchido(int x, int y, int altura, int largura) {
		super(x, y, altura, largura);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desenha(Graphics g) {
		g.fillRect(x, y, largura, altura);
		g.drawRect(x, y, largura, altura);
	}
}