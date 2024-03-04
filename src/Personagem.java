
public class Personagem extends ElementoBasico {
    private ElementoBasico anterior;
    private Direção direção;

    public Personagem(String id, String iconPath, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, iconPath, linInicial, colInicial, tabuleiro);
    }

    public void setAnterior(ElementoBasico anterior) {
        this.anterior = anterior;
    }

    public Direção getDireção() {
        return this.direção;
    }

    public void moveDireita() {

        if (!this.incCol()) {
            return;
        }

        getTabuleiro().insereElemento(anterior);

        this.direção = Direção.RIGHT;

        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());

        if (!elemento.acao(this)) {
            this.decCol();
        }

        this.anterior = getTabuleiro().insereElemento(this);
    }

    public void moveEsquerda() {

        if (!this.decCol()) {
            return;
        }

        getTabuleiro().insereElemento(anterior);

        this.direção = Direção.LEFT;

        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());

        if (!elemento.acao(this)) {
            this.incCol();
        }

        this.anterior = getTabuleiro().insereElemento(this);
    }

    public void moveCima() {
        if (!this.decLin()) {
            return;
        }

        getTabuleiro().insereElemento(anterior);

        this.direção = Direção.UP;

        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());
        if (!elemento.acao(this)) {
            this.incLin();
        }

        this.anterior = getTabuleiro().insereElemento(this);
    }

    public void moveBaixo() {

        if (!this.incLin()) {
            return;
        }

        getTabuleiro().insereElemento(anterior);

        this.direção = Direção.DOWN;
        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());
        if (!elemento.acao(this)) {
            this.decLin();
        }
        this.anterior = getTabuleiro().insereElemento(this);

    }

    @Override
    public boolean acao(ElementoBasico outro) {
        throw new UnsupportedOperationException("Unimplemented method 'acao'");
    }
}
