package BBSTs;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class TimeAnalysisTest {
    public double[] time_insert_delete(ITree<Integer> tree, int n) {
        final Random r = new Random();
        double startTime, insertTime, meanInsertTime, deleteTime, meanDeleteTime;
        long height;
        int[] keys = new int[n];

        for (int i = 0; i < n; i++){
            keys[i] = r.nextInt(n * 10) + 1;
        }

        startTime = System.nanoTime();
        for (int i = 0; i < n; i++){
            tree.insert(keys[i]);
        }
        insertTime = System.nanoTime() - startTime;
        meanInsertTime = insertTime / n;

        height = tree.height();

        startTime = System.nanoTime();
        for (int i = 0; i < n; i++){
            tree.delete(keys[i]);
        }
        deleteTime = System.nanoTime() - startTime;
        meanDeleteTime = deleteTime / n;

        return new double[]{height,meanInsertTime, meanDeleteTime};
    }

    @Test
    public void run(){
        AVL_Tree<Integer> avlTree = new AVL_Tree<>();
        RB_Tree<Integer> rbTree = new RB_Tree<>();


        for (int i = 100; i <= 1000000; i *= 10){
            double[] avlResults = time_insert_delete(avlTree, i);
            double[] rbResults = time_insert_delete(rbTree, i);

            System.out.println("Tree size: " + i);
            System.out.println("AVL: height= " + (long)avlResults[0] + ", mean insert time= " + avlResults[1] + ", mean delete time= " + avlResults[2]);
            System.out.println("RB: height= " + (long)rbResults[0] + ", mean insert time= " + rbResults[1] + ", mean delete time= " + rbResults[2]);
        }
    }
}
