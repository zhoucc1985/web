package com.cloud.mapper.master.datacollect;

import com.cloud.entity.datacollect.CollectEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectEnumMapper {
    List<CollectEnum> fineEnumsBySystemNameAndLevelName(@Param(value = "systemName") String systemName,
                                                        @Param(value = "levelName") String levelName);

    List<CollectEnum> selectCollectEnum();

    String selectEnumFirst(@Param("firstTypeCode") Integer firstTypeCode,@Param("parentId") Integer parentId);

    String selectEnumSecond( @Param("secondTypeCode") Integer secondTypeCode);
}
