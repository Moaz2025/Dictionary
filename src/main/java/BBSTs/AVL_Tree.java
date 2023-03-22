package BBSTs;

public class AVL_Tree<T extends Comparable<T>> implements ITree<T> {
    private long size = 0;
    private AVL_Node<T> root = null;

    // AVL node class
    private static class AVL_Node<K>{
        public long height = 0;
        public long balance = 0;
        public K value;
        public AVL_Node<K> left;
        public AVL_Node<K> right;
        public AVL_Node(K value) {
            this.value = value;
        }
    }

    // return true if item is found otherwise return false;
    public boolean search(T key){
        if (key == null)
            return false;

        return contains(this.root, key);
    }

    // check if the tree contains a certain key
    private boolean contains(AVL_Node<T> node, T key){
        if (node == null)
            return false;

        if (key.compareTo(node.value) == 0)
            return true;

        if (key.compareTo(node.value) < 0)
            return contains(node.left, key);
        else
            return contains(node.right, key);
    }

    // return true if insertion successes otherwise return false
    public boolean insert(T key){
        if (key == null)
            return false;

        if (!contains(this.root, key)) {
            this.root = insert(this.root, key);
            this.size++;
            return true;
        }

        return false;
    }

    // search for position for the node to be inserted in
    private AVL_Node<T> insert(AVL_Node<T> node, T key) {
        if (node == null)
            return new AVL_Node<>(key);

        if (key.compareTo(node.value) < 0)
            node.left = insert(node.left, key);
        else
            node.right = insert(node.right, key);

        update(node);
        return balance(node);
    }

    // return true if deletion successes otherwise return false
    public boolean delete(T key) {
        if (key == null)
            return false;

        if (contains(this.root, key)){
            this.root = delete(this.root, key);
            this.size--;
            return true;
        }

        return false;
    }

    // delete a node from tree based on the four cases: leaf node, with right child, with left child, with both left and right children
    public AVL_Node<T> delete(AVL_Node<T> node, T key) {
        if (node == null)
            return null;

        if (key.compareTo(node.value) == 0) {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                AVL_Node<T> newNode = digRight(node.left);
                node.value = newNode.value;
                node.left = delete(node.left, newNode.value);
            }
        }

        if (key.compareTo(node.value) < 0)
            node.left = delete(node.left, key);
        else
            node.right = delete(node.right, key);

        update(node);
        return balance(node);
    }

    // update node info of height and balance
    private void update(AVL_Node<T> node) {
        long leftNodeHeight = (node.left == null)? -1:node.left.height;
        long rightNodeHeight = (node.right == null)? -1:node.right.height;

        node.height = Math.max(leftNodeHeight, rightNodeHeight) + 1;
        node.balance = leftNodeHeight - rightNodeHeight;
    }

    // checking for balance of a node and do the required to restore balance property
    private AVL_Node<T> balance (AVL_Node<T> node) {
        if (node.balance == -2) {
            if (node.right.balance > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        } else if (node.balance == 2){
            if (node.left.balance < 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        }

        return node;
    }

    // swap the node in anti-clockwise direction
    private AVL_Node<T> leftRotate(AVL_Node<T> node){
        AVL_Node<T> child = node.right;
        node.right = child.left;
        child.left = node;
        update(node);
        update(child);
        return child;
    }

    // swap the node in clockwise direction
    private AVL_Node<T> rightRotate(AVL_Node<T> node){
        AVL_Node<T> child = node.left;
        node.left = child.right;
        child.right = node;
        update(node);
        update(child);
        return child;
    }

    //get the most right node from a current node, useful for deletion of a node
    private AVL_Node<T> digRight(AVL_Node<T> node){
        if (node.right == null)
            return node;
        return digRight(node.right);
    }

    // return the current size of the tree
    public long size(){ return this.size; }

    // return the current height of the tree
    public long height(){
        if (this.root == null)
            return 0;
        return this.root.height;
    }

    // reset the tree to empty state
    public void clear() {
        this.root.left = null;
        this.root.right = null;
        this.root = null;
        this.size = 0;
    }

    // traversing the tree, calls inorder traverse method
    public void traverse(){
        inorder(this.root);
    }

    // traverse the tree in an inorder fashion
    public void inorder(AVL_Node<T> node){
        if (node == null)
            return;

        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);

        if (node == this.root)
            System.out.println();
    }
}