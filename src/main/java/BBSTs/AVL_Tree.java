package BBSTs;

public class AVL_Tree<T extends Comparable<T>> implements ITree<T> {
    private long size = 0;
    private AVL_Node<T> root = null;
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

    public boolean search(T key){
        if (key == null)
            return false;

        return contains(root, key);
    }
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

    public boolean delete(T key) {
        if (key == null)
            return false;

        if (contains(root, key)){
            this.root = delete(root, key);
            this.size--;
            return true;
        }

        return false;
    }
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

    private void update(AVL_Node<T> node) {
        long leftNodeHeight = (node.left == null)? -1:node.left.height;
        long rightNodeHeight = (node.right == null)? -1:node.right.height;

        node.height = Math.max(leftNodeHeight, rightNodeHeight) + 1;
        node.balance = leftNodeHeight - rightNodeHeight;
    }
    private AVL_Node<T> balance (AVL_Node<T> node) {
        if (node.balance < -1) {
            if (node.right.balance < 0) {
                return leftRotate(node);
            } else if (node.right.balance > 0){
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        } else if (node.balance > 1){
            if (node.left.balance > 0) {
                return rightRotate(node);
            } else if (node.left.balance < 0){
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        return node;
    }

    private AVL_Node<T> leftRotate(AVL_Node<T> node){
        AVL_Node<T> child = node.right;
        node.right = child.left;
        child.left = node;
        return child;
    }
    private AVL_Node<T> rightRotate(AVL_Node<T> node){
        AVL_Node<T> child = node.left;
        node.left = child.right;
        child.right = node;
        return child;
    }
    private AVL_Node<T> digRight(AVL_Node<T> node){
        if (node.right == null)
            return node;
        return digRight(node.right);
    }

    public long size(){ return this.size; }

    public long height(){
        if (this.root == null)
            return 0;
        return this.root.height;
    }

    public void traverse(){
        inorder(this.root);
    }
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