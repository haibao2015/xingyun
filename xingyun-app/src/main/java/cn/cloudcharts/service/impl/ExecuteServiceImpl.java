package cn.cloudcharts.service.impl;

import cn.cloudcharts.common.exception.ServiceException;
import cn.cloudcharts.common.utils.AssertUtil;
import cn.cloudcharts.model.request.SqlRequest;
import cn.cloudcharts.model.entity.Database;
import cn.cloudcharts.service.ExecuteService;
import cn.cloudcharts.service.IDatabaseService;
import cn.cloudcharts.metadata.driver.Driver;
import cn.cloudcharts.metadata.model.result.JdbcSelectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuque
 * @title: ExecuteServiceImpl
 * @projectName xingyun
 * @description: 任务执行
 * @date 2023/5/2320:24
 */
@Service
public class ExecuteServiceImpl implements ExecuteService {

    @Autowired
    private IDatabaseService databaseService;


    @Override
    public JdbcSelectResult executeCommonSql(SqlRequest sqlRequest) {

        Database database = databaseService.getById(sqlRequest.getDatabaseId());
        AssertUtil.isNull(database,"该数据源不存在，请检查！");
        JdbcSelectResult selectResult;

        try {
            Driver driver = Driver.build(database.getDriverConfig());
            selectResult = driver.executeSql(sqlRequest.getStatement(), sqlRequest.getMaxRowNum());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

        return selectResult;
    }


}
