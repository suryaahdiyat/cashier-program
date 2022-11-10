package com.company.view.Admin;

import com.company.entity.Admin;

public interface AdminRepo {

    boolean isEmpty();

    boolean login(Admin admin);

    boolean add(Admin admin);

    boolean delete(String username);

    Admin[] getAll();

    String getStatus(String username);
}
