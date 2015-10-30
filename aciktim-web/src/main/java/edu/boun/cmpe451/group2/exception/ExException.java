package edu.boun.cmpe451.group2.exception;

/**
 * Extended Exception class for this project
 */
public class ExException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 3429545863617402531L;

    /**
     * error code
     */
    protected String errCode = null;

    /**
     * constructor
     */
    public ExException() {
        super();
    }

    /**
     * constructor
     *
     * @param errCode error code
     */
    public ExException(String errCode) {
        super(errCode);
        this.errCode = errCode;
    }

	/*
     * getter & setter
	 */

    /**
     * @return the errCode
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * @param errCode the errCode to set
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}