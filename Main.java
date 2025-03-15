import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                inventario.cargarDesdeCSV("inventario.csv");
            } catch (IOException e) {
                System.out.println("Error cargando CSV: " + e.getMessage());
            }

            int opcion;
            do {
                System.out.println("\n1. Listar productos por SKU");
                System.out.println("2. Listar productos por Nombre");
                System.out.println("3. Buscar producto por SKU");
                System.out.println("4. Editar producto");
                System.out.println("5. Agregar producto");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1 -> inventario.listarPorSku();
                    case 2 -> inventario.listarPorNombre();
                    case 3 -> {
                        System.out.print("Ingrese SKU: ");
                        String sku = scanner.nextLine();
                        Merchandising producto = inventario.buscarPorSku(sku);
                        System.out.println(producto != null ? producto : "Producto no encontrado.");
                    }
                    case 4 -> {
                        System.out.print("Ingrese SKU del producto a editar: ");
                        String sku = scanner.nextLine();
                        System.out.print("Nueva descripción: ");
                        String descripcion = scanner.nextLine();
                        System.out.print("Nuevas tallas (formato xs:10|s:15|m:25|l:10|xl:8): ");
                        String tallas = scanner.nextLine();
                        System.out.println(inventario.editarProducto(sku, descripcion, tallas) ? "Producto editado." : "Producto no encontrado.");
                    }
                    case 5 -> {
                        // Opción para agregar un nuevo producto
                        System.out.print("Ingrese SKU del producto: ");
                        String sku = scanner.nextLine();
                        System.out.print("Ingrese nombre del producto: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese descripción del producto: ");
                        String descripcion = scanner.nextLine();
                        System.out.print("Ingrese tallas disponibles (formato xs:10|s:15|m:25|l:10|xl:8): ");
                        String tallas = scanner.nextLine();
                        inventario.add(sku, nombre, descripcion, tallas);
                        System.out.println("Producto agregado con éxito.");
                    }
                }
            } while (opcion != 6);
        }
    }
}
