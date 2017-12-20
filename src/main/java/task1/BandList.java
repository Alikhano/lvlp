package task1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BandList<E> implements Iterable<E> {

    private Link linkOne;

    public BandList() {
        linkOne = null;
    }

    public int size() {
        int size = 0;
        Link current = linkOne;
        while (current != null) {
            size++;
            current = current.nextLink;
        }
        return size;
    }

    public boolean isEmpty() {
        return linkOne == null;
    }

    public boolean add(E value) {
        Link link = new Link(value);

        if (value == null) {
            System.out.println("Nothing to add");
            return false;
        }
        if (linkOne == null) {
            linkOne = link;
        } else {
            Link current = linkOne;
            while (current.nextLink != null) {
                current = current.nextLink;
            }
            current.nextLink = link;
        }
        return true;
    }

    public boolean remove(E value) {
        Link current = linkOne;
        if (current.info == null) {
            return false;
        }
        else if (current.info == value ) {
            linkOne = linkOne.nextLink;
            return true;
        }

        while (true) {
            Link next = current.nextLink;
            if(next == null) {
                return false;
            }
            else if (next.info == value) {
                break;
            }
            current = next;
        }
        Link toFollow = current.nextLink;
        current.nextLink = toFollow.nextLink;
        return true;
    }

    public String get(int index) {
        Link current = linkOne;
        int count = 0;
        while (count != index) {
            current = current.nextLink;
            count++;
        }
        System.out.println(current.info);
        return current.info.toString();
    }

    public boolean remove(int index)  {
        Link current = linkOne;
        if (index == 0) {
            linkOne = linkOne.nextLink;
        }
        else if (index > size()) {
            throw new NullPointerException();
        }
        else {

            for (int i = 0; i < index - 1; i++) {
                current = current.nextLink;
            }
            current.nextLink = current.nextLink.nextLink;
        }
        return true;
    }

    public Iterator<E> iterator() {
        return new BandIterator();
    }

    class BandIterator implements Iterator<E> {
        private Link<E> current;
        public BandIterator() {
            this.current = BandList.this.linkOne;
        }

        public boolean hasNext() {
            return this.current != null;
        }

        public E next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            E value = current.info;
            current = current.nextLink;
            return value;
        }
        public void remove() {}
    }
}
