package org.kniit.multithreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModCountExample {

    public static void main(String[] args) {
        System.out.println("ArrayList (FAIL-FAST):");
        List<String> arrayList = new ArrayList<>(Arrays.asList("X", "Y", "Z"));
        try {
            Iterator<String> it1 = arrayList.iterator();
            arrayList.add("NEW");
            it1.forEachRemaining(System.out::println);
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException!");
        }

        System.out.println("=================================");
        System.out.println("CopyOnWriteArrayList (FAIL-SAFE):");
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>(Arrays.asList("X", "Y", "Z"));
        Iterator<String> it2 = cowList.iterator();
        cowList.add("NEW");
        it2.forEachRemaining(System.out::println);

        System.out.println("Финальный список: " + cowList);

    }
}
