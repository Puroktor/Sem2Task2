package ru.vsu.cs.skofenko;

public class InputData {
    private MyLinkedList<Integer> list;
    private int to;

    public MyLinkedList<Integer> getList() {
        return list;
    }

    public int getTo() {
        return to;
    }

    public InputData(MyLinkedList<Integer> list, int to) {
        this.list = list;
        this.to = to;
    }
}
