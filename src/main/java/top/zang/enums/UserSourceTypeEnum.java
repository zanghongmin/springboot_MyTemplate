package top.zang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum UserSourceTypeEnum {
    /**
     * 用户请求来源
     */
    backend("backend", "后台用户"),
    app("app", "app移动端");
    private String code;
    private String value;

}
