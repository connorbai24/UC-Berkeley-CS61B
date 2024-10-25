package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        public int count;
        public int start;
        public ArrayDequeIterator() {
            count = 0;
            start = head;
        }

        @Override
        public boolean hasNext() {
            if (count != size) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            T returnItem = items[(start) % items.length];
            count++;
            start++;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof ArrayDeque61B<?> AList) {
            if (this.size != AList.size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (this.get(i) != AList.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("[");
        for (int i = 0; i < size - 1; i += 1) {
            returnString.append(get(i));
            returnString.append(", ");
        }
        returnString.append(get(size - 1));
        returnString.append("]");

        return returnString.toString();
    }

    private T[] items;
    private int size;
    private int head;
    private int prevPointer;
    private int nextPointer;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        head = 0;
        prevPointer = 7;
        nextPointer = 0;
    }

    private void resizeUp() {
        int newSize = size * 2;
        T[] temp = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            temp[i] = items[(head + i) % items.length];
        }
        items = temp;

        head = 0;
        prevPointer = newSize - 1;
        nextPointer = size;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resizeUp();
        }
        items[nextPointer] = x;
        nextPointer++;
        size++;
    }

    @Override
    public void addFirst(T x) { // prepointer's range
        if (size == items.length) {
            resizeUp();
        }
        items[prevPointer] = x;
        head = prevPointer;
        prevPointer--;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.add(get(i));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    private void resizeDown() {
        T[] temp = (T[]) new Object[items.length / 2];
        for (int i = 0; i < size; i++) {
            temp[i] = items[(head + i) % items.length];
        }
        items = temp;

        head = 0;
        prevPointer = items.length - 1;
        nextPointer = size;
    }

    @Override
    public T removeFirst() {
        if (((double) size / items.length) < 0.25) {
            resizeDown();
        }
        T value = items[head];
        items[head] = null;
        if (prevPointer == items.length - 1) {
            prevPointer = 0;
        } else {
            prevPointer++;
        }
        head++;
        size--;
        return value;
    }

    @Override
    public T removeLast() {
        if (((double) size / items.length) < 0.25) {
            resizeDown();
        }
        T value = items[(head + size - 1) % items.length];
        items[(head + size - 1) % items.length] = null;
        if (nextPointer == 0) {
            nextPointer = items.length - 1;
        } else {
            nextPointer--;
        }
        size--;
        return value;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(head + index) % items.length];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
