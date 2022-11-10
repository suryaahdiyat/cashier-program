package com.company.service.Admin;

import com.company.entity.Admin;

public interface AdminService {

    void showAll();

    boolean loginUser(Admin admin);

    void addNewAdmin(Admin admin);

    boolean remove(String username);

}
