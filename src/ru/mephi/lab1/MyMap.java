package ru.mephi.lab1;

public class MyMap {
    private int numberOfKeys;
    private Record[] records;
    private final int CONTAINER_CAPACITY = 10;
    private int numberOfContainers = 0;

    public static class Record {
        private Object key;
        private Object value;

        public Record(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

    }

    public void increase() {
        if (numberOfKeys % CONTAINER_CAPACITY == 0) { // it means all the containers are full
            numberOfContainers++;
            Record[] newRecord = new Record[CONTAINER_CAPACITY * numberOfContainers];
            System.arraycopy(records, 0, newRecord, 0, numberOfKeys);
            records = newRecord;
        }

    }

    public void decrease() {
        if (CONTAINER_CAPACITY * (numberOfContainers - 1) >= numberOfKeys) {
            numberOfContainers--;
            Record[] newArray = new Record[CONTAINER_CAPACITY * numberOfContainers];
            System.arraycopy(records, 0, newArray, 0, numberOfKeys);
            records = newArray;
        }
    }

    public void put(Object key, Object value) {
        if (numberOfKeys == 0) {
            numberOfContainers++;
            records = new Record[numberOfContainers * CONTAINER_CAPACITY];
            records[0] = new Record(key, value);
        } else {
            increase();
            records[numberOfKeys] = new Record(key, value);
        }
        numberOfKeys++;
    }

    public Object get(Object key) {
        int index = findIndexOfKey(key);
        if (index == -1) {
            return null;
        }
        return records[index].value;

    }

    public int findIndexOfKey(Object key) {
        for (int i = 0; i < numberOfKeys; i++) {
            if (records[i].key.equals(key)) {
                return i;
            }
        }
        return -1;  // in case there is no such a key in records
    }

    public Object get(Object key, Object byDefault) {
        int index = findIndexOfKey(key);
        if (index == -1) {
            put(key, byDefault);
            return byDefault;
        }
        if (records[index].value == null) {
            records[index].value = byDefault;
            return byDefault;
        }
        return records[index].value;
    }

    public Object remove(Object key) {
        int index = findIndexOfKey(key);
        if (index == -1) {
            return null;
        }
        Object removedObject = records[index].value;
        Record[] newRecord = new Record[CONTAINER_CAPACITY * numberOfContainers];
        System.arraycopy(records, 0, newRecord, 0, index);
        System.arraycopy(records, index + 1, newRecord, index, numberOfKeys - index - 1);
        records = newRecord;
        numberOfKeys--;
        decrease();
        return removedObject;
    }

    public boolean keyContains(Object key) {
        return findIndexOfKey(key) != -1;
    }

    public MyList getKeys() {
        MyList keyList = new MyList();
        for (int i = 0; i < numberOfKeys; i++) {
            keyList.currentArray[i] = records[i].key;
        }
        return keyList;
    }

    public MyList getValues() {
        MyList valueList = new MyList();
        for (int i = 0; i < numberOfKeys; i++) {
            valueList.currentArray[i] = records[i].value;
        }
        return valueList;
    }

    public MyList getEntries() {
        MyList entriesList = new MyList();
        for (int i = 0; i < numberOfKeys; i++) {
            entriesList.currentArray[i] = records[i];
        }
        return entriesList;
    }

    public int size() {
        return numberOfKeys;
    }

    public boolean isEmpty() {
        return numberOfKeys == 0;
    }

//    public static void main(String[] args) {
//        MyMap map = new MyMap();
//        map.put(1234, "Dean");
//        System.out.println(map.size());
//        System.out.println(map.findIndexOfKey(1234));
//        System.out.println(map.records[0].key);
//        System.out.println(map.records[0].value);
//        map.put(4567, "Peter");
//        System.out.println(map.get(4567));
//        System.out.println(map.remove(1234));
//        System.out.println(map.keyContains(4567));
//        System.out.println(map.findIndexOfKey(4567));
//        System.out.println(map.numberOfKeys);
//        System.out.println(map.isEmpty());
//        System.out.println(map.size());
//        System.out.println(map.records[0].value);
//    }
}
