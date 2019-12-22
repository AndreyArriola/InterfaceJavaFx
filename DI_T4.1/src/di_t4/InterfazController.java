/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di_t4;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JOptionPane;

/**
 *Clase donde se controla la interfaz
 * @author Andrey Arriola
 */
public class InterfazController implements Initializable {
    
    @FXML 
    private AnchorPane contenido;
    @FXML private MenuBar MenuBarra; 
    @FXML private Menu opcArchivo;
    @FXML private Menu opcEditar;
    @FXML private Menu opcAyuda;
    @FXML private TextField txtalumno;
    @FXML private ComboBox cmbomodulo;
   
    
    //Declaramos un ObservableList que va a tener todas las personas a visualizar en la tabla
    private final ObservableList <alumno> datosTabla = FXCollections.observableArrayList();
    
    @FXML private TextField txtnota;
    @FXML private TextField txtrecup;
    @FXML private Button btonGuardar;
    
    //declaramos una tabla de objetos alumno y sus columnas
    @FXML private TableView Tablaalumnos ;
    @FXML private TableColumn dniColum;
    @FXML private TableColumn ModColum;
    @FXML private TableColumn notaColum;
    @FXML private TableColumn recColum;
    
    
    /**
     * evento para cargar ayuda con swing
     * @param event 
     */
    @FXML
    private void Ayuda(ActionEvent event){
        try {
        javax.swing.JButton boton = new javax.swing.JButton();
        File fichero = new File ("src\\documentacion\\help_set.hs");
        URL hsURL = fichero.toURI().toURL();
        HelpSet helpset = new HelpSet (getClass().getClassLoader(), hsURL);
        HelpBroker hb = helpset.createHelpBroker();
        hb.enableHelpOnButton(boton, "GestorDeNotas", helpset);
        boton.doClick();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * //Menú opción Ayuda, Se crea un alert de información de la opción Ayuda-acerca de...
     * @param event
     */
    @FXML
    private void nombreInformacion(ActionEvent event) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Ventana Informativa");
        alert.setHeaderText("Acerca de DI_T6");
        alert.setContentText("Luis Andrey Arriola Vassiliev - DNI: 09109448E");
        alert.showAndWait();
       
    }
    
  /**
   * Menú opción salir de la aplicación 
   * @param event 
   */
    @FXML
    private void salirAplicacion(ActionEvent event){
        
        
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("Dialogo de eleccion");
        alert.setHeaderText("Está seguro que desea salir de la aplicación?...");
        alert.setContentText("");
        Optional<ButtonType> resultado = alert.showAndWait();
        ButtonType button = resultado.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK){
            System.exit(0);
        }
    }    
        
    
           
     /**
      * Boton para guardar los datos introducidos  
      * @param event 
      */           
     @FXML
     private void botonGuardar(ActionEvent event){
         //hacemos que el focus este en el textfield
          txtalumno.requestFocus();
          //creamos nuevoalumno para que almacene los datos introducidos  
          alumno nuevoalumno = new alumno(txtalumno.getText(), cmbomodulo.getSelectionModel().getSelectedItem().toString(),txtnota.getText(),txtrecup.getText()); 
          //hacemos un if para que tengan que estar todos los campos llenos
        if(txtalumno.getText().equals("")||txtnota.getText().equals(""))
          
         JOptionPane.showMessageDialog(null, "Rellene todos los campos en blanco", "Error", JOptionPane.ERROR_MESSAGE);
            
         
          
            try{
         //hacemos que el DNI tenga 8 numero y una letra al finakl
            int nota = Integer.parseInt(txtnota.getText());
         
            Integer recuperacion = null;
                        
               // Se crea la condicion para que se añada 8 numeros del 0 al 9 y 1 letra de la A a la Z
            Pattern pat = Pattern.compile("[0-9]{8}[A-Z]{1}");
            Matcher mat = pat.matcher(txtalumno.getText());
          
             //hacemos un if para que de un error si no cumple los 9 digitos
            if(txtalumno.getText().length()!=9 || !mat.matches()){
           
           JOptionPane.showMessageDialog(null, "El número de DNI es incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
           
           posicion();
           return;        
        }         
            //hacemos que si la nota es aprobada que que se añada
            if(nota > 4 && nota < 11 ){
                if(txtrecup.getText().isEmpty()){
                datosTabla.add(nuevoalumno);
                posicion();
                }else {
                JOptionPane.showMessageDialog(null, "El campor de recuperacion debe de estar vacio, porque la nota es mayor que 5", "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);
                
                }
            
            }
            
            //Si la nota es desaprobada, habilitamos recuperacion
            else if (nota < 5){
                  txtrecup.requestFocus();
                if (!txtrecup.getText().isEmpty()){
                recuperacion = Integer.parseInt(txtrecup.getText());
            //si la nota de recuperacion es mayor, que mueste un error
                    if(recuperacion >5){
            JOptionPane.showMessageDialog(null, "El campo de recuperacion no puede ser mayor que 5", "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);
            txtrecup.setText("");
          // Caso contrario que se añada a la lista y se limpie
                    }else{
                    datosTabla.add(nuevoalumno);
                    posicion();
                    txtrecup.setDisable(true);
                    txtrecup.setPromptText("");
                    }
                }else{
             JOptionPane.showMessageDialog(null, "El campo de recuperacion no puede estar vacio", "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE); 
             txtrecup.requestFocus();
                        }
                
            }else {
            JOptionPane.showMessageDialog(null, "La nota debe de ser del 1 al 10", "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);
            txtnota.setText("");  
            txtnota.requestFocus();
            }
            
            }catch(NumberFormatException ex){
             JOptionPane.showMessageDialog(null, "El valor del campo nota debe de ser un numero del 0 al 10", "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            catch (Exception e){
           JOptionPane.showMessageDialog(null, "Se ha producido un error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            posicion();
            }
        
     }

     
    
    /**
     * Evento para poner los campos a cero
     * @param event 
     */
    @FXML
    private void campoPosicion(ActionEvent event){
         
         posicion();
                 
        }
    /**
     * Metodo para limpiar los campos 
     */
     private void posicion(){
         
         
         
        if(txtalumno.getText().isEmpty() && txtnota.getText().isEmpty() && txtrecup.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese los valores del nuevo Alumno", "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);
            
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Dialogo de elección");
            alert.setHeaderText("¿Desea limpiar los campos del formulario?...");
            alert.setContentText("");
            Optional<ButtonType> resultado = alert.showAndWait();
            ButtonType button = resultado.orElse(ButtonType.CANCEL);
            
            if(button==ButtonType.OK){
                borrar();
                txtrecup.setDisable(true);
                txtrecup.setPromptText("");
                txtalumno.requestFocus();
            }
                
      
         } 
     
     }
      /**
       *  metodo para que borre los datos
       */
      private void borrar(){
         
        txtalumno.setText("");
        cmbomodulo.getSelectionModel().select(0);
        txtnota.setText("");
        txtrecup.setText("");
        txtalumno.setText("");
        
        
      }
 
    
   /**
    * Metodo que inicializa el programa
    * @param url
    * @param rb 
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // aqui es donde el campo de recuperacion se habilita cuando la nota es menor que 5
        txtrecup.setDisable(true);
        txtnota.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
       
                try{
                    if(!txtnota.getText().isEmpty()) {
                        int nota = Integer.parseInt(txtnota.getText());
                     
                         if(nota <5){
                           txtrecup.setDisable(false);   
                           txtrecup.setPromptText("numero del 0 al 5");
                           
                         }
                         else{
                         txtrecup.setText("");
                         txtrecup.setDisable(true);  
                         txtrecup.setPromptText("");
                         
                         }
                         
                    }
               }catch (NumberFormatException ex){
                  JOptionPane.showMessageDialog(null, "El valor del campo nota debe de ser un numero del 0 al 10", "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);                  
                        
                        }
                    
            }
                }
        );
         //Creamos un arrayList de tipo ObservableList y añadimos los elementos de combobox
        ObservableList <String> mods = FXCollections.observableArrayList("DI",
        "AD","SGE","PMDM","PSP","EIE");
        
        cmbomodulo.setItems(mods);
        cmbomodulo.getSelectionModel().select(0);
        
        //Asociamos las columnas con la tabla
        dniColum.setCellValueFactory(new PropertyValueFactory<alumno,String>("dni"));
        ModColum.setCellValueFactory(new PropertyValueFactory <alumno,String>("modulo"));
        notaColum.setCellValueFactory(new PropertyValueFactory<alumno,String>("nota"));
        recColum.setCellValueFactory(new PropertyValueFactory<alumno,String>("recuperacion"));
        
        Tablaalumnos.setItems(datosTabla);
    }    
    
}
