package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class jdbcApp14
{
    private String dBurl,dBuname,dBpwd;
    Vector<Connection> v=new Vector<Connection>();

    public jdbcApp14(String dBurl,String dBuname,String dBpwd)
    {
        this.dBurl=dBurl;
        this.dBuname=dBuname;
        this.dBpwd=dBpwd;
    }

    void con_Initialization()
    {
        System.out.println("Creating '5' Connection Objects");

        while(v.size()<5)
        {
            try
            {
                Connection con=DriverManager.getConnection(dBurl,dBuname,dBpwd);
                v.addElement(con); //passing Connection Objects into Vector
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        for(Object data :v)
        {
            System.out.println(data);
        }

        System.out.println(v.size()+" Connection Objects present in the Connection Pool");
    }

    Connection con_Acquisition()
    {
        System.out.println("Assigning a Connection Object");
        Connection con=v.elementAt(0);
        v.remove(con);
        return con;
    }

    void con_Return(Connection obj)
    {
        System.out.println("Adding the Connection Object back into Connection Pool");
        v.addElement(obj);
        System.out.println("----------------");

        for(Object data :v)
        {
            System.out.println(data);
        }
    }
}