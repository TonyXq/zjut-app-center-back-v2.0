package com.xdbigdata.app_center.exception;

/**
 * Created by tangyijun on 2016/8/12.
 * good good study,day day up!
 */
public enum ErrorCode {
    //-----------------------------统一异常捕获（50***）错误码开始-------------------------------------
    /**程序内部错误，操作失败*/
    INTERNAL_PROGRAM_ERROR(50000,"程序内部错误，操作失败"),
    //说明：以下的异常名称定义，为了可读性均是异常原名，不建议作全部大写‘_’分隔 样式 lyl
    /**数据库操作失败*/
    DataAccessException(50001,"数据库操作失败"),
    /**
     * 数据库连接异常
     */
    CommunicationsException(50002, "数据库连接中断"),
//    /**违反数据库约（唯一）束异常*/
//    ConstraintViolationException(50002,"对象已经存在，请勿重复操作"),
    /**hibernate 违反数据库约（唯一）束异常*/
    DataIntegrityViolationException(50003,"对象已经存在，请勿重复操作"),
    /**mysql 违反数据库约（唯一）束异常*/
    MySQLIntegrityConstraintViolationException(50004,"对象已经存在，请勿重复操作"),
    /**空指针异常*/
    NullPointerException(50005,"调用了未经初始化的对象或者是不存在的对象"),
    /**IO异常*/
    IOException(50006,"IO异常"),
    /**指定的类不存在*/
    ClassNotFoundException(50007,"指定的类不存在"),
    /**数学运算异常*/
    ArithmeticException(50008,"数学运算异常"),
    /**数组下标越界*/
    ArrayIndexOutOfBoundsException(50009,"数组下标越界"),
    /**方法的参数错误或非法*/
    IllegalArgumentException(50010,"参数错误或非法"),
    /**类型强制转换错误*/
    ClassCastException(50011,"类型强制转换错误"),
    /**操作数据库异常*/
    SQLException(50013,"操作数据库异常"),
    /**违背安全原则异常*/
    SecurityException(50012,"违背安全原则异常"),
    /**方法未找到异常*/
    NoSuchMethodException(50014,"方法未找到异常"),
    /**Java虚拟机发生了内部错误*/
//    InternalError(50015,"内部错误"),
    ConnectException(50016,"服务器连接异常"),
    CancellationException(50017,"任务已被取消的异常"),
//    /**Java阿里服务器错误*/
//    ApiException(50018,"阿里服务器错误"),
    /**[日期]或[数值]等转换错误*/
    ParseException(50019,"日期格式错误"),

    //-----------------------------统一异常捕获（50***）错误码结束-------------------------------------


    //-----------------------------参数异常（51***）错误码开始-------------------------------------
    ParaIsNull(51002,"参数为空"),
    paraNotRight(51003,"参数非法"),
    //-----------------------------参数异常（51***）错误码结束-------------------------------------

    //-----------------------------公共操作成功、失败（60***）错误码开始-------------------------------------
    HANDLER_SUCCESS(60000,"操作成功"),
    HANDLER_FAILED(60001,"操作失败"),
    SAVE_SUCCESS(60002,"新增成功"),
    SAVE_FAILED(60003,"新增失败"),
    DELETE_SUCCESS(60004,"删除成功"),
    DELETE_FAILED(60005,"删除失败"),
    UPDATE_SUCCESS(60006,"修改成功"),
    UPDATE_FAILED(60007,"修改失败"),
    SET_SUCCESS(60008,"设置成功"),
    SET_FAILED(60009,"设置失败"),
    /**无对应数据*/
    NO_DATA(60010,"无对应数据"),
    /**同步成功*/
    SYNC_SUCCESS(60011,"同步成功"),
    /**同步失败*/
    SYNC_FAILED(60012,"同步失败"),
    /**同步数据为空*/
    SYNC_DATA_IS_NULL(60013,"同步数据为空"),
    /**同步数据部分成功*/
    SYNC_DATA_NOT_ALL_SUCCESS(60014,"同步数据部分成功"),
    /** 查询成功 */
    FIND_SUCCESS(60015,"查询成功"),
    /** 查询失败 */
    FIND_FAILED(60016,"查询失败"),
    //-----------------------------公共操作成功、失败（60***）错误码结束-------------------------------------

    //-----------------------------登录验证成功、失败（70***）错误码开始-------------------------------------
    /** 登录成功 */
    LOGIN_SUCCESS(70000,"登录成功"),
    /** 用户不存在 */
    LOGIN_FAILED(70001,"用户不存在"),
    /** 用户不存在 */
    LOGIN_FAILED_USERNAME(70002,"用户不存在"),
    /** 密码错误 */
    LOGIN_FAILED_PASSWORD(70003,"密码错误"),
    /** 用户名或密码错误 */
    LOGIN_FAILED_USERNAME_OR_PASSWORD(70004,"用户名或密码错误"),
    /** 请重新登录 */
    LOGIN_FAILED_SESSION_TIMEOUT(70005,"重新登录"),
    /** 用户异常 */
    LOGIN_ERROR_USER(70006,"用户状态异常"),
    //-----------------------------登录验证成功、失败（70***）错误码结束-------------------------------------

    //-----------------------------登录验证成功、失败（70***）错误码开始-------------------------------------
    /** 新闻动态置顶 */
    NEWS_ERROR_TOP_MAX(80000,"最多只能有4个置顶新闻"),

    //-----------------------------登录验证成功、失败（70***）错误码结束-------------------------------------
    NOT_FOUND_PAGE(404, "找不到页面"),

    //-----------------------------贷款相关错误码及描述------------------------------------------------------------
    APPLY_LOAN_SUCCESS(90000,"预申请成功"),
    LOAN_NO_EXIST(90001,"该贷款项不存在"),
    LOAN_IS_STOP(90002,"该贷款项已停止申请"),
    LOAN_REPEAT(90003, "重复申请贷款"),
    SYS_EXCEPTION(90004, "系统异常"),
    FILE_TYPE_ERROR(90005,"文件类型不匹配"),
    EXCEL_IS_NULL(90006,"数据为空"),
    EXCEL_COLUMN_ERROR(90007,"导入数据的列数不正确"),
    FILE_UPLOAD_SUCCESS(90008, "文件上传成功"),
    INSUFFICIENT_USER_PERMISSIONS(90009, "用戶权限不足"),


    ;

    public Integer code;
    public String des;

    ErrorCode(Integer code, String des){
        this.code = code;
        this.des = des;
    }

    public static ErrorCode get(Integer code){
        for(ErrorCode errorCode:ErrorCode.values()){
            if(errorCode.code.toString().equals(code.toString())){
                return errorCode;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return "code:"+code +", des:"+des;
    }

}
