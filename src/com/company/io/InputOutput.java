package com.company.io;

import com.company.controller.ContactManager;
import com.company.model.Contact;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputOutput {
    Scanner scanner = new Scanner(System.in);
    ContactManager contactManager = new ContactManager();

    //region INPUT
    public String inputPhoneNumber() {
        final String PHONE_NUMBER_REGEX = "(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}";
        String phoneNumber;
        do {
            System.out.println("Nhập số điện thoại:");
            phoneNumber = scanner.nextLine();
            return phoneNumber;
        }
        while (!Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber));
    }

    public String inputGroup() {
        System.out.println("Nhập nhóm danh bạ:");
        String group = scanner.nextLine();
        return group;
    }

    public String inputName() {
        System.out.println("Nhập Họ và tên:");
        String name = scanner.nextLine();
        return name;
    }

    public String inputGender() {
        final String GENDER_REGEX = "[n][a|u][m]";
        String gender;
        do {
            System.out.println("Nhập giới tính (nam or nu):");
            gender = scanner.nextLine();
            return gender;
        }
        while (!Pattern.matches(GENDER_REGEX, gender));
    }

    public String inputAddress() {
        System.out.println("Nhập Địa chỉ:");
        String address = scanner.nextLine();
        return address;
    }

    public LocalDate inputBirthday() {
        while (true) {
            try {
                System.out.println("Nhập ngày tháng năm sinh");
                System.out.println("Nhập ngày");
                int day = Integer.parseInt(scanner.nextLine());
                System.out.println("Nhập tháng");
                int month = Integer.parseInt(scanner.nextLine());
                System.out.println("Nhập năm");
                int year = Integer.parseInt(scanner.nextLine());
                if (year < 1800 || year > 2022) throw new Exception();
                LocalDate birthday = LocalDate.of(year, month, day);
                return birthday;
            } catch (Exception e) {
                System.out.println("Nhập sai. Mời nhập lại");
            }
        }
    }

    public String inputEmail() {
        final String EMAIL_REGEX = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
        String email;
        do {
            System.out.println("Nhập email:");
            email = scanner.nextLine();
        } while (!Pattern.matches(EMAIL_REGEX, email));
        return email;
    }

    public Contact inputContact(){
        return new Contact(inputPhoneNumber(),inputGroup(),inputName(),inputGender(),inputAddress(),inputBirthday(),inputEmail());
    }

    //endregion

    //regionOUTPUT
    public void showContacts() {
        List<Contact> showList = contactManager.contactList;
        if (!showList.isEmpty()) {
            contactManager.display(showList);
        } else {
            System.out.println("Không có danh bạ nào trong danh sách");
        }
    }
    public void addContact() {
        Contact addContact = inputContact();
        if (contactManager.add(addContact)) {
            contactManager.add(addContact);
            System.out.println("Đã thêm thành công danh bạ");
        } else {
            System.out.println("Đã tồn tại danh bạ với số điện thoại này");
        }
    }

    public void updateContact() {
        String phoneNumber = inputPhoneNumber();
        if (contactManager.findByPhoneNumber(phoneNumber) != null) {
            System.out.println("Đã tìm thấy danh bạ với số điện thoại trên");
            System.out.println("Nhập thông tin cần cập nhật");
            Contact editContact = inputContact();
            contactManager.update(phoneNumber, editContact);
            System.out.println("Đã cập nhật thành công danh bạ này");
        } else {
            System.out.println("Không tìm thấy danh bạ với số điện thoại trên");
        }
    }

    public void deleteContact() {
        String phoneNumber = inputPhoneNumber();
        Contact deleteContact = contactManager.findByPhoneNumber(phoneNumber);
        if (deleteContact != null) {
            System.out.println("Đã tìm thấy danh bạ với số điện thoại trên");
            System.out.println("Nhập Y để xóa");
            String selector = scanner.nextLine();
            if (selector.equals("Y")) {
                contactManager.delete(phoneNumber);
                System.out.println("Đã xóa danh bạ này");
            }
        } else {
            System.out.println("Không tìm thấy danh bạ với số điện thoại trên");
        }
    }

    public void findByName() {
        List<Contact> list = contactManager.findByName(inputName());
        contactManager.display(list);
    }

    public void readFromFile() {
        System.out.println("Chương trình sẽ xóa toàn bộ danh bạ trong bộ nhớ");
        System.out.println("Nhập Y để tiếp tục");
        String selector = scanner.nextLine();
        if (selector.equals("Y")) {
            contactManager.contactList.clear();
            contactManager.contactList.addAll(IOFile.readFile());
            System.out.println("Đã cập nhật lại danh bạ từ file");
        }
    }

    public void writeToFile() {
        System.out.println("Chương trình sẽ ghi toàn bộ danh bạ vào file");
        System.out.println("Nhập Y để tiếp tục");
        String selector = scanner.nextLine();
        if (selector.equals("Y")) {
            IOFile.writeFile(contactManager.contactList);
            System.out.println("Đã ghi file thành công");
        }
    }
}
