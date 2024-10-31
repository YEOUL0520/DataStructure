public class ArrayStack<E> implements Stack <E> {
    public static final int CAPACITY = 1000; //크기를 1000으로 지정
    private E[] data;
    private int top = -1;

    public ArrayStack() {
        this(CAPACITY);
    } //Stack 배열을 생성하는 기본 생성자

    //매개변수로 크기를 받아오는 생성자 (사용자 지정 값으로 초기화)
    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return top + 1;
    } //top = -1이므로 크기는 +1인 0 (배열에 아무 것도 들어있지 않은 상태)

    public boolean isEmpty() {
        return top == -1;
    }  //만족할 경우 true

    public boolean isFull() {
        return size() == data.length;
    } //만족할 경우 true

    //throws A에서 A는 예외처리를 의미
    public void push(E e) throws IllegalStateException {
        if (isFull()) throw new IllegalStateException("Stack full"); //스택이 가득 차있을 경우 예외 처리
        data[++top] = e; //가득 차있지 않을 경우 top값을 1 증가시키고, 그곳에 e를 넣음
    }

    public E top() {
        if (isEmpty()) return null; //stack이 비어있을 경우 null값을 return
        return data[top]; //stack이 차 있을 경우 맨 위의 값을 return
    }

    public E pop() {
        if (isEmpty()) return null; //stack이 비어있을 경우 null값을 return
        E item = data[top]; //stack이 차있을 경우 현재 top값을 item에 저장하고
        data[top--] = null; //top에 저장된 값을 null로 바꾼 후 top을 1 감소시킴
        return item; //item (현재 top값)반환
    }
}
