/**
 * Implementacion de un arbol binario de busqueda (BST) y un arbol binario de busqueda (BTS) en Java.
 */

public class BST_BTS<K extends Comparable<K>, V> {
    public static class Node<K, V> {

        K key;
        V value;
        Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
    }

    public Node<K, V> root;

    /**
     * Metodo para agregar un nodo al arbol.
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = addRecursividad(root, key, value);
    }

    /**
     * Metodo recursivo para agregar un nodo al arbol.
     * @param current
     * @param key
     * @param value
     * @return
     */
    private Node<K, V> addRecursividad(Node<K, V> current, K key, V value) {
        if (current == null) return new Node<>(key, value);
        if (key.compareTo(current.key) < 0)
            current.left = addRecursividad(current.left, key, value);
        else if (key.compareTo(current.key) > 0)
            current.right = addRecursividad(current.right, key, value);
        return current;
    }

    /**
     * Metodo para buscar un nodo en el arbol.
     * @param key
     * @return
     */
    public V search(K key) {
        return searchRecursividad(root, key);
    }

    /**
     * Metodo recursivo para buscar un nodo en el arbol.
     * @param current
     * @param key
     * @return
     */
    private V searchRecursividad(Node<K, V> current, K key) {
        if (current == null) return null;
        if (key.compareTo(current.key) == 0) return current.value;
        return key.compareTo(current.key) < 0 ? searchRecursividad(current.left, key) : searchRecursividad(current.right, key);
    }

    /**
     * Metodo para recorrer el arbol en orden.
     */
    public void inorderTraversal() {
        inorderRecursividad(root);
    }

    /**
     * Metodo recursivo para recorrer el arbol en orden.
     * @param node
     */
    private void inorderRecursividad(Node<K, V> node) {
        if (node != null) {
            inorderRecursividad(node.left);
            System.out.println(node.key + " -> " + node.value);
            inorderRecursividad(node.right);
        }
    }
}