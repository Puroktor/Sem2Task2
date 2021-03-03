package ru.vsu.cs.skofenko;

import java.util.Collection;
import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {

    private class LinkedListNode<T> {
        T value;
        LinkedListNode<T> next;

        private LinkedListNode(T value) {
            this.value = value;
        }
    }

    private LinkedListNode<T> head = null;
    private LinkedListNode<T> tail = null;
    private int size = 0;

    public MyLinkedList() {
    }

    public MyLinkedList(Collection<? extends T> collection) {
        for (T element : collection) {
            this.add(element);
        }
    }//O(n)

    public MyLinkedList(T[] arr) {
        for (T element : arr) {
            this.add(element);
        }
    }//O(n)

    public int size() {
        return size;
    }//O(1)

    public boolean isEmpty() {
        return size == 0;
    }//O(1)

    public boolean contains(Object o) {
        LinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.value.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }//O(n)

    @Override
    public Iterator<T> iterator() {
        class LinkedListIterator implements Iterator<T> {
            LinkedListNode<T> curr;

            public LinkedListIterator(LinkedListNode<T> head) {
                curr = head;
            }

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T result = curr.value;
                curr = curr.next;
                return result;
            }
        }

        return new LinkedListIterator(head);
    }//O(1)

    public Object[] toArray() {
        Object[] arr = new Object[size];
        LinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.value;
            current = current.next;
        }
        return arr;
    }//O(n)

    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] a) {
        if (a.length < size)
            a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        Object[] result = a;

        LinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            result[i] = current.value;
            current = current.next;
        }

        if (a.length > size)
            a[size] = null;

        return a;
    }//O(n)

    public boolean add(T t) {
        LinkedListNode<T> node = new LinkedListNode<>(t);
        if (size > 0) {
            tail.next = node;
        } else {
            head = node;
        }
        tail = node;
        size++;
        return true;
    }//добавление в конец O(1)

    public boolean remove(Object o) {
        LinkedListNode<T> previous = null;
        LinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.value.equals(o)) {
                if (i > 0) {
                    previous.next = current.next;
                    if (i == size - 1) {
                        tail = previous;
                    }
                } else {
                    if (size == 1) {
                        head = tail = null;
                    } else {
                        head = current.next;
                    }
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }//O(n)

    public void clear() {
        head = tail = null;
        size = 0;
    }//O(1)

    public T get(int index) {
        checkIndex(index);
        return getNode(index).value;
    }//O(n)

    public T set(int index, T element) {
        checkIndex(index);
        LinkedListNode<T> node = getNode(index);
        T value = node.value;
        node.value = element;
        return value;
    }//O(n)

    public void add(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (size == 0) {
            head = tail = new LinkedListNode<>(element);
        } else {
            LinkedListNode<T> node = new LinkedListNode<>(element);
            if (index == 0) {
                node.next = head;
                head = node;
            } else if (index == size) {
                tail.next = node;
                tail = node;
            } else {
                LinkedListNode<T> prev = getNode(index - 1);
                node.next = prev.next;
                prev.next = node;
            }
        }
        size++;
    }//O(n)

    public T remove(int iTx) {
        checkIndex(iTx);
        T value;
        if (iTx > 0) {
            LinkedListNode<T> previous = getNode(iTx - 1);
            value = previous.next.value;
            previous.next = previous.next.next;
            if (iTx == size - 1) {
                tail = previous;
            }
        } else if (size == 1) {
            value = head.value;
            head = tail = null;
        } else {
            value = head.value;
            head = head.next;
        }
        size--;
        return value;
    }//O(n)

    public int indexOf(Object o) {
        LinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.value.equals(o)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }//O(n)

    public int lastIndexOf(Object o) {
        int index = -1;
        LinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.value.equals(o)) {
                index = i;
            }
            current = current.next;
        }
        return index;
    }//O(n)

    private LinkedListNode<T> getNode(int index) {
        LinkedListNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    public void shiftLeft(int quantity) {
        if (size < 2) {
            return;
        }
        quantity %= size;
        for (int i = 0; i < quantity; i++) {
            add(head.value);//O(1)
            remove(0);//O(1)
            //removeFirst();
        }
    }//O(n)
    /*
    private void removeFirst() {
        head = head.next;
        size--;
        //  без проверок, ибо size>1
    }*/
}
