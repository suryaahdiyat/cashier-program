package com.company;

import com.company.view.Admin.AdminRepoImpl;
import com.company.service.Admin.AdminServiceImpl;
import com.company.view.View;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new View(new AdminServiceImpl(new AdminRepoImpl()), new AdminRepoImpl());
    }
}
