package demo.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class ByteBufferDemo {

    static final float fBuffer[] = {0.1f, 0.2f, 0.3f};

    public static void main(String[] args) {

        ByteBuffer bb = ByteBuffer.allocateDirect(fBuffer.length * 4);
        System.out.println("no.01  " + bb);

        System.out.println("ByteOrder.nativeOrder() " + ByteOrder.nativeOrder());
        bb.order(ByteOrder.nativeOrder());

        System.out.println("no.02  " + bb);

        FloatBuffer fb = bb.asFloatBuffer();
        System.out.println("no.03  " + fb);

        fb.put(fBuffer);
        fb.position(0);

        System.out.println("get.01 " + fb.get());
        System.out.println("get.02 " + fb.get());
    }
}
