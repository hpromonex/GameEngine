package ecs.component;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComponentBuffer {

    private static final int EMPTY = -1;

    private final int capacity;
    private int size = 0;
    private final ByteBuffer buffer;
    private final int[] compids;
    private final int complength;


    public ComponentBuffer(int capacity, int complength, ByteBuffer buffer) {
        assert buffer != null : "Buffer null";

        this.compids = new int[capacity];
        this.complength = complength;
        this.capacity = capacity;
        this.buffer = buffer;

        clear();

    }

    public void clear() {
        Arrays.fill(compids, EMPTY);
    }

    public int indexOf(int ID) {
        for (int i = 0; i < compids.length; i++) {
            if (compids[i] == ID)
                return i;
        }
        return -1;
    }

    public boolean contains(int ID) {
        return indexOf(ID) != -1;
    }

    public boolean full() {
        return size == capacity;
    }

    public int putNewComponent(int id) {
        if (full())
            return -1;

        int i;
        for (i = 0; i < compids.length; i++) {
            if (compids[i] == -1)
                break;
        }
        compids[i] = id;
        size++;
        return i;
    }

    public void remove(int i) {
        compids[i] = EMPTY;
        size--;
    }

    public int size() {
        return size;
    }

    public int getInt(int idx, int offset) {
        return buffer.getInt(idx * complength + offset);
    }

    public void setInt(int idx, int offset, int value) {
        buffer.putInt(idx * complength + offset, value);
    }

    public double getDouble(int idx, int offset) {
        return buffer.getDouble(idx * complength + offset);
    }

    public void setDouble(int idx, int offset, double value) {
        buffer.putDouble(idx * complength + offset, value);
    }

    public float getFloat(int idx, int offset) {
        return buffer.getFloat(idx * complength + offset);
    }

    public void setFloat(int idx, int offset, float value) {
        buffer.putFloat(idx * complength + offset, value);
    }

    public List<Integer> getAllComponents() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (int compid : compids) {
            if (compid != EMPTY)
                ids.add(compid);
        }
        return ids;
    }
}
