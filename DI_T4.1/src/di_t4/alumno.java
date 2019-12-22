/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di_t4;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *Clase de alumno
 * @author Andrey Arriola
 */
public class alumno {
   private final SimpleStringProperty dni;
   private final SimpleStringProperty modulo;
   private final SimpleStringProperty nota;
   private final SimpleStringProperty recuperacion;

    @SuppressWarnings("empty-statement")
    alumno(String dni, String modulo, String nota, String recuperacion) {
        this.dni =  new SimpleStringProperty(dni); ;
        this.modulo =  new SimpleStringProperty(modulo);
        this.nota =  new SimpleStringProperty(nota);
        this.recuperacion =  new SimpleStringProperty(recuperacion);
    }

    

    
    
    
/**
 * metodo get de DNI
 * @return devuelve el DNI
 */
    public String getDni() {
        return dni.get();
    }
    /**
     * metodo se de DNI
     * @param dni 
     */
    public void setDni(String dni){
        this.dni.set(dni);
    
    }

    public String getModulo() {
        return modulo.get();
    }
    
     public void setModulo(String modulo){
        this.modulo.set(modulo);
    
    }

    public String getNota() {
        return nota.get();
    }
    
     public void setNota(String nota){
        this.nota.set(nota);
    
    }

    public String getRecuperacion() {
        return recuperacion.get();
    }
    
    public void setRecuperacion(String recuperacion){
        this.recuperacion.set(recuperacion);
    
    }
    
}

