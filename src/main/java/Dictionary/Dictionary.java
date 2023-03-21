package Dictionary;

import BBSTs.AVL_Tree;
import BBSTs.ITree;
import BBSTs.RB_Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public int[] batchInsert(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner rd = new Scanner(file);
        int insert = 0, exist = 0;

        while (rd.hasNextLine()) {
            T data = (T)rd.nextLine();
            if(tree.insert(data))
                insert++;
            else
                exist++;
        }

        rd.close();
        return new int[]{insert, exist};
    }

    public int[] batchDelete(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner rd = new Scanner(file);
        int delete = 0, nonExist = 0;
        while (rd.hasNextLine()) {
            String data = rd.nextLine();
            if(tree.delete((T)data))
                delete++;
            else
                nonExist++;
        }
        rd.close();
        return new int[]{delete, nonExist};
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
