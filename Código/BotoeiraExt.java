import java.awt.Graphics;
import java.awt.Color;

public class BotoeiraExt extends MeuObjeto{

	
	public BotoeiraExt(int x, int y, int altura, int largura) {
		super(x, y, altura, largura);		
	}

	@Override
	public void desenha(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, largura, altura);		
		g.fillOval(x + 25, y + 5, largura - 50, altura - 50);
	}
	

}