package Dictionary;

import Dictionary.Dictionary;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {

    @Test
    void testInsertAVL() {
        Dictionary<String> dictionary = new Dictionary<String>();
        dictionary.initialize("AVL");
        assertTrue(dictionary.insert("Moaz"));
        assertFalse(dictionary.insert("Moaz"));
        assertEquals(dictionary.size(), 1);
    }

    @Test
    void testInsertRB() {
        Dictionary<Double> dictionary = new Dictionary<Double>();
        dictionary.initialize("RB");
        assertTrue(dictionary.insert(34.56));
        assertFalse(dictionary.insert(34.56));
        assertEquals(dictionary.size(), 1);
    }

    @Test
    void testSearchAVL() {
        Dictionary<Integer> dictionary = new Dictionary<Integer>();
        dictionary.initialize("AVL");
        assertTrue(dictionary.insert(21));
        assertTrue(dictionary.search(21));
        assertEquals(dictionary.size(), 1);
    }

    @Test
    void testSearchRB() {
        Dictionary<String> dictionary = new Dictionary<String>();
        dictionary.initialize("RB");
        assertTrue(dictionary.insert("Kamal"));
        assertTrue(dictionary.search("Kamal"));
        assertEquals(dictionary.size(), 1);
    }

    @Test
    void testDeleteAVL() {
        Dictionary<Character> dictionary = new Dictionary<Character>();
        dictionary.initialize("AVL");
        assertTrue(dictionary.insert('C'));
        assertEquals(dictionary.size(), 1);
        assertTrue(dictionary.delete('C'));
        assertEquals(dictionary.size(), 0);
        assertFalse(dictionary.delete('C'));
    }

    @Test
    void testDeleteRB() {
        Dictionary<String> dictionary = new Dictionary<String>();
        dictionary.initialize("RB");
        assertTrue(dictionary.insert("Galal"));
        assertEquals(dictionary.size(), 1);
        assertTrue(dictionary.delete("Galal"));
        assertEquals(dictionary.size(), 0);
        assertFalse(dictionary.delete("Galal"));
    }

    @Test
    void testBatch() throws IOException {
        String path1 = "C:\\Users\\mosta\\Desktop\\Dictionary\\Dictionary-main\\src\\test\\java\\Dictionary\\batchInsertTest.txt";
        String path2 =  "C:\\Users\\mosta\\Desktop\\Dictionary\\Dictionary-main\\src\\test\\java\\Dictionary\\batchDeleteTest.txt";
        Dictionary<String> dict1 = new Dictionary<String>();
        Dictionary<String> dict2 = new Dictionary<String>();
        long[] res1, res2;

        dict1.initialize("AVL");
        dict2.initialize("RB");

        res1 = dict1.batchInsert(path1);
        res2 = dict2.batchInsert(path1);
        System.out.println();

        assertEquals(dict1.size(), dict2.size());

        System.out.println(res1[0] + " Inserted");
        System.out.println(res1[1] + " Exist");
        System.out.println();

        System.out.println(res2[0] + " Inserted");
        System.out.println(res2[1] + " Exist");
        System.out.println();

        System.out.println("AVL dictionary height: " + dict1.height());
        System.out.println("RB dictionary height: " + dict2.height());
        System.out.println();

        assertTrue(dict1.search("abattriez"));
        assertTrue(dict2.search("abattriez"));

        assertFalse(dict1.search("abfsdfsdddddd"));
        assertFalse(dict2.search("abfsdfsdddddd"));

        assertFalse(dict1.insert("abattriez"));
        assertFalse(dict2.insert("abattriez"));

        res1 = dict1.batchDelete(path2);
        res2 = dict2.batchDelete(path2);
        System.out.println();

        assertEquals(dict1.size(), dict2.size());

        System.out.println(res1[0] + " Deleted");
        System.out.println(res1[1] + " Doesn't exist");
        System.out.println();

        System.out.println(res2[0] + " Deleted");
        System.out.println(res2[1] + " Doesn't exist");
        System.out.println();

        System.out.println("AVL dictionary height: " + dict1.height());
        System.out.println("RB dictionary height: " + dict2.height());
        System.out.println();
    }
}