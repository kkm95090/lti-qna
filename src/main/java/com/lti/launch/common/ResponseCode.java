package com.lti.launch.common;

public enum ResponseCode {

    SUCCESS("200", "성공"),
    FAIL("900", "실패"),
    INVALID_REQUEST("400", "요청 데이터 오류"),
    BINDING_FAIL("400", "필수값이 누락 되었습니다. 관리자에게 문의 하세요."),
    INVALID_DATA("401", "데이터 형식 오류"),
    SYSTEM_ERROR("500", "시스템 오류."),
    UNKNOWN_EXCEPTION("500", "unknown exception"),
    RUNTIME_EXCEPTION("500", "Runtime Exception!"),
    NO_RESPONSE_URL("512", "요청 URL 응답 오류"),
    HTTP_COMMON_ERROR("512", "외부 통신 오류"),
    REQUEST_TIMEOUT("408", "Request Timeout"),
    IO_EXCEPTION("408", "Request Timeout"),
    MYBATIS_EXCEPTION("500", "mybatis system exception"),
    ADDRESS_EXCEPTION("601", "주소 검색 오류"),
    CELLPHONE_NUMBER_EXCEPTION("603", "전화번호 오류");

    private String code;
    private String desc;

    ResponseCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Getter for property 'code'.
     *
     * @return Value for property 'code'.
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter for property 'desc'.
     *
     * @return Value for property 'desc'.
     */
    public String getDesc() {
        return desc;
    }
}
