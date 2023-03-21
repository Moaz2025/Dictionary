package BBSTs;

public interface ITree<T>{
    boolean search(T key);
    boolean insert(T key);
    boolean delete(T key);
    long size();
    long height();
    void clear();
    void traverse();
}
