package iducs.java.pim201812043;

import iducs.java.pim201812043.controller.MemberController;

public class Main {

    public static void main(String[] args) {
        MemberController memberController = new MemberController();
        memberController.dispatch();
    }
}
