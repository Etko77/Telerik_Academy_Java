import java.util.LinkedList;
import java.util.List;

public class MyLinkedList<T> {
    private class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head;

    public MyLinkedList() {
    }

    public int getLast() {
        if(head == null) throw new IndexOutOfBoundsException();
        List<String> strings = new LinkedList<>();
        Node last = head;
        while(last.next != null){
            last = last.next;
        }
        return last.value;
    }
}
