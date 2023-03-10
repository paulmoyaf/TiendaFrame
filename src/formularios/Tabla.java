package formularios;


import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import productos.Teclado;

public class Tabla extends JFrame {

    private JTable tabla = null;
    DefaultTableModel modelo = null;
    JScrollPane desplazamiento = null;
    public static JButton btAdd, btBorrar, btCambiar;
    public static ImageIcon imageCambiar = new ImageIcon("icono/cambiar1.png");
    public static ImageIcon imageBorrar = new ImageIcon("icono/eliminar1.png");
    

    public Tabla() throws Exception {
        String[] columnas = {"Código", "Marca", "Precio", "Descuento", "Tipo", "Color","Teclas","Conector","Envío","PVP","1","2"};
        
        
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
        // tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
        tabla.setFillsViewportHeight(true);
// 
        tabla.setModel(modelo);
        tabla.getPreferredSize();
        tabla.setRowHeight(50);

        // this.addMouseListener(tabla);

        


        // Agregamos datos
        // this.agregarDatos(modelo);
        this.llenarJTabla(tabla);
        
        
        int ybt = 50;
        int yGap = 50;
    
        btAdd = new JButton("Crear");
        btAdd.setBounds(300, ybt+(5 * yGap), 100, 30);
        add(btAdd);
        
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });


        // Agregando elementos a la ventana
        this.getContentPane().add(desplazamiento, BorderLayout.NORTH);
        this.pack();
    
    }
    

    public void llenarJTabla(JTable jtTeclados)throws Exception{

        try{



            // para los botones
            jtTeclados.setDefaultRenderer(Object.class, new Render());
            btCambiar = new JButton();
            btCambiar.setName("c");
            btCambiar.setIcon(imageCambiar);
            btBorrar = new JButton();
            btBorrar.setName("b");
            btBorrar.setIcon(imageBorrar);




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
 
            for(Teclado teclado : LecturaBBDD.listar()){
                modelo.addRow(new Object[]{	teclado.getId(),
											teclado.getMarca(),
											teclado.getPrecio(),
											teclado.getDcto(),
											teclado.isPrime(),
											teclado.getColor(),
											teclado.getTeclas(),
											teclado.getConector(),
											teclado.getEnvio(),
											teclado.getPrecioVenta(),btCambiar,btBorrar}); 
            }
            //se actualiza la Tabla
            jtTeclados.setModel(modelo);
        //en caso de error
        }catch(Exception sqle){
            JOptionPane.showMessageDialog(null,"Error llenar JTable " + sqle);
        }
    }


    public boolean isCellEditable(int row, int column){
        return false;
    }
  
    private void tablaMouseClicked(java.awt.event.MouseEvent evt){
        int column = tabla.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tabla.getRowHeight();

        if(row < tabla.getRowCount() && row >= 0 && column < tabla.getColumnCount() && column >= 0){
            Object value = tabla.getValueAt(row, column);
            if(value instanceof JButton){
                ((JButton)value).doClick();
                JButton boton = (JButton) value;
                System.out.println(value);
                if(boton.getName().equals("c")){
                    //EVENTOS MODIFICAR
                    System.out.println("Click en el boton modificar");
                    for(int col = 0; col<10;col++){
                        System.out.println(tabla.getValueAt(row,col));
                    }
                    // System.out.println(tabla.getValueAt(1,2));
                    // Formulario.cambiarItem();
                }
                if(boton.getName().equals("b")){
                    JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    System.out.println("Click en el boton eliminar");
                    //EVENTOS ELIMINAR
                }
            }
            if(value instanceof JCheckBox){
                //((JCheckBox)value).doClick();
                JCheckBox ch = (JCheckBox)value;
                if(ch.isSelected()==true){
                    ch.setSelected(false);
                }
                if(ch.isSelected()==false){
                    ch.setSelected(true);
                }
            }
        }
    }

    


}
    

