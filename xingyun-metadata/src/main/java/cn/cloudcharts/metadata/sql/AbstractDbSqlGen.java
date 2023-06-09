package cn.cloudcharts.metadata.sql;

import cn.cloudcharts.common.support.CustomSQL;
import cn.cloudcharts.common.utils.AssertUtil;
import cn.cloudcharts.metadata.model.dto.CreateTableDTO;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author wuque
 * @title: AbstractDdlOpertion
 * @projectName xingyun
 * @description:
 * @date 2023/5/2919:56
 */
public  abstract class AbstractDbSqlGen implements IDbSqlGen {


    @Override
    public String buildCreateTableSql(CreateTableDTO tableDTO) {


        return null;
    }

    @Override
    public String queryAllColumns(String catalogName, String dbName, String tableName) {

        return null;
    }

    @Override
    public String getTableList(String catalogName, String dbName) {
        AssertUtil.checkNullString(dbName, "数据库名不可空");

        Map map = Maps.newHashMap();
        if( StrUtil.isNotEmpty(catalogName)){
            map.put("catalogName", catalogName);
        }

        map.put("dbName",dbName);

        return CustomSQL.getInstance().get("dml.jdbc.getTableList",map);
    }

    @Override
    public String exsitTbl(String catalogName, String dbName, String tblName) {
        AssertUtil.checkNullString(dbName, "数据库名不可空");
        AssertUtil.checkNullString(tblName, "表名不可空");

        Map map = Maps.newHashMap();
        if( StrUtil.isNotEmpty(catalogName)){
            map.put("catalogName", catalogName);
        }

        map.put("dbName",dbName);
        map.put("tblName",tblName);

        return CustomSQL.getInstance().get("dml.jdbc.exsitTbl",map);
    }

    @Override
    public String exsitSchema(String catalogName, String dbName) {

        AssertUtil.checkNullString(dbName, "数据库名不可空");

        Map map = Maps.newHashMap();
        if( StrUtil.isNotEmpty(catalogName)){
            map.put("catalogName", catalogName);
        }
        map.put("dbName",dbName);

        return CustomSQL.getInstance().get("dml.jdbc.exsitSchema",map);
    }

    @Override
    public String createSchema(String dbName) {

        AssertUtil.checkNullString(dbName, "数据库名不可空");

        Map map = Maps.newHashMap();
        map.put("dbName",dbName);

        return CustomSQL.getInstance().get("dml.jdbc.createSchema",map);
    }

}
