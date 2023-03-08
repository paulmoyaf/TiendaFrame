package main;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JFrame;

import formularios.Formulario;
import formularios.VentanaListener;

public class Principal {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Formulario ventana = new Formulario();
        VentanaListener le = new VentanaListener();
        ventana.addWindowListener(le);
        ventana.setVisible(true);
        
    }
}
