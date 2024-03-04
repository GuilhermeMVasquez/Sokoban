public class Parede extends ElementoBasico{

    public Parede(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "blackSquare.jpg", linInicial, colInicial, tabuleiro);
    }


    @Override
    public boolean acao(ElementoBasico outro) {
        return false;
    }    
}
