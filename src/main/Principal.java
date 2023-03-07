package main;
import java.sql.SQLException;

import javax.swing.JFrame;

import formularios.Formulario;
import formularios.VentanaListener;

public class Principal {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        // App ventana = new App();
        // ventana.setBounds(0,0,300,200);
        // ventana.setResizable(true);
        // ventana.setVisible(true);
        // ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // VentanaListener le = new VentanaListener();
        // ventana.addWindowListener(le);
        // ventana.setVisible(true);

        Formulario ventana = new Formulario();
        VentanaListener le = new VentanaListener();
        ventana.addWindowListener(le);
        ventana.setVisible(true);
    }
}
