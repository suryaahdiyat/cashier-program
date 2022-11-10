package com.company.view;

import com.company.entity.Admin;
import com.company.view.Admin.AdminRepo;
import com.company.service.Admin.AdminService;
import com.company.util.InputUtil;

public class View {

    private final AdminService adminService;

    public View(AdminService adminService, AdminRepo adminRepo) {
        this.adminService = adminService;

        if (adminRepo.isEmpty()){
            System.out.println("No Admin Are Added\nPlease Add New Admin Below");
            addView();
            InputUtil.clearScreen();
        }
        loginView();
    }

    private void loginView(){
        System.out.print("Username  : ");
        String username = InputUtil.input();
        System.out.print("Password  : ");
        String password = InputUtil.input();


        if (!adminService.loginUser(new Admin(username, password))){
            System.out.println("login fail\nPlease Insert Your Username, password, and User Level Correctly");
            String addNew =  InputUtil.input("Write N to add new Admin" , 2);
            if (addNew.equalsIgnoreCase("n")) addView();
            loginView();
        }

        homeScreen();
    }

    private void addView(){
        System.out.print("Username  : ");
        String username = InputUtil.input();
        System.out.print("Password  : ");
        String password = InputUtil.input();
        System.out.print("User Level: ");
        String level = InputUtil.input("Format Level\n" +
                "lA00 or lA01 or LA02", 1);

        adminService.addNewAdmin(new Admin(username, password, level));
    }

    private void showAllView(){
        adminService.showAll();
        System.out.println("A -> Add New User");
        System.out.println("R -> Remove User");
    }
    private void homeScreen(){
        InputUtil.clearScreen();
        System.out.println("0 to Show All User");
        System.out.println("\n\n\nHere All Menu Will Show Up");
        System.out.print("Please Insert Your Input Here : ");
        int input = InputUtil.inputInt();
        if (input == 0) showAllView();
    }
}
