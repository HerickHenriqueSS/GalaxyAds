package modelo;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;


import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Fase extends JPanel implements ActionListener {

    private Image fundo;
    private Player player;
    private Timer timer;
    private List<Enemy1> enemy1;
    private List<Stars> stars;
    public int emJogo;
    public int contador;
    public int contadorTiros = 90;
    public int contadorVida = 100;


    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon referencia = new ImageIcon("src\\res\\Blackground.png");
        fundo = referencia.getImage();

        player = new Player();
        player.load();

        addKeyListener(new TecladoAdapter());

        timer = new Timer(5, this);
        timer.start();

        inicializaInimigos();
        inicializaStars();
        emJogo = contadorVida;
        

    }

    public void inicializaInimigos() {
        int coordenadas [] = new int [40];
        enemy1 = new ArrayList<Enemy1>();

        for (int i = 0; i < coordenadas.length; i++ ) {
            int x = (int)(Math.random() * 8000+1024);
            int y = (int)(Math.random() * 650+30);
            enemy1.add(new Enemy1(x, y));
        }
    }

    public void inicializaStars() {
        int coordenadas [] = new int [400];
        stars = new ArrayList<Stars>();
        for (int i = 0; i < coordenadas.length; i++ ) {
            int x = (int)(Math.random() * 1050+1024);
            int y = (int)(Math.random() * 768+0);
            stars.add(new Stars(x, y));
        }
    }

    public void paint(Graphics g) {


        Graphics2D graficos = (Graphics2D) g;
        if(emJogo >= 1 ) {
            graficos.drawImage(fundo, 0, 0, null);
            

            for(int u = 0; u < stars.size(); u++) {
                Stars q = stars.get(u);
                q.load();
                graficos.drawImage(q.getImagem(), q.getX(), q.getY() , this);
            } 

            graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

            List<Tiro> tiros = player.getTiros();
            
            for(int i = 0; i < tiros.size(); i++) {
                Tiro m = tiros.get(i);
                m.load();
                graficos.drawImage(m.getImagem(), m.getX(), m.getY() , this);   
            }

            for (int o = 0; o < enemy1.size(); o++) {
                Enemy1 in = enemy1.get(o);
                in.load();
                graficos.drawImage(in.getImagem(), in.getX() , in.getY(), this);
            }
        }
        else{
            if(contadorVida>=0){
                ImageIcon fimJogo = new ImageIcon("src\\res\\fimdejogo.png");
                graficos.drawImage(fimJogo.getImage(), 0, 0, null);
                //Retry retry = new Retry();
            }
        }

                
        /*public void update(){
            if(contadorVida <=0){
                contadorVida --;
            }
        }*/
        g.setColor(Color.white);

        g.setFont(new Font("Arial", Font.BOLD, 23));
        g.drawString("Pontos: " + contador, 1024/2- 60, 30);

        g.setFont(new Font("Arial", Font.BOLD, 23));
        g.drawString("Tiros: " + contadorTiros , 1024/6- 60, 30);

        /*g.setFont(new Font("Arial", Font.BOLD, 23));
        g.drawString("Vidas: " + contadorVida, 1024/2+ 300, 30);*/

        g.setColor(Color.white);
        g.fillRect(1024/2+300, 10, contadorVida+10, 25);


        g.setColor(Color.red);
        g.fillRect(1024/2+300, 10, emJogo+10, 25);
        
        g.dispose();
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        player.Update();
        if(player.isTurbo() == false) {
            timer.setDelay(2);
        }

        if(player.isTurbo() == true) {
            timer.setDelay(8);
        }
    

        for (int u = 0; u < stars.size(); u++) {
            Stars on = stars.get(u);
                if(on.isVisivel()) {
                    on.update();
              } else {
                 stars.remove(u); 
              }

        }

        List<Tiro> tiros = player.getTiros();
        contadorTiros --;
        for(int p = 0; p < tiros.size(); p++) {
            
            Tiro m = tiros.get(p);
                if(m.isVisivel()) {
                    m.update();
                    //contadorTiros --;
                } else {
                    tiros.remove(p);
                }
        }

        for (int o = 0; o < enemy1.size(); o++) {
            Enemy1 in = enemy1.get(o);
                if(in.isVisivel()) {
                    in.update();
                } else {
                    enemy1.remove(o);
                    
                }
        }

        checarColisoes();
        repaint();
    }

    public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaEnemy1;
		Rectangle formaTiro ;
		
		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBounds();
			if(formaNave.intersects(formaEnemy1)) {
				player.setVisivel(false);
				tempEnemy1.setVisivel(false);
                emJogo = emJogo - 20;
                
			}//if
		}//for
		
		List<Tiro> tiros = player.getTiros();
		
		for (int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBounds();

			for (int k = 0; k < enemy1.size(); k++) {
                Enemy1 tempEnemy1 = enemy1.get(k);
				formaEnemy1 = tempEnemy1.getBounds();
				if(formaTiro.intersects(formaEnemy1)) {
					tempEnemy1.setVisivel(false);
                    contador ++;
					tempTiro.setVisivel(false);
				}//if
			}//for
		}//for


    }
    
    private class TecladoAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			player.KeyPressed(e);
		}//keyPressed
		
		@Override
		public void keyReleased(KeyEvent e) {
			player.KeyRealease(e);
		}//keyRelease
    }
    
}