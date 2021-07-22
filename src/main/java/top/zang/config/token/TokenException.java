package top.zang.config.token;

/**
 */
public class TokenException extends RuntimeException {
    public String getDetailMessage() {
        return detailMessage;
    }

    private String detailMessage;

    public TokenException(String detailMessage){
        this.detailMessage = detailMessage;
    }


}
