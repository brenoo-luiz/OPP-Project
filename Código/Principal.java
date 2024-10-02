import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.*;

public class Principal {
    private static Elevador elevador;
    public static LinkedBlockingQueue<Integer> filaDeAndares;
    private static DirecaoTriangulo[] triCima, triBaixo;
    private static PintaAndar[] botoesAndares;

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            JFrame janela = new JFrame();
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setSize(750, 968);
            MeuPanel painel = new MeuPanel();
            janela.add(painel);

            inicializarComponentes(painel);

            janela.setVisible(true);
        });
    }

    private static void inicializarComponentes(MeuPanel painel) {
        // Adiciona retangulos dos andares
        painel.getLista().add(new Retangulo(90, 0, 910, 100));
        painel.getLista().add(new Retangulo(350, 0, 910, 1));
        for (int i = 0; i < 10; i++) {
            painel.getLista().add(new Retangulo(500, 820 - (i * 90), 80, 80));
        }

        // Adiciona botoes dos andares
        botoesAndares = new PintaAndar[10];
        for (int i = 0; i < 10; i++) {
            botoesAndares[i] = new PintaAndar(100, 820 - (i * 90), 80, 80);
            painel.getLista().add(botoesAndares[i]);
        }

        // Adiciona numeracao dos andares
        painel.getLista().add(new NumAndares(0, 0, 0, 0));

        // Adiciona elevador
        elevador = new Elevador(500, 0, 80, 80);
        painel.getLista().add(elevador);

        // Adiciona triangulos de direcao
        triCima = new DirecaoTriangulo[10];
        triBaixo = new DirecaoTriangulo[10];
        for (int i = 0; i < 10; i++) {
            int y = 880 - (i * 90);
            triCima[i] = new DirecaoTriangulo(132, y - 20, 15, 15, true);
            triBaixo[i] = new DirecaoTriangulo(132, y, 15, 15, false);
            painel.getLista().add(triCima[i]);
            painel.getLista().add(triBaixo[i]);
        }

        // Inicializa fila de andares
        filaDeAndares = new LinkedBlockingQueue<>();

        // Evento para clique nos botoes do GUI
        painel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                atualizaFila(e.getX(), e.getY());
            }
        });

        // Evento para entrada do teclado
        painel.setFocusable(true); 
        painel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (Character.isDigit(keyChar)) {
                    int andar = Character.getNumericValue(keyChar);
                    if (andar >= 1 && andar <= 10) {
                        filaDeAndares.offer(andar);
                        atualizaBotaoAndar(andar - 1, true);
                        painel.repaint();
                    }
                }
            }
        });

        // Cria a instância de MovimentaElevador
        MovimentaElevador movimentaElevador = new MovimentaElevador(elevador, filaDeAndares, triCima, triBaixo, botoesAndares);

        // Inicia a movimentação do elevador
        movimentaElevador.movimentaElevador(painel);
    }

    private static void atualizaFila(int x, int y) {
        for (PintaAndar botao : botoesAndares) {
            if (x >= botao.x && x <= botao.x + botao.largura &&
                y >= botao.y && y <= botao.y + botao.altura) {
                int andar = (820 - botao.y) / 90 + 1;
                filaDeAndares.offer(andar);
                atualizaBotaoAndar(andar - 1, true); // Pinta o botao selecionado
                break;
            }
        }
    }

    private static void atualizaBotaoAndar(int indice, boolean pintado) {
        if (indice >= 0 && indice < botoesAndares.length) {
            botoesAndares[indice].setPintado(pintado);
        }
    }
}
