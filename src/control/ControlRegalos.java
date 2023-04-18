package control;

import entidades.Producto;
import entidades.Proveedor;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControlRegalos {

    private List<Producto> regalos;
    private List<Producto> productos;
    private List<Proveedor> proveedores;
    private List<Producto>productosConEdad5;

    public List<Producto> getProductosConEdad5() {
        return productosConEdad5;
    }

    public ControlRegalos(){
        getProductos();
        getProveedores();

    }

    public void mostrarRegalos(List<Double>precios, List<Producto>productos){
        System.out.println("==============================================================================");
        if (precios.size() == 0 || productos.size() == 0) {
            System.out.println("No se tiene productos para esa edad o precio");
        }else{
            System.out.printf("%-15s %-15s %-15s %-15s\n", "Nombre", "Precio Base", "Precio Envío", "Precio Total");
            for (int i = 0; i < productos.size(); i++) {
                Producto producto = productos.get(i);
                double precio = precios.get(i);
                double precioEnvio = precios.get(i);
                double precioTotal = producto.getPrecio() + precioEnvio;
                System.out.printf("%-15s $%-15.2f $%-15.2f $%-15.2f\n", producto.getNombre(), producto.getPrecio(), precioEnvio, precioTotal);
            }
        }

    }



    public List<Producto> getProductos(){
        productos = new ArrayList<>();
        ControlDatos datos = new ControlDatos();
        String filePath = "src/data/productos.json"; // Ruta del archivo
        datos.readAndFillProductos(filePath,productos);
        return productos;


    }


    public List<Proveedor> getProveedores(){
        proveedores = new ArrayList<>();
        ControlDatos datos = new ControlDatos();
        String filePath = "src/data/provedores.json"; // Ruta del archivo
        datos.readAndFillProveedores(filePath,proveedores);
        return proveedores;


    }



    public void imprimirProductos(List<Producto> productos) {
        System.out.println("Lista de Productos:");
        for (Producto producto : getProductos()) {
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Edad: " + producto.getEdad());
            System.out.println("Precio: " + producto.getPrecio());
            System.out.println("Proveedor: " + producto.getProveedor());
            System.out.println("-------------------------");
        }
    }


    public void imprimirProveedores(List<Proveedor> proveedores) {
        System.out.println("Lista de Proveedores:");
        for (Proveedor proveedor : getProveedores()) {
            System.out.println("Nombre: " + proveedor.getNombre());
            System.out.println("PrecioEnvio: " + proveedor.getPrecioEnvio());
            System.out.println("-------------------------");
        }
    }


    public List<Producto> buscarRegaloporEdad(int edad, List<Producto>productos,double precioMaximo){
        return productos.stream()
                .filter(producto -> producto.getEdad() == edad && producto.getPrecio() < precioMaximo)
                .collect(Collectors.toList());

    }


    public List<Double> calcularPreciosEnvios(List<Producto>productos){
        Map<String, Double> preciosEnvioPorProveedor = new HashMap<>();
        List<Double> preciosEnvio = new ArrayList<>();
        for (Proveedor proveedor : proveedores) {
            preciosEnvioPorProveedor.put(proveedor.getNombre(), proveedor.getPrecioEnvio());
        }
        for (Producto producto : productos) {
            String nombreProveedor = producto.getProveedor().getNombre();
                double precioEnvio = preciosEnvioPorProveedor.get(nombreProveedor);
                preciosEnvio.add(precioEnvio);

        }
        return preciosEnvio;
    }











}



