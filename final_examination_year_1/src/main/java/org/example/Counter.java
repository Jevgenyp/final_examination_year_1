package org.example;

public class Counter implements AutoCloseable {
    private int count = 0;
    private boolean isClosed = false;

    public void add() {
        if (isClosed) {
            throw new RuntimeException("Counter is closed!");
        }
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void close() {
        isClosed = true;
    }

    protected void finalize() {
        if (!isClosed) {
            throw new RuntimeException("Counter was not closed!");
        }
    }
}
