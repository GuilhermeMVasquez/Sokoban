public class Fundo extends ElementoBasico{

    public Fundo(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "fundo.jpg", linInicial, colInicial, tabuleiro);
    }

    @Override
    public boolean acao(ElementoBasico outro) {
        return true;
    }
}
