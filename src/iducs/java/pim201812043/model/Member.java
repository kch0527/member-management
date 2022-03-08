package iducs.java.pim201812043.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Member {

    private final String memberid = "./src/memberid.txt";

    public int id;
    private String email; // 길이 20, 이메일, 유일키
    private String pw; // 길이 15,  암호
    private String name; // 길이 15, 이름
    private String phone; // 길이 20, 전화번호
    private String address; // 길이 30, 주소

    public int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int idCount() {

        count = readLastId();
        count++;
        saveLastId(count);

        return count;
    }

    public int readLastId() {

        File file = new File(memberid);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Scanner sc;
            try {
                sc = new Scanner(file);
                int id = Integer.parseInt(sc.nextLine());
                sc.close();

                return id;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return 1000;
    }

    public void saveLastId(int id) {

        try {

            File file = new File(memberid);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(memberid, false);
            fileWriter.write(String.valueOf(id));
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
