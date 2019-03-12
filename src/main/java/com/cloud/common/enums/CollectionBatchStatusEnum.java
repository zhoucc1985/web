package com.cloud.common.enums;

/**
 * 批次状态
 *
 * @program cloudIAS
 * @author: Pan Jianneng
 * @create: 2018/11/17 17:58
 */
public enum CollectionBatchStatusEnum {

    /**
     * 枚举定义
     */
    CHECKING("数据校验中", "CHECKING"), CHECKED("校验完成", "CHECKED"), IMPORTING("数据导入中", "IMPORTING");
    /**
     * 成员变量value
     */
    private String name;
    /**
     * 成员变量key
     */
    private String key;
    /**
     * 添加方法描述
     * @author Pan Jianneng
     * @date 2018/11/17 6:08 PM
     * @param 
     * @return 
    */
    CollectionBatchStatusEnum(String name, String key) {
        this.name = name;
        this.key = key;
    }
    /**
     * 根据key获取名称
     * @author Pan Jianneng
     * @date 2018/11/17 6:08 PM
     * @param 
     * @return 
    */
    public static String getName(String key) {
        for (CollectionBatchStatusEnum c : CollectionBatchStatusEnum.values()) {
            if ( c.key == key) {
                return c.name;
            }
        }
        return null;
    }

}
