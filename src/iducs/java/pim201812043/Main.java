package iducs.java.pim201812043;

import iducs.java.pim201812043.controller.MemberController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //MemberController memberController = new MemberController();
        //memberController.dispatch();

            ServerSocket serverSocket = null;
            try{
                serverSocket = new ServerSocket(9000);
            }catch (IOException e){
                System.out.println("not Port");
                System.exit(0);
            }
            System.out.println("client waiting");

            try {
                    Socket accept = serverSocket.accept();
                    System.out.println("client: " + accept.getInetAddress() + ":" + accept.getPort());
                    DataInputStream in = new DataInputStream(new BufferedInputStream(accept.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(accept.getOutputStream()));


                while (true) {
                    String s = in.readUTF();
                    System.out.println("client: " + s);

                    //Scanner scanner = new Scanner(System.in);
                    //String message = scanner.nextLine();

                    out.writeUTF(s);
                    out.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
