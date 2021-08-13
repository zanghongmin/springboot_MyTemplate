package top.zang.core.exception;

import cn.hutool.core.util.StrUtil;

public interface IAssert<T extends RuntimeException> {
  T ofException(Object... paramVarArgs);

  default  void isEmpty(Object str, Object... args) {
    if (str==null||StrUtil.isEmpty(str.toString())){
      throw ofException(args);
    }
  }
  default  void isTrue(boolean expression, Object... args) {
    if (expression){
      throw ofException(args);
    }
  }

  default  <R> R throwException(Object... args) {
      throw ofException(args);
  }
}
