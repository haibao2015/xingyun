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


package cn.cloudcharts.xingyun.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.cloudcharts.xingyun.client.JSON;

/**
 * SqlDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-05-26T11:49:41.855+08:00[Asia/Shanghai]")
public class SqlDTO {
  public static final String SERIALIZED_NAME_STATEMENT = "statement";
  @SerializedName(SERIALIZED_NAME_STATEMENT)
  private String statement;

  public static final String SERIALIZED_NAME_DATABASE_ID = "databaseId";
  @SerializedName(SERIALIZED_NAME_DATABASE_ID)
  private Integer databaseId;

  public static final String SERIALIZED_NAME_MAX_ROW_NUM = "maxRowNum";
  @SerializedName(SERIALIZED_NAME_MAX_ROW_NUM)
  private Integer maxRowNum;

  public SqlDTO() {
  }

  public SqlDTO statement(String statement) {
    
    this.statement = statement;
    return this;
  }

   /**
   * Get statement
   * @return statement
  **/
  @javax.annotation.Nullable
  public String getStatement() {
    return statement;
  }


  public void setStatement(String statement) {
    this.statement = statement;
  }


  public SqlDTO databaseId(Integer databaseId) {
    
    this.databaseId = databaseId;
    return this;
  }

   /**
   * Get databaseId
   * @return databaseId
  **/
  @javax.annotation.Nullable
  public Integer getDatabaseId() {
    return databaseId;
  }


  public void setDatabaseId(Integer databaseId) {
    this.databaseId = databaseId;
  }


  public SqlDTO maxRowNum(Integer maxRowNum) {
    
    this.maxRowNum = maxRowNum;
    return this;
  }

   /**
   * Get maxRowNum
   * @return maxRowNum
  **/
  @javax.annotation.Nullable
  public Integer getMaxRowNum() {
    return maxRowNum;
  }


  public void setMaxRowNum(Integer maxRowNum) {
    this.maxRowNum = maxRowNum;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SqlDTO sqlDTO = (SqlDTO) o;
    return Objects.equals(this.statement, sqlDTO.statement) &&
        Objects.equals(this.databaseId, sqlDTO.databaseId) &&
        Objects.equals(this.maxRowNum, sqlDTO.maxRowNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statement, databaseId, maxRowNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SqlDTO {\n");
    sb.append("    statement: ").append(toIndentedString(statement)).append("\n");
    sb.append("    databaseId: ").append(toIndentedString(databaseId)).append("\n");
    sb.append("    maxRowNum: ").append(toIndentedString(maxRowNum)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("statement");
    openapiFields.add("databaseId");
    openapiFields.add("maxRowNum");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to SqlDTO
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (!SqlDTO.openapiRequiredFields.isEmpty()) { // has required fields but JSON object is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in SqlDTO is not found in the empty JSON string", SqlDTO.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!SqlDTO.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `SqlDTO` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }
      if ((jsonObj.get("statement") != null && !jsonObj.get("statement").isJsonNull()) && !jsonObj.get("statement").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `statement` to be a primitive type in the JSON string but got `%s`", jsonObj.get("statement").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!SqlDTO.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'SqlDTO' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<SqlDTO> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(SqlDTO.class));

       return (TypeAdapter<T>) new TypeAdapter<SqlDTO>() {
           @Override
           public void write(JsonWriter out, SqlDTO value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public SqlDTO read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of SqlDTO given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of SqlDTO
  * @throws IOException if the JSON string is invalid with respect to SqlDTO
  */
  public static SqlDTO fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, SqlDTO.class);
  }

 /**
  * Convert an instance of SqlDTO to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

