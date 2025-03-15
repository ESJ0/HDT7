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

    private Node<K, V> root;

    public void add(K key, V value) {
        root = addRecursividad(root, key, value);
    }

    private Node<K, V> addRecursividad(Node<K, V> current, K key, V value) {
        if (current == null) return new Node<>(key, value);
        if (key.compareTo(current.key) < 0)
            current.left = addRecursividad(current.left, key, value);
        else if (key.compareTo(current.key) > 0)
            current.right = addRecursividad(current.right, key, value);
        return current;
    }

    public V contenidoNode(K key) {
        return contenidoNodeRecursividad(root, key);
    }

    private V contenidoNodeRecursividad(Node<K, V> current, K key) {
        if (current == null) return null;
        if (key.compareTo(current.key) == 0) return current.value;
        return key.compareTo(current.key) < 0 ? contenidoNodeRecursividad(current.left, key) : contenidoNodeRecursividad(current.right, key);
    }

    public void inorderTraversal() {
        inorderRecursividad(root);
    }

    private void inorderRecursividad(Node<K, V> node) {
        if (node != null) {
            inorderRecursividad(node.left);
            System.out.println(node.key + " -> " + node.value);
            inorderRecursividad(node.right);
        }
    }
}