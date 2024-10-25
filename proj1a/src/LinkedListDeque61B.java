import java.util.List;
import java.util.ArrayList; // import the ArrayList class

/* creating a circular list*/
public class LinkedListDeque61B<T> implements Deque61B<T> {

    private class Node {
        private T item;
        private Node next;
        private Node prev;

        public Node(Node p, T i, Node n) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        sentinel.prev = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> testList = new ArrayList<>();
        Node pointer = sentinel.next;
        while (pointer != sentinel) {
            testList.add(pointer.item);
            pointer = pointer.next;
        }
        return testList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T val = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return val;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T val = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return val;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }

        Node pointer = sentinel.next;
        while (index != 0) {
            pointer = pointer.next;
            index--;
        }
        return pointer.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index > size) {
            return null;
        }

        return getHelper(index, sentinel.next);
    }

    private T getHelper(int index, Node pointer) {
        if (index == 0) {
            return pointer.item;
        } else {
            return getHelper(index--, pointer.next);
        }
    }
}
