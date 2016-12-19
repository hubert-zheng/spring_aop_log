package com.hubert.mapper;

import com.hubert.bean.AopLog;
import com.hubert.bean.AopLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AopLogMapper {
    int countByExample(AopLogExample example);

    int deleteByExample(AopLogExample example);

    int deleteByPrimaryKey(String sid);

    int insert(AopLog record);

    int insertSelective(AopLog record);

    List<AopLog> selectByExample(AopLogExample example);

    AopLog selectByPrimaryKey(String sid);

    int updateByExampleSelective(@Param("record") AopLog record, @Param("example") AopLogExample example);

    int updateByExample(@Param("record") AopLog record, @Param("example") AopLogExample example);

    int updateByPrimaryKeySelective(AopLog record);

    int updateByPrimaryKey(AopLog record);
}