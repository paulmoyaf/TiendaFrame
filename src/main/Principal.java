package main;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import formularios.Formulario;
import formularios.FormListener;

public class Principal {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try {
            Formulario form = new Formulario();
            FormListener le = new FormListener();
            form.addWindowListener(le);
            form.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error con el servicio de conexión a la Base de Datos\n" + e, "Error Conexión", 0);
            System.out.println("error conexion");
            // TODO: handle exception
        }

        
    }
}
