import java.awt.Color;
import java.awt.Graphics;

public class Elevador extends MeuObjeto {
    private int andarAtual;
    private int sentido; // 1 para cima, 0 para baixo
    private Integer andarDestino; // Andar de destino

    public Elevador(int x, int y, int altura, int largura) {
        super(x, y, altura, largura);
        this.andarAtual = 1;
        this.sentido = 1; // Inicialmente subindo
    }

    public void moverParaAndar(int andar) {
        this.andarAtual = andar;
    }

    public void setAndarDestino(Integer andarDestino) {
        this.andarDestino = andarDestino;
    }

    public Integer getAndarDestino() {
        return andarDestino;
    }

    @Override
    public void desenha(Graphics g) {
        g.setColor(Color.blue.darker().darker());
        int y = 820 - (90 * (andarAtual - 1));
        g.fillRect(x, y, largura, altura);
        g.drawRect(x, y, largura, altura);
    }

    public int getAndarAtual() {
        return andarAtual;
    }

    public int getSentido() {
        return sentido;
    }

    public void setSentido(int sentido) {
        this.sentido = sentido;
    }

    @Override
    public String toString() {
        StringBuilder fila = new StringBuilder();
        fila.append("[");
        for (Integer andar : Principal.filaDeAndares) {
            fila.append(andar).append(", ");
        }
        if (fila.length() > 1) {
            fila.setLength(fila.length() - 2); // Remove a última vírgula e espaço
        }
        fila.append("]");
        
        String status = (andarDestino == null) ? "Parado" : (sentido == 1 ? "Subindo" : "Descendo");
        
        return "Andar Atual: " + andarAtual +
               ", Sentido: " + status +
               ", Ciclos: " + MovimentaElevador.ciclos +
               ", Fila: " + fila.toString() +
               ", Andar de Destino: " + (andarDestino != null ? andarDestino : "N/A");
    }
}
