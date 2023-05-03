package org.example;

import org.example.functions.Functions;
import org.example.info.Info;
import org.example.info.InfoItem;
import java.util.Arrays;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
    public void run() {
        Functions functions = new Functions();
        List<InfoItem> infoItems = Arrays.asList(
                new InfoItem("Вийти з програми", () -> {
                    System.out.println();
                    System.exit(0);
                }),
                new InfoItem("Список пацієнтів, які мають вказаний діагноз в порядку зростання номерів медичної картки;", () -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Введіть діагноз: ");
                    String diagnosis = scanner.nextLine();
                    functions.searchAndSortMedicalCardNumber(diagnosis);
                }),
                new InfoItem("Список пацієнтів, номер медичної карти у яких знаходиться в заданому інтервалі;", () -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Введіть номер медичної карти першого інтервалу: ");
                    int startCardNumber = scanner.nextInt();
                    System.out.println("Введіть номер медичної карти другого інтервалу: ");
                    int endCardNumber = scanner.nextInt();
                    functions.searchFullNameAndPhone(startCardNumber, endCardNumber);
                }),
                new InfoItem("Кількість та список пацієнтів, номер телефона яких починається з вказаної цифри;", () -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Введіть цифру, з якої починається номер телефону: ");
                    String phonePrefix = scanner.next();
                    functions.foundPatients(phonePrefix);
                }),
                new InfoItem("Список діагнозів пацієнтів (без повторів) із вказанням кількості пацієнтів, що мають цей діагноз у порядку спадання цієї кількості;", () -> {
                    functions.ListOfPatientDiagnoses();
                }),
                new InfoItem("Список діагнозів пацієнтів, зареєстрованих у системі без повторів;", () -> {
                    functions.showAllDiagnosisPatients();
                }),
                new InfoItem("Для кожного діагнозу визначити кількість пацієнтів, яким він поставлений.", () -> {
                    functions.fullNameSort();
                })
        );
        Info info = new Info(infoItems);
        info.run();
    }
}