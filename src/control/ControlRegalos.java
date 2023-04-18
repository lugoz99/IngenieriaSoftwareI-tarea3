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

    private List<Producto> productos;
    private List<Proveedor> proveedores;
    private final String RUTA_ARCHIVO_PROVEEDOR = "src/data/provedores.json";
    private final String RUTA_ARCHIVO_PRODUCTO = "src/data/productos.json";


    public ControlRegalos(){
        getProveedores();
        getProductos();
    }


    /**
     * Muestra información sobre los productos y los precios de envío en la consola.
     *
     * @param precios     Lista de precios de envío correspondientes a los productos.
     * @param productos   Lista de productos para los cuales se mostrará la información.
     */
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


    /**
     * @return una lista de productos o regalos
     */
    public List<Producto> getProductos(){
        productos = new ArrayList<>();
        ControlDatos datos = new ControlDatos();
        datos.readAndFillProductos(RUTA_ARCHIVO_PRODUCTO,productos);
        return productos;


    }


    /**
     *
     * @return una lista de proveedores
     */
    public List<Proveedor> getProveedores(){
        proveedores = new ArrayList<>();
        ControlDatos datos = new ControlDatos();
        datos.readAndFillProveedores(RUTA_ARCHIVO_PROVEEDOR,proveedores);
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


    /**
     * Busca productos en la lista de productos basándose en la edad y el precio máximo.
     *
     * @param edad          La edad a buscar en los productos.
     * @param productos     La lista de productos en la que se realizará la búsqueda.
     * @param precioMaximo  El precio máximo permitido para los productos buscados.
     * @return              Una lista de productos que cumplen con los criterios de búsqueda.
     */
    public List<Producto> buscarRegaloporEdad(int edad, List<Producto>productos,double precioMaximo){
        return productos.stream()
                .filter(producto -> producto.getEdad() == edad && producto.getPrecio() < precioMaximo)
                .collect(Collectors.toList());

    }


    /**
     * @param productos
     * @return
     * se retorna una lista de precio de envio de los proveedores
     * para asociar los precios de envío con los nombres de proveedores.
     * Se crea una lista vacía de Double llamada preciosEnvio para almacenar
     * los precios de envío calculados. Se itera
     * a través de la lista de proveedores y se agrega al mapa
     */
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



