package clases;


public class Producto {

    public static String[] tipo_producto = {"Bebida", "Postre", "Sandwich","Comida"};
    private int id;
    private String nombre;
    private int precio;
    private String descrp;
    private int tipo;

    public Producto(int id, String nombre, int precio, String descrp, int tipo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descrp = descrp;
        this.tipo = tipo;
    }

    public static String[] getTipo_producto() {
        return tipo_producto;
    }

    public static void setTipo_producto(String[] tipo_producto) {
        Producto.tipo_producto = tipo_producto;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
