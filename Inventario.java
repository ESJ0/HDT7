import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Inventario {
    private BST_BTS<String, Merchandising> bstSKU = new BST_BTS<>();
    private BST_BTS<String, Merchandising> bstNombre = new BST_BTS<>();

    public void add(String SKU, String Nombre, String Descripcion, String Tallas) {
        Merchandising producto = new Merchandising(SKU, Nombre, Descripcion, Tallas);
        bstSKU.add(SKU, producto);
        bstNombre.add(Nombre, producto);
        guardarEnCSV();
    }

    public Merchandising buscarPorSku(String SKU) {
        return bstSKU.search(SKU);
    }

    public Merchandising buscarPorNombre(String Nombre) {
        return bstNombre.search(Nombre);
    }

    public void listarPorSku() {
        bstSKU.inorderTraversal();
    }

    public void listarPorNombre() {
        bstNombre.inorderTraversal();
    }

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

     public void guardarEnCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventario.csv"))) {
            guardarRecursivo(bstSKU.root, bw);
        } catch (IOException e) {
            System.out.println("Error al guardar CSV: " + e.getMessage());
        }
    }

    private void guardarRecursivo(BST_BTS.Node<String, Merchandising> node, BufferedWriter bw) throws IOException {
        if (node != null) {
            guardarRecursivo(node.left, bw);
            bw.write(node.value.toCSV());
            bw.newLine();
            guardarRecursivo(node.right, bw);
        }
    }

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
