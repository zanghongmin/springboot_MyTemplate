package top.zang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatusEnum {

    NORMAL((byte)0, "正常"),
    FROZEN((byte)1, "禁用");
    private byte code;
    private String value;

}
