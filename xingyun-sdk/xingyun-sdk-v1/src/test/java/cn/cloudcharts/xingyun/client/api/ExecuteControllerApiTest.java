/*
 * 星云 SpringDoc API
 * 星云 SpringDoc Application
 *
 * The version of the OpenAPI document: 1.0.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package cn.cloudcharts.xingyun.client.api;

import cn.cloudcharts.xingyun.client.ApiClient;
import cn.cloudcharts.xingyun.client.ApiException;
import cn.cloudcharts.xingyun.client.model.RJdbcSelectResult;
import cn.cloudcharts.xingyun.client.model.SqlDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * API tests for ExecuteControllerApi
 */
@Disabled
public class ExecuteControllerApiTest {

    private final ExecuteControllerApi api = new ExecuteControllerApi();

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void executeSqlTest() throws ApiException {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("http://192.168.217.140:8088");
        ExecuteControllerApi api = new ExecuteControllerApi(apiClient);

        SqlDTO sqlDTO = new SqlDTO();
        sqlDTO.setDatabaseId(1);
        sqlDTO.setStatement("show catalogs;");
        sqlDTO.setMaxRowNum(100);
        RJdbcSelectResult response = api.executeSql(sqlDTO);

        System.out.println(response.toJson());
    }

}