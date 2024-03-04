public class Marcador extends ElementoBasico implements Checkable{

    private boolean checked;

    public Marcador(String id, int linInicial, int colInicial, Tabuleiro tabuleiro) {
        super(id, "redCircle.png", linInicial, colInicial, tabuleiro);
        this.checked = false;
    }

    public void setChecked() {
        this.checked = true;
        setImage(Tabuleiro.createImageIcon("greenCircle.png"));
    }

    public boolean check() {
        return checked;
    }

    @Override
    public boolean acao(ElementoBasico outro) {
        if (outro instanceof Caixa) {
            this.setChecked();
        }
        
        return true;
    }
}
