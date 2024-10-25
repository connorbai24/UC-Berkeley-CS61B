public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /**
         * Creates a RBTreeNode with item ITEM and color depending on ISBLACK
         * value.
         * @param isBlack
         * @param item
         */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /**
         * Creates a RBTreeNode with item ITEM, color depending on ISBLACK
         * value, left child LEFT, and right child RIGHT.
         * @param isBlack
         * @param item
         * @param left
         * @param right
         */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Creates an empty RedBlackTree.
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * Flips the color of node and its children. Assume that NODE has both left
     * and right children
     * @param node
     */
    void flipColors(RBTreeNode<T> node) {
        if (node.left == null && node.right == null) {
            throw new IllegalArgumentException();
        }
        node.left.isBlack = true;
        node.right.isBlack = true;
        node.isBlack = false;
    }

    /**
     * Rotates the given node to the right. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        RBTreeNode<T> childNode = node.left;
        RBTreeNode<T> childNodeRight = childNode.right;

        childNode.right = node;
        node.left = childNodeRight;
        return childNode;
    }

    /**
     * Rotates the given node to the left. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        RBTreeNode<T> childNode = node.right;
        RBTreeNode<T> childNodeLeft = childNode.left;

        childNode.left = node;
        node.right = childNodeLeft;
        return childNode;
    }

    /**
     * Helper method that returns whether the given node is red. Null nodes (children or leaf
     * nodes) are automatically considered black.
     * @param node
     * @return
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    /**
     * Inserts the item into the Red Black Tree. Colors the root of the tree black.
     * @param item
     */
    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /**
     * Inserts the given node into this Red Black Tree. Comments have been provided to help break
     * down the problem. For each case, consider the scenario needed to perform those operations.
     * Make sure to also review the other methods in this class!
     * @param node
     * @param item
     * @return
     */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        if (node == null) {
            node = new RBTreeNode<>(false, item, null, null);
            return node;
        } else if (node.item.compareTo(item) < 0) {
            node.right = insert(node.right, item);
        } else if (node.item.compareTo(item) > 0){
            node.left = insert(node.left, item);
        }
        
        /* here the node doesn't refer to the whole tree.
        it's just the same node which has done the insert function*/
        // right is red and left is black or null.
        if (isRed(node.right) && !isRed(node) && !isRed(node.left)) {
            node = rotateLeft(node);
            node.isBlack = true;
            node.left.isBlack = false;
        }
        if (isRed(node.left) && isRed(node.left.right)){ //right is red and its child right is also red
            node.left = rotateLeft(node.left);
        }
        if (isRed(node.left) && isRed(node.left.left)) { //right is red and its child left is also red
            node = rotateRight(node);
            node.isBlack = true;
            node.left.isBlack = false;
            node.right.isBlack = false;
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

}
