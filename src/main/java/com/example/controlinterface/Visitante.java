
// Se define el paquete donde se encuentra la clase
package com.example.controlinterface;

// Se define la clase Visitante
public class Visitante {

    // Se definen las variables privadas de la clase
    private String name;
    private int age;
    private Boolean affiliated;
    private String category;
    private String precio;
    private String id;

    // Se define el constructor de la clase con parámetros
    public Visitante(String name, int age, Boolean affiliated, String category, String precio, String id) {
        this.name = name;
        this.age = age;
        this.affiliated = affiliated;
        this.category = category;
        this.precio = precio;
        this.id = id;
    }

    // Métodos de acceso para las variables privadas

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAffiliated(Boolean affiliated) {
        this.affiliated = affiliated;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Boolean getAffiliated() {
        return affiliated;
    }

    public String getCategory() {
        return category;
    }

    public String getPrecio() {
        return precio;
    }

    public String getId() {
        return id;
    }
}
