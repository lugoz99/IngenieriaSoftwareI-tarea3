package entidades;

public class Producto {

    private String nombre;
    private int edad;
    private double precio;
    private Proveedor proveedor;

    public Producto(String nombre, int edad, double precio,Proveedor proveedor){
        this.nombre = nombre;
        this.edad = edad;
        this.precio = precio;
        this.proveedor = proveedor;
    }


    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", precio=" + precio +
                ", proveedor='" + proveedor + '\'' +
                '}';
    }
}

