package cn.cloudcharts.metadata.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wuque
 * @title: Column
 * @projectName xingyun
 * @description:
 * @date 2023/5/614:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description ="字段信息")
public class ColumnDTO implements Serializable {

    private String colName;

    /**
     * 字段类型
     */
    @Schema(description ="【cn.cloudcharts.metadata.enums.ColumnTypeEnums】字段类型:TINYINT,SMALLINT,INT,BIGINT,LARGEINT,DECIMAL,DOUBLE,FLOAT,BOOLEAN,\n" +
            "    CHAR,VARCHAR,STRING,BINARY,\n" +
            "    DATE,DATETIME,\n" +
            "    ARRAY,bitmap,JSON,HLL")
    private String colType;

    /**
     * 取值范围
     * DECIMAL类型输入[precision,scale],以逗号分割
     */
    @Schema(description ="取值范围\n" +
            "     * DECIMAL类型输入[precision,scale],以逗号分割")
    private String len;

    /**
     * 字段默认值 ''
     */
    @Schema(description ="字段默认值 ''")
    private String defaultVal;

    /**
     * 字段注释
     */
    @Schema(description ="字段注释")
    private String comment;

    /**
     * 聚合类型，如果不指定，则该列为 key 列。否则，该列为 value 列。
     * key | agg_type
     */
    @Schema(description ="聚合类型，如果不指定，则该列为 key 列。否则，该列为 value 列: key | agg_type")
    private String keyType;

    /**
     * NOT NULL
     * 列数据是否允许为 NULL
     */
    @Schema(description ="列数据是否允许为 NULL, 不为null 填：NOT NULL")
    private String notNull;
}
