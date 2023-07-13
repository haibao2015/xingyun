package cn.cloudcharts.metadata.task;

import cn.cloudcharts.common.support.CustomSQL;
import cn.cloudcharts.common.utils.GsonUtils;
import cn.cloudcharts.common.utils.bean.BeanUtils;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuque
 * @title: StarrocksTaskOpertion
 * @projectName xingyun
 * @description:
 * @date 2023/6/2717:18
 */
@Slf4j
public class StarrocksTaskOpertion implements ITaskOpertion{


    @Override
    public boolean submitSyncTask(Connection conn, SyncTaskGenInfo taskGenInfo) {

        Map map = BeanUtils.bean2Map(taskGenInfo);
        String taskSql = CustomSQL.getInstance().get("task.sr.sync.insert.async",map);

        int res = 0;
        try {
            QueryRunner queryRunner = new QueryRunner();
            res = queryRunner.update(conn,taskSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        log.info("result:{},submit task:{}",res,taskSql);

//        if(res > 0){
        ThreadUtil.execAsync(() -> {
            Map resMap = new HashMap();
            resMap.put("taskName",taskGenInfo.getTaskName());
            String taskResultSql = CustomSQL.getInstance().get("task.sr.sync.insert.async.result",resMap);
            try {
                QueryRunner queryRunner = new QueryRunner();
                TaskRunsResult taskRunsResult = queryRunner.query(conn,taskResultSql,new BeanHandler<TaskRunsResult>(TaskRunsResult.class, new BasicRowProcessor(new GenerousBeanProcessor())));
                log.info("task:{},result:{}",taskGenInfo.getTaskName(), GsonUtils.gsonString(taskRunsResult));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
//        }
        log.info("result:{},submit task:{}",res,taskSql);

//        if(res > 0){
        ThreadUtil.execAsync(() -> {
            Map resMap = new HashMap();
            resMap.put("taskName",taskGenInfo.getTaskName());
            String taskResultSql = CustomSQL.getInstance().get("task.sr.sync.insert.async.result",resMap).toLowerCase();
            try {
                QueryRunner queryRunner = new QueryRunner();
                TaskRunsResult taskRunsResult = queryRunner.query(conn,taskResultSql,new BeanHandler<TaskRunsResult>(TaskRunsResult.class, new BasicRowProcessor(new GenerousBeanProcessor())));
                log.info("task:{},result:{}",taskGenInfo.getTaskName(), GsonUtils.gsonString(taskRunsResult));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
//        }

        return res > 0;
    }
}
