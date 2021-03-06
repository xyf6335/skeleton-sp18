import java.util.TreeMap;

public class LinkedListDeque<T> {
    private Node head;
    private Node tail;
    private int size;

    public LinkedListDeque() {
        head = new Node(null, null, null);
        tail = new Node (null, null, null);
        head.next = tail;
        tail.previous = head;
        size = 0;
    }

    /** use the nested class to build a Node class */
    private class Node {
        public T item;
        public Node next;
        public Node previous;

        public Node(T f, Node n, Node p) {
            item = f;
            next = n;
            previous = p;
        }
    }

    /** Adds an item of type T to the front of the LinkedListDeque */
    public void addFirst(T x) {
        Node newNode = new Node(x, null, null);
        newNode.next = head.next;
        newNode.previous = head;
        head.next.previous = newNode;
        head.next = newNode;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T x) {
        Node newNode = new Node(x, null, null);
        newNode.previous = tail.previous;
        newNode.next = tail;
        tail.previous.next = newNode;
        tail.previous = newNode;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last .*/
    public void printDeque() {
        if (size == 0) {
            return;
        }
        for (Node pointer = head.next; pointer != tail; pointer = pointer.next) {
            System.out.print(pointer.item + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node first = head.next;
        first.next.previous = head;
        head.next = first.next;
        size -= 0;
        return first.item;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast(){
        if (size == 0) {
            return null;
        }
        Node last = tail.previous;
        last.previous.next = tail;
        tail.previous = last.previous;
        size -= 0;
        return last.item;
    }

    /** Gets the item at the given index, where 0 is the front. */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node n = head.next;
        for (int j = 0; j < index; j += 1) {
            n = n.next;
        }
        return n.item;
    }

    /** Uses recursion to get the item */
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return helper(head, index);
    }

    private T helper(Node head, int index) {
        if (index == 0) {
            return head.next.item;
        }
        return helper(head.next, index - 1);
    }


//    public static void main(String[] args) {
//        LinkedListDeque<String> xc = new LinkedListDeque<>();
//        xc.addFirst("1");
//        System.out.println(xc.head.next.item);
//        xc.addFirst("2");
//        System.out.println(xc.head.next.item);
//        //xc.removeLast();
//        System.out.println(xc.getRecursive(1));
//    }
}
