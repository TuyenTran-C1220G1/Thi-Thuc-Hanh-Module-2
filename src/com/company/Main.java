package com.company;

import com.company.io.InputOutput;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputOutput inputOutput = new InputOutput();

        int selector = 0;
        do {
            showMenu();

            try {
                selector = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                selector = 0;
            }

            switch (selector) {
                case 1:
                    inputOutput.showContacts();
                    break;
                case 2:
                    inputOutput.addContact();
                    break;
                case 3:
                    inputOutput.updateContact();
                    break;
                case 4:
                    inputOutput.deleteContact();
                    break;
                case 5:
                    inputOutput.findByName();
                    break;
                case 6:
                    inputOutput.readFromFile();
                    break;
                case 7:
                    inputOutput.writeToFile();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Nhập sai. Mời bạn nhập lại");

            }

        } while (selector != 8);
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("-----CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ-----");
        System.out.println("Chọn chức năng theo số để tiếp tục:");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm theo tên");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Thoát");
    }
}
