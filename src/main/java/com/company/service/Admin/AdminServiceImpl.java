package com.company.service.Admin;

import com.company.entity.Admin;
import com.company.view.Admin.AdminRepo;
import com.company.view.Admin.AdminRepoImpl;


public record AdminServiceImpl(AdminRepoImpl adminRepo) implements AdminService {

    @Override
    public void showAll() {
        AdminRepo adminRepo = new AdminRepoImpl();
        Admin[] admins = adminRepo.getAll();

        System.out.println("--ALL ADMIN--");

        String str = tabs(maxNum(), "No")+ "|\t" + tabs(maxLengthUserName(), "Username") + "\t|\t"
                + tabs(maxLengthPassword(), "Password") + "\t|\t"
                + tabs(maxLengthLevel(), "As") + "\t|\t"
                + "Was added at";

        System.out.println(str);
        System.out.println(strip(str.length() + 25));
        for (var admin : admins) {
            System.out.println("" + tabs(maxNum(),admin.getNumber()) + "|\t"
                    + tabs(maxLengthUserName(), admin.getUserName()) + "\t|\t"
                    + tabs(maxLengthPassword(), admin.getPassword()) + "\t|\t"
                    + tabs(maxLengthLevel(), admin.getLevel()) + "\t|\t"
                    + admin.getWas_added());
        }
    }

    @Override
    public boolean loginUser(Admin admin) {
        if (adminRepo.login(admin)) {
            System.out.println("Welcome " + admin.getUserName() );
            return true;
        } else {
            System.out.println("Wrong Username Or Password");
            return false;
        }
    }

    @Override
    public void addNewAdmin(Admin admin) {
        if (adminRepo.isInvalidIdLevel(admin.getLevel())) {
            if (adminRepo.add(new Admin(admin.getUserName(), admin.getPassword(), "lA02"))) {
                System.out.println("You insert invalid id_level, default id_level to become GUEST");
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Success add new admin : " + admin.getUserName() + " as GUEST");
            } else {
                System.out.println("Failed add, Username is exist");
            }
        } else {
            if (adminRepo.add(admin)) {
                // problem
                System.out.println("Success add new admin : " + admin.getUserName() + " as " + adminRepo.getStatus(admin.getLevel()));
            } else {
                System.out.println("Failed add, Username is exist inv");
            }
        }
    }

    @Override
    public boolean remove(String username) {
        if (adminRepo.delete(username)) {
            System.out.println("Success removed : " + username);
            return true;
        } else {
            System.out.println("Failed remove, username is not exist");
            return false;
        }
    }

    String strip(int s) {
        if (s - 1 != 0) {
            System.out.print("-");
            return strip(s - 1);
        } else return "-";
    }

    String tabs(int lgt, String usr) {
        StringBuilder stringBuilder = new StringBuilder(usr);

        while (stringBuilder.length() != lgt) {
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    int maxNum(){
        AdminRepo adminRepo = new AdminRepoImpl();
        Admin[] admins = adminRepo.getAll();

        int max = 0;

        for (Admin admin : admins) {
            if (max < admin.getNumber().length()) max = admin.getNumber().length();
        }

        return max;
    }
    int maxLengthUserName() {
        AdminRepo adminRepo = new AdminRepoImpl();
        Admin[] admins = adminRepo.getAll();

        int max = 0;

        for (var admin : admins) {
            if (max < admin.getUserName().length()) max = admin.getUserName().length();
        }

        while (max % 4 != 0) {
            max++;
        }

        return max;
    }

    int maxLengthPassword() {
        AdminRepo adminRepo = new AdminRepoImpl();
        Admin[] admins = adminRepo.getAll();

        int max = 0;

        for (var admin : admins) {
            if (max < admin.getPassword().length()) max = admin.getPassword().length();
        }

        while (max % 4 != 0) {
            max++;
        }

        return max;
    }

    int maxLengthLevel() {
        AdminRepo adminRepo = new AdminRepoImpl();
        Admin[] admins = adminRepo.getAll();

        int max = 0;

        for (var admin : admins) {
            if (max < admin.getLevel().length()) max = admin.getLevel().length();
        }

        while (max % 4 != 0) {
            max++;
        }

        return max;
    }
}
