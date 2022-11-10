package com.company;

import com.company.entity.Admin;
import com.company.view.Admin.AdminRepoImpl;
import com.company.service.Admin.AdminServiceImpl;
import org.junit.jupiter.api.Test;

public class AdminServiceTest {

    @Test
    void testShowAll() {
        new AdminServiceImpl(new AdminRepoImpl()).showAll();
    }

    @Test
    void testLogin() {
        Admin admin = new Admin("Surya", "suryaown", "lA00");
        new AdminServiceImpl(new AdminRepoImpl()).loginUser(admin);
    }

    @Test
    void testAdd() {
       new AdminServiceImpl(new AdminRepoImpl()).addNewAdmin(new Admin("iiuuyy", "asu", "lA01"));
    }

    @Test
    void testRemove() {
        new AdminServiceImpl(new AdminRepoImpl()).remove("0p0");
    }
}
