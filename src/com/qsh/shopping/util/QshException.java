package com.qsh.shopping.util;

public class QshException extends Exception {
	private String resutlMsg;
	
	public QshException(String resutlMsg){
		this.resutlMsg = resutlMsg;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return resutlMsg;
	}
	
}
