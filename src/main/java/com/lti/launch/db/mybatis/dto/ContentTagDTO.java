package com.lti.launch.db.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentTagDTO {

    private long id;
    private long content_id;
    private String content_type;
    private String title;
    private long position;
    private String workflow_state;

}
