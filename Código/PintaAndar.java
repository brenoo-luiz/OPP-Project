import java.awt.Color;
import java.awt.Graphics;

public class PintaAndar extends BotoeiraExt {
    private boolean pintado;

    public PintaAndar(int x, int y, int altura, int largura) {
        super(x, y, altura, largura);
        this.pintado = false;
    }

    public void setPintado(boolean pintado) {
        this.pintado = pintado;
    }

    @Override
    public void desenha(Graphics g) {
        if (pintado) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.white);
        }
        g.fillOval(x + 25, y + 5, largura - 50, altura - 50);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, largura, altura);
        g.drawOval(x + 25, y + 5, largura - 50, altura - 50);
    }
}