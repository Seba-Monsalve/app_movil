package clases;

public class Usuario {
    private int id = -1;
    private String nombre;
    private String apellido;
    private String rut;
    private String contrasena;
    private boolean habilitado;

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Usuario(String nombre, String apellido, String rut, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.contrasena = contrasena;
    }
    public Usuario(int id,String nombre, String apellido, String rut, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.contrasena = contrasena;
    }
    public Usuario(int id,String nombre,String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRut() {
        return rut;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Id: " +
                getId() +
                "\nNombre: " +
                getNombre() +
                "\nPass:  " +
                getContrasena() +
                "\nRut:  " +
                getRut() +
                "\nHabilitado: "+
                isHabilitado();

    }
}
