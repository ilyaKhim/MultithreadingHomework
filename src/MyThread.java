import java.util.Arrays;

public class MyThread {
    static final int SIZE = 1_000_000;
    static final int HALF = SIZE / 2;
    float[] arr = new float[SIZE];

    private float[] makeDefaultArr() {
        Arrays.fill(arr, 1);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - begin);
        return arr;
    }

    private synchronized float[] makeCoolArr() {
        Arrays.fill(arr, 1);
        long begin = System.currentTimeMillis();
        float[] coolArr1 = new float[HALF];
        float[] coolArr2 = new float[HALF];
        System.arraycopy(arr, 0, coolArr2, 0, HALF);
        System.arraycopy(arr, HALF, coolArr2, 0, HALF);

        Thread thread = new MathThread(coolArr1);
        Thread thread2 = new MathThread(coolArr2);

        try {
            thread.start();

            thread2.start();
            wait(150);

            //thread.join();
            //thread2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.arraycopy(coolArr1, 0, arr, 0, HALF);
        System.arraycopy(coolArr2, 0, arr, HALF, HALF);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
        return arr;
    }

    public class MathThread extends Thread {
        private float[] data;

        public MathThread(float[] data) {
            this.data = data;
        }

        @Override
        public void run() {
            for (int i = 0; i < HALF; i++) {
                data[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//                System.out.println(Thread.currentThread().getId() + " running");
            }
        }
    }

    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        float[] defaultArr = thread1.makeDefaultArr();
//        System.out.println(Arrays.toString(defaultArr));
        MyThread thread2 = new MyThread();
        float[] coolArr = thread2.makeCoolArr();
//        System.out.println(Arrays.toString(coolArr));
    }


}
