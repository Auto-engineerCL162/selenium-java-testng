package javaSDET;

public class SplitString {
    static void main(String[] args) {

        String basicUrl = "http://the-internet.herokuapp.com/basic_auth";

        String[] urlSplit = basicUrl.split("//");
        System.out.println(urlSplit[0]);
        System.out.println(urlSplit[1]);

        basicUrl = urlSplit[0] + "//" + "admin:admin@" + urlSplit[1];
        System.out.println(basicUrl);
    }
}