package com.company.controller;

import com.company.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager {
    public static List<Contact> contactList = new ArrayList<>();

    public boolean add(Contact contact) {
        if (findByPhoneNumber(contact.getPhoneNumber()) == null) {
            contactList.add(contact);
            return true;
        }
        return false;
    }

    public Contact findByPhoneNumber(String phoneNumber) {
        for (Contact contact : contactList) {
            if (phoneNumber.equals(contact.getPhoneNumber())) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> findByName(String name) {
        List<Contact> list = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getName().contains(name)) {
                list.add(contact);
            }
        }
        return list;
    }

    public boolean update(String phoneNumber, Contact contact) {
        Contact editPhoneNumber = findByPhoneNumber(phoneNumber);
        if (editPhoneNumber != null) {
            editPhoneNumber.setGroup(contact.getGroup());
            editPhoneNumber.setName(contact.getName());
            editPhoneNumber.setGender(contact.getGender());
            editPhoneNumber.setAddress(contact.getAddress());
            editPhoneNumber.setBirthday(contact.getBirthday());
            editPhoneNumber.setEmail(contact.getEmail());
            return true;
        }
        return false;
    }

    public boolean delete(String phoneNumber) {
        if (findByPhoneNumber(phoneNumber) != null) {
            contactList.remove(findByPhoneNumber(phoneNumber));
            return true;
        }
        return false;
    }

    public void display(List<Contact> list) {
        System.out.println("Số điện thoại,Nhóm,Tên,Giới tính,Địa chỉ,Ngày sinh,Email");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            if (i % 5 == 4 && i != list.size() - 1) {
                System.out.println("Bấm Enter để tiếp tục xem");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
            }
        }
    }
}

