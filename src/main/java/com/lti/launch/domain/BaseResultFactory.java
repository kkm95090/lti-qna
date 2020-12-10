package com.lti.launch.domain;

import com.lti.launch.common.ResponseCode;
import com.lti.launch.exception.AbstractBaseException;
import com.lti.launch.util.MDCUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * API 응답에 대한 객체 생성을 담당하는 클래스
 *
 * @author Parking Cloud, bhkwon@iparking.co.kr
 * @since 2019.03.04
 */
@Slf4j
public class BaseResultFactory {

    private static BaseResultFactory instance = new BaseResultFactory();

    /**
     * Create success base result.
     *
     * @param <T> the type parameter
     * @param t   the t
     * @return the base result
     */
    public static <T> BaseResult<T> createSuccess(T t) {
        return instance.internalCreate(t);
    }

    /**
     * Create success base result.
     *
     * @param <T> the type parameter
     * @return the base result
     */
    public static <T> BaseResult<T> createSuccess() {
        return instance.internalCreate(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }

    /**
     * Create fail base result.
     *
     * @param <T>      the type parameter
     * @param codeEnum the code enum
     * @return the base result
     */
    public static <T> BaseResult<T> createFail(ResponseCode codeEnum) {
        return instance.internalCreate(codeEnum.getCode(), codeEnum.getDesc());
    }

    public static <T> BaseResult<T> createFail(BaseResult baseResult) {
        return baseResult;
    }

    public static <T> BaseResult<T> createFail(ResponseCode codeEnum, String errorMsg) {
        log.error("SESSION ID=" + MDCUtil.get(MDCUtil.REQUEST_ID)
                + "\r ERROR CODE=" + "'" + codeEnum.getCode() + "'"
                + "\r ERROR MESSAGE=" + "'" + codeEnum.getDesc() + "'", errorMsg);
        return createFail(codeEnum);
    }

    /**
     * Create fail base result.
     *
     * @param ex the ex
     * @return the base result
     */
    public static <T> BaseResult<T> createFail(AbstractBaseException ex) {
        ResponseCode codeEnum = ex.getErrorEnum();
        log.error("SESSION ID=" + MDCUtil.get(MDCUtil.REQUEST_ID)
                + "\r ERROR CODE=" + "'" + codeEnum.getCode() + "'"
                + "\r ERROR MESSAGE=" + "'" + codeEnum.getDesc() + "'", ex);
        return instance.internalCreate(codeEnum.getCode(), codeEnum.getDesc());
    }

    /**
     * Create fail base result.
     *
     * @param <T>    the type parameter
     * @param errors the errors
     * @return the base result
     */
    public static <T> BaseResult<T> createFail(Errors errors) {
        /*최상위 예외 처리에 대한 부분만 노출*/
        ObjectError error = errors.getAllErrors().get(0);
        ResponseCode codeEnum = (ResponseCode) error.getArguments()[1];
        return instance.internalCreate(codeEnum.getCode(), codeEnum.getDesc() + ": " + error.getDefaultMessage());
    }

    private <T> BaseResult<T> internalCreate(T t) {
        return internalCreate(t, ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }

    private <T> BaseResult<T> internalCreate(String resCode, String resMsg) {
        return new BaseResult(resCode, resMsg);
    }

    private <T> BaseResult<T> internalCreate(T t, String resCode, String resMsg) {
        return new BaseResult(resCode, resMsg, t);
    }
}

