package formularios;

/* Importamos las componentes Swing, así como el paquete con los interfaces para los eventos */
import javax.swing.*;

import conexion.Conexion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* La clase JFrame encapsula el concepto de una ventana, para implementar una aplicación que muestre una ventana debemos plantear una clase que herede de la clase JFrame e implemente a a ActionListener para el evento del botón*/
public class Formulario extends JFrame implements ActionListener {

  /* Definimos variables. */
  public JTextField txt_idF, txt_1, txt_2, txt_3, txt_4, txt_5;
  public static JTextArea txt_id, txt_marca, txt_precio, txt_dcto, txt_tipo, txt_color, txt_teclas, txt_conector, txt_envio, txt_pvp, txt_code;
  public JLabel lb_id, lb_marca, lb_precio, lb_dcto, lb_tipo, lb_color, lb_teclas, lb_conector, lb_envio, lb_pvp, lb_code;
  public static JLabel lb_temp;
  public JButton btForward, btNext, btAdd, btBorrar, btCambiar, btBuscar, btOk, btCancel, btVerBB, btLimpiar;
  public JPanel p;
  // Conexion co = new Conexion();
	// co.conectarMySQL();

  /*
   * En el constructor de la clase llamamos al método heredado de la clase JFrame
   * llamado setLayout y le pasamos como parámetro un valor null, con esto estamos
   * informándole a la clase JFrame que utilizaremos posicionamiento absoluto para
   * los controles visuales dentro del JFrame
   */
  public Formulario() {

    /* Configuración del JFrame */
    setLayout(null);
    setBounds(0, 0, 600, 600);
    setTitle("Music Store");
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    int y = 90;
    int yGap = 40;

    lb_code = new JLabel("Seleccione Codigo:");
    lb_code.setBounds(20, y-yGap, 180, 30);
    add(lb_code);

    /* Etiqueta de usuario */
    lb_id = new JLabel("Id:");
    lb_id.setBounds(20, y+(0 * yGap), 100, 30);
    add(lb_id);

    lb_marca = new JLabel("Marca:");
    lb_marca.setBounds(20, y+(1 * yGap), 100, 30);
    add(lb_marca);

    lb_precio = new JLabel("Precio:");
    lb_precio.setBounds(20, y+(2 * yGap), 100, 30);
    add(lb_precio);

    lb_dcto = new JLabel("Descuento:");
    lb_dcto.setBounds(20, y+(3 * yGap), 100, 30);
    add(lb_dcto);

    lb_tipo = new JLabel("Prime:");
    lb_tipo.setBounds(20, y+(4 * yGap), 100, 30);
    add(lb_tipo);

    lb_color = new JLabel("Color:");
    lb_color.setBounds(20, y+(5 * yGap), 100, 30);
    add(lb_color);

    lb_teclas = new JLabel("Teclas:");
    lb_teclas.setBounds(20, y+(6 * yGap), 100, 30);
    add(lb_teclas);

    lb_conector = new JLabel("Conector:");
    lb_conector.setBounds(20, y+(7 * yGap), 100, 30);
    add(lb_conector);

    lb_envio = new JLabel("Envío:");
    lb_envio.setBounds(20, y+(8 * yGap), 100, 30);
    add(lb_envio);

    lb_pvp = new JLabel("PVP:");
    lb_pvp.setBounds(20, y+(9 * yGap), 100, 30);
    add(lb_pvp);

    lb_temp = new JLabel("dato_temp");
    lb_temp.setBounds(20, y+(10 * yGap), 100, 30);
    add(lb_temp);




    /* Campo de texto */
    // txt_idF=new JTextField();
    // txt_idF.setBounds(100,15,150,20);
    // add(txt_idF);

    txt_code = new JTextArea();
    txt_code.setBounds(160, 55, 80, 20);
    add(txt_code);

    txt_id = new JTextArea();
    txt_id.setBounds(100, 95, 150, 20);
    txt_id.setFocusable(false);
    add(txt_id);

    txt_marca = new JTextArea();
    txt_marca.setBounds(100, 135, 150, 20);
    txt_marca.setFocusable(false);
    add(txt_marca);

    txt_precio = new JTextArea();
    txt_precio.setBounds(100, 175, 150, 20);
    txt_precio.setFocusable(false);
    add(txt_precio);

    txt_dcto = new JTextArea();
    txt_dcto.setBounds(100, 215, 150, 20);
    txt_dcto.setFocusable(false);
    add(txt_dcto);

    txt_tipo = new JTextArea();
    txt_tipo.setBounds(100, 255, 150, 20);
    txt_tipo.setFocusable(false);
    add(txt_tipo);

    txt_color = new JTextArea();
    txt_color.setBounds(100, 295, 150, 20);
    txt_color.setFocusable(false);
    add(txt_color);

    txt_teclas = new JTextArea();
    txt_teclas.setBounds(100, 335, 150, 20);
    txt_teclas.setFocusable(false);
    add(txt_teclas);

    txt_conector = new JTextArea();
    txt_conector.setBounds(100, 375, 150, 20);
    txt_conector.setFocusable(false);
    add(txt_conector);

    txt_envio = new JTextArea();
    txt_envio.setBounds(100, 415, 150, 20);
    txt_envio.setFocusable(false);
    add(txt_envio);

    txt_pvp = new JTextArea();
    txt_pvp.setBounds(100, 455, 150, 20);
    txt_pvp.setFocusable(false);
    add(txt_pvp);

 

    /* Botones */

    btForward = new JButton("Anterior");
    btForward.setBounds(300, 60, 100, 30);
    add(btForward);

    btNext = new JButton("Siguiente");
    btNext.setBounds(300, 100, 100, 30);
    add(btNext);

    btAdd = new JButton("Crear");
    btAdd.setBounds(300, 180, 100, 30);
    add(btAdd);

    btBorrar = new JButton("Borrar");
    btBorrar.setBounds(300, 220, 100, 30);
    btBorrar.setVisible(false);
    add(btBorrar);

    btCambiar = new JButton("Cambiar");
    btCambiar.setBounds(300, 260, 100, 30);
    btCambiar.setVisible(false);
    add(btCambiar);

    btBuscar = new JButton("Buscar");
    btBuscar.setBounds(300, 300, 100, 30);
    add(btBuscar);

    btVerBB = new JButton("Ver BBDD");
    btVerBB.setBounds(300, 340, 100, 30);
    btVerBB.setBackground(Color.GREEN);
    btVerBB.setForeground(Color.white);
    btVerBB.setOpaque(true);
    // btVerBB.setFont(new Font("MONOSPACED",0,16));
    add(btVerBB);

    btLimpiar = new JButton("Limpiar");
    btLimpiar.setBounds(300, 380, 100, 30);
    btLimpiar.setBackground(Color.cyan);
    btLimpiar.setForeground(Color.white);
    btLimpiar.setOpaque(true);
    // btVerBB.setFont(new Font("MONOSPACED",0,16));
    add(btLimpiar);

    btOk = new JButton("Aceptar");
    btOk.setBounds(300, 440, 100, 30);
    btOk.setVisible(false);
    add(btOk);

    
    

    btCancel = new JButton("Cancelar");
    btCancel.setBounds(300, 480, 100, 30);
    btCancel.setVisible(false);
    // btSalir.setBackground(Color.red);
    // btSalir.setForeground(Color.white);
    btCancel.setOpaque(true);
    // btCancel.setFont(new Font("MONOSPACED",0,16));
    add(btCancel);



    /* Inicializo escuchador del botón */
    btOk.addActionListener(this);
    btCancel.addActionListener(this);
    btBuscar.addActionListener(this);
    btCambiar.addActionListener(this);
    btAdd.addActionListener(this);
    btBorrar.addActionListener(this);
    btVerBB.addActionListener(this);
    btLimpiar.addActionListener(this);
    btNext.addActionListener(this);
  


  }



  /* Método que implementa la acción del botón */

  public void actionPerformed(ActionEvent e) {
    
    if (e.getSource() == btOk) {
      if (lb_temp.getText().equals("crear")){
        Formulario.crearItem();
      }

      if (lb_temp.getText().equals("cambiar")){
        Formulario.cambiarItem();
      }

      if (lb_temp.getText().equals("borrar")){
        try {
          VerBBDD.borrarItem(txt_code.getText());
        } catch (Exception e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
      
    }

    if (e.getSource() == btBorrar) {
      lb_temp.setText("borrar");
      btOk.setVisible(true);
      btCancel.setVisible(true);
    }

    if (e.getSource() == btAdd) {
      lb_temp.setText("crear");
      txt_code.setVisible(false);
      lb_code.setVisible(false);
      btBorrar.setVisible(false);
      btCambiar.setVisible(false);
      btBuscar.setVisible(false);
      btOk.setVisible(true);
      btCancel.setVisible(true);
      Formulario.limpiarTXT();
      Formulario.habilitarTXT();
    }

    if (e.getSource() == btCambiar) {
      btOk.setVisible(true);
      btCancel.setVisible(true);
      lb_temp.setText("cambiar");
      Formulario.habilitarTXT();
    }

    if (e.getSource() == btBuscar) {
       Formulario.buscarItem();
       Formulario.desahabilitarTXT();
       btBorrar.setVisible(true);
       btCambiar.setVisible(true);
    }

    if (e.getSource() == btLimpiar) {
      Formulario.limpiarTXT();
      btBorrar.setVisible(false);
      btCambiar.setVisible(false);
      btOk.setVisible(false);
      btCancel.setVisible(false);
   }

   if (e.getSource() == btNext) {
    Formulario.verItem2();    
 }
   
    if (e.getSource() == btVerBB) {

        try {


          VerBBDD.listar();
          Tabla tabla = new Tabla();
          tabla.setLocationRelativeTo(null);
          tabla.setVisible(true);
          Formulario.verItem();      
          
        } catch (ClassNotFoundException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        } catch (Exception e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
    }


    if (e.getSource() == btCancel) {
      txt_code.setVisible(true);
      lb_code.setVisible(true);
      btBorrar.setVisible(false);
      btCambiar.setVisible(false);
      btBuscar.setVisible(true);
      Formulario.limpiarTXT();
      Formulario.desahabilitarTXT();
      btOk.setVisible(false);
      btCancel.setVisible(false);
    }

  }

 

  public static void limpiarTXT(){
    txt_code.setText(null);
    txt_id.setText(null);
    txt_marca.setText(null);
    txt_precio.setText(null);
    txt_dcto.setText(null);
    txt_tipo.setText(null);
    txt_color.setText(null);
    txt_teclas.setText(null);
    txt_conector.setText(null);
    txt_envio.setText(null);
    txt_pvp.setText(null);
    // lb_temp.setText("dato_temp");
  }

  public static void habilitarTXT(){
    txt_id.setFocusable(true);
    txt_marca.setFocusable(true);
    txt_precio.setFocusable(true);
    txt_dcto.setFocusable(true);
    txt_tipo.setFocusable(true);
    txt_color.setFocusable(true);
    txt_teclas.setFocusable(true);
    txt_conector.setFocusable(true);
  }

  public static void desahabilitarTXT(){
    txt_id.setFocusable(false);
    txt_marca.setFocusable(false);
    txt_precio.setFocusable(false);
    txt_dcto.setFocusable(false);
    txt_tipo.setFocusable(false);
    txt_color.setFocusable(false);
    txt_teclas.setFocusable(false);
    txt_conector.setFocusable(false);
  }

  private static void buscarItem() {
    try {
      txt_id.setText(VerBBDD.buscarItem(txt_code.getText()).getId());
      txt_marca.setText(VerBBDD.buscarItem(txt_code.getText()).getMarca());
      txt_precio.setText(Double.toString(VerBBDD.buscarItem(txt_code.getText()).getPrecio()));
      txt_dcto.setText(String.valueOf(VerBBDD.buscarItem(txt_code.getText()).getDcto()));
      txt_tipo.setText(VerBBDD.buscarItem(txt_code.getText()).isPrime());
      txt_color.setText(VerBBDD.buscarItem(txt_code.getText()).getColor());
      txt_teclas.setText(Integer.toString(VerBBDD.buscarItem(txt_code.getText()).getTeclas()));
      txt_conector.setText(VerBBDD.buscarItem(txt_code.getText()).getConector());
      txt_envio.setText(VerBBDD.buscarItem(txt_code.getText()).getEnvio());
      txt_pvp.setText(Double.toString(VerBBDD.buscarItem(txt_code.getText()).getPrecioVenta()));
     
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }


  private static void verItem() {
    try {
      
      txt_id.setText(VerBBDD.primerItem().getId());
      txt_marca.setText(VerBBDD.primerItem().getMarca());
      txt_precio.setText(Double.toString(VerBBDD.primerItem().getPrecio()));
      txt_dcto.setText(Double.toString(VerBBDD.primerItem().getDcto()));
      txt_tipo.setText(VerBBDD.primerItem().isPrime());
      txt_color.setText(VerBBDD.primerItem().getColor());
      txt_teclas.setText(Integer.toString(VerBBDD.primerItem().getTeclas()));
      txt_conector.setText(VerBBDD.primerItem().getConector());
      txt_envio.setText(VerBBDD.primerItem().getEnvio());
      txt_pvp.setText(Double.toString(VerBBDD.primerItem().getPrecioVenta()));

    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }




  private static void verItem2() {
    try {
      txt_id.setText(VerBBDD.primer2Item().getId());
      txt_marca.setText(VerBBDD.primer2Item().getMarca());
      txt_precio.setText(Double.toString(VerBBDD.primer2Item().getPrecio()));
      txt_dcto.setText(Double.toString(VerBBDD.primer2Item().getDcto()));
      txt_tipo.setText(VerBBDD.primer2Item().isPrime());
      txt_color.setText(VerBBDD.primer2Item().getColor());
      txt_teclas.setText(Integer.toString(VerBBDD.primer2Item().getTeclas()));
      txt_conector.setText(VerBBDD.primer2Item().getConector());
      txt_envio.setText(VerBBDD.primer2Item().getEnvio());
      txt_pvp.setText(Double.toString(VerBBDD.primer2Item().getPrecioVenta()));

    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  public static void cambiarItem(){
    try {
      VerBBDD.cambiarItem(
        txt_code.getText(), txt_id.getText(), txt_marca.getText(),  Double.parseDouble(txt_precio.getText()), 
        Double.parseDouble(txt_dcto.getText()), txt_tipo.getText(), txt_color.getText(),  Integer.parseInt(txt_teclas.getText()),
        txt_conector.getText(), txt_envio.getText(),  Double.parseDouble(txt_pvp.getText()));
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  public static void crearItem(){
    try {
      Double total = Double.parseDouble(txt_precio.getText()) - ((Double.parseDouble(txt_precio.getText()) * Double.parseDouble(txt_dcto.getText()))/100);

      if(txt_tipo.getText().equals("PRIME")){
        txt_envio.setText("GRATIS");
        txt_pvp.setText(String.valueOf(total));
      }else{
        txt_envio.setText("+15€");
        txt_pvp.setText(String.valueOf(total+15));
      }


      VerBBDD.crearItem(
        txt_id.getText(), txt_marca.getText(),  Double.parseDouble(txt_precio.getText()), 
        Double.parseDouble(txt_dcto.getText()), txt_tipo.getText(), txt_color.getText(),  Integer.parseInt(txt_teclas.getText()),
        txt_conector.getText());

        
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

}
