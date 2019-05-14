import java.util.Arrays;

public class MyThread  {
    static final int SIZE = 10;
    static final int HALF = SIZE/2;
    float[] arr = new float[SIZE];

    public float[] makeDefaultArr(){

        boolean fullOfOne = false;
        for(int i=0; i<SIZE; i++){
            arr[i] = 1;
        }
        long begin = System.currentTimeMillis();

        fullOfOne = true;
        if (fullOfOne){
            for(int i = 0; i<SIZE; i++){
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
        long end = System.currentTimeMillis();
        System.out.println( end - begin);

        return arr;
    }

    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        float[] defaultArr = thread1.makeDefaultArr();
        System.out.println(Arrays.toString(defaultArr));

    }
}
