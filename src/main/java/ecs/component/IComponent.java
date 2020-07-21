package ecs.component;

import java.nio.ByteBuffer;

public interface IComponent {

    int buffersize();

    int length();

    ByteBuffer createDataBuffer(int size);

    int[] getAllComponents();

    void setDouble(int id, int offset, double value);

    double getDouble(int id, int offset);

    void setFloat(int id, int offset, float value);

    float getFloat(int id, int offset);

    void setInt(int id, int offset, int value);

    int getInt(int id, int offset);

}
