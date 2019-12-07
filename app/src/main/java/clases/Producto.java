package clases;


public class Producto {

    private int id;
    private String nombre;
    private int precio;
    private String descrp;
    private int categoria;

    public Producto(int id, String nombre, int precio, int categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descrp = descrp;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescrp() {
        return descrp;
    }

    public void setDescrp(String descrp) {
        this.descrp = descrp;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
