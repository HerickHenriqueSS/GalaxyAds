package modelo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Retry extends JFrame{   
    
    public Retry(){
        JOptionPane.showConfirmDialog(null, "RETRY? ");
        Fase fase = new Fase();
    }

}
