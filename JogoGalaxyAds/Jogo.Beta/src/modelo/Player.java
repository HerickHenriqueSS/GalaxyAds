package modelo;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Player implements ActionListener {

    private int x, y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;
    private List <Tiro> tiros;
    private Boolean isVisivel, isTurbo;
    private Timer timer;
    
    


    
    public Player() {
        this.x = 100;
        this.y = 100;
        isVisivel = true;
        isTurbo = false;
        tiros = new ArrayList<Tiro>();

        timer = new Timer(4000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isTurbo == true) {
            turbo();
            isTurbo = false;
        }

        if (isTurbo == false);
            load();
    }
 

    public int getLargura() {
        return largura;
    }


    public void setLargura(int largura) {
        this.largura = largura;
    }


    public int getAltura() {
        return altura;
    }


    public void setAltura(int altura) {
        this.altura = altura;
    }


    public void load() {
        ImageIcon referencia = new ImageIcon("src\\res\\spaceship3.png");
        imagem = referencia.getImage();
        
        setLargura(imagem.getWidth(null));
        setAltura(imagem.getHeight(null));
        
    }

    public void Update() {
        x += dx;
        y += dy;
    }

    public void tiroSimples() {
        this.tiros.add(new Tiro(x+largura, y + (altura/2)));
              
    }


    public void turbo() {
        isTurbo = true;
        ImageIcon referencia = new ImageIcon("src\\res\\naveturbo2.png");
        imagem = referencia.getImage();
    }


    public Rectangle getBounds() {
        return new Rectangle (x, y, largura, altura);
    }

    public void KeyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE) {
            turbo();
            
        }

        if (codigo == KeyEvent.VK_A) {
            tiroSimples();
                        
        }

        if (codigo == KeyEvent.VK_UP) {
            dy = -4;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 4;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = -4;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 4;
        }
    }

    public void KeyRealease(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
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

    public List<Tiro> getTiros() {
        return tiros;
    }

    public boolean Turbo() {
        return false;
    }

    public boolean isTurbo() {
        return false;
    }

    

    

}