package com.lti.launch.db.mybatis.mapper.mariadb;

import java.util.List;
import java.util.Map;

@MariaDBConnMapper
public interface MariaDBSampleMapper {

    public List<Map<String, String>> findAll();
}
