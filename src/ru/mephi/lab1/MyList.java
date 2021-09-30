package ru.mephi.lab1;

public class MyList {

    private int currentSize = 0;
    private final int CONTAINER_CAPACITY = 10;
    private int numberOfContainers = 0;
    Object[] currentArray;

    public int size() {
        return currentSize;
    }

    public Object get(int index) {
        returnIndex(index);
        if (index == currentSize) { // My choice was to take valid index for adding elements, so I have to make it lower here
            index--;
        }
        return currentArray[index];
    }

    public void set(Object object, int index) {
        returnIndex(index);
        if (index == currentSize) { // In that case setting is just like adding
            add(object);
        } else {
            currentArray[index] = object;
        }
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean contains(Object object) {
        int cnt = 0;
        for (int i = 0; i < currentSize; i++) {
            if (currentArray[i] == object) {
                cnt++;
                break;
            }
        }
        return cnt > 0;
    }

    public int indexOf(Object object) {
        if (this.contains(object)) {
            int cnt = 0;
            for (int i = 0; i < currentSize; i++) {
                if (currentArray[i] == object) {
                    break;
                }
                cnt++;
            }
            return cnt;
        } else {
            return -1;
        }
    }

    public int returnIndex(int index) { // makes index valid for the task
        if (index < 0) {
            return 0;
        } else if (index > currentSize) {
            return currentSize;
        } else return index;
    }

    public void increase() { // method for increasing number of containers
        if (currentSize % CONTAINER_CAPACITY == 0) { // it means all the containers are full
            numberOfContainers++;
            Object[] newArray = new Object[CONTAINER_CAPACITY * numberOfContainers];
            System.arraycopy(currentArray, 0, newArray, 0, currentSize);
            currentArray = newArray;
        }
    }

    public void decrease() {
        if (CONTAINER_CAPACITY * (numberOfContainers - 1) >= currentSize) {
            numberOfContainers--;
            Object[] newArray = new Object[CONTAINER_CAPACITY * numberOfContainers];
            System.arraycopy(currentArray, 0, newArray, 0, currentSize);
            currentArray = newArray;
        }
    }

    public void add(Object object) {
        if (currentSize == 0) {
            numberOfContainers++;
            currentArray = new Object[CONTAINER_CAPACITY * numberOfContainers];
            currentArray[0] = object;
        } else {
            increase();
            currentArray[currentSize] = object;
        }
        currentSize++;
    }

    public void add(Object object, int index) {
        index = returnIndex(index);
        if (index == currentSize) {
            add(object);
        } else {
            increase();
            Object[] newArray = new Object[CONTAINER_CAPACITY * numberOfContainers];
            System.arraycopy(currentArray, 0, newArray, 0, index);
            newArray[index] = object;
            System.arraycopy(currentArray, index, newArray, index + 1, currentSize - index);
            currentArray = newArray;
            currentSize++;
        }
    }

    public Object remove(int index) {
        returnIndex(index);
        if (index == currentSize) { // I have to do it or it will be nothing to delete
            index--;
        }
        Object removedObject = currentArray[index];
        Object[] newArray = new Object[CONTAINER_CAPACITY * numberOfContainers];
        System.arraycopy(currentArray, 0, newArray, 0, index);
        System.arraycopy(currentArray, index + 1, newArray, index, currentSize - index - 1);
        currentArray = newArray;
        currentSize--;
        decrease();
        return removedObject;
    }

    public static void main(String[] args) {
//        // THERE ARE JUST SOME SILLY TESTS HERE
//        MyList list = new MyList();
//        System.out.println(list.isEmpty());
//        for (int i = 0; i<5; i++) {
//            list.add(i + 1);
//        }
//        for( int i = 5; i<10; i++) {
//            list.add(i + 1, i);
//        }
//        list.add("new2", 2);
//        list.add("new0", 0);
//        list.add("new20", 30);
//        for (int i = 0; i < list.currentSize; i++){
//            System.out.println(list.currentArray[i]);
//        }
//
//        System.out.println(list.currentSize);
//        System.out.println("/////////////");
//        System.out.println(list.contains(1));
//        System.out.println(list.isEmpty());
//        System.out.println(list.indexOf(1));
//        System.out.println(list.indexOf(40));
//        list.set(1,10);
//        System.out.println(list.currentArray[10]);
//        System.out.println(list.get(10));
//        System.out.println(list.currentSize);
//        System.out.println(list.currentArray[0]);
//        System.out.println(list.remove(1));
//        System.out.println(list.currentArray[1]);
//        System.out.println(list.currentSize);
//        System.out.println(list.CONTAINER_CAPACITY* list.numberOfContainers);
//        list.remove(1);
//        list.remove(2);
//        list.remove(3);
//        System.out.println(list.currentSize);
//        System.out.println(list.CONTAINER_CAPACITY* list.numberOfContainers);
    }
}