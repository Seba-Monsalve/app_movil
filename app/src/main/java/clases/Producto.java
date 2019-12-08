package clases;




public class Producto {

    private int id;
    private String nombre;
    private int stock;
    private int precio;


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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Producto(int id, String nombre, int  stock, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }


    @Override
    public String toString(){
        return "ID: "+ this.getId()
                + "\nNombre: "+this.getNombre()
                + "\nStock: " + this.getStock()
                + "\nPrecio: "+ this.precio;
    }

}
