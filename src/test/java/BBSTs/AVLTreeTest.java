package BBSTs;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void testEmptyTree()
    {
        AVL_Tree<String> tree = new AVL_Tree<>();
        assertEquals(0, tree.size());   // Empty tree should have 0 nodes.
        assertEquals(0, tree.height()); // Empty tree should have 0 height.
        assertFalse(tree.delete("Galal"));  // Removing non-existing key should return false.
        assertFalse(tree.search("Galal"));  // Searching non-existing key should return false.
        assertEquals(0, tree.size());   // Empty tree should still have 0 nodes.
    }

    @Test
    void testSingleNode()
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
    void testInsertionOfDuplicateElement() {
        AVL_Tree<String> tree = new AVL_Tree<>();
        assertTrue(tree.insert("Karim"));
        assertTrue(tree.insert("Essam"));
        assertFalse(tree.insert("Karim"));
        tree.traverse();
    }

    @Test
    void testMultipleRightInsertions()
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
    void testMultipleLeftInsertions()
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
    void testRightLeftRotation()
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
    void testLeftRightRotation()
    {
        AVL_Tree<Double> tree = new AVL_Tree<>();
        assertTrue(tree.insert(20.54));        // Root node
        assertTrue(tree.insert(13.11));        // Left child
        assertTrue(tree.insert(15.98));        // Right child of left child -> triggers LR rotation
        assertTrue(tree.search(13.11));
        assertEquals(1, tree.height());
        tree.traverse();
    }

    @Test
    void testDeleteLeafNode() {
        AVL_Tree<Integer> tree = new AVL_Tree<>();
        assertTrue(tree.insert(10));
        assertTrue(tree.insert(5));
        assertTrue(tree.insert(15));
        assertTrue(tree.insert(2));
        assertTrue(tree.insert(7));
        assertTrue(tree.insert(12));
        assertTrue(tree.insert(17));
        assertTrue(tree.delete(2));
        assertFalse(tree.search(2));
        tree.traverse();
    }

    @Test
    void testDeleteNodeWithOneChild() {
        AVL_Tree<Integer> tree = new AVL_Tree<>();
        assertTrue(tree.insert(10));
        assertTrue(tree.insert(5));
        assertTrue(tree.insert(15));
        assertTrue(tree.insert(2));
        assertTrue(tree.insert(7));
        assertTrue(tree.insert(12));
        assertTrue(tree.insert(17));
        assertTrue(tree.delete(2));
        assertFalse(tree.search(2));
        assertTrue(tree.search(15));
        tree.traverse();
    }

    @Test
    void testDeleteNodeWithTwoChildren() {
        AVL_Tree<Integer> tree = new AVL_Tree<>();
        assertTrue(tree.insert(10));
        assertTrue(tree.insert(5));
        assertTrue(tree.insert(15));
        assertTrue(tree.insert(2));
        assertTrue(tree.insert(7));
        assertTrue(tree.insert(12));
        assertTrue(tree.insert(17));
        assertTrue(tree.delete(2));
        assertFalse(tree.search(2));
        assertTrue(tree.search(5));
        tree.traverse();
    }

    @Test
    void testDeleteRoot() {
        AVL_Tree<Double> tree = new AVL_Tree<>();
        assertTrue(tree.insert(11.2113));
        assertTrue(tree.insert(3.22423));
        assertTrue(tree.insert(30.757));
        assertTrue(tree.insert(1.5634));
        assertTrue(tree.insert(8.066));
        assertTrue(tree.insert(12.9087));
        assertTrue(tree.insert(56.234));
        assertTrue(tree.delete(11.2113));
        assertFalse(tree.search(11.2113));
        tree.traverse();
    }
    @Test
    void testDeleteLeafNodeWithRebalancing() {
        AVL_Tree<String> tree = new AVL_Tree<>();
        assertTrue(tree.insert("apple"));
        assertTrue(tree.insert("banana"));
        assertTrue(tree.insert("cherry"));
        assertTrue(tree.insert("dates"));
        assertTrue(tree.insert("elderberry"));
        assertTrue(tree.insert("fig"));
        assertTrue(tree.insert("grape"));
        assertTrue(tree.insert("kiwi"));
        assertTrue(tree.delete("dates"));
        assertFalse(tree.search("dates"));
        tree.traverse();
    }

    @Test
    void testDeleteNodeWithOneChildAndRebalancing() {
        AVL_Tree<Integer> tree = new AVL_Tree<>();
        assertTrue(tree.insert(10));
        assertTrue(tree.insert(5));
        assertTrue(tree.insert(15));
        assertTrue(tree.insert(2));
        assertTrue(tree.insert(7));
        assertTrue(tree.insert(12));
        assertTrue(tree.insert(17));
        assertTrue(tree.delete(15));
        assertFalse(tree.search(15));
        tree.traverse();
    }

    @Test
    void testDeleteNodeWithTwoChildrenAndRebalancing() {
        AVL_Tree<Integer> tree = new AVL_Tree<>();
        assertTrue(tree.insert(10));
        assertTrue(tree.insert(5));
        assertTrue(tree.insert(15));
        assertTrue(tree.insert(2));
        assertTrue(tree.insert(7));
        assertTrue(tree.insert(12));
        assertTrue(tree.insert(17));
        assertTrue(tree.delete(5));
        assertFalse(tree.search(5));
    }

    @Test
    void testDeleteNodeWithTwoChildrenAndSubsequentRebalancing() {
        AVL_Tree<Character> tree = new AVL_Tree<>();
        assertTrue(tree.insert('e'));
        assertTrue(tree.insert('c'));
        assertTrue(tree.insert('m'));
        assertTrue(tree.insert('a'));
        assertTrue(tree.insert('d'));
        assertTrue(tree.insert('i'));
        assertTrue(tree.insert('o'));
        assertTrue(tree.insert('g'));
        assertTrue(tree.insert('l'));
        assertTrue(tree.delete('m'));
        assertFalse(tree.search('m'));
        tree.traverse();
    }

    @Test
    void testClearMethod()
    {
        final int n_insertions = 20;
        final Random r = new Random();
        AVL_Tree<Double> tree = new AVL_Tree<>();

        for (int i = n_insertions; i > 0; --i)
        {
            assertTrue(tree.insert(r.nextDouble() * i));                                  // Check the node made is in
            assertEquals(n_insertions - i + 1, tree.size());                          // Check the size is updated after each insertion
            assertTrue(tree.height() <= 1.44 * Math.log(tree.size()) / Math.log(2)); // height should have upper limit 1.44log2(N)
        }

        tree.traverse();
        tree.clear();

        assertEquals(0, tree.size());   // Empty tree should have 0 nodes.
        assertEquals(0, tree.height()); // Empty tree should have 0 height.
    }
}