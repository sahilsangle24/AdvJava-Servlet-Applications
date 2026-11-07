package com.pack1;

import java.sql.Connection;

public class ConnectionPool
{
    String DBurl="jdbc:oracle:thin:@localhost:1521:xe";
    String DBuname="system";
    String DBpwd="manager";

    jdbcApp14 obj=new jdbcApp14(DBurl,DBuname,DBpwd);

    void meth1()
    {
        System.out.println("Implementing Connection pooling");

        obj.con_Initialization();
        System.out.println("Size of Vector : "+obj.v.size());//5

        System.out.println("\n------User 1------");
        Connection con1=obj.con_Acquisition();
        System.out.println("Size of Vector : "+obj.v.size());//4

        System.out.println("\n------User 2------");
        Connection con2=obj.con_Acquisition();
        System.out.println("Size of Vector : "+obj.v.size());//3

        System.out.println("\n------User 3------");
        Connection con3=obj.con_Acquisition();
        System.out.println("Size of Vector : "+obj.v.size());//2

        obj.con_Return(con1);
        obj.con_Return(con2);
        obj.con_Return(con3);
    }
    public static void main(String[] args)
    {
        ConnectionPool obj = new ConnectionPool();
        obj.meth1();
    }
  
}