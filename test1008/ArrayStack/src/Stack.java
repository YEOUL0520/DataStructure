public interface Stack <E>{
    int size();
    boolean isEmpty();
    boolean isFull();
    void push(E e);
    E top();
    E pop();
}
