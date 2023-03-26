package by.htp.ex.service;


public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;


	public ServiceException() {
		super();
	}
	
	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String massage) {
		super(massage);
	}

	public ServiceException(String massage, Exception e) {
		super(massage, e);
	}



}
