package BBSTs;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TimeAnalysisTest {
    public double[] time_insert_delete(ITree<Integer> tree, int n) {
        final Random r = new Random();
        long startTime, insertTime, deleteTime, height;
        double meanInsertTime, meanDeleteTime;
        int[] keys = new int[n];

        for (int i = 0; i < n; i++){
            keys[i] = r.nextInt(n * 10) + 1;
        }

        startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++){
            tree.insert(keys[i]);
        }
        insertTime = System.currentTimeMillis() - startTime;
        meanInsertTime = insertTime * 1.0 / n;

        height = tree.height();

        startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++){
            tree.delete(keys[i]);
        }
        deleteTime = System.currentTimeMillis() - startTime;
        meanDeleteTime = deleteTime * 1.0 / n;

        return new double[]{height, meanInsertTime, meanDeleteTime};
    }

    @Test
    public void run(){
        AVL_Tree<Integer> avlTree = new AVL_Tree<>();
        RB_Tree<Integer> rbTree = new RB_Tree<>();

        for (int i = 10; i <= 1000000; i *= 10){
            double[] avlResults = time_insert_delete(avlTree, i);
            double[] rbResults = time_insert_delete(rbTree, i);

            System.out.println("AVL: height= " + (long)avlResults[0] + ", mean insert time= " + avlResults[1] + " ms, mean delete time= " + avlResults[2] + " ms");
            System.out.println("RB: height= " + (long)rbResults[0] + ", mean insert time= " + rbResults[1] + " ms, mean delete time= " + rbResults[2] + " ms");
            System.out.println();
        }
    }
}
