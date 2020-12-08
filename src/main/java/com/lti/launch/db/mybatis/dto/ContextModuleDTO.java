package com.lti.launch.db.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContextModuleDTO {

    private long id;
    private long context_id;
    private String context_type;
    private String name;
    private long position;
    private String workflow_state;


}
