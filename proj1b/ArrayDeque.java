public class ArrayDeque<Item> implements Deque<Item>{
    private Item[] items;
    private int size;
    private int headIndex;
    private int tailIndex;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        headIndex = 0;
        tailIndex = 1;
    }

    /** Resizes the capacity of ArrayDeque by doubling it */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        int i = headIndex;
        for (int j = 0; j < size + 2; j += 1) {
            a[i] = items[i];
            i = nIndex(i);
        }
        items = a;
    }

    /** Helps return the previous index to the existing one. */
    private int pIndex(int i) {
        int len = items.length;
        return i = (i + len - 1) % len;
    }

    /** Helps return the next index to the existing one. */
    private int nIndex(int i) {
        int len = items.length;
        return i = (i + 1) % len;
    }

    /** Adds an item of type T to the front of the ArrayDeque */
    @Override
    public void addFirst(Item x) {
        if (size == items.length - 2) {
            resize(items.length * 2);
            //System.out.println("resizing");
        }
        items[headIndex] = x;
        size += 1;
        headIndex = pIndex(headIndex);
        //System.out.println("size = " + size);
    }

    /** Adds an item of type T to the back of the ArrayDeque */
    @Override
    public void addLast(Item x) {
        if (size == items.length - 2) {
            resize(items.length * 2);
            System.out.println("resizing");
        }
        items[tailIndex] = x;
        size += 1;
        tailIndex = nIndex(tailIndex);
        System.out.println("tailIndex = " + tailIndex);
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last .*/
    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        }
        int len = items.length;
        int i = headIndex;
        for (int j = 0; j < size; j += 1) {
            i = nIndex(i);
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    @Override
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        int len = items.length;
        Item first = items[nIndex(headIndex)];
        items[nIndex(headIndex)] = null;
        headIndex = nIndex(headIndex);
        size -= 1;
        if (size / len < 0.25 && len > 16) {
            resize(items.length / 2);
        }
        return first;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    @Override
    public Item removeLast(){
        if (size == 0) {
            return null;
        }
        int len = items.length;
        Item last = items[pIndex(tailIndex)];
        items[pIndex(tailIndex)] = null;
        tailIndex = pIndex(tailIndex);
        size -= 1;
        if (size / len < 0.25 && len > 16) {
            resize(items.length / 2);
        }
        return last;
    }

    /** Gets the item at the given index, where 0 is the front. */
    @Override
    public Item get(int index) {
        if (index <0 || index >= size) {
            return null;
        }
        int realIndex = (headIndex + index) % items.length;
        return items[realIndex];
    }

    public static void main(String[] args) {
        ArrayDeque<String> xc = new ArrayDeque<>();
        xc.addLast("1");

        xc.printDeque();
        xc.addLast("2");
        xc.addLast("3");
        xc.addLast("4");
        xc.addLast("5");
        xc.addLast("6");
        xc.addLast("7");
        xc.printDeque();
        xc.addLast("8");
        xc.printDeque();
        xc.addLast("9");
        xc.printDeque();
        xc.removeLast();
        xc.printDeque();
    }
}
