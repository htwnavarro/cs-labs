// This class represents an LinkedIntList of Nodes
// with a utility methods for:
// - adding elements
// - removing elements
// - joining two instances
// - comparing equality of two instances
// - getting a string representation of the list

import java.util.LinkedList;
import java.util.Objects;

public class LinkedIntList implements IntList {
    private LinkedList<Node> list;

    LinkedIntList() {
        list = new LinkedList<>();
    }

    // Pre: constructor has been called
    // Post: list is updated with new Node
    public void add(int value) {
        list.add(new Node(value));
    }

    // Pre: constructor has been called
    // Post: the size of the linked list
    public int size() {
        return list.size();
    }

    // Pre: constructor has been called, valid index is passed in
    // Post: the node value at the given position in the linked list
    public int get(int index) {
        if (!isValid()) {
            throw new IndexOutOfBoundsException();
        }

        return list.get(index).data;
    }

    // Pre: constructor has been called
    // Post: the index (or -1) for the position in the linked list where the Node data matches given value
    public int indexOf(int value) {
        return list.indexOf(new Node(value));
    }

    // Pre: constructor has been called
    // Post: updates the list so that a given value is inserted at a given index
    public void add(int index, int value) {
        if (!this.isValid(index)) {
            return;
        }

        list.add(index, new Node(value));
    }

    // Pre: constructor has been called, value has been inserted at the given index
    // Post: removes the value at a given position in the linked list
    public void remove(int index) {
        list.remove(index);
    }

    // Pre: constructor has been called, a new linked list is initialized
    // Post returns the combination of two linked lists starting at a given insertion index
    public void insertList(int index, LinkedIntList l) {
        if (!isValid()) {
            return;
        }

        for (int i = 0; i < l.size(); i++) {
            this.add(i + index, l.get(i));
        }
    }

    // Pre: constructor has been called
    // Post: a boolean for if two linked list instances are equal
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LinkedIntList that = (LinkedIntList) o;

        return Objects.equals(list, that.list);
    }

    // Pre: constructor has been called
    // Post: a string representation of the linked list node values
    public String toString() {
        if (list.size() == 0) {
            return "[]";
        } else {
            String result = "[" + list.get(0).data;
            for (int x = 1; x < list.size(); x++) {
                result += ", " + list.get(x).data;
            }
            result += "]";
            return result;
        }
    }

    // Pre: N/A
    // Post: a boolean representing if the instance is valid i.e. initialized
    private boolean isValid() {
        boolean isValid = true;

        if (list == null) {
            System.out.println("List is null");
            isValid = false;
        }

        return isValid;
    }

    //Pre: constructor has been called
    // Post: a boolean representing if the given index is a valid position in the linked list
    private boolean isValid(int index) {
        boolean isValid = true;

        try {
            list.get(index);
        } catch (IndexOutOfBoundsException error) {
            System.out.println(error);
            isValid = false;
        }

        return isValid;
    }
}
