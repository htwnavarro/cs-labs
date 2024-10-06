// this is a runnable demonstration of ArrayIntList
// it runs each method with a new instance of ArrayIntList
// and demonstrates catching the thrown relevant exceptions

public class Main {
    public static void main(String[] args) {
        addElements();

        addElementsByIndex();

        removeElementByIndex();

        getValue();

        getIndex();

        throwExceptions();
    }

    private static ArrayIntList addElements() {
        ArrayIntList arrayIntList = new ArrayIntList();

        arrayIntList.add(0);
        arrayIntList.add(1);
        arrayIntList.add(2);
        arrayIntList.add(3);
        arrayIntList.add(4);
        arrayIntList.add(5);
        arrayIntList.add(6);
        arrayIntList.add(7);
        arrayIntList.add(8);
        arrayIntList.add(9);
        arrayIntList.add(10);

        System.out.println("add elements: " + arrayIntList.toString());

        return arrayIntList;
    }

    private static ArrayIntList addElementsByIndex() {
        ArrayIntList arrayIntList = new ArrayIntList();
        arrayIntList.add(0, 1);
        arrayIntList.add(1, 2);
        arrayIntList.add(2, 3);
        arrayIntList.add(3, 4);
        arrayIntList.add(4, 5);
        arrayIntList.add(5, 6);
        arrayIntList.add(6, 7);
        arrayIntList.add(7, 8);
        arrayIntList.add(8, 9);
        arrayIntList.add(9, 10);

        System.out.println("add elements by index: " + arrayIntList.toString());

        return arrayIntList;
    }

    private static ArrayIntList removeElementByIndex() {
        ArrayIntList arrayIntList = new ArrayIntList();

        arrayIntList.add(0, 1);

        System.out.println("added one element by index: " + arrayIntList.toString());

        arrayIntList.remove(0);

        System.out.println("removed by index -- should be empty: " + arrayIntList.toString());

        return arrayIntList;
    }

    private static ArrayIntList getValue() {
        ArrayIntList arrayIntList = new ArrayIntList();

        arrayIntList.add(1);

        System.out.println("value: " + arrayIntList.get(0));

        return arrayIntList;
    }

    private static ArrayIntList getIndex() {
        ArrayIntList arrayIntList = new ArrayIntList();

        arrayIntList.add(1);

        System.out.println("index: " + arrayIntList.indexOf(1));

        return arrayIntList;
    }

    private static Exception[] throwExceptions() {
        ArrayIntList noSpaceArrayIntList = new ArrayIntList(0);
        Exception[] exceptions = new Exception[2];
        try {
            noSpaceArrayIntList.add(1);
        } catch (IllegalStateException e) {
            exceptions[0] = e;
        }

        try {
            noSpaceArrayIntList.remove(0);
        } catch (IndexOutOfBoundsException e) {
            exceptions[1] = e;
        }

        System.out.println("Caught exceptions: " + exceptions.length);

        return exceptions;
    }
}

/*
add elements: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
add elements by index: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
added one element by index: [1]
removed by index -- should be empty: []
value: 1
index: 0
Caught exceptions: 2
 */