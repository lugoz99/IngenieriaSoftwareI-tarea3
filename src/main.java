import control.ControlRegalos;
import entidades.Producto;

import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {
        ControlRegalos controlRegalos = new ControlRegalos();
        // CASO 1
        List<Producto>productos = controlRegalos.buscarRegaloporEdad(5,controlRegalos.getProductos(),90000.0);
        List<Double> preciosEnvio = controlRegalos.calcularPreciosEnvios(productos);
        controlRegalos.mostrarRegalos(preciosEnvio,productos);
        // CASO 2
        List<Producto>productos1 = controlRegalos.buscarRegaloporEdad(7,controlRegalos.getProductos(),50000.0);
        List<Double> preciosEnvio1 = controlRegalos.calcularPreciosEnvios(productos);
        controlRegalos.mostrarRegalos(preciosEnvio1,productos1);
        // CASO 3
        List<Producto>productos2 = controlRegalos.buscarRegaloporEdad(8,controlRegalos.getProductos(),50000.0);
        List<Double> preciosEnvio2 = controlRegalos.calcularPreciosEnvios(productos);
        controlRegalos.mostrarRegalos(preciosEnvio2,productos2);


    }
}
