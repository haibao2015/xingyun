package cn.cloudcharts.metadata.sql;

import cn.cloudcharts.common.exception.ServiceException;
import cn.cloudcharts.common.support.CustomSQL;
import cn.cloudcharts.common.utils.AssertUtil;
import cn.cloudcharts.common.utils.StringUtils;
import cn.cloudcharts.common.utils.bean.BeanUtils;
import cn.cloudcharts.metadata.enums.ColumnTypeEnums;
import cn.cloudcharts.metadata.enums.TblDataModelEnums;
import cn.cloudcharts.metadata.model.dto.AlertColumnDTO;
import cn.cloudcharts.metadata.model.dto.ColumnDTO;
import cn.cloudcharts.metadata.model.dto.CreateTableDTO;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wuque
 * @title: StarRocksDdlOpertion
 * @projectName xingyun
 * @description:
 * @date 2023/5/2919:57
 */
public class StarRocksDbSqlGen extends AbstractDbSqlGen {


    @Override
    public String buildCreateTableSql(CreateTableDTO dto) {

//          0-明细 1-聚合 2-更新 3-主键
            if(!TblDataModelEnums.contains(dto.getTblDataType())){
                throw new ServiceException("Please select a data model! " +
                        "StarRocks provides four table types: [0]Duplicate Key table, [1]Aggregate table, [2]Unique Key table, and [3]Primary Key table.");
            }
            String sqlId = "ddl.sr.createTbl";
            if("1".equals(dto.getTblType())){ //外表
                sqlId = "ddl.sr.createExternalTbl";
            }

        List<String> colList = dto.getCols().stream().map(columnDTO -> {
            String colType = columnDTO.getColType().toUpperCase(Locale.ROOT);
            if( colType.contains(ColumnTypeEnums.DATETIME.name())
                    || colType.contains(ColumnTypeEnums.DATE.name())
                    || colType.contains(ColumnTypeEnums.INT.name())
                    || colType.contains(ColumnTypeEnums.TINYINT.name()) ){
                return columnDTO.getColName();
            }
            return null;
        }).filter(c ->{ return null != c; }).collect(Collectors.toList());

        String colDesc = colList.size() > 0 ? colList.get(0) : dto.getCols().get(0).getColName();

        //如果keydesc排序键未指定，则默认取一个
        dto.setKeyDesc(StringUtils.isEmpty(dto.getKeyDesc())?colDesc : dto.getKeyDesc());

        //如果分桶未指定，则默认取keydesc
        dto.setDistributedCols(StringUtils.isEmpty(dto.getDistributedCols())?dto.getKeyDesc() : dto.getDistributedCols());

        //主键模型  主键必须定义在其他列之前
        List<ColumnDTO> columnSortList = new LinkedList<>();
//        if(TblDataModelEnums.PRIMARY.getCode().equals(dto.getTblDataType())){
            List<ColumnDTO> columnDTOS = dto.getCols();
            String keyDesc = dto.getKeyDesc();
            if(StringUtils.isEmpty(keyDesc)){
                throw new ServiceException("The primary key model needs to define the primary key!");
            }
            String keyDescs[] = keyDesc.split(",");
            for (int i = 0; i < keyDescs.length; i++) {
                String key = keyDescs[i];
                ColumnDTO columnDTO = columnDTOS.parallelStream().filter(column -> {
                    return key.equals(column.getColName());
                }).findFirst().get();
                if(ObjectUtil.isNotEmpty(columnDTO)){
                    columnSortList.add(columnDTO);
                    columnDTOS.remove(columnDTO);
                }
            }
            columnSortList.addAll(columnDTOS);
            dto.setCols(columnSortList);
//        }

        Map<String, Object> map = BeanUtils.nestedObj2Map(dto);
        if(null != dto.getProperties() && dto.getProperties().size() > 0){
            map.put("properties",dto.getProperties());
        }
        return CustomSQL.getInstance().get(sqlId,map);
    }

    @Override
    public String queryAllColumns(String catalogName, String dbName, String tableName) {

        AssertUtil.checkNullString(dbName, "数据库名不可空");
        AssertUtil.checkNullString(tableName, "表名不可空");

        Map map = Maps.newHashMap();
        map.put("catalogName", StrUtil.isEmpty(catalogName)?"default_catalog":catalogName);
        map.put("dbName",dbName);
        map.put("tableName",tableName);

        return CustomSQL.getInstance().get("ops.sr.queryAllColumns",map);
    }

    @Override
    public String getExecQuery(String sql, Integer limit) {
        Map map = Maps.newHashMap();
        map.put("sql",sql);
        map.put("maxVal",limit);
        return CustomSQL.getInstance().get("dml.sr.execQuery",map);
    }

    @Override
    public String buildAddColumnsSql(AlertColumnDTO dto) {
        Map<String, Object> map = BeanUtils.nestedObj2Map(dto);
        return CustomSQL.getInstance().get("ddl.sr.addColumns",map);
    }

    @Override
    public String getSchemaList(String catalogName) {
        Map map = Maps.newHashMap();
        map.put("catalogName",catalogName);
        return CustomSQL.getInstance().get("dml.sr.getSchemaList",map);
    }

    @Override
    public String getPartitionsList(String schema, String tbl) {
        Map map = Maps.newHashMap();
        map.put("schema",schema);
        map.put("tbl",tbl);
        return CustomSQL.getInstance().get("dml.sr.getPartitionsList",map);
    }

    @Override
    public String tblNormal(String schema, String tbl, String operType) {
        Map map = Maps.newHashMap();
        map.put("schema",schema);
        map.put("tbl",tbl);
        map.put("operType",operType);
        return CustomSQL.getInstance().get("ddl.sr.tblNormal",map);
    }

    @Override
    public String exsitSchema(String catalogName, String dbName) {
        AssertUtil.checkNullString(dbName, "数据库名不可空");

        Map map = Maps.newHashMap();
        if( StrUtil.isNotEmpty(catalogName)){
            map.put("catalogName", catalogName);
        }
        map.put("dbName",dbName);

        return CustomSQL.getInstance().get("dml.sr.exsitSchema",map);
    }
}
