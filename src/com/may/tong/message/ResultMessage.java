package com.may.tong.message;

public class ResultMessage {
	
	private boolean flag;
	
	private String message;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultMessage(boolean flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}

	public ResultMessage() {
		super();
	}

	@Override
	public String toString() {
		return "ResultMessage [flag=" + flag + ", message=" + message + "]";
	}
	
	

}
