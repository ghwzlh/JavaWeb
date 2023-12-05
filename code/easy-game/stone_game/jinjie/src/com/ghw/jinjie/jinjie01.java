package com.ghw.jinjie;

import java.io.*;


public class jinjie01 {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\test.txt");

        FileInputStream fis = new FileInputStream("D:\\test.txt");
        byte[] bytes = fis.readAllBytes();
        System.out.println(new String(bytes));
        System.out.println(file.length());
        System.out.println(bytes.length);

        FileOutputStream fos = new FileOutputStream("D:\\test01.txt", true);
        fos.write(bytes);
        fos.close();
        fis.close();


    }
}





