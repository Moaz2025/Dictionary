package BBSTs;

public class RB_Tree<T extends Comparable<T>> implements ITree<T> {
    public RB_Node<T> root;
    long size;
    boolean Black = false, Red = true;

    private static class RB_Node<K>{
        public K value;
        public RB_Node<K> left;
        public RB_Node<K> right;
        public RB_Node<K> parent;
        boolean color;
        public RB_Node (K value){ this.value = value; }
    }

    public RB_Tree(T value) {
        root = new RB_Node<>(value);
        root.color = Black;
        root.left = null;
        root.right = null;
        size = 1;
    }

    private class NilRB_Node<T> extends RB_Node<T> {
        private NilRB_Node() {
            super(null);
            this.color = Black;
        }
    }

    private RB_Node<T> getMinimum(RB_Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    ///////////////// search//////////////////////////////
    public boolean search(T key){
        if (key == null)
            return false;

        return contains(root, key);
    }
    private boolean contains(RB_Node<T> node, T key) {
        while (node != null) {
            if (node.value.compareTo(key) > 0)
                node = node.left;
            else if (node.value.compareTo(key) < 0)
                node = node.right;
            else
                return true;
        }
        return false;
    }

    /////////////////////// insert///////////////////////
    public boolean insert(T key) {
        RB_Node<T> node = root, parent = null;

        while (node != null) {
            parent = node;
            if (node.value.compareTo(key) > 0)
                node = node.left;
            else if (node.value.compareTo(key) < 0)
                node = node.right;
            else
                return false;
        }

        RB_Node<T> newRB_Node = new RB_Node(key);
        newRB_Node.color = Red;
        if (parent == null)
            root = newRB_Node;
        else if (parent.value.compareTo(key) > 0)
            parent.left = newRB_Node;
        else
            parent.right = newRB_Node;

        newRB_Node.parent = parent;
        fixInsert(newRB_Node);
        size++;
        return true;
    }

    public void fixInsert(RB_Node<T> node) {
        RB_Node<T> parent = node.parent;

        // Case 1: parent is null or parent is black
        if (parent == null || parent.color == Black)
            return;

        RB_Node<T> grandparent = parent.parent;

        // Case2 : parent is root
        if (grandparent == null) {
            parent.color = Black;
            return;
        }

        RB_Node<T> uncle = getUncle(parent);

        // Case 3: uncle and parent are red
        if (uncle != null && uncle.color == Red) {
            uncle.color = Black;
            parent.color = Black;
            grandparent.color = Red;

            fixInsert(grandparent);
        }
        // Case 4: parent is left child of grandparent
        else if (parent == grandparent.left) {
            // if node is right child
            if (node == parent.right) {
                leftRotate(parent);
                parent = node;
            }

            rightRotate(grandparent);
            parent.color = Black;
            grandparent.color = Red;
        }
        // Case 5: parent is right child of grandparent
        else {
            // if node is left child
            if (node == parent.left) {
                rightRotate(parent);
                parent = node;
            }

            leftRotate(grandparent);
            parent.color = Black;
            grandparent.color = Red;
        }

    }

    ///////////////// Delete////////////////////////////////
    public boolean delete(T key) {
        RB_Node<T> node = root;

        while (node != null && node.value.compareTo(key) != 0) {
            if (node.value.compareTo(key) > 0)
                node = node.left;
            else
                node = node.right;
        }
        if (node == null)
            return false;

        RB_Node<T> movedUpRB_Node;
        boolean deletedRB_NodeColor;
        // node has one or no children
        if (node.left == null || node.right == null) {
            movedUpRB_Node = deleteRB_NodeWithZeroOrOneChild(node);
            deletedRB_NodeColor = node.color;
        }
        // node has two children
        else {
            RB_Node<T> inOrderSuccessor = getMinimum(node.right);
            node.value = inOrderSuccessor.value;

            movedUpRB_Node = deleteRB_NodeWithZeroOrOneChild(inOrderSuccessor);
            deletedRB_NodeColor = inOrderSuccessor.color;
        }

        if (deletedRB_NodeColor == Black)
            fixDelete(movedUpRB_Node);

        if (movedUpRB_Node != null && movedUpRB_Node.getClass() == NilRB_Node.class)
            replaceParentsChild(movedUpRB_Node.parent, movedUpRB_Node, null);

        size--;
        return true;
    }

    private void fixDelete(RB_Node<T> node) {
        // Case 1
        if (node == root)
            return;

        RB_Node<T> sibling = getSibling(node);
        // Case 2: red sibling
        if (sibling.color == Red) {

            sibling.color = Black;
            node.parent.color = Red;
            if (node == node.parent.left)
                leftRotate(node.parent);
            else
                rightRotate(node.parent);

            sibling = getSibling(node);
        }
        // case 3:Black sibling with two black children
        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = Red;
            // Case 3 A: Black sibling with two black children + red parent
            if (node.parent.color == Red)
                node.parent.color = Black;
                // Case 3 B: Black sibling with two black children + black parent
            else
                fixDelete(node.parent);
        }
        // case 4:Black sibling with at least one red child
        else {
            boolean nodeIsLeftChild = node == node.parent.left;
            // case 4 A:"outer nephew" is black
            if (nodeIsLeftChild && isBlack(sibling.right)) {
                sibling.left.color = Black;
                sibling.color = Red;
                rightRotate(sibling);
                sibling = node.parent.right;
            } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
                sibling.right.color = Black;
                sibling.color = Red;
                leftRotate(sibling);
                sibling = node.parent.left;
            }
            // case 4 B:"outer nephew" is red
            sibling.color = node.parent.color;
            node.parent.color = Black;
            if (nodeIsLeftChild) {
                sibling.right.color = Black;
                leftRotate(node.parent);
            } else {
                sibling.left.color = Black;
                rightRotate(node.parent);
            }

        }
    }

    private RB_Node<T> deleteRB_NodeWithZeroOrOneChild(RB_Node<T> node) {
        if (node.left != null) {
            replaceParentsChild(node.parent, node, node.left);
            return node.left;
        } else if (node.right != null) {
            replaceParentsChild(node.parent, node, node.right);
            return node.right;
        }

        else {
            RB_Node<T> newChild = node.color == Black ? new NilRB_Node() : null;
            replaceParentsChild(node.parent, node, newChild);
            return newChild;
        }
    }

    ////////////////////////////////////////////////////////////
    private RB_Node<T> getSibling(RB_Node<T> node) {
        RB_Node<T> parent = node.parent;
        if (node == parent.left)
            return parent.right;
        else
            return parent.left;
    }

    private boolean isBlack(RB_Node<T> node) {
        return node == null || node.color == Black;
    }

    private RB_Node<T> getUncle(RB_Node<T> parent) {
        RB_Node<T> grandparent = parent.parent;
        if (grandparent.left == parent)
            return grandparent.right;
        else
            return grandparent.left;
    }

    private void rightRotate(RB_Node<T> node) {
        RB_Node<T> parent = node.parent;
        RB_Node<T> leftChild = node.left;
        node.left = leftChild.right;

        if (leftChild.right != null)
            leftChild.right.parent = node;

        leftChild.right = node;
        node.parent = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }

    private void leftRotate(RB_Node<T> node) {
        RB_Node<T> parent = node.parent;
        RB_Node<T> rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null)
            rightChild.left.parent = node;

        rightChild.left = node;
        node.parent = rightChild;

        replaceParentsChild(parent, node, rightChild);
    }

    private void replaceParentsChild(RB_Node<T> parent, RB_Node<T> oldChild, RB_Node<T> newChild) {
        if (parent == null)
            root = newChild;
        else if (parent.left == oldChild)
            parent.left = newChild;
        else
            parent.right = newChild;

        if (newChild != null)
            newChild.parent = parent;
    }

    ///////////// size////////////////
    public long size() {
        return this.size;
    }

    //////////////// height///////////////////////
    public long height() {
        return RBheight(root);
    }
    private long RBheight(RB_Node<T> node) {
        if (node == null)
            return 0;
        long leftHeight = RBheight(node.left);
        long rightHeight = RBheight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    //////// isEmpty/////////////////
    boolean isEmpty() {
        return root == null;
    }

    ////////// clear//////////////////////
    void clear() {
        root.left = null;
        root.right = null;
        root.parent = null;
        root = null;
        size = 0;
    }

    public RB_Node<T> getRoot() {
        return root;
    }

    public void traverse(){ this.inorder(this.root);}
    private void inorder(RB_Node<T> node) {

        if (node != null) {
            if (node.left != null) {
                inorder(node.left);
            }

            char c = 'R';
            if (node.color == Black)
                c = 'B';
            System.out.print(node.value + "" + c + " ");
            inorder(node.right);
        }
    }
}
