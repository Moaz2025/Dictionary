package BBSTs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void test_empty_tree()
    {
        AVL_Tree<String> tree = new AVL_Tree<>();
        assertEquals(0, tree.size());   // Empty tree should have 0 nodes.
        assertEquals(0, tree.height()); // Empty tree should have 0 height.
        assertFalse(tree.delete("Galal"));  // Removing non-existing key should return false.
        assertFalse(tree.search("Galal"));  // Searching non-existing key should return false.
        assertEquals(0, tree.size());   // Empty tree should still have 0 nodes.
    }

    @Test
    void test_single_node()
    {
        final double value = 23.7;
        AVL_Tree<Double> tree = new AVL_Tree<>();
        tree.insert(value);

        // After insertion of single node:
        assertEquals(1, tree.size());       // tree should have 1 node.
        assertEquals(0, tree.height());     // tree should have 1 height.
        assertFalse(tree.delete(46.2));         // Removing non-existing key should return false.
        assertTrue(tree.search(23.7));          // search should return true.
        assertEquals(1, tree.size());       // tree should have 1 node.

        // after removing single node:
        assertTrue(tree.delete(23.7));          // Removing existing key should return true.
        assertEquals(0, tree.size());       // Empty tree should have 0 nodes.
        assertEquals(0, tree.height());     // Empty tree should have 0 height.
        assertFalse(tree.delete(23.7));         // Removing non-existing key should return false.
        assertFalse(tree.search(23.7));         // Searching non-existing key should return false.
    }

    @Test
    void test_multiple_right_insertions()
    {
        final int n_insertions = 40;
        AVL_Tree<Integer> tree = new AVL_Tree<>();

        for (int i = 1; i <= n_insertions; ++i)
        {
            assertTrue(tree.insert(i));             // Check the node made is in
            assertEquals(i, tree.size());           // Check the size is updated after each insertion
            assertTrue(tree.height() <= 1.44 * Math.log(tree.size()) / Math.log(2)); // height should have upper limit 1.44log2(N)
        }

        tree.traverse();

        for (int i = 1; i <= n_insertions; ++i)
        {
            assertTrue(tree.search(i));         // search should return true.
            assertTrue(tree.delete(i));         // delete should return true.
        }
        assertFalse(tree.search(1));        // search should return False.
        assertEquals(0, tree.size());   // Empty tree should have 0 nodes.
        assertEquals(0, tree.height()); // Empty tree should have 0 height.
    }

    @Test
    void test_multiple_left_insertions()
    {
        final int n_insertions = 40;
        AVL_Tree<Integer> tree = new AVL_Tree<>();

        for (int i = n_insertions; i > 0; --i)
        {
            assertTrue(tree.insert(i));             // Check the node made is in
            assertEquals(n_insertions - i + 1, tree.size());           // Check the size is updated after each insertion
            assertTrue(tree.height() <= 1.44 * Math.log(tree.size()) / Math.log(2)); // height should have upper limit 1.44log2(N)
        }

        tree.traverse();

        for (int i = n_insertions; i > 0; --i)
        {
            assertTrue(tree.search(i));         // search should return true.
            assertTrue(tree.delete(i));         // delete should return true.
        }
        assertFalse(tree.search(1));        // search should return False.
        assertEquals(0, tree.size());   // Empty tree should have 0 nodes.
        assertEquals(0, tree.height()); // Empty tree should have 0 height.
    }

    @Test
    void test_right_left_rotation()
    {
        AVL_Tree<String> tree = new AVL_Tree<>();
        assertTrue(tree.insert("Galal"));      // Root
        assertTrue(tree.insert("Moaz"));       // Right child of root
        assertTrue(tree.insert("Kamal"));      // left child of right child of root -> triggers RL rotation
        assertTrue(tree.search("Kamal"));
        assertEquals(1, tree.height());
        tree.traverse();
    }

    @Test
    void test_left_right_rotation()
    {
        AVL_Tree<Double> tree = new AVL_Tree<>();
        assertTrue(tree.insert(20.54));        // Root node
        assertTrue(tree.insert(13.11));        // Left child
        assertTrue(tree.insert(15.98));        // Right child of left child -> triggers LR rotation
        assertTrue(tree.search(13.11));
        assertEquals(1, tree.height());
        tree.traverse();
    }
}