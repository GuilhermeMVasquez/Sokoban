import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class App extends JFrame implements ActionListener {
    private Personagem personagem;
    private final Tabuleiro tabuleiro;
    private final ArrayList<Checkable> checagens;
    private final Fases fases;

    public App() {
        super();
        // Define os componentes da tela
        tabuleiro = new Tabuleiro();
        checagens = new ArrayList<>();
        fases = new Fases();

        JPanel botoesDirecao = new JPanel(new FlowLayout());
        JButton butReset = new JButton("Reset");
        butReset.addActionListener(this);

        botoesDirecao.add(butReset);

        JPanel painelGeral = new JPanel();
        painelGeral.setLayout(new BoxLayout(painelGeral, BoxLayout.PAGE_AXIS));
        painelGeral.add(tabuleiro);
        painelGeral.add(botoesDirecao);

        /* COMEÇA AREA DE MANUSEIO */

        this.setTabuleiroComFase();

        /* ACABA AREA DE MANUSEIO */
        this.add(painelGeral);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_UP) {
                    personagem.moveCima();
                } 
                else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
                    personagem.moveBaixo();
                } 
                else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
                    personagem.moveEsquerda();
                } 
                else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
                    personagem.moveDireita();
                }
                
                
                if (checarTerminou()) {
                    fases.passarFase();
                    if (fases.checarTerminou()) {
                        JOptionPane.showMessageDialog(getRootPane(), "Parabens, Você Ganhou");
                        System.exit(0);
                    }
                    setTabuleiroComFase();
                }
                
                tabuleiro.atualizaVisualizacao();
                
            }
            public void keyReleased(KeyEvent arg0) {}
            public void keyTyped(KeyEvent arg0) {}
            
        });

        this.setFocusable(true);
        this.setSize(1200, 1000);
        this.setTitle("Sokoban");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
        tabuleiro.atualizaVisualizacao();
    }

    private void setTabuleiroComFase() {
        String fase = fases.getFase();
        checagens.clear();

        for (int i = 0; i < Tabuleiro.getMaxlin(); i++) {
            for (int j = 0; j < Tabuleiro.getMaxcol(); j++) {
                char cur = fase.charAt(j + i * Tabuleiro.getMaxcol());

                if (cur == 'p') {
                    tabuleiro.insereElemento(new Parede("0", i, j, tabuleiro));
                } else if (cur == 'j') {
                    personagem = new Personagem("jogador", "chad.jpg", i, j, tabuleiro);
                    tabuleiro.insereElemento(personagem);
                    personagem.setAnterior(new Fundo("f", i, j, tabuleiro));
                } else if (cur == 'c') {
                    Caixa caixa = new Caixa("caixa", i, j, tabuleiro);
                    tabuleiro.insereElemento(caixa);
                    caixa.setAnterior(new Fundo("f", i, j, tabuleiro));
                } else if (cur == 'd') {
                    Destino destino = new Destino("dest", i, j, tabuleiro);
                    tabuleiro.insereElemento(destino);
                    checagens.add(destino);
                } else if (cur == ' ') {
                    tabuleiro.insereElemento(new Fundo("f", i, j, tabuleiro));
                } else if (cur == 'm') {
                    Marcador marker = new Marcador("m", i, j, tabuleiro);
                    tabuleiro.insereElemento(marker);
                    checagens.add(marker);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton but = (JButton) arg0.getSource();

        if (but.getText().equals("Reset")) {
            this.requestFocus();
            this.setTabuleiroComFase();
        }
        
        tabuleiro.atualizaVisualizacao();
    }

    private boolean checarTerminou() {
        for (Checkable checagem : this.checagens) {
            if (!checagem.check()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}
