package com.cst.CstLabs.utils;

public class CstLabsExpection extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4874944297L;
	
	public CstLabsExpection() {
		super();
	}

	public CstLabsExpection(String message, Throwable cause) {
		super(message, cause);
	}

	public CstLabsExpection(String message) {
		super(message);
	}

	public CstLabsExpection(Throwable cause) {
		super(cause);
	}


}
