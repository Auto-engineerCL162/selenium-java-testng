package javaSDET.javaBasic;

public class SystemProperties {

    static void main(String[] args) {

        String filePath = System.getProperty("user.dir")
                + "\\src\\test\\resources\\dragDrop.js";

        System.out.println(filePath);

        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("java.version"));
    }
}
