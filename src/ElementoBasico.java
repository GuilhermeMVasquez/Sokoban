import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class ElementoBasico extends JButton{
    private Tabuleiro tabuleiro;
    private String id;
    private ImageIcon imagem;
    private int lin;
    private int col;

    public ElementoBasico(String id,String iconPath,int linInicial,int colInicial,Tabuleiro tabuleiro){
        this.setPreferredSize(new Dimension(60, 60));
        this.id = id;
        this.imagem = Tabuleiro.createImageIcon(iconPath); 
        this.setIcon(this.imagem); 
        this.lin = linInicial;
        this.col = colInicial;
        this.tabuleiro = tabuleiro;
        this.setFocusable(false);
    }

    public ImageIcon getImagem(){
        return this.imagem;
    }
    public void setImage(ImageIcon imagem){
        this.imagem = imagem;
        this.setIcon(imagem); 
    }

    public String getId() {
        return id;
    }

    public int getLin() {
        return lin;
    }

    public boolean incLin(){
        if (lin < Tabuleiro.getMaxlin()-1){
            lin++;
            return true;
        }
        return false;
    }

    public boolean decLin(){
        if (lin > 0){
            lin--;
            return true;
        }
        return false;
    }

    public boolean incCol(){
        if (col < Tabuleiro.getMaxcol()-1){
            col++;
            return true;
        }
        return false;
    }

    public boolean decCol(){
        if (col > 0){
            col--;
            return true;
        }
        return false;
    }

    public int getCol() {
        return col;
    }

    public Tabuleiro getTabuleiro(){
        return tabuleiro;
    }

    public abstract boolean acao(ElementoBasico outro);
}
