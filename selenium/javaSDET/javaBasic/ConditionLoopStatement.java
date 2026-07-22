package javaSDET.javaBasic;

import java.util.ArrayList;
import java.util.List;

public class ConditionLoopStatement {
    public static void main(String[] args) {
        int workingDay = 100;
        float salary = 0;
        // condition statement
        // if
        if (workingDay > 20) {
            salary = salary + 1000000;
        }
        System.out.println("Thưởng:" + salary);

        salary = 15000000;
        workingDay = 18;

        // if-else
        if (workingDay > 20) {
            salary = salary + 1000000;
        } else {
            salary = salary + 500000;
        }
        System.out.println("Thưởng:" + salary);

        salary = 15000000;
        workingDay = 18;
        // if-else-if
        // nêú ngày công > 20 thì thưởng 1tr - > 15 thì 500 nếu nhỏ hơn 15 thi thưởng 200
        if (workingDay > 20) {
            salary = salary + 1000000;
        } else if (workingDay > 15) {
            salary = salary + 500000;
        } else {
            salary = salary + 200000;
        }
        System.out.println("Thưởng:" + salary);


        String browserName = "Chrome";
        // switch-case
        switch (browserName) {
            case "Chromium": // có thể 2 case ra 1 kq
            case "Chrome":
                System.out.println("Khởi tạo Chrome");
                break;
            case "Firefox":
                System.out.println("Khởi tạo Firefox");
                break;
            case "Edge:":
                System.out.println("Khởi tạo edge");
                break;
            default:
                System.out.println("khởi tạo Safari");
                break;
        }


        // loop statement
        int studentNumber = 100;
        String studentAddress = "Quảng Nam";
        //for classic
        for (int i = 1; i <= studentNumber; i++) {
            System.out.println("Tặng 1 voucher cho sv thử:" + i);
        }

        for (int i = 1; i < studentNumber; i++) {
            if (studentAddress.equals("Quảng Bình")) {
                System.out.println("Tặng voucher xe về quê cho sinh viên thứ: " + i);
            }
        }


        // for
        String[] studentName = {"Nguyễn Văn A", "Nguyễn Văn B", "Nguyễn Văn C"};
        // for each array /list / set /map
        for (String name : studentName) {
            System.out.println(name);
        }

        studentNumber = 100;
        int y = 0;
        // while
        while (y < studentNumber) {
            System.out.println("Tặng 1 voucher giamr giá đi xe:" + y);
            y++;
        }

        // do-while
        studentNumber = 10;
        int x = 0;
        do {
            System.out.println("Tặng phiếu giảm giá đi xe:" + x);
            x++;
        }
        while (x < studentNumber);

        List<String> studentCity = new ArrayList<>();
        studentCity.add("Hà Nội");
        studentCity.add("Quy Nhơn");
        studentCity.add("Tây Ninh");
        studentCity.add("Cần Thơ");
        studentCity.add("Lạng Sơn");

        for (int i = 0; i < studentCity.size(); i++){
            System.out.println("Danh sách tỉnh thành: " + studentCity.get(i));
        }

        for(String city: studentCity){
            System.out.println("Danh sách tỉnh thành:" + city);
        }
    }
}
