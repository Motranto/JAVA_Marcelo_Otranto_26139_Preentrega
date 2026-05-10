package com.techlab.articulo;

// Importamos ArrayList para la lista
import java.util.ArrayList;

// Importamos Scanner para leer datos del teclado.
import java.util.Scanner;

// Importamos la clase Articulo
import com.techlab.articulo.model.Articulo;

/* CLASE APP 
 * Clase principal de la aplicacion
*/

public class App {

    public static void main(String[] args) {

        // Creamos un Scanner para leer los datos desde la consola.
        Scanner sc = new Scanner(System.in);

        // ArrayList para guardar objeto de tipo Articulo
        ArrayList<Articulo> articulos = new ArrayList<>();

        // Variable opción del menú.
        int opcion;

        // Repetimos el menú hasta que el usuario elija 0 - salir.
        do {
            System.out.println("\n==========================================");
            System.out.println("   SISTEMA CRUD DE ARTICULOS   ");
            System.out.println("==========================================");
            System.out.println("1 - Crear artículo");
            System.out.println("2 - Consultar artículo por SKU");
            System.out.println("3 - Consultar artículo por EAN");
            System.out.println("4 - Modificar artículo por SKU");
            System.out.println("5 - Eliminar artículo por SKU");
            System.out.println("6 - Listar todos los artículos");
            System.out.println("------------------------------------------");
            System.out.println("0 - Salir");
            System.out.println("==========================================");

            opcion = leerEntero(sc, "Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    ingresarArticulo(sc, articulos);
                    break;
                case 2:
                    consultarArticuloSku(sc, articulos);
                    break;
                case 3:
                    consultarArticuloEan(sc, articulos);
                    break;
                case 4:
                    modificarArticuloSku(sc, articulos);
                    break;
                case 5:
                    eliminarArticuloSku(sc, articulos);
                    break;
                case 6:
                    listarArticulos(articulos);
                    break;
                case 0:
                    System.out.println("\nSaliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nError: la opción ingresada no es válida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    /*
     * MÉTODO: ingresarArticulo
     * ---------------------------------------------------------
     * Pedimos los datos del artículo:
     * SKU
     * EAN
     * NOMBRE
     * DESCRIPCION
     * PRECIO
     * CATEGORIA  (en modo texto)
     * Luego construimos un objeto Articulo y lo guardamos en la lista.
     */
    public static void ingresarArticulo(Scanner sc, ArrayList<Articulo> articulos) {

        System.out.println("\n--- INGRESAR ARTÍCULO ---");

        int sku           = leerEntero           (sc, "Ingrese el código SKU del artículo : ");
        // Validamos que no exista otro artículo con el mismo SKU.
        if (buscarArticuloPorSku(articulos, sku) != null) {
            mostrarMensajeError("Artículo existente con ese SKU.");
            return;
        }

        int ean           = leerEntero           (sc, "Ingrese el código EAN del artículo : ");
        // Validamos que no exista otro artículo con el mismo EAN.
        if (buscarArticuloPorEan(articulos, ean) != null) {
            mostrarMensajeError("Artículo existente con ese EAN.");
            return;
        }

        String nombre      = leerTextoNoVacio    (sc, "Ingrese el nombre del artículo     : ");
        String descripcion = leerTextoNoVacio    (sc, "Ingrese la descripción del artículo: ");
        double precio      = leerDoubleNoNegativo(sc, "Ingrese el precio del artículo     : ");
        String categoria   = leerTextoNoVacio    (sc, "Ingrese la categoría del artículo  : ");

        String nombreMayuscula = nombre.trim().toUpperCase();
        String categoriaMinuscula = categoria.trim().toLowerCase();
        // Creamos un objeto Articulo usando el constructor.
        Articulo articulo = new Articulo(sku, ean, nombreMayuscula, descripcion, precio, categoriaMinuscula);

        // Guardamos el objeto en la lista.
        articulos.add(articulo);

        System.out.println("Artículo ingresado correctamente.");
    }

    /*
     * MÉTODO: consultarArticuloSku
     * ---------------------------------------------------------
     * Busca un artículo por SKU.
     */
    public static void consultarArticuloSku(Scanner sc, ArrayList<Articulo> articulos) {

        System.out.println("\n--- CONSULTAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            mostrarMensajeError("No hay artículos cargados.");
            return;
        }

        int sku = leerEntero(sc, "Ingrese el SKU del artículo a consultar: ");

        Articulo articulo = buscarArticuloPorSku(articulos, sku);

        if (articulo == null) {
            mostrarMensajeError("Artículo inexistente con ese SKU.");
        } else {
            System.out.println("Artículo encontrado:");
            System.out.println(articulo);
        }
    }

    /*
     * MÉTODO: consultarArticuloEan
     * ---------------------------------------------------------
     * Busca un artículo por EAN.
     */
    public static void consultarArticuloEan(Scanner sc, ArrayList<Articulo> articulos) {

        System.out.println("\n--- CONSULTAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            mostrarMensajeError("No hay artículos cargados.");
            return;
        }

        int ean = leerEntero(sc, "Ingrese el EAN del artículo a consultar: ");

        Articulo articulo = buscarArticuloPorEan(articulos, ean);

        if (articulo == null) {
            mostrarMensajeError("Artículo inexistente con ese EAN.");
        } else {
            System.out.println("Artículo encontrado:");
            System.out.println(articulo);
        }
    }

    /*
     * MÉTODO: modificarArticuloSku
     * ---------------------------------------------------------
     * Permite cambiar los datos de un artículo existente ingresando el SKU.
     */
    public static void modificarArticuloSku(Scanner sc, ArrayList<Articulo> articulos) {

        System.out.println("\n--- MODIFICAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            mostrarMensajeError("No hay artículos cargados.");
            return;
        }

        int sku                 = leerEntero           (sc, "Ingrese el código SKU a modificar  : ");

        // Buscamos y validamos que exista el SKU.  Cargamos articulo
        Articulo articulo = buscarArticuloPorSku(articulos, sku);
        if (articulo == null) {
            mostrarMensajeError("Artículo inexistente con ese SKU.");
            return;
        }

        int nuevoEan            = leerEntero           (sc, "Ingrese el código EAN del artículo : ");
        // Validamos que no exista otro artículo con el mismo EAN, pero permito ingresar el EAN anterior del articulo 
        if (buscarArticuloPorEan(articulos, nuevoEan) != null && articulo.getEan()!=nuevoEan) {
            mostrarMensajeError("Artículo existente con ese EAN.");
            return;
        }

        String nuevoNombre      = leerTextoNoVacio    (sc, "Ingrese el nombre del artículo     : ");
        String nuevaDescripcion = leerTextoNoVacio    (sc, "Ingrese la descripción del artículo: ");
        double nuevoPrecio      = leerDoubleNoNegativo(sc, "Ingrese el precio del artículo     : ");
        String nuevaCategoria   = leerTextoNoVacio    (sc, "Ingrese la categoría del artículo  : ");

        String nuevoNombreMayuscula = nuevoNombre.trim().toUpperCase();
        String nuevaCategoriaMinuscula = nuevaCategoria.trim().toLowerCase();

        // Usamos setters para modificar el estado del objeto.
        articulo.setEan(nuevoEan);
        articulo.setNombre(nuevoNombreMayuscula);
        articulo.setDescripcion(nuevaDescripcion);
        articulo.setPrecio(nuevoPrecio);
        articulo.setCategoria(nuevaCategoriaMinuscula);

        System.out.println("Artículo modificado correctamente.");
    }

    /*
     * MÉTODO: eliminarArticuloSku
     * ---------------------------------------------------------
     * Elimina un artículo de la lista buscando primero por SKU.
     */
    public static void eliminarArticuloSku(Scanner sc, ArrayList<Articulo> articulos) {

        System.out.println("\n--- ELIMINAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            mostrarMensajeError("No hay artículos cargados.");
            return;
        }

        int sku = leerEntero(sc, "Ingrese el SKU del artículo a eliminar: ");

        Articulo articulo = buscarArticuloPorSku(articulos, sku);

        if (articulo == null) {
            mostrarMensajeError("Artículo inexistente con ese SKU.");
            return;
        }

        articulos.remove(articulo);

        System.out.println("Artículo eliminado correctamente.");
    }

    /*
     * MÉTODO: listarArticulos
     * ---------------------------------------------------------
     * Recorre la lista de objetos Articulo y muestra cada uno.
     *
     * Gracias a toString(), no necesitamos mostrar cada atributo por separado.
     */
    public static void listarArticulos(ArrayList<Articulo> articulos) {

        System.out.println("\n--- LISTADO DE ARTÍCULOS ---");

        if (articulos.isEmpty()) {
            mostrarMensajeError("No hay artículos cargados.");
            return;
        }

        for (Articulo articulo : articulos) {
            System.out.println(articulo);
        }
    }

    /*
     * MÉTODO: buscarArticuloPorSku
     * ---------------------------------------------------------
     * Recorre la lista y devuelve el objeto Articulo que tenga el SKU buscado.
     *
     * Si no lo encuentra, devuelve null.
     */
    public static Articulo buscarArticuloPorSku(ArrayList<Articulo> articulos, int sku) {

        for (Articulo articulo : articulos) {
            if (articulo.getSku() == sku) {
                return articulo;
            }
        }

        return null;
    }

    /*
     * MÉTODO: buscarArticuloPorEan
     * ---------------------------------------------------------
     * Recorre la lista y devuelve el objeto Articulo que tenga el EAN buscado.
     *
     * Si no lo encuentra, devuelve null.
     */
    public static Articulo buscarArticuloPorEan(ArrayList<Articulo> articulos, int ean) {

        for (Articulo articulo : articulos) {
            if (articulo.getEan() == ean) {
                return articulo;
            }
        }
    
        return null;
    }

    /*
     * MÉTODO: leerEntero
     * ---------------------------------------------------------
     * Lee enteros de manera segura.
     */
    public static int leerEntero(Scanner scanner, String mensaje) {

        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero válido.");
            }
        }
    }

    /*
     * MÉTODO: leerDoubleNoNegativo
     * ---------------------------------------------------------
     * Lee un número decimal y además valida que no sea negativo.
     */
    public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {

        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());

                if (valor < 0) {
                    System.out.println("Error: el precio no puede ser negativo.");
                    continue;
                }

                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número decimal válido.");
            }
        }
    }

    /*
     * MÉTODO: leerTextoNoVacio
     * ---------------------------------------------------------
     * Obliga a ingresar un texto no vacío.
     */
    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {

        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine();

            if (!texto.trim().isEmpty()) {
                return texto.trim();
            }

            System.out.println("Error: el texto no puede estar vacío.");
        }
    }

    /*
     * MÉTODO: mostrarMensajeError
     * ---------------------------------------------------------
     * Mustra un mensaje de error en la pantalla
     */
    public static void mostrarMensajeError(String mensaje){
        System.out.println("==========================================");
        System.out.println(" ERROR: "+ mensaje);
        System.out.println("==========================================");
    }

}