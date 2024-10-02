import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MovimentaElevador {
    private Elevador elevador;
    private LinkedBlockingQueue<Integer> filaDeAndares;
    private DirecaoTriangulo[] triCima, triBaixo;
    private PintaAndar[] botoesAndares;
    public static int ciclos = 0;

    public MovimentaElevador(Elevador elevador, LinkedBlockingQueue<Integer> filaDeAndares, 
                             DirecaoTriangulo[] triCima, DirecaoTriangulo[] triBaixo, 
                             PintaAndar[] botoesAndares) {
        this.elevador = elevador;
        this.filaDeAndares = filaDeAndares;
        this.triCima = triCima;
        this.triBaixo = triBaixo;
        this.botoesAndares = botoesAndares;
    }

    public void movimentaElevador(MeuPanel painel) {
        new Thread(() -> {
            while (true) {
                try {
                    Integer andarDestino = filaDeAndares.take();
                    elevador.setAndarDestino(andarDestino);
                    while (elevador.getAndarAtual() != andarDestino) {
                    	Thread.sleep(1000);
                        int andarAtual = elevador.getAndarAtual();
                        ciclos = 0; // Reseta ciclos ao iniciar movimento para um novo andar
                        if (andarAtual < andarDestino) {
                            elevador.setSentido(1);
                            for (int i = 0; i < 10; i++) {
                                triCima[i].setPreenchido(true);
                                triBaixo[i].setPreenchido(false);
                            }
                            while (andarAtual < andarDestino) {
                                elevador.moverParaAndar(andarAtual + 1);
                                painel.repaint();
                                ciclos++;
                                System.out.println(elevador.toString()); // Imprime as informações do elevador a cada ciclo
                                andarAtual = elevador.getAndarAtual();
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else if (andarAtual > andarDestino) {
                            elevador.setSentido(0);
                            for (int i = 0; i < 10; i++) {
                                triCima[i].setPreenchido(false);
                                triBaixo[i].setPreenchido(true);
                            }
                            while (andarAtual > andarDestino) {
                                elevador.moverParaAndar(andarAtual - 1);
                                painel.repaint();
                                ciclos++;
                                System.out.println(elevador.toString()); // Imprime as informações do elevador a cada ciclo
                                andarAtual = elevador.getAndarAtual();
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    elevador.setAndarDestino(null); // O elevador chegou ao destino
                    limparAposChegada();
                    painel.repaint();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        // Executor para atualizar o status "parado" a cada 1 segundo
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            if (elevador.getAndarDestino() == null) {
                System.out.println(elevador.toString()); // Imprime as informações do elevador quando parado
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void limparAposChegada() {
        for (int i = 0; i < 10; i++) {
            triCima[i].setPreenchido(false);
            triBaixo[i].setPreenchido(false);
        }
        botoesAndares[elevador.getAndarAtual() - 1].setPintado(false); // Apaga o botao
    }
}
