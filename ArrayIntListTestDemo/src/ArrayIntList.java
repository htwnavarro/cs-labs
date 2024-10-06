// ArrayIntList aims to add functionality to Java ArrayList,
// a field of only valid elements and the number of only valid elements
// add, remove, equals methods make the native Java Array more user-friendly

public class ArrayIntList implements IntList {

    private int[] data; // array of integers
    private int size;   // current number of elements in the list

    public static final int CAPACITY = 20; // dynamic capacity

    // constructor -- primary
    public ArrayIntList() {
        data = new int[CAPACITY];
        size = 0;
    }
    // constructor -- a constructor overload for manual setting of the capacity
    // useful for test assertion on a known boundary
    public ArrayIntList(int capacity) {
        data = new int[capacity];
        size = 0;
    }


    // adds an item to the end of array
    // throws out of bounds exception
    public void add(int value) {
        checkCapacity(size + 1);
        data[size] = value;
        size++;
    }

    // returns the data in a visualized string format
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + data[0];
            for (int x = 1; x < size; x++) {
                result += ", " + data[x];
            }
            result += "]";
            return result;
        }
    }

    // returns the size of the ArrayLists valid values
    public int size() {
        return size;
    }

    // gets an index of the data at a given index
    public int get(int index) {
        checkIndex(index);
        return data[index];
    }

    // gets the index of a value in the ArrayList or
    // returns -1 for and for values not found
    public int indexOf(int value) {
        for (int x = 0; x < size; x++) {
            if (data[x] == value)
                return x;
        }
        return -1;
    }

    // An overloaded add method
    // adds an item to the array given array index and value
    // throws out of bounds exception
    public void add(int index, int value) {
        checkCapacity(size + 1);
        // this check makes this function less like add a value to a position and more like update a value at a position
        // checkIndex(index);
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    // Removes an item in the array given index
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = 0;
    }

    // checks if given object is exactly equal to an ArrayIntList
    public boolean equals(Object o) {
        // return true if the given object has the same instance value
        if (this == o)
            return true;
        // return false if the given object is null
        if (o == null)
            return false;
        // return false if the class name of the instance and the given object are different
        if (getClass() != o.getClass())
            return false;
        ArrayIntList list = (ArrayIntList) o;
        // return false if the given ArrayIntList object has a different size
        if (size != list.size()) {
            return false;
        }
        // return false if any position in the given ArrayIntList is different (order matters)
        for (int x = 0; x < size; x++) {
            if (data[x] != list.get(x)) {
                return false;
            }
        }
        return true;
    }

    // throws an IndexOutOfBoundsException if the given index is
    // not a legal index of the current list
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }

    // throwing an IllegalStateException given a capacity greater than the size of the data array,
    private void checkCapacity(int capacity) {
        if (capacity > data.length) {
            // out of bounds execption
            throw new IllegalStateException("would exceed list capacity of " + CAPACITY);
        }
    }
}