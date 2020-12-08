package com.lti.launch.model.view;


import com.lti.launch.db.mybatis.dto.ContentTagDTO;
import com.lti.launch.db.mybatis.dto.ContextModuleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Module {

    private ContextModuleDTO contextModule;
    private List<ContentTagDTO> contentTags;

}
