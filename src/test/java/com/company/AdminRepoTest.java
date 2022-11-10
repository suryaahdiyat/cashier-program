package com.company;

import com.company.entity.Admin;
import com.company.view.Admin.AdminRepo;
import com.company.view.Admin.AdminRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AdminRepoTest {

    @Test
    void testAdd() {
        Assertions.assertTrue(new AdminRepoImpl().add(new Admin("qw", "sss", "lA00")));
    }

    @Test
    void testRemove() {
        Assertions.assertTrue(new AdminRepoImpl().delete("abiyah"));
    }

    @Test
    void testGetAll() {
        AdminRepo adminRepo = new AdminRepoImpl();
        Admin[] admins = adminRepo.getAll();

        for (var admin: admins) {
            System.out.println(maxLengthUserName());
            System.out.println("|" + admin.getNumber() + "|\t"
                    + tabs(maxLengthUserName(), admin.getUserName()) + "|\t"
                    + admin.getPassword() + "\t|\t"
                    + admin.getLevel());
        }
    }

    String tabs(int lgt, String usr){
        StringBuilder stringBuilder =  new StringBuilder(usr);

        while (stringBuilder.length() != lgt){
            stringBuilder.append(" ");
        }

        System.out.println(stringBuilder.length());

        return stringBuilder.toString();
    }

    int maxLengthUserName(){
        AdminRepo adminRepo = new AdminRepoImpl();
        Admin[] admins = adminRepo.getAll();

        int max = 0;

        for (var admin: admins) {
            if (admin.getNumber().equals("1")) max = admin.getUserName().length();
            if (max < admin.getUserName().length()) max = admin.getUserName().length();
        }

        return max;
    }

    @Test
    void testLogin() {
//        new AdminRepoImpl().login(new Admin("surya", "suryaown"));
    }
}
