package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String testA = "89008001234"; //Правильный номер;
        String testB = "+79008001234"; //Правильный номер / неверный формат 1;
        String testC = "8 900 800 1234"; //Правильный номер / неверный формат 2;
        String testD = "+7-900-800-12-34"; //Неправильный номер - длинный;
        String testE = "+7 900 800 1234"; //Правильный номер / неверный формат 3;
        String testF = "+7 9 9 9 9 9 9"; //Короткий;
        String testG = ""; //Пустой;
        String[] tests = new String[]{testA, testB, testC, testC, testD, testE, testF, testG};
        for (String test : tests) {
            String[] result = phoneValidation(test);
            System.out.println(test + ":");
            System.out.println(Arrays.toString(result) + "\n");
        }
    }

    public static String[] phoneValidation(String phoneNumber) {
        String[] checks = {"(", ")", " "};
        String[] output;
        String message = "";
        boolean garbage = false;
        boolean wrongLength = false;
        int fixedLength = phoneNumber.length();

        if (phoneNumber.isEmpty())
            return output = new String[]{message = "Пустая строка "};

        phoneNumber = phoneNumber.replace("+7", "8");
        if (fixedLength != phoneNumber.length()) {
            fixedLength = phoneNumber.length();
            message += "Замена +7 на 8; ";
        }

        for (int i = 0; i < checks.length; i++) {
            phoneNumber = phoneNumber.replace(checks[i], "");
            if (fixedLength != phoneNumber.length() && !garbage) {
                fixedLength = phoneNumber.length();
                garbage = true;
            }
        }

        if (garbage)
            message += "В номере есть пробелы и/или скобки; ";
        if (phoneNumber.length() > 11) {
            message += "Сумма символов больше 11 ";
            wrongLength = true;
        }
        if (phoneNumber.length() < 11) {
            message += "Сумма символов меньше 11 ";
            wrongLength = true;
        }
        if (wrongLength)
            phoneNumber = "Введен некорректный номер";
        if (message.isEmpty())
            message = "Не было изменений ";

        return output = new String[]{phoneNumber, message};
    }
}

