package tabla;


import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import formularios.LecturaBBDD;
import productos.Producto;
import productos.Teclado;

public class Tabla extends JFrame {

    private static JTable tabla = null;
    DefaultTableModel modelo = null;
    JScrollPane desplazamiento = null;
    public static JButton btAdd, btBorrar, btCambiar, btGuardar, btCancel;
    public static ImageIcon imageCambiar = new ImageIcon("icono/cambiar1.png");
    public static ImageIcon imageBorrar = new ImageIcon("icono/eliminar1.png");
    public static JLabel lb_codeTemp;
    

    public Tabla() throws Exception {
        String[] columnas = {"Código", "Marca", "Precio", "Descuento", "Tipo", "Color","Teclas","Conector","Envío","PVP","1","2"};
        
        int ybt = 50;
        int yGap = 50;

        btAdd = new JButton("Crear");
        btAdd.setBounds(20, ybt+(7 * yGap), 100, 30);
        add(btAdd);
        btGuardar = new JButton("Guardar");
        btGuardar.setBounds(140, ybt+(7 * yGap), 100, 30);
        add(btGuardar);
        btCancel = new JButton("Cancelar");
        btCancel.setBounds(280, ybt+(7 * yGap), 100, 30);
        add(btCancel);
        
        tabla = new JTable();
        modelo = new DefaultTableModel();
        desplazamiento = new JScrollPane(tabla);  
        
        // Parametros de la ventana
        this.setTitle("Stock de Productos");
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // this.setLayout(null);
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
        // tabla.setEnabled(false);

        // this.addMouseListener(tabla);

        // Agregamos datos
        // this.agregarDatos(modelo);
        this.llenarJTabla(tabla);
        
    
        lb_codeTemp = new JLabel("nada");
        lb_codeTemp.setBounds(20, ybt+(7* yGap), 100, 30);
        add(lb_codeTemp);


        tabla.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String codeTemp = "";
                lb_codeTemp.setText(tempMouseClicked(evt));
                codeTemp = lb_codeTemp.getText();
                try {
                    // mouseSimple(evt, codeTemp);
                    tablaMouseClicked(evt, codeTemp);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


        });


        // Agregando elementos a la ventana
        this.getContentPane().add(desplazamiento, BorderLayout.NORTH);
        this.pack();
    
    }
  

    private Teclado obtenerObj(int row) throws Exception {
        Teclado teclado = new Teclado();

        try {

            String codigo   = tabla.getValueAt(row, 0).toString();
            String marca    = tabla.getValueAt(row, 1).toString();
            String precio   = tabla.getValueAt(row, 2).toString();
            String dcto     = tabla.getValueAt(row, 3).toString();
            String tipo     = tabla.getValueAt(row, 4).toString();
            boolean prime   = false;
            if (tipo.equals("PRIME")) {
                prime = true;
            }
            String color    = tabla.getValueAt(row, 5).toString();
            String teclas   = tabla.getValueAt(row, 6).toString();
            String conector = tabla.getValueAt(row, 7).toString();
            String envio    = tabla.getValueAt(row, 8).toString();
            String pvp      = tabla.getValueAt(row, 9).toString();

            teclado.setId(codigo);
            teclado.setMarca(marca);
            teclado.setPrecio(Double.parseDouble(precio));
            // teclado.setPrecio(precio);
            teclado.setDcto(Double.parseDouble(dcto));
            // teclado.setDcto(dcto);
            teclado.setPrime(prime);
            teclado.setColor(color);
            teclado.setTeclas(Integer.parseInt(teclas));
            teclado.setConector(conector);

            // System.out.print(teclado.getId(),teclado.getMarca(),teclado.getPrecio(),teclado.getDcto(),teclado.isPrime(),teclado.getColor(),teclado.getTeclas(),teclado.getConector());
            teclado.mostrarInfo();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Item in table was not a valid double - "
                    + e.getMessage());

        } catch (Exception e) {
            // TODO: handle exception
        }
        return teclado;

    }
  
    private String tempMouseClicked(java.awt.event.MouseEvent evt){
        int column = tabla.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tabla.getRowHeight();
        String codeTemp = "";

        if(row < tabla.getRowCount() && row >= 0 && column < tabla.getColumnCount() && column >= 0){
            // Object value = tabla.getValueAt(row, column);
            codeTemp = (String) tabla.getValueAt(row, 0);
        }
        return codeTemp;
    }

    private void mouseSimple(java.awt.event.MouseEvent evt, String codeTemp) throws Exception{
    
        int row = tabla.getSelectedRow();
        Teclado teclado = new Teclado();
        teclado = obtenerObj(row);
    }

    private void tablaMouseClicked(java.awt.event.MouseEvent evt, String codeTemp) throws Exception{
        // tabla.setEnabled(true);
        
        int column = tabla.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tabla.getRowHeight();
        
        Teclado teclado = new Teclado();
        if(row < tabla.getRowCount() && row >= 0 && column < tabla.getColumnCount() && column >= 0){
            Object value = tabla.getValueAt(row, column);
           
            if(value instanceof JButton){
                ((JButton)value).doClick();
                JButton boton = (JButton) value;
                System.out.println(value);
                if(boton.getName().equals("c")){
                    //EVENTOS MODIFICAR
                    // JOptionPane.showConfirmDialog(null, "Desea Cambiar el Registro?", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    try {
                        teclado = obtenerObj(row);
                        LecturaBBDD.cambiarItem(codeTemp,teclado.getId(),teclado.getMarca(),teclado.getPrecio(),teclado.getDcto(),teclado.isPrime(),teclado.getColor(),teclado.getTeclas(),teclado.getConector());
                        System.out.println("cambiado");
                        JOptionPane.showMessageDialog(null, "Item modificado correctamente", "Modificación de Item", 1);
                        tabla.removeAll();
                        llenarJTabla(tabla);
                        } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error" + e, "Error", 0);
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                }
                if(boton.getName().equals("b")){
                    JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    LecturaBBDD.borrarItem(codeTemp);
                    JOptionPane.showMessageDialog(null, "Item eliminado", "Eliminación de Item", 1);
                    System.out.println("ALGO SE HIZO");
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

    public boolean isCellEditable (int row, int column)
    {
        // Aquí devolvemos true o false según queramos que una celda
        // identificada por fila,columna (row,column), sea o no editable
        if (column == 0)
           return false;
        return true;
    }

}
    

