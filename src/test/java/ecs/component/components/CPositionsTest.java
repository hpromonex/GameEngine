package ecs.component.components;

import ecs.component.Component;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CPositionsTest {

    @Test
    public void testComponent() {

        CPositions cPositions = new CPositions(2);

        int id = cPositions.create();

        assertTrue(id != Component.EMPTY_ID);

        cPositions.setX(id, 10);
        cPositions.setY(id, 5);

        assertEquals(10d, cPositions.getX(id));
        assertEquals(5d, cPositions.getY(id));

        for (int i = 0; i < 10; i++) {
            int id2 = cPositions.create(i, i * i);
            assertTrue(id2 != Component.EMPTY_ID);
            assertEquals(i, cPositions.getX(id2));
            assertEquals(i * i, cPositions.getY(id2));
        }
        assertEquals(6, cPositions.buffercount());


    }

}
