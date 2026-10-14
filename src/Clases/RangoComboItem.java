// Archivo: Clases/RangoComboItem.java
package Clases;

public class RangoComboItem {
    private String id;
    private String nombre; // Esta variable almacena el nombre del rango

    public RangoComboItem(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre; // Muestra el nombre en el JComboBox
    }

    public String getId() {
        return id; // Método para obtener el ID (ya lo tenías)
    }
    
    // <<< ESTE ES EL MÉTODO QUE FALTA: >>>
    /**
     * Devuelve el nombre visible del rango.
     * @return El nombre del rango.
     */
    public String getNombre() { 
        return nombre;
    }
}