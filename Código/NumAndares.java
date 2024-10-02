import java.awt.*;

public class NumAndares extends MeuObjeto {

    private String[] andares = {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1"};
    private int posicaoInicialX = 105; // Posição inicial X para desenho dos números
    private int posicaoInicialY = 30; // Posição inicial Y para desenho do primeiro andar
    private int espacoEntreAndares = 90; // Espaço vertical entre cada andar

    public NumAndares(int x, int y, int altura, int largura) {
        super(x, y, altura, largura);
    }
    
    public void desenha(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 15));
        g.setColor(Color.BLACK);

        int y = posicaoInicialY;
        for (String andar : andares) {
            g.drawString(andar, posicaoInicialX, y);
            y += espacoEntreAndares;
        }
    }
    
}
