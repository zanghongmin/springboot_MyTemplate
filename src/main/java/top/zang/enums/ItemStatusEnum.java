package top.zang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatusEnum {

    NORMAL(0, "正常"),
    FROZEN(1, "禁用");
    private int code;
    private String value;

}
