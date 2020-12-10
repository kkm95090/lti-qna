package com.lti.launch.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.lti.launch.common.ResponseCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * API 응답 결과에 대한 데이터/코드/메세지를 포함하는 모델 클래스
 *
 * @param <T> the type parameter
 * @author Parking Cloud, bhkwon@iparking.co.kr
 * @since 2019.03.04
 */
@Getter
@Setter
@JsonPropertyOrder({"code", "message", "data"})
public class BaseResult<T> {

    @JsonUnwrapped
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "결과 데이터")
    private T data;
    @ApiModelProperty(value = "결과 코드")
    private String code;
    @ApiModelProperty(value = "결과 메세지")
    private String message;

    /**
     * Instantiates a new Base result.
     *
     * @param resCode the res code
     * @param resMsg  the res msg
     */
    public BaseResult(String resCode, String resMsg) {
        this.code = resCode;
        this.message = resMsg;
    }

    /**
     * Instantiates a new Base result.
     *
     * @param data    the data
     * @param resCode the res code
     * @param resMsg  the res msg
     */
    public BaseResult(String resCode, String resMsg, T data) {
        this.code = resCode;
        this.message = resMsg;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return !StringUtils.isEmpty(this.code) && this.code.equals(ResponseCode.SUCCESS.getCode());
    }
}

