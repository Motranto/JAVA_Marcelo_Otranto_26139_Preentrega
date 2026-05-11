package com.techlab.articulo;

// Importamos ArrayList para la lista
import java.util.ArrayList;

// Importamos Scanner para leer datos del teclado.
import java.util.Scanner;

// Importamos las clases 
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloTextil;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.model.Color;

/* CLASE APP 
 * Clase principal de la aplicacion
*/

public class App {

    public static void main(String[] args) {

        // Creamos un Scanner para leer los datos desde la consola.
        Scanner sc = new Scanner(System.in);

        // ArrayList para guardar objeto de tipo Articulo (puede guardar las clases hijas)
        ArrayList<Articulo> articulos = new ArrayList<>();

        ArrayList<Categoria> categorias = new ArrayList<>();
        precargarCategorias(categorias);

        ArrayList<Color> colores = new ArrayList<>();
        precargarColores(colores);

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
            System.out.println("7 - Listar categorias");
            System.out.println("8 - Listar colores");
            System.out.println("------------------------------------------");
            System.out.println("0 - Salir");
            System.out.println("==========================================");

            opcion = leerEntero(sc, "Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    ingresarArticulo(sc, articulos, categorias, colores);
                    break;
                case 2:
                    consultarArticuloSku(sc, articulos);
                    break;
                case 3:
                    consultarArticuloEan(sc, articulos);
                    break;
                case 4:
                    modificarArticuloSku(sc, articulos, categorias, colores);
                    break;
                case 5:
                    eliminarArticuloSku(sc, articulos);
                    break;
                case 6:
                    listarArticulos(articulos);
                    break;
                case 7:
                    listarCategorias(categorias);
                    break;
                case 8:
                    listarColores(colores);
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
     * MÉTODO: precargarCategorias
     * --------------------------------------------------
     * Seguimos usando categorías precargadas para no sumar todavía
     * el CRUD de categorías.
     */
    public static void precargarCategorias(ArrayList<Categoria> categorias) {
        categorias.add(new Categoria(1, "Electrónica", "Productos tecnológicos y electrónicos"));
        categorias.add(new Categoria(2, "Periféricos", "Accesorios para computadora"));
        categorias.add(new Categoria(3, "Alimentos", "Productos alimenticios"));
        categorias.add(new Categoria(4, "Limpieza", "Artículos de limpieza del hogar"));
        categorias.add(new Categoria(5, "Remeras", "Artículos Remeras "));
        categorias.add(new Categoria(6, "Pantalones", "Artículos Pantalones"));
    }

    /*
     * MÉTODO: precargarColores
     * --------------------------------------------------
     * Seguimos usando colores precargadas para no sumar todavía
     * el CRUD de colores.
     */
        public static void precargarColores(ArrayList<Color> colores) {
            colores.add(new Color(1, "Azul"));
            colores.add(new Color(2, "Rojo"));
            colores.add(new Color(3, "Negro"));
            colores.add(new Color(4, "Amarillo"));
            colores.add(new Color(5, "Blanco"));
            colores.add(new Color(6, "Verde"));
        }

    /*
     * MÉTODO: ingresarArticulo
     * ---------------------------------------------------------
     * Ahora el usuario debe elegir qué tipo de artículo quiere crear.
     * Según la elección:
     *    - si es electrónico -> creamos ArticuloElectronico
     *    - si es alimenticio -> creamos ArticuloAlimenticio
     *    - si es textil      -> creamos ArticuloTextil
     * Pedimos los datos del artículo:
     * SKU
     * EAN
     * NOMBRE
     * DESCRIPCION
     * PRECIO
     * CATEGORIA
     * y los de la clase hija
     *      GARANTIA MESES (electronico)
     *      DIAS VENCIMIENTO (alimenticio)
     *      TALLE y COLOR (textil)
     * Luego lo guardamos en la lista Articulos.
     */
    public static void ingresarArticulo(Scanner sc, ArrayList<Articulo> articulos, ArrayList<Categoria> categorias, ArrayList<Color> colores) {

        System.out.println("\n--- INGRESAR ARTÍCULO ---");

        System.out.println("1 - Artículo electrónico");
        System.out.println("2 - Artículo alimenticio");
        System.out.println("3 - Artículo textil");

        int tipo;
        do {
            tipo = leerEntero(sc, "Seleccione el tipo de artículo: ");

            if (tipo != 1 && tipo != 2 && tipo != 3) {
                System.out.println("Error: debe elegir 1 o 2 o 3.");
            }

        } while (tipo != 1 && tipo != 2 && tipo != 3);

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

        String nombreMayuscula = nombre.trim().toUpperCase();

        listarCategorias(categorias);
        Categoria categoria = pedirCategoriaExistente(sc, categorias);

        // Declaramos una variable de tipo Articulo.
        // Después le asignaremos una instancia de la clase hija correspondiente.
        Articulo articulo;

        if (tipo == 1) {
            int garantiaMeses = leerEnteroNoNegativo(sc, "Ingrese la garantía en meses       : ");
            // Creamos un objeto de la clase hija ArticuloElectronico.
            articulo = new ArticuloElectronico(sku, ean, nombreMayuscula, descripcion, precio, categoria, garantiaMeses);
        } else if (tipo == 2){
            int diasParaVencimiento = leerEnteroNoNegativo(sc, "Ingrese los días para vencimiento  : ");
            // Creamos un objeto de la clase hija ArticuloAlimenticio.
            articulo = new ArticuloAlimenticio(sku, ean, nombreMayuscula, descripcion, precio, categoria, diasParaVencimiento);
        } else {
            int talle = leerEnteroNoNegativo(sc, "Ingrese el talle                   : ");
            
            listarColores(colores);
            Color color = pedirColorExistente(sc, colores);

            // Creamos un objeto de la clase hija ArticuloTalle.
            articulo = new ArticuloTextil(sku, ean, nombreMayuscula, descripcion, precio, categoria, talle, color);
        }

        // Guardamos el objeto en la lista.
        articulos.add(articulo);

        System.out.println("Artículo ingresado correctamente.");
        System.out.println("Resumen del objeto creado:");
        System.out.println(articulo);
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
            System.out.println("Detalle específico: " + articulo.getDetalleEspecifico());
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
            System.out.println("Detalle específico: " + articulo.getDetalleEspecifico());
        }
    }

    /*
     * MÉTODO: modificarArticuloSku
     * ---------------------------------------------------------
     * Permite cambiar los datos de un artículo existente ingresando el SKU.
     */
    public static void modificarArticuloSku(Scanner sc, 
                                            ArrayList<Articulo> articulos, 
                                            ArrayList<Categoria> categorias, 
                                            ArrayList<Color> colores) {

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

        String nuevoNombreMayuscula = nuevoNombre.trim().toUpperCase();

        listarCategorias(categorias);
        Categoria nuevaCategoria = pedirCategoriaExistente(sc, categorias);

        // Usamos setters para modificar el estado del objeto.
        articulo.setEan(nuevoEan);
        articulo.setNombre(nuevoNombreMayuscula);
        articulo.setDescripcion(nuevaDescripcion);
        articulo.setPrecio(nuevoPrecio);
        articulo.setCategoria(nuevaCategoria);

        // Si el artículo real es electrónico, permitimos modificar la garantía.
        if (articulo instanceof ArticuloElectronico) {
            ArticuloElectronico electronico = (ArticuloElectronico) articulo;

            int nuevaGarantia = leerEnteroNoNegativo(sc, "Ingrese la garantía en meses       : ");
            electronico.setGarantiaMeses(nuevaGarantia);
        }

        // Si el artículo real es alimenticio, permitimos modificar los días para vencimiento.
        if (articulo instanceof ArticuloAlimenticio) {
            ArticuloAlimenticio alimenticio = (ArticuloAlimenticio) articulo;

            int nuevosDias = leerEnteroNoNegativo(sc, "Ingrese los días para vencimiento  : ");
            alimenticio.setDiasParaVencimiento(nuevosDias);
        }

        // Si el artículo real es textil, permitimos modificar el talle y el color
        if (articulo instanceof ArticuloTextil) {
            ArticuloTextil textil = (ArticuloTextil) articulo;

            int nuevoTalle = leerEnteroNoNegativo(sc, "Ingrese el talle                   : ");
            listarColores(colores);
            Color nuevoColor = pedirColorExistente(sc, colores);
            
            textil.setTalle(nuevoTalle);
            textil.setColor(nuevoColor);
        }


        System.out.println("Artículo modificado correctamente.");
        System.out.println("Resumen del objeto modificado:");
        System.out.println(articulo);
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
     * MÉTODO: listarCategorias
     * --------------------------------------------------
     * Muestra las categorías disponibles.
     */
    public static void listarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");

        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

    /*
     * MÉTODO: pedirCategoriaExistente
     * --------------------------------------------------
     * Obliga al usuario a elegir una categoría válida.
     */
        public static Categoria pedirCategoriaExistente(Scanner sc, ArrayList<Categoria> categorias) {
            while (true) {
                int codigoCategoria = leerEntero(sc, "Ingrese el código de la categoría  : ");
    
                Categoria categoria = buscarCategoriaPorCodigo(categorias, codigoCategoria);
    
                if (categoria != null) {
                    return categoria;
                }
    
                mostrarMensajeError("la categoría no existe.");
            }
        }

    /*
     * MÉTODO: listarColores
     * --------------------------------------------------
     * Muestra los colores disponibles.
     */
    public static void listarColores(ArrayList<Color> colores) {
        System.out.println("\n--- COLORES DISPONIBLES ---");

        for (Color color : colores) {
            System.out.println(color);
        }
    }

    /*
     * MÉTODO: pedirColorExistente
     * --------------------------------------------------
     * Obliga al usuario a elegir un color válido.
     */
    public static Color pedirColorExistente(Scanner sc, ArrayList<Color> colores) {
        while (true) {
            int codigoColor = leerEntero(sc, "Ingrese el código del color       : ");

            Color color = buscarColorPorCodigo(colores, codigoColor);

            if (color != null) {
                return color;
            }

            mostrarMensajeError("el color no existe.");
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
     * MÉTODO: buscarCategoriaPorCodigo
     * --------------------------------------------------
     * Recorre la lista de categorías y devuelve la coincidente.
     */
    public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {
        for (Categoria categoria : categorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }
        return null;
    }

    /*
     * MÉTODO: buscarColorPorCodigo
     * --------------------------------------------------
     * Recorre la lista de colores y devuelve el coincidente.
     */
        public static Color buscarColorPorCodigo(ArrayList<Color> colores, int codigo) {
            for (Color color : colores) {
                if (color.getCodigo() == codigo) {
                    return color;
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
     * MÉTODO: leerEnteroNoNegativo
     * --------------------------------------------------
     * Lee enteros y además valida que no sean negativos.
     */
    public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);

            if (valor < 0) {
                System.out.println("Error: el valor no puede ser negativo.");
                continue;
            }

            return valor;
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