package top.zang.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 统一返回对象
 */
@ApiModel(value="统一返回对象")
public class ReturnT<T> {
    private static final Logger logger = LoggerFactory.getLogger(ReturnT.class);
    public static final int FAIL_CODE = ReturnTEnum.HTTP_ERROR_500.getCode();
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                jsonGenerator.writeString("");
            }
        });
    }
	@ApiModelProperty(name= "code", value = "返回状态码 200:成功,401:无效的token,403:没有该权限,407:参数解析失败,500:5XX错误 ",example = "200",required = true)
	private int code;
    @ApiModelProperty(name= "msg", value = "返回状态描述",required = true)
	private String msg;
    @ApiModelProperty(name= "result", value = "具体的返回内容",required = true)
	private T result;
    private ReturnT() {

    }
    public static ReturnT  Success() {
        ReturnT returnT = new ReturnT();
        returnT.code =  ReturnTEnum.OK.getCode();
        returnT.msg =  ReturnTEnum.OK.getMsg();
        returnT.result = null;
        printLog(returnT);
        return returnT;
    }
    public static <T> ReturnT<T>  Success(T result) {
        ReturnT returnT = new ReturnT();
        returnT.code =  ReturnTEnum.OK.getCode();
        returnT.msg =  ReturnTEnum.OK.getMsg();
        returnT.result = result;
        printLog(returnT);
        return returnT;
    }
    public static ReturnT  Fail() {
        ReturnT returnT = new ReturnT();
        returnT.code =  ReturnTEnum.HTTP_ERROR_500.getCode();
        returnT.msg =  "error";
        returnT.result = null;
        printLog(returnT);
        return returnT;
    }
    public static ReturnT  Fail(String msg) {
        ReturnT returnT = new ReturnT();
        returnT.code =  ReturnTEnum.HTTP_ERROR_500.getCode();
        returnT.msg =  msg;
        returnT.result = null;
        printLog(returnT);
        return returnT;
    }
    public static <T> ReturnT<T>  Fail(String msg,T result) {
        ReturnT returnT = new ReturnT();
        returnT.code =  ReturnTEnum.HTTP_ERROR_500.getCode();
        returnT.msg =  msg;
        returnT.result = result;
        printLog(returnT);
        return returnT;
    }
    public static <T> ReturnT<T>  Common(ReturnTEnum resultStatusCode) {
        ReturnT returnT = new ReturnT();
        returnT.code =  resultStatusCode.getCode();
        returnT.msg =  resultStatusCode.getMsg();
        returnT.result = null;
        printLog(returnT);
        return returnT;
    }
    public static <T> ReturnT<T>  Common(ReturnTEnum resultStatusCode, T result) {
        ReturnT returnT = new ReturnT();
        returnT.code =  resultStatusCode.getCode();
        returnT.msg =  resultStatusCode.getMsg();
        returnT.result = result;
        printLog(returnT);
        return returnT;
    }

    /**
     * 打印日志
     */
    private static void printLog(ReturnT returnT) {
        if(returnT.code==ReturnTEnum.OK.getCode()){
            try {
                logger.info("返回成功结果,状态码:{},状态描述:{},返回内容:{}",returnT.code,returnT.msg,returnT==null?null:objectMapper.writeValueAsString(returnT));
            } catch (JsonProcessingException e) {
                logger.error("返回成功结果日志解析失败:{}",e.getMessage());
            }
        }else{
            try {
                logger.info("返回失败结果,状态码:{},状态描述:{},返回内容:{}",returnT.code,returnT.msg,returnT==null?null:objectMapper.writeValueAsString(returnT));
            } catch (JsonProcessingException e) {
                logger.error("返回成功结果日志解析失败:{}",e.getMessage());
            }
        }
    }

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
