package com.company.io;

import com.company.model.Contact;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IOFile {

    final static String PATH = "contacts.csv";

    public static void writeFile(List<Contact> list) {
        try {
            File file = new File(PATH);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Contact contact : list) {
                bufferedWriter.write(contact.toString());
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Contact> readFile() {
        List<Contact> list = new ArrayList<>();
        try {
            File file = new File(PATH);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(",");
                String phoneNumber = arr[0];
                String group = arr[1];
                String name = arr[2];
                String gender = arr[3];
                String address = arr[4];
                String[] arrDate = arr[5].split("-");
                LocalDate birthday = LocalDate.of(Integer.parseInt(arrDate[0]), Integer.parseInt(arrDate[1]), Integer.parseInt(arrDate[2]));
                String email = arr[6];
                Contact contact = new Contact(phoneNumber, group, name, gender, address, birthday, email);
                list.add(contact);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

