import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Inventario {
    private BST_BTS<String, Merchandising> bstSKU = new BST_BTS<>();
    private BST_BTS<String, Merchandising> bstNombre = new BST_BTS<>();

    public void add(String SKU, String Nombre, String Descripcion, String Tallas) {
        Merchandising producto = new Merchandising(SKU, Nombre, Descripcion, Tallas);
        bstSKU.add(SKU, producto);
        bstNombre.add(Nombre, producto);
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
        if (producto == null) return false;

        bstNombre = removerProductoNombre(bstNombre, producto.Nombre);
        producto.Descripcion = nuevaDescripcion;
        producto.TallasDisponibles = producto.parseTallas(nuevasTallas);
        bstNombre.add(producto.Nombre, producto);
        return true;
    }

    private BST_BTS<String, Merchandising> removerProductoNombre(BST_BTS<String, Merchandising> arbol, String nombre) {
        BST_BTS<String, Merchandising> nuevoArbol = new BST_BTS<>();
        inorderReinsert(arbol.root, nuevoArbol, nombre);
        return nuevoArbol;
    }

    private void inorderReinsert(BST_BTS.Node<String, Merchandising> node, BST_BTS<String, Merchandising> nuevoArbol, String nombreAEliminar) {
        if (node == null) return;
        inorderReinsert(node.left, nuevoArbol, nombreAEliminar);
        if (!node.key.equals(nombreAEliminar)) {
            nuevoArbol.add(node.key, node.value);
        }
        inorderReinsert(node.right, nuevoArbol, nombreAEliminar);
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
