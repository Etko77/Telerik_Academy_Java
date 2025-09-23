public class ArrayList {
    private int capacity;
    private int size;
    private int[] data;

    public ArrayList() {
        capacity = 10;
        size = 0;
        data = new int[capacity];
    }

    public int getLast() {
        if(size == 0) throw new IndexOutOfBoundsException();
        return data[size-1];
    }
}
