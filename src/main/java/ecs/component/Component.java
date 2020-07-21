package ecs.component;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public abstract class Component implements IComponent {

    public static final int EMPTY_ID = -1;

    final List<ComponentBuffer> buffers = new ArrayList<>();
    private static int counter = 1;

    protected void init() {
        newBuffer();
    }

    private ComponentBuffer newBuffer() {
        int buffersize = buffersize();
        int length = length();
        ComponentBuffer componentBuffer = new ComponentBuffer(buffersize, length, createDataBuffer());
        buffers.add(componentBuffer);
        return componentBuffer;
    }

    public int create() {
        int id = counter++;

        boolean idxadded = false;

        for (ComponentBuffer buffer : buffers) {
            if (buffer.full())
                continue;
            idxadded = buffer.putNewComponent(id) != -1;
            if (idxadded)
                return id;
        }

        if (!idxadded) {
            ComponentBuffer componentBuffer = newBuffer();
            int i = componentBuffer.putNewComponent(id);
            if (i != EMPTY_ID)
                return id;
        }

        counter--;
        return EMPTY_ID;
    }

    public void remove(int id) {
        for (ComponentBuffer buffer : buffers) {
            int i = buffer.indexOf(id);
            if (i != -1)
                buffer.remove(i);
        }
    }

    public int count() {
        int sum = 0;
        for (ComponentBuffer buffer : buffers) {
            sum += buffer.size();
        }
        return sum;
    }

    public int getInt(int id, int offset) {
        for (ComponentBuffer buffer : buffers) {
            int idx = buffer.indexOf(id);
            if (idx == -1)
                continue;

            return buffer.getInt(idx, offset);
        }
        return 0;
    }


    public void setInt(int id, int offset, int value) {
        for (ComponentBuffer buffer : buffers) {
            int idx = buffer.indexOf(id);
            if (idx == -1)
                continue;

            buffer.setInt(idx, offset, value);
        }
    }

    public double getDouble(int id, int offset) {
        for (ComponentBuffer buffer : buffers) {
            int idx = buffer.indexOf(id);
            if (idx == -1)
                continue;

            return buffer.getDouble(idx, offset);
        }
        return 0;
    }


    public void setDouble(int id, int offset, double value) {
        for (ComponentBuffer buffer : buffers) {
            int idx = buffer.indexOf(id);
            if (idx == -1)
                continue;

            buffer.setDouble(idx, offset, value);
            break;
        }

    }

    public float getFloat(int id, int offset) {
        for (ComponentBuffer buffer : buffers) {
            int idx = buffer.indexOf(id);
            if (idx == -1)
                continue;

            return buffer.getFloat(idx, offset);
        }
        return 0;
    }


    public void setFloat(int id, int offset, float value) {
        for (ComponentBuffer buffer : buffers) {
            int idx = buffer.indexOf(id);
            if (idx == -1)
                continue;

            buffer.setFloat(idx, offset, value);
            break;
        }

    }

    @Override
    public int[] getAllComponents() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (ComponentBuffer buffer : buffers) {
            ids.addAll(buffer.getAllComponents());
        }
        int[] arr = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) arr[i] = ids.get(i);
        return arr;
    }

    protected ByteBuffer createDataBuffer() {
        return DataBufferFactory.createDataBuffer(buffersize() * length());
    }

    public int buffercount() {
        return buffers.size();
    }
}
