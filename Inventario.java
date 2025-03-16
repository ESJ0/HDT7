import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que representa el inventario de productos de merchandising.
 */

public class Inventario {
    private BST_BTS<String, Merchandising> bstSKU = new BST_BTS<>();
    private BST_BTS<String, Merchandising> bstNombre = new BST_BTS<>();

    /**
     * Metodo para agregar un producto al inventario.
     * @param SKU
     * @param Nombre
     * @param Descripcion
     * @param Tallas
     */
    public void add(String SKU, String Nombre, String Descripcion, String Tallas) {
        Merchandising producto = new Merchandising(SKU, Nombre, Descripcion, Tallas);
        bstSKU.add(SKU, producto);
        bstNombre.add(Nombre, producto);
        guardarEnCSV();
    }

    /**
     * Metodo para buscar un producto por SKU.
     * @param SKU
     * @return
     */
    public Merchandising buscarPorSku(String SKU) {
        return bstSKU.search(SKU);
    }

    /**
     * Metodo para buscar un producto por nombre.
     * @param Nombre
     * @return
     */
    public Merchandising buscarPorNombre(String Nombre) {
        return bstNombre.search(Nombre);
    }

    /**
     * Metodo para eliminar un producto del inventario.
     * @param SKU
     * @return
     */
    public void listarPorSku() {
        bstSKU.inorderTraversal();
    }


    /**
     * Metodo para listar los productos por nombre.
     */
    public void listarPorNombre() {
        bstNombre.inorderTraversal();
    }

    /**
     * Metodo para eliminar un producto del inventario.
     * @param SKU
     * @return
     */
    public boolean editarProducto(String SKU, String nuevaDescripcion, String nuevasTallas) {
        Merchandising producto = bstSKU.search(SKU);
        if (producto != null) {
            producto.Descripcion = nuevaDescripcion;
            producto.TallasDisponibles = producto.parseTallas(nuevasTallas);
            guardarEnCSV();
            return true;
        }
        return false;
    }

    /**
     * Metodo para eliminar un producto del inventario.
     * @param SKU
     */
     public void guardarEnCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventario.csv"))) {
            guardarRecursivo(bstSKU.root, bw);
        } catch (IOException e) {
            System.out.println("Error al guardar CSV: " + e.getMessage());
        }
    }

    /**
     * Metodo recursivo para guardar los productos en un archivo CSV.
     * @param node
     * @param bw
     * @throws IOException
     */
    private void guardarRecursivo(BST_BTS.Node<String, Merchandising> node, BufferedWriter bw) throws IOException {
        if (node != null) {
            guardarRecursivo(node.left, bw);
            bw.write(node.value.toCSV());
            bw.newLine();
            guardarRecursivo(node.right, bw);
        }
    }

    /**
     * Metodo para cargar los productos desde un archivo CSV.
     * @param filename
     * @throws IOException
     */
    public void cargarDesdeCSV(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    add(parts[0], parts[1], parts[2], parts[3]);
                }
            }
        }
    }
}
