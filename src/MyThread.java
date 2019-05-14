import java.util.Arrays;

public class MyThread  {
    static final int SIZE = 1_0;
    static final int HALF = SIZE/2;
    float[] arr = new float[SIZE];

    private float[] makeDefaultArr(){
        Arrays.fill(arr, 1);
        long begin = System.currentTimeMillis();
        for(int i = 0; i<SIZE; i++){
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println( System.currentTimeMillis() - begin);
        return arr;
    }

    private void  makeCoolArr() {
        Arrays.fill(arr,1);
        long begin = System.currentTimeMillis();
        System.out.println(begin);
        float[] coolArr1 = new float[HALF];
        float[] coolArr2 = new float[HALF];
        System.arraycopy(arr,0,coolArr1,0,HALF);
        System.arraycopy(arr,HALF,coolArr2,0,HALF);
        System.out.println(Arrays.toString(coolArr1) + "1");
        System.out.println(Arrays.toString(coolArr2) + "2");
    }

    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        float[] defaultArr = thread1.makeDefaultArr();
//        System.out.println(Arrays.toString(defaultArr));
        MyThread thread2 = new MyThread();
        thread2.makeCoolArr();

    }


}
