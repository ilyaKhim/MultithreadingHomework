
import java.util.Arrays;
import java.util.Scanner;

public class TestMyThread {
    static final int SIZE = 1_0;
    float[] arr = new float[SIZE];
    public static int hello(){
        System.out.println("На сколько частей будем дробить массив? Введите четное число!");
        Scanner sc = new Scanner(System.in);
        final int parts = sc.nextInt();
        return parts;
    }

    static final int PART = SIZE/hello();


    public  float[] makeDefArr(){
        Arrays.fill(arr,1);
        long begin = System.currentTimeMillis();
        calculate(SIZE,arr);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
        return arr;
    }

    public synchronized float[] makeCoolArr(){
        Arrays.fill(arr,1);
        long begin = System.currentTimeMillis();

//        System.out.println(parts);
        float arrays[][] = new float[PART][SIZE/PART];
        for (int i = 0; i < PART; i++) {
//            System.out.print(Arrays.toString(arrays[i]));
            System.arraycopy(arr,i*(SIZE/PART),arrays[i],0,SIZE/PART);
//            System.out.print(Arrays.toString(arrays[i]));
        }
        Thread[] threads = new Thread[PART];
        for (int i = 0; i <PART ; i++) {
            threads[i] = new MathThread(arrays[i]);
        }



        try {

            for (int i = 0; i <PART ; i++) {
                threads[i].start();
                threads[i].join();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i < PART; i++) {
//            System.out.print(Arrays.toString(arrays[i]));
            System.arraycopy(arrays[i],0,arr,i*(SIZE/PART),SIZE/PART);
//            System.out.print(Arrays.toString(arrays[i]));
        }
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
        return arr;
    }

    private  void  calculate(float size, float[] arr){
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    class MathThread extends Thread{
        private float[] data;
        public MathThread(float[] data){
            this.data = data;
        }
        @Override
        public void run(){
            calculate((float)SIZE/PART,data);
        }
    }

    public static void main(String[] args) {
        TestMyThread first = new TestMyThread();
        float[] defArr = first.makeDefArr();
        TestMyThread second = new TestMyThread();
        float[] coolArr = second.makeCoolArr();
        System.out.println(Arrays.toString(defArr));
        System.out.println(Arrays.toString(coolArr));
    }
}
