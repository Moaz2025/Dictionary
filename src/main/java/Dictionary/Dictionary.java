package Dictionary;

import BBSTs.AVL_Tree;
import BBSTs.ITree;
import BBSTs.RB_Tree;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Dictionary<T extends Comparable<T>> {
    ITree<T> tree;

    public void initialize(String type){
        if (type.equals("AVL"))
            this.tree = new AVL_Tree<>();
        else if (type.equals("RB"))
            this.tree = new RB_Tree<>();
        else
            System.out.println("Invalid tree type");
    }

    public boolean search(T key) { return tree.search(key); }
    public boolean insert(T key){
        return tree.insert(key);
    }

    public boolean delete(T key) {
        return tree.delete(key);
    }

    public long[] batchInsert(String filePath) throws IOException {
        long insert = 0, exist = 0, startTime;

        File file = new File(filePath);
        FileInputStream fl = new FileInputStream(file);
        InputStreamReader st = new InputStreamReader(fl, StandardCharsets.UTF_8);
        BufferedReader rd = new BufferedReader(st);
        String key;

        startTime = System.currentTimeMillis();
        while ((key = rd.readLine()) != null) {
            T data = (T)key;
            if(tree.insert(data))
                insert++;
            else
                exist++;
        }
        System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + " ms");

        rd.close();
        return new long[]{insert, exist};
    }

    public long[] batchDelete(String filePath) throws IOException {
        long delete = 0, nonExist = 0, startTime;

        File file = new File(filePath);
        FileInputStream fl = new FileInputStream(file);
        InputStreamReader st = new InputStreamReader(fl, StandardCharsets.UTF_8);
        BufferedReader rd = new BufferedReader(st);
        String key;

        startTime = System.currentTimeMillis();
        while ((key = rd.readLine()) != null) {
            T data = (T)key;
            if(tree.delete(data))
                delete++;
            else
                nonExist++;
        }
        System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + " ms");

        rd.close();
        return new long[]{delete, nonExist};
    }

    public long size(){
        return tree.size();
    }

    public long height() {
        return tree.height();
    }

    public void clear() { tree.clear(); }

    public void traverse(){ tree.traverse(); }
}
