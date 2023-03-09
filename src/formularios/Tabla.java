package formularios;


import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import productos.Teclado;

public class Tabla extends JFrame {

    private JTable tabla = null;
    DefaultTableModel modelo = null;
    JScrollPane desplazamiento = null;

    public Tabla() throws Exception {
        String[] columnas = {"Código", "Marca", "Precio", "Descuento", "Tipo", "Color","Teclas","Conector","Envío","PVP"};
        tabla = new JTable();
        modelo = new DefaultTableModel();
        desplazamiento = new JScrollPane(tabla);       

        // Parametros de la ventana
        this.setTitle("Stock de Productos");
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Modelo de la tabla
        modelo.setColumnIdentifiers(columnas);

        // Barras de desplazamiento
        desplazamiento.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        desplazamiento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Propiedades de la tabla
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla.setFillsViewportHeight(true);

        tabla.setModel(modelo);

        // Agregamos datos
        // this.agregarDatos(modelo);
        this.llenarJTabla(tabla);
        
        
        // Agregando elementos a la ventana
        this.getContentPane().add(desplazamiento, BorderLayout.NORTH);
        this.pack();
    }
    

    public void llenarJTabla(JTable jtTeclados)throws Exception{
        try{
            //Se crea un array para llenar las columnas de la tabla
            ArrayList<Object> nombreColumna = new ArrayList<>();
            nombreColumna.removeAll(nombreColumna);
            nombreColumna.add("id");
            nombreColumna.add("marca");
            nombreColumna.add("precio");
            nombreColumna.add("descuento");
            nombreColumna.add("tipo");
            nombreColumna.add("color");
            nombreColumna.add("teclas");
            nombreColumna.add("conector");
            nombreColumna.add("envio");
            nombreColumna.add("pvp");

            //se rellena con cada una de las columnas al array

            // for(Object columna : nombreColumna){
            //     modelo.addColumn(columna);
            // }

            //Se rellena con el array de listar Nivel2       
            //Nivel2 controlador, hace referencia a la clase donde se creó el método listar  
            for(Teclado teclado : LecturaBBDD.listar()){
                //Como dato nivel es de nivo nivel2, este puede acceder a los métodos get y set
                modelo.addRow(new Object[]{	teclado.getId(),
											teclado.getMarca(),
											teclado.getPrecio(),
											teclado.getDcto(),
											teclado.isPrime(),
											teclado.getColor(),
											teclado.getTeclas(),
											teclado.getConector(),
											teclado.getEnvio(),
											teclado.getPrecioVenta(),}); 
            }
            //se actualiza la Tabla
            jtTeclados.setModel(modelo);
        //en caso de error
        }catch(Exception sqle){
            JOptionPane.showMessageDialog(null,"Error llenar JTable " + sqle);
        }
    }


    


}
    

