package com.mhw.boot.mapper;

import com.mhw.boot.model.po.UserToken;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserToken record);

    UserToken selectByPrimaryKey(Long id);

    List<UserToken> selectAll();

    int updateByPrimaryKey(UserToken record);

    /**
     * 通过用户编号更新token和更改时间
     *
     * @param userToken
     * @return 操作成功返回1，失败返回0
     * @author mhw_mhw
     * @data 2018/6/22 14:48
     */
    int updateByUserId(UserToken userToken);

    /**
     * 通过用户编号查询数据
     *
     * @param userId 用户编号
     * @return UserToken
     * @author mhw_mhw
     * @data 2018/6/22 18:08
     */
    UserToken getByUserId(Long userId);
}