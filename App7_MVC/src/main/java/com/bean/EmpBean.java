package com.bean;

import java.io.Serializable;

public class EmpBean implements Serializable
{
	private String empId;
	private String empFname;
	private String empLname;
	private int empSal;
	private String empAddr;

	public String getEmpId()
	{
		return empId;
	}

	public void setEmpId(String empId)
	{
		this.empId = empId;
	}

	public String getEmpFname()
	{
		return empFname;
	}

	public void setEmpFname(String empFname)
	{
		this.empFname = empFname;
	}

	public String getEmpLname()
	{
		return empLname;
	}

	public void setEmpLname(String empLname)
	{
		this.empLname = empLname;
	}

	public int getEmpSal()
	{
		return empSal;
	}

	public void setEmpSal(int empSal)
	{
		this.empSal = empSal;
	}

	public String getEmpAddr()
	{
		return empAddr;
	}

	public void setEmpAddr(String empAddr)
	{
		this.empAddr = empAddr;
	}
}