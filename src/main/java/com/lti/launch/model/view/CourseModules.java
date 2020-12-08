package com.lti.launch.model.view;

import com.lti.launch.db.mybatis.dto.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseModules {

    private CourseDTO course;
    private List<Module> modules;

}
