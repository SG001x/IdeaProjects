package exam1.studentsystemplus;

import javax.naming.AuthenticationNotSupportedException;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
// git test3
public class SystemTest {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<User>();
        System.out.println("欢迎来到学生管理系统");
        while (true) {
            System.out.println("请选择操作1登录 2注册 3忘记密码");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            switch (input) {
                case "1" -> login(list);
                case "2" -> register(list);
                case "3" -> forgetPW(list);
                default -> {
                    System.out.println("输入错误，请重新输入（1~3）：");
                }
            }
        }

    }


    public static void showInfo(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("用户名：" + list.get(i).getUsername());
            System.out.println("密码：" + list.get(i).getPassword());
            System.out.println("身份证：" + list.get(i).getIdCard());
            System.out.println("手机号码：" + list.get(i).getPhoneNumber());
            System.out.println();
        }
    }

    public static void login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.next();
        int indx, cnt = 0;
        if ((indx = containUsername(list, username)) != -1) {
            while (true) {
                System.out.println("请输入密码：");
                String password = sc.next();
                String anwser = getPin();
                System.out.println("请输入验证码" + anwser + ": ");
                String pin = sc.next();
                if (pin.equals(anwser)) {
                    if (password.equals(list.get(indx).getPassword())) {
                        System.out.println("登录成功！欢迎" + username + "!");
                        //进入主系统
                        break;
                    } else {
                        cnt++;
                        System.out.println("密码错误，还剩" + (3 - cnt) + "次机会！");
                        if (cnt == 3) {
                            System.exit(0);
                        }
                    }
                } else {
                    System.out.println("验证码错误，请重新输入：");
                }
            }
        } else {
            System.out.println("用户未注册，请先注册！");
        }


    }


    public static void register(ArrayList<User> list) {
        // 用户名部分
        System.out.println("请输入用户名：");
        String username, password, idCard, phoneNumber;
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.next();
            int flag = containUsername(list, input);
            if (flag == -1) {
                if (checkUsername(input)) {
                    username = input;
                    break;
                } else {
                    System.out.println("用户名不合法，请重新输入：");
                }
            } else {
                System.out.println("用户名已存在，请重新输入：");
            }
        }
        // 密码部分
        while (true) {
            System.out.println("请输入密码：");
            String input = sc.next();
            System.out.println("再次输入密码：");
            String reInput = sc.next();
            if (input.equals(reInput)) {
                password = input;
                break;
            } else {
                System.out.println("两次密码输入不一致！请重新输入：");
            }
        }

        // 身份证验证
        while (true) {
            System.out.println("请输入身份证号码：");
            String input = sc.next();
            if (checkID(input)) {
                idCard = input;
                break;
            } else {
                System.out.println("身份证输入有误！请重新输入：");
            }
        }

        // 手机号验证
        while (true) {
            System.out.println("请输入手机号码：");
            String input = sc.next();
            int cnt = 0;
            for (int i = 0; i < input.length(); i++) {
                if ((input.charAt(i) >= '0' && input.charAt(i) <= '9')) {
                    cnt++;
                }
            }
            if (cnt == 11 && input.charAt(0) != '0') {
                phoneNumber = input;
                break;
            } else {
                System.out.println("手机号输入有误！请重新输入：");
            }
        }
        User user = new User(username, password, idCard, phoneNumber);
        list.add(user);
        System.out.println("注册成功！");
        showInfo(list);
    }

    public static void forgetPW(ArrayList<User> list
    ) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.next();
        int indx = 0;
        if ((indx = containUsername(list, username)) != -1) {
            System.out.println("请输入身份证号：");
            String idCard = sc.next();
            System.out.println("请输入手机号：");
            String phoneNumber = sc.next();
            if (idCard.equals(list.get(indx).getIdCard()) && phoneNumber.equals(list.get(indx).getPhoneNumber())) {
                System.out.println("请输入修改后的密码：");
                String password = sc.next();
                list.get(indx).setPassword(password);
                System.out.println("修改成功！");
            } else {
                System.out.println("身份证信息不匹配，修改失败！：");
            }
        } else {
            System.out.println("该用户名未注册");
        }
    }

    public static String getPin() {
        char[] pin = new char[5];
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int num = r.nextInt(26);
            int bigOrSmall = r.nextInt(2);
            if (bigOrSmall == 0) {
                pin[i] = (char) ('a' + num);
            } else {
                pin[i] = (char) ('A' + num);
            }
        }
        pin[4] = (char) ('0' + r.nextInt(10));
        int indx = r.nextInt(5);
        char temp = pin[4];
        pin[4] = pin[indx];
        pin[indx] = temp;
        String result = new String(pin);
        return result;
    }

    public static int containUsername(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean checkUsername(String username) {
        int cnt = 0;
        boolean flag1 = false, flag2 = false;
        if (username.length() >= 3 && username.length() <= 15) {
            flag1 = true;
        } else {
            System.out.println("用户名长度必须在3~15位之间！");
        }
        for (int i = 0; i < username.length(); i++) {
            if ((username.charAt(i) >= 'a' && username.charAt(i) <= 'z') || (username.charAt(i) >= 'A' && username.charAt(i) <= 'Z')) {
                cnt++;
            }
        }
        if (cnt == 0) {
            System.out.println("用户名为字母加数字的组合，不能是纯数字！");
        } else {
            flag2 = true;
        }
        if (flag1 && flag2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkID(String idCard) {
        int cnt = 0;
        for (int i = 0; i < idCard.length() - 1; i++) {
            if (idCard.charAt(i) >= '0' && idCard.charAt(i) <= '9') {
                cnt++;
            }
        }
        if (idCard.length() == 18 && idCard.charAt(0) != '0' && cnt == 17 &&
                ((idCard.charAt(17) >= '0' && idCard.charAt(17) <= '9') || idCard.charAt(17) == 'x' || idCard.charAt(17) == 'X')) {
            return true;
        } else {
            return false;
        }
    }
}
