package com.lti.launch.db.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data               //모든 private 필드에 대한 Getter,Setter,ToString, EqualsAndHashCode 오버라이드, RequiredArgsConstructor(필수파라미터 생성자)
@AllArgsConstructor //모든 파라미터를 받는 생성자
@NoArgsConstructor  //파라미터 값이 없는 빈 생성자
@Builder            //모든 멤버 필드에 대해서 매개변수를 받는 기본 생성자 생성
public class QnaDTO {
    private int qna_no;
    private int qna_account_id;
    private int qna_course_id;
    private String qna_title;
    private String qna_contents;
    private int qna_user_id;
    private String qna_secret;
    private int qna_attach_id;
    private Date qna_insert_date;
    private Date qna_update_date;
    private String qna_delete_YN;
    private String qna_name;
}
