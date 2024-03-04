
public class Caixa extends ElementoBasico {
    private ElementoBasico anterior;

    public Caixa(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "caixa.jpg", linInicial, colInicial, tabuleiro);
    }

    public void setAnterior(ElementoBasico anterior) {
        this.anterior = anterior;
    }

    private boolean moveDireita(Personagem p) {
 
        if (!this.incCol()) {
            return false;
        }

        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());

        if (elemento instanceof Caixa || !elemento.acao(this)) {
            this.decCol();
            return false;
        }

        getTabuleiro().insereElemento(anterior);
        this.anterior = getTabuleiro().insereElemento(this);
        return true;


 
    }

    private boolean moveEsquerda(Personagem p) {
        if (!this.decCol()) {
            return false;
        }

        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());

        if (elemento instanceof Caixa || !elemento.acao(this)) {
            this.incCol();
            return false;
        }

        getTabuleiro().insereElemento(anterior);
        this.anterior = getTabuleiro().insereElemento(this);
        return true;

    }

    private boolean moveCima(Personagem p) {
        if (!this.decLin()) {
            return false;
        }

        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());

        if (elemento instanceof Caixa || !elemento.acao(this)) {
            this.incLin();
            return false;
        }

        getTabuleiro().insereElemento(anterior);
        this.anterior = getTabuleiro().insereElemento(this);
        return true;
    }

    private boolean moveBaixo(Personagem p) {
        if (!this.incLin()) {
            return false;
        }

        ElementoBasico elemento = getTabuleiro().getElementoNaPosicao(this.getLin(), this.getCol());

        if (elemento instanceof Caixa || !elemento.acao(this)) {
            this.decLin();
            return false;
        }

        getTabuleiro().insereElemento(anterior);
        this.anterior = getTabuleiro().insereElemento(this);
        return true;
    }

    @Override
    public boolean acao(ElementoBasico outro) {

        if (outro instanceof Personagem) {
            Personagem personagem = (Personagem) outro;
            switch (personagem.getDireção()) {
                case UP:
                    return moveCima(personagem);
                case DOWN:
                    return moveBaixo(personagem);
                case LEFT:
                    return moveEsquerda(personagem);
                case RIGHT:
                    return moveDireita(personagem);
            }
        }

        return false;

    }
}
