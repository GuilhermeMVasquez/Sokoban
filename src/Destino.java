public class Destino extends ElementoBasico implements Checkable{

    public Destino(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "yellowSquare.jpg", linInicial, colInicial, tabuleiro);
    }

    public boolean check() {
        return getTabuleiro().getElementoNaPosicao(getLin(), getCol()) instanceof Caixa;
    }

    @Override
    public boolean acao(ElementoBasico outro) {
        return true;
    }
}
