package org.kniit.multithreading;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class SortTask extends RecursiveAction {

    static final int THRESHOLD = 1000;
    final long[] array;
    final int lo, hi;

    SortTask(long[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    SortTask(long[] array) {this(array, 0, array.length);}

    protected void compute() {
        if (hi - lo < THRESHOLD) {
            sortSequentially(lo, hi);
        }
        else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new SortTask(array, lo, mid), new SortTask(array, mid, hi));
            merge(lo, mid, hi);
        }
    }

    void sortSequentially(int lo, int hi) {Arrays.sort(array, lo, hi);}

    void merge(int lo, int mid, int hi) {
        long[] buf = Arrays.copyOfRange(array, lo, mid);
        for (int i = 0, j = lo, k = mid; i < buf.length; j++) {
            array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
        }
    }

}

class SumTask extends RecursiveTask<Long> {

    static final int THRESHOLD = 1000;
    final long[] array;
    final int lo, hi;

    SumTask(long[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    SumTask(long[] array) {
        this(array, 0, array.length);
    }


    @Override
    protected Long compute() {
        long sum = 0;
        if (hi - lo < THRESHOLD) {
            for (int i = lo; i < hi; i++) {
                sum += array[i];
            }
        } else {
            int mid = (lo + hi) >>> 1;
            SumTask sumTaskOne = new SumTask(array, lo, mid);
            SumTask sumTaskTwo = new SumTask(array, mid, hi);
            sumTaskOne.fork();
            sumTaskTwo.fork();

            sum = sumTaskOne.join() + sumTaskTwo.join();
        }
        return sum;
    }

}

public class ForkJoinExample {

    public static void main(String[] args) {
        Random random = new Random();
        long[] array = LongStream.generate(() -> random.nextLong(-5000, 5000))
            .limit(10_000)
            .toArray();
        System.out.println("Before");
        System.out.println(LongStream.of(array).mapToObj(String::valueOf).collect(Collectors.joining(", ")));
        SortTask sortTask = new SortTask(array);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(sortTask);
        System.out.println("After");
        System.out.println(LongStream.of(array).mapToObj(String::valueOf).collect(Collectors.joining(", ")));
        SumTask sumTask = new SumTask(array);
        Long result = forkJoinPool.invoke(sumTask);
        System.out.println("Sum = " + result);
    }

}
