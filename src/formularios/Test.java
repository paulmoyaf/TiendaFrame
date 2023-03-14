package formularios;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Test extends JFrame {

    JPanel contentPane = null;
    JTable jt1 = null;
    private static JButton btAdd, btBorrar, btCambiar, btGuardar, btCancel;

    public Test() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 286);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(26, 46, 352, 131);
        contentPane.add(scrollPane);
                
        String[][] data = {{"Alex","Five","55","Male"},
            {"Ron","Six","65","Femal"}};
        String[] column= {"Name","Class","Mark","Sex"};
                
        jt1 = new JTable(data,column);
        scrollPane.setViewportView(jt1);
        

        int ybt = 50;
        int yGap = 50;


        btAdd = new JButton("Crear");
        btAdd.setBounds(20, ybt+(7 * yGap), 100, 30);
        add(btAdd);
    }
    }
