package control;

import java.io.FileReader;
import java.util.List;

import entidades.Producto;
import entidades.Proveedor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;





public class ControlDatos {

    public ControlDatos(){

    }




    public  void readAndFillProductos(String filePath, List<Producto> productos) {
        try {
            // Crear un objeto JSONParser
            JSONParser parser = new JSONParser();

            // Leer el archivo JSON
            FileReader fileReader = new FileReader(filePath);
            Object obj = parser.parse(fileReader);

            // Convertir el objeto JSON en un objeto JSONArray
            JSONArray jsonArray = (JSONArray) obj;

            // Iterar a través del JSONArray y obtener los datos
            for (Object producto : jsonArray) {
                JSONObject jsonProducto = (JSONObject) producto;
                String nombre = (String) jsonProducto.get("nombre");
                int edad = Integer.parseInt(jsonProducto.get("edad").toString());
                double precio = Double.parseDouble(jsonProducto.get("precio").toString());
                String proveedor1 = (String) jsonProducto.get("proveedor");
                Proveedor proveedor = new Proveedor(proveedor1);
                // Crear un objeto Producto y agregarlo a la lista
                Producto prod = new Producto(nombre, edad, precio, proveedor);
                productos.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public  void readAndFillProveedores(String filePath, List<Proveedor> proveedores) {
        try {
            // Crear un objeto JSONParser
            JSONParser parser = new JSONParser();

            // Leer el archivo JSON
            FileReader fileReader = new FileReader(filePath);
            Object obj = parser.parse(fileReader);

            // Convertir el objeto JSON en un objeto JSONArray
            JSONArray jsonArray = (JSONArray) obj;

            // Iterar a través del JSONArray y obtener los datos
            for (Object proveedor : jsonArray) {
                JSONObject jsonProveedor = (JSONObject) proveedor;
                String nombre = (String) jsonProveedor.get("nombre");
                double precioEnvio = Double.parseDouble(jsonProveedor.get("precioEnvio").toString());
                Proveedor proveedor1 = new Proveedor(nombre,precioEnvio );
                proveedores.add(proveedor1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
