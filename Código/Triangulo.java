import java.awt.Color;
import java.awt.Graphics;

public class Triangulo extends MeuObjeto {
    public Triangulo(int x, int y, int altura, int largura) {
        super(x, y, altura, largura);
    }

    @Override
    public void desenha(Graphics g) {
        g.setColor(Color.BLACK);

        int[] xPoints = {150, 140, 130};
        int[] yPoints = {60, 47, 60};
        
        int[] xPoints1 = {150, 140, 130};
        int[] yPoints1 = {63, 76, 63};
        for (int i = 0; i < 10; i++) {
            g.drawPolygon(xPoints, yPoints, 3);
            yPoints[0] += 90;
            yPoints[1] += 90;
            yPoints[2] += 90;
        }
        
        for (int i = 0; i < 10; i++) {
            g.drawPolygon(xPoints1, yPoints1, 3);
            yPoints1[0] += 90;
            yPoints1[1] += 90;
            yPoints1[2] += 90;
        }
    }
}