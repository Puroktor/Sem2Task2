package ru.vsu.cs.skofenko;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InOutClass {
    public static InputData readListFromFile(String path) throws FileNotFoundException, NullPointerException, InputMismatchException {
        try (Scanner scanner = new Scanner(new File(path))) {
            MyLinkedList<Integer> list = new MyLinkedList<>();
            String[] strings = scanner.nextLine().trim().split(" ");
            if(strings.length!=1 && !(strings[0].isEmpty())){
                for (String s: strings) {
                    list.add(Integer.parseInt(s));
                }
            }
            int to = scanner.nextInt();
            return new InputData(list,to);
        }
    }

    public static void writeListToFile(String path, MyLinkedList<Integer> answer) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(path)) {
            for (Integer a : answer) {
                pw.print(a + " ");
            }
        }
    }

    public static void writeDataToFile(String path, InputData data) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(new File(path))) {
            for (Integer a : data.getList()) {
                pw.print(a+" ");
            }
            pw.println();
            pw.print(data.getTo());
        }
    }
}
