package utils;

public class ArrayList<T> {
    private T[] data;
    private int size;
    private int tail;
    private int head;

    public ArrayList() {
        data = (T[]) new Object[10];

        for (int i = 0; i < 10; i++) {
            data[i] = null;
        }

        size = 0;
        tail = 0;
        head = 0;
    }

    public int size() {
        return this.size;
    }

    public T get(int index) {
        return data[index];
    }

    public void add(T item) {
        if (head == data.length) {
            grow();
        }
        data[head] = item;
        size++;
        head++;
    }

    // TODO
    public void remove(int index) {

    }

    private void grow() {
        var temp = (T[]) new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }
}
