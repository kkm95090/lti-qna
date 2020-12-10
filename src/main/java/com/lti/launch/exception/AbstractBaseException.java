package com.lti.launch.exception;


import com.lti.launch.common.ResponseCode;

/**
 * 최상위 예외처리 클래스
 *
 * @author Parking Cloud, bhkwon@iparking.co.kr
 * @since 2019.03.04
 */
public abstract class AbstractBaseException extends RuntimeException {

    private static final long serialVersionUID = -1364618486741278790L;

    protected final String errorMessge;

    protected AbstractBaseException() {
        super();
        this.errorMessge = null;
    }

    protected AbstractBaseException(Throwable e) {
        super(e);
        this.errorMessge = null;
    }

    protected AbstractBaseException(String errorMessge) {
        super(errorMessge);
        this.errorMessge = errorMessge;
    }

    protected AbstractBaseException(String errorMessge, Throwable e) {
        super(errorMessge, e);
        this.errorMessge = errorMessge;
    }

    public abstract ResponseCode getErrorEnum();

    public String getErrorMessge() {
        return errorMessge;
    }
}

