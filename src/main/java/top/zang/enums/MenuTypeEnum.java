package top.zang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型(M目录C菜单F按钮)
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    M("M", "目录"),
    C("C", "菜单"),
    F("F", "按钮");
    private String code;
    private String value;

}
