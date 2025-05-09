package Marcus;

class SharedCounter {
    private int value = 0;

    public void increment() {
        value++;  
    }

    public int getValue() {
        return value;
    }
}

