package com.pack1;
import java.sql.ResultSet;
public class jdbcApp7
{
 void meth1() {
	 System.out.println(" types ");
	 System.out.println(ResultSet.TYPE_FORWARD_ONLY);
	 System.out.println(ResultSet.TYPE_SCROLL_INSENSITIVE);
	 System.out.println(ResultSet.TYPE_SCROLL_SENSITIVE);
	 
	 System.out.println("  MODES ");
	 System.out.println(ResultSet.CONCUR_READ_ONLY);
	 System.out.println(ResultSet.CONCUR_UPDATABLE);
 }
 public static void main(String[] args)
 {
	 jdbcApp7 obj=new jdbcApp7();
	 obj.meth1();
 }
}
