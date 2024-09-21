import org.example.Som;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class TelaJogo extends JPanel implements ActionListener {

    private static final int LARGURA_TELA = 1300;
    private static final int ALTURA_TELA = 700;
    private static final int TAMANHO_BLOCO = 45;
    private static final int UNIDADES = LARGURA_TELA * ALTURA_TELA / (TAMANHO_BLOCO * TAMANHO_BLOCO);
    private int INTERVALO = 120;
    private static final String NOME_FONTE = "Ink Free";
    private final int[] eixoX = new int[UNIDADES];
    private final int[] eixoY = new int[UNIDADES];
    private int corpoCobra = 6;
    private int blocosComidos;
    private int blocoX;
    private int blocoY;
    private char direcao = 'D'; // C - Cima, B - Baixo, E - Esquerda, D - Direita
    private boolean estaRodando = false;
    private boolean noMenu = true;
    private boolean dificuldadeSelecionada = false;
    private boolean corSelecionada = false;
    private String corEscolhida = "Verde";
    private Timer timer;
    private Random random;
    private Image fundo; // Para armazenar a imagem de fundo
    private Image imagemMaca; // Para armazenar a imagem da maçã
    private Image fundoAtual; // Para armazenar a imagem de fundo atual
    private Som som; // Instância da classe Som

    TelaJogo() {
        random = new Random();
        setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(new LeitorDeTeclasAdapter());

        // Carregando as imagens
        fundo = new ImageIcon("C:/aa/fotooo.png").getImage(); // Imagem de fundo inicial
        fundoAtual = fundo; // Inicializa a imagem de fundo atual
        imagemMaca = new ImageIcon("C:/aa/fotoo.png").getImage(); // Imagem da maçã
        som = new Som(); // Inicializa a instância de Som
    }

    public void iniciarJogo() {
        corpoCobra = 6;
        blocosComidos = 0;
        direcao = 'D'; // Reinicia a direção da cobra para a direita
        criarBloco();
        estaRodando = true;
        noMenu = false;

        // Parar a música do menu ao iniciar o jogo
        som.pararSom();

        if (timer != null) {
            timer.stop(); // Para o timer antigo
        }
        timer = new Timer(INTERVALO, this);
        timer.start();

        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fundoAtual, 0, 0, LARGURA_TELA, ALTURA_TELA, this); // Desenha a imagem de fundo

        if (noMenu) {
            desenharMenu(g);
        } else if (estaRodando) {
            desenharTela(g);
        } else {
            fimDeJogo(g);
        }
    }

    public void desenharMenu(Graphics g) {
        if (!som.isPlaying()) { // Toca a música do menu apenas se não estiver tocando
            som.tocarSom("C:/aa/Jogo snake between worlds/assets/Nova pasta/audio do menu.wav");
        }

        g.setColor(Color.WHITE); // Fonte branca
        g.setFont(new Font(NOME_FONTE, Font.BOLD, 50));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Escolha a dificuldade: ", (LARGURA_TELA - metrics.stringWidth("Escolha a dificuldade: ")) / 2, ALTURA_TELA / 4);

        g.setFont(new Font(NOME_FONTE, Font.BOLD, 40));
        g.drawString("1. Fácil", (LARGURA_TELA - metrics.stringWidth("1. Fácil")) / 2, ALTURA_TELA / 4 + 100);
        g.drawString("2. Médio", (LARGURA_TELA - metrics.stringWidth("2. Médio")) / 2, ALTURA_TELA / 4 + 200);
        g.drawString("3. Difícil", (LARGURA_TELA - metrics.stringWidth("3. Difícil")) / 2, ALTURA_TELA / 4 + 300);

        g.drawString("Escolha a cor da cobra: ", (LARGURA_TELA - metrics.stringWidth("Escolha a cor da cobra: ")) / 2, ALTURA_TELA / 4 + 400);
        g.drawString("A. Verde, B. Azul, C. Vermelho, D. Preto, E. Amarelo, F. Roxo", 100, ALTURA_TELA / 4 + 500);
        g.drawString("Pressione ENTER para começar", (LARGURA_TELA - metrics.stringWidth("Pressione ENTER para começar")) / 2, ALTURA_TELA / 4 + 600);
    }

    public void desenharTela(Graphics g) {
        g.drawImage(imagemMaca, blocoX, blocoY, TAMANHO_BLOCO, TAMANHO_BLOCO, this); // Desenha a imagem da maçã

        for (int i = 0; i < corpoCobra; i++) {
            if (i == 0) {
                g.setColor(getCorCobra());
                g.fillRect(eixoX[0], eixoY[0], TAMANHO_BLOCO, TAMANHO_BLOCO);
            } else {
                g.setColor(getCorCobra().darker());
                g.fillRect(eixoX[i], eixoY[i], TAMANHO_BLOCO, TAMANHO_BLOCO);
            }
        }

        g.setColor(Color.WHITE); // Fonte branca
        g.setFont(new Font(NOME_FONTE, Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Pontos: " + blocosComidos, (LARGURA_TELA - metrics.stringWidth("Pontos: " + blocosComidos)) / 2, g.getFont().getSize());
    }

    public void fimDeJogo(Graphics g) {
        g.setColor(Color.WHITE); // Fonte branca
        g.setFont(new Font(NOME_FONTE, Font.BOLD, 40));
        FontMetrics fontePontuacao = getFontMetrics(g.getFont());
        g.drawString("Pontos: " + blocosComidos, (LARGURA_TELA - fontePontuacao.stringWidth("Pontos: " + blocosComidos)) / 2, g.getFont().getSize());

        g.setFont(new Font(NOME_FONTE, Font.BOLD, 75));
        FontMetrics fonteFinal = getFontMetrics(g.getFont());
        g.drawString("\uD83D\uDE1D Fim do Jogo.", (LARGURA_TELA - fonteFinal.stringWidth("Fim do Jogo")) / 2, ALTURA_TELA / 2);

        g.setFont(new Font(NOME_FONTE, Font.BOLD, 40));
        g.drawString("Pressione ESC para voltar ao Menu", (LARGURA_TELA - fontePontuacao.stringWidth("Pressione ESC para voltar ao Menu")) / 2, ALTURA_TELA / 2 + 100);
    }

    private void criarBloco() {
        blocoX = random.nextInt(LARGURA_TELA / TAMANHO_BLOCO) * TAMANHO_BLOCO;
        blocoY = random.nextInt(ALTURA_TELA / TAMANHO_BLOCO) * TAMANHO_BLOCO;
    }

    private Color getCorCobra() {
        switch (corEscolhida) {
            case "Azul":
                return Color.blue;
            case "Vermelho":
                return Color.red;
            case "Preto":
                return Color.black;
            case "Amarelo":
                return Color.yellow;
            case "Roxo":
                return new Color(128, 0, 128); // Roxo
            default:
                return Color.green;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (estaRodando) {
            andar();
            alcancarBloco();
            mudarFundoPorPontuacao(); // Chama a função para mudar o fundo
            validarLimites();
        }
        repaint();
    }


    private void andar() {
        for (int i = corpoCobra; i > 0; i--) {
            eixoX[i] = eixoX[i - 1];
            eixoY[i] = eixoY[i - 1];
        }

        switch (direcao) {
            case 'C':
                eixoY[0] = eixoY[0] - TAMANHO_BLOCO;
                break;
            case 'B':
                eixoY[0] = eixoY[0] + TAMANHO_BLOCO;
                break;
            case 'E':
                eixoX[0] = eixoX[0] - TAMANHO_BLOCO;
                break;
            case 'D':
                eixoX[0] = eixoX[0] + TAMANHO_BLOCO;
                break;
            default:
                break;
        }
    }

    private void alcancarBloco() {
        if (eixoX[0] == blocoX && eixoY[0] == blocoY) {
            corpoCobra++;
            blocosComidos++;
            criarBloco();

            // Tocar som baseado na quantidade de maçãs comidas
            if (blocosComidos == 15) {
                som.tocarSom("C:/aa/Jogo snake between worlds/assets/Nova pasta/audio passar nivel1.wav"); // Som ao pegar a 1ª maçã
            } else if (blocosComidos == 301a
        ) {
                som.tocarSom("C:/aa/Jogo snake between worlds/assets/Nova pasta/audio passar nivel2.wav"); // Som ao pegar a 2ª maçã
            } else {
                som.tocarSom("C:/aa/Jogo snake between worlds/assets/Nova pasta/audio maça.wav"); // Som ao pegar maçãs subsequentes
            }
        }
    }

    private void mudarFundoPorPontuacao() {
        if (blocosComidos == 15) {
            fundoAtual = new ImageIcon("C:\\aa\\Jogo snake between worlds\\assets\\fotoooo.png").getImage(); // Muda para fotooo.png
        } else if (blocosComidos == 30) {
            fundoAtual = new ImageIcon("C:\\aa\\Jogo snake between worlds\\assets\\foto.png").getImage(); // Muda para foto.png

        }
    }


    private void validarLimites() {
        if (eixoY[0] < 0 || eixoY[0] >= ALTURA_TELA || eixoX[0] < 0 || eixoX[0] >= LARGURA_TELA) {
            estaRodando = false; // O jogo termina

        }

        for (int i = corpoCobra; i > 0; i--) {
            if (i > 5 && eixoX[0] == eixoX[i] && eixoY[0] == eixoY[i]) {
                estaRodando = false; // O jogo termina

            }
        }

        if (!estaRodando) {
            timer.stop(); // Para o timer
        }
    }

    private class LeitorDeTeclasAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (noMenu) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1:
                        INTERVALO = 150; // Dificuldade fácil
                        dificuldadeSelecionada = true;
                        break;
                    case KeyEvent.VK_2:
                        INTERVALO = 120; // Dificuldade média
                        dificuldadeSelecionada = true;
                        break;
                    case KeyEvent.VK_3:
                        INTERVALO = 90; // Dificuldade difícil
                        dificuldadeSelecionada = true;
                        break;
                    case KeyEvent.VK_A:
                        corEscolhida = "Verde";
                        corSelecionada = true;
                        break;
                    case KeyEvent.VK_B:
                        corEscolhida = "Azul";
                        corSelecionada = true;
                        break;
                    case KeyEvent.VK_C:
                        corEscolhida = "Vermelho";
                        corSelecionada = true;
                        break;
                    case KeyEvent.VK_D:
                        corEscolhida = "Preto";
                        corSelecionada = true;
                        break;
                    case KeyEvent.VK_E:
                        corEscolhida = "Amarelo";
                        corSelecionada = true;
                        break;
                    case KeyEvent.VK_F:
                        corEscolhida = "Roxo";
                        corSelecionada = true;
                        break;
                    case KeyEvent.VK_ENTER:
                        if (dificuldadeSelecionada && corSelecionada) {
                            iniciarJogo();
                        }
                        break;
                }
            } else {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (direcao != 'B') direcao = 'C'; // Cima
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direcao != 'C') direcao = 'B'; // Baixo
                        break;
                    case KeyEvent.VK_LEFT:
                        if (direcao != 'D') direcao = 'E'; // Esquerda
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direcao != 'E') direcao = 'D'; // Direita
                        break;
                    case KeyEvent.VK_ESCAPE:
                        estaRodando = false; // Voltar ao menu
                        noMenu = true;
                        som.pararSom(); // Para a música ao voltar para o menu
                        break;
                }
            }
        }
    }
}