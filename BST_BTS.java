public class BST_BTS<T extends Comparable<T>> {
    
    public static class Node<T> {
        
        T value;
        Node<T> left;
        Node<T> right;
    
        public Node(T value) {
            this.value = value;
            right = null;
            left = null;
        }
    }

    private Node<T> root;

    BST_BTS() {
        root = null;
    }

    private Node<T> addRecursividad(Node<T> current, T value) {
        if (current == null) {
            return new Node<>(value);
        }
        if (value.compareTo(current.value) < 0) {
            current.left = addRecursividad(current.left, value);
        } else if (value.compareTo(current.value) > 0) {
            current.right = addRecursividad(current.right, value);
        } else {
            return current;
        }
        return current;
    }

    public void add(T value) {
        root = addRecursividad(root, value);
    }

    private boolean contenidoNodeRecursividad(Node<T> current, T value) {
        if (current == null) {
            return false;
        }
        if (value.compareTo(current.value) == 0) {
            return true;
        }
        return value.compareTo(current.value) < 0 ? contenidoNodeRecursividad(current.left, value) : contenidoNodeRecursividad(current.right, value);
    }

    public boolean contenidoNode(T value) {
        return contenidoNodeRecursividad(root, value);
    }
}
