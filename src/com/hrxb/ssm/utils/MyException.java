package com.hrxb.ssm.utils;

/**
 * �Զ����쳣��
 * @author Admin
 *
 */
public class MyException extends Exception{

	private String message;

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MyException(String message) {
		super();
		this.message = message;
	}

	public MyException() {
		// TODO Auto-generated constructor stub
	}
	
	
}
