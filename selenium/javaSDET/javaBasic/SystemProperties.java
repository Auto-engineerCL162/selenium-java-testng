package javaSDET.javaBasic;

import java.io.File;

public class SystemProperties {

    static void main(String[] args) {

        String uploadFilePath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

        String firstImage = "TestImg1.png";
        String secondImage = "TestImg2.png";
        String thirdImage = "TestImg3.png";

        String firstImagePath = uploadFilePath + firstImage;
        String secondImagePath = uploadFilePath + secondImage;
        String thirdImagePath = uploadFilePath + thirdImage;

        System.out.println(firstImagePath);
        System.out.println(secondImagePath);
        System.out.println(thirdImagePath);


    }
}
