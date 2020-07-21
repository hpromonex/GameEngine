package ecs.component.components;

import ecs.component.Component;

import java.nio.ByteBuffer;

public class CPositions extends Component {

    static final int X_OFF = 0;
    static final int Y_OFF = X_OFF + 8;
    static final int LENGTH = Y_OFF + 8;

    final int bufferSize;

    public CPositions() {
        this(100);
    }

    public int create(double x, double y) {
        int i = create();
        if (i == -1)
            return EMPTY_ID;

        setX(i, x);
        setY(i, y);
        return i;
    }

    public CPositions(int bufferSize) {
        this.bufferSize = bufferSize;
        init();
    }

    public double getX(int ID) {
        return getDouble(ID, X_OFF);
    }

    public double getY(int ID) {
        return getDouble(ID, Y_OFF);
    }

    public void setX(int ID, double x) {
        setDouble(ID, X_OFF, x);
    }

    public void setY(int ID, double y) {
        setDouble(ID, Y_OFF, y);
    }

    public int buffersize() {
        return bufferSize;
    }

    public int length() {
        return LENGTH;
    }

    public ByteBuffer createDataBuffer(int size) {
        return null;
    }
}
