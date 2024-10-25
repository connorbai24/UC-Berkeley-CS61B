import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    // this Node represents the whole tree.
    private class Node {
        private K dict;
        private V val;
        private Node left;
        private Node right;

        private Node(K k, V v) {
            dict = k;
            val = v;
            left = null;
            right = null;
        }
    }

    private int size;
    private Node root;

    public BSTMap() {
        root = null;
        // root = new Node(null, null) is not equal to null, as it has a Node regardless of empty or not.
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    public Node putHelper(K key, V value, Node root) {
        if (root == null) {
            root = new Node(key, value);
            size++;
        } else if (key.compareTo(root.dict) < 0) { // it must use compareTo method. K type has not < or >.
            root.left = putHelper(key, value, root.left);
        } else if (key.compareTo(root.dict) > 0) {
            root.right = putHelper(key, value, root.right);
        } else {
            root.dict = key;
            root.val = value;
        }
        return root;
    }

    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    private V getHelper(K key, Node root) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.dict) == 0) {
            return root.val;
        } else if (key.compareTo(root.dict) < 0) {
            return getHelper(key, root.left);
        } else {
            return getHelper(key, root.right);
        }
    }

    @Override
    public boolean containsKey(K key) {
        return containsHelper(key, root);
    }

    private boolean containsHelper(K key, Node root) {
        if (root == null) {
            return false;
        } else if (key.compareTo(root.dict) < 0) {
            return containsHelper(key, root.left);
        } else if (key.compareTo(root.dict) > 0) {
            return containsHelper(key, root.right);
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> ks = new HashSet<>();
        keySetHelper(ks, root);
        return ks;
    }

    private void keySetHelper(Set<K> ks, Node root) {
        if (root == null) {
            return;
        }
        keySetHelper(ks, root.left);
        ks.add(root.dict);
        keySetHelper(ks, root.right);
    }

    @Override
    public V remove(K key) {
        V result = get(key);
        if (result != null) {
            root = removeHelper(key, root);
        }
        size--;
        return result;
    }

    private Node removeHelper(K key, Node root) {
        if (key == root.dict) {
            if (root.left == null && root.right == null) {
                root = null;
                return root;
            } else if (root.left == null) {
                root = root.right;
                return root;
            } else if (root.right == null){
                root = root.left;
                return root;
            } else {
                Node predecessor = findMostRight(root.left);
                root.val = predecessor.val;
                root.dict = predecessor.dict;
                root.left = removeHelper(predecessor.dict, root.left);
                return root;
            }
        } else if (key.compareTo(root.dict) < 0) {
            root.left = removeHelper(key, root.left);
            return root;
        } else if (key.compareTo(root.dict) > 0){
            root.right = removeHelper(key, root.right);
            return root;
        }
        throw new UnsupportedOperationException();
    }

    private Node findMostRight(Node root) {
        if (root.right == null) {
            return root;
        } else {
            return findMostRight(root.right);
        }
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    /* prints out your BSTMap in order of increasing Key */
    public void printInOrder() {

    }
}
