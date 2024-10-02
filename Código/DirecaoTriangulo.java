import java.awt.Color;
import java.awt.Graphics;

public class DirecaoTriangulo extends Triangulo {
    private boolean apontandoParaCima;
    private boolean preenchido;

    public DirecaoTriangulo(int x, int y, int altura, int largura, boolean apontandoParaCima) {
        super(x, y, altura, largura);
        this.apontandoParaCima = apontandoParaCima;
        this.preenchido = false;
    }

    public void setPreenchido(boolean preenchido) {
        this.preenchido = preenchido;
    }

    @Override
    public void desenha(Graphics g) {
        int[] xPoints;
        int[] yPoints;

        if (apontandoParaCima) {
            xPoints = new int[]{x + largura / 2, x, x + largura};
            yPoints = new int[]{y, y + altura, y + altura};
        } else {
            xPoints = new int[]{x + largura / 2, x, x + largura};
            yPoints = new int[]{y + altura, y, y};
        }

        if (preenchido) {
            g.setColor(Color.black);
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            g.setColor(Color.black);
            g.drawPolygon(xPoints, yPoints, 3);
        }
    }
}