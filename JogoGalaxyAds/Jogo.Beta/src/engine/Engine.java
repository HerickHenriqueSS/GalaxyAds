package engine;

import javax.swing.JFrame;
import modelo.Fase;

public class Engine extends JFrame {
    
    public Engine() {
        add(new Fase());
        setTitle("Space Ship");
        setSize(1024,728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Engine();
    }
}
    
