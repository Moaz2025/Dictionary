package BBSTs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RBTreeTest {

    @Test
    public void test_empty_tree() {
        RB_Tree<String> tree = new RB_Tree<>();
        assertTrue(tree.isEmpty());
        assertFalse(tree.delete("kamal"));
        assertFalse(tree.search("kamal"));
        assertEquals(0, tree.size());
        assertEquals(0, tree.height());
    }
    @Test
    void test_single_node()
    {
        RB_Tree<String> tree = new RB_Tree<>();
        tree.insert("Mohamed");


        assertEquals(1, tree.size());
        assertEquals(0, tree.height());
        assertFalse(tree.delete("Ahmed"));
        assertTrue(tree.search("Mohamed"));
        assertEquals(1, tree.size());

        assertTrue(tree.delete("Mohamed"));
        assertEquals(0, tree.size());
        assertEquals(0, tree.height());
        assertFalse(tree.delete("Mohamed"));
        assertFalse(tree.search("Mohamed"));
    }

    @Test
    public void clearNonEmptyTreeIsEmptyAndSizeIs0() {
        RB_Tree<Integer> tree = new RB_Tree<>();
        for (int i = 1; i <= 10; i++) {
            tree.insert(i);
        }
        assertFalse(tree.isEmpty());
        assertTrue(tree.size() > 0);
        assertTrue(tree.search(2));
        assertFalse(tree.search(11));
        assertEquals(10,tree.size());
        assertEquals(4,tree.height());

        tree.clear();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertFalse(tree.search(1));

    }

    @Test
    void test_multiple_right_insertions()
    {
        final int n_insertions = 40;
        RB_Tree<Integer> tree = new RB_Tree<>();

        for (int i = 1; i <= n_insertions; ++i)
        {
            assertTrue(tree.insert(i));             // Check the node made is in
            assertEquals(i, tree.size());           // Check the size is updated after each insertion
            assertTrue(tree.height() <=2 * Math.log(tree.size()+1) / Math.log(2)); // height should have upper limit 1.44log2(N)
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
        RB_Tree<Integer> tree = new RB_Tree<>();

        for (int i = n_insertions; i > 0; --i)
        {
            assertTrue(tree.insert(i));             // Check the node made is in
            assertEquals(n_insertions - i + 1, tree.size());           // Check the size is updated after each insertion
            assertTrue(tree.height() <= 2 * Math.log(tree.size()) + 1 / Math.log(2)); // height should have upper limit 1.44log2(N)
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
        RB_Tree<String> tree = new RB_Tree<>();
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
        RB_Tree<Double> tree = new RB_Tree<>();
        assertTrue(tree.insert(20.54));        // Root node
        assertTrue(tree.insert(13.11));        // Left child
        assertTrue(tree.insert(15.98));        // Right child of left child -> triggers LR rotation
        assertTrue(tree.search(13.11));
        assertEquals(1, tree.height());
        tree.traverse();
    }
    @Test
    void testDeleteLeafNode() {
        RB_Tree<Integer> tree = new RB_Tree<>();
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
        RB_Tree<Integer> tree = new RB_Tree<>();
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
        RB_Tree<Integer> tree = new RB_Tree<>();
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
        RB_Tree<Double> tree = new RB_Tree<>();
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
        RB_Tree<String> tree = new RB_Tree<>();
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
        RB_Tree<Integer> tree = new RB_Tree<>();
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
        RB_Tree<Character> tree = new RB_Tree<>();
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




    //////////insert problems ////////

    //Parent and Uncle Nodes Are Red
    @Test
    void test_insert_case1()
    {
        RB_Tree<Integer> tree = new RB_Tree<>();
        tree.insert(17);    //                17 B                              17 B
        tree.insert(9);     //             /        \                        /        \
        tree.insert(19);    //           9 B       19 B        ---------->  9 B       19 R
        tree.insert(18);    //                    /    \                             /    \
        tree.insert(75);    //                  18 R   75 R                        18 B   75 B
        tree.insert(81);    //                           \                                  \
                                //                           81 R                               81 R
        assertEquals(6, tree.size());
        assertEquals(3, tree.height());
        tree.traverse(); //9B 17B 18B 19R 75B 81R
    }

    //Parent Node Is Red, Uncle Node Is Black, Inserted Node Is "Inner Grandchild"
    @Test
    void test_insert_case2()
    {
        RB_Tree<Integer> tree = new RB_Tree<>();
        tree.insert(17);    //                17 B                              17 B
        tree.insert(9);     //             /        \                        /        \
                                //           9 B       19 B        ---------->  9 B       24 B
        tree.insert(19);    //                         \                              /   \
        tree.insert(75);    //                         75 R                         19 R   75 R
        tree.insert(24);    //                          /
                                //                        24 R
        assertEquals(5, tree.size());
        assertEquals(2, tree.height());
        tree.traverse(); //9B 17B 19R 24B 75R
    }
    //Parent Node Is Red, Uncle Node Is Black, Inserted Node Is "Outer Grandchild"
    @Test
    void test_insert_case3()
    {
        RB_Tree<Integer> tree = new RB_Tree<>();
        tree.insert(17);    //                17 B                              17 B
        tree.insert(9);     //             /        \                        /        \
        tree.insert(19);    //           9 B       19 B        ---------->  9 B       75 B
                                //                         \                             /    \
        tree.insert(75);    //                        75 R                        19 R   81 R
        tree.insert(81);    //                           \
                                //                           81 R
        assertEquals(5, tree.size());
        assertEquals(2, tree.height());
        tree.traverse(); //9B 17B 19R 75B 81R
    }
    //////////Delete problems ////////
    //Sibling Is Red
    @Test
    void test_delete_case1(){
        RB_Tree<Integer> tree = new RB_Tree<>();
        tree.insert(17);    //                17 B                              19 B
        tree.insert(9);     //             /        \                        /        \
        tree.insert(19);    //           9 B       19 B        ---------->  17 B       75 R
        tree.insert(18);    //                    /    \                      \
        tree.insert(75);    //                  18 R   75 R                   18 R

        tree.traverse(); //9B 17B 18R 19B 75R
        tree.delete(9);
        System.out.println();
        tree.traverse();//17B 18R 19B 75B
    }

    // Sibling is black and has at least one red child, "outer nephew" is black
    @Test
    void test_delete_case2(){
        RB_Tree<Integer> tree = new RB_Tree<>();
        tree.insert(17);    //                17 B                              17 B
        tree.insert(9);     //             /        \                        /        \
        tree.insert(19);    //           9 B       19 R        ---------->  9 B       24 R
        tree.insert(18);    //                    /    \                             /   \
        tree.insert(75);    //                   18 B  75 B                       19 B   75 B
        tree.insert(24);    //                           /
                                //                         24 R
        tree.traverse(); //9B 17B 18B 19R 24R 75B
        tree.delete(18);
        System.out.println();
        tree.traverse();//9B 17B 19B 24R 75B
    }
   //Sibling is black and has at least one red child, "outer nephew" is red
    @Test
    void test_delete_case3(){
        RB_Tree<Integer> tree = new RB_Tree<>();
        tree.insert(17);    //                17 B                              17 B
        tree.insert(9);     //             /        \                        /        \
        tree.insert(19);    //           9 B       19 R        ---------->  9 B       75 R
        tree.insert(18);    //                    /    \                             /   \
        tree.insert(75);    //                   18 B  75 B                       19 B   75 B
        tree.insert(24);    //                         /   \                         \
        tree.insert(81);    //                       24 R   81 R                     24 R
        tree.traverse(); //9B 17B 18B 19R 24R 75B 81R
        tree.delete(18);
        System.out.println();
        tree.traverse();//9B 17B 19B 24R 75R 81B
    }
    
}
