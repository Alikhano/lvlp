package task2;

import java.util.Arrays;

public class DynamicQueue<E> {

    private E[] baseArr;
    private int capacity = 10;
    private int front;
    private int end;
    private int queueSize;

    public DynamicQueue() {
        baseArr = (E[])new Object[this.capacity];
        front = 0;
        end = -1;
        queueSize = 0;
    }

    public boolean enqueue (E number) {
        if (queueSize == baseArr.length) {
            reSize();
            enqueue(number);
        }
        queueSize++;
        if (end + 1 == baseArr.length) end = 0;
        baseArr[++end] = number;
        return true;
    }

    public E dequeue () {
        queueSize--;
        E number = baseArr[front];
        if (front + 1 == baseArr.length) front = 0;
        else front++;
        return number;
    }

    public String printQueue() {
        for (int i = 0; i < baseArr.length - 1; i++) {
            if (baseArr[i] != null) {
                System.out.println(baseArr[i].toString());
            }
        }
        return Arrays.toString(baseArr);
    }

    public boolean reSize() {
        int secondCap = this.baseArr.length*2;
        E[] secondQue = (E[]) new Object[secondCap];
        int current = front;
        int oldSize = capacity;
        for (int i = 0; i < baseArr.length - 1; i++) {
            secondQue[i] = baseArr[current];
            current++;
            if (current == this.baseArr.length) {
                current = 0;
            }
        }
        this.baseArr = secondQue;
        front = 0;
        end = secondCap - 1;
        capacity = secondCap;
        return secondCap > oldSize;
    }

    public String toString() { return Arrays.toString(baseArr); }
}

