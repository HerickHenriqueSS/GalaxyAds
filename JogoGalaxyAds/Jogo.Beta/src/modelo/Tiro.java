package modelo;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Tiro {

    private Image imagem;
    private int x, y;
    private int largura, altura;
    private Boolean isVisivel;

    private static final int LARGURA = 938;
    private static int VELOCIDADE = 4;

    
    public Tiro(int x, int y) {
        this.x = x;
        this.y = y;
        isVisivel = true;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public void load() {
        ImageIcon referencia = new ImageIcon("src\\res\\TiroSimples.png");
        imagem = referencia.getImage();

        this.setLargura(imagem.getWidth(null));
        this.setAltura(imagem.getHeight(null));
    }
    
    public void  update() {
        this.x += VELOCIDADE;
            if(this.x > LARGURA){
                isVisivel = false;
            }    
    }

    public Rectangle getBounds() {
        return new Rectangle (x, y, largura, altura);
    }

    public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}


}