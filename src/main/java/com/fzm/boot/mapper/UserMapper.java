package com.fzm.boot.mapper;

import com.fzm.boot.model.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    /**
     * 通过手机号查找用户信息
     *
     * @param userTel 用户手机号
     * @return List<User> 可能返回多个重复手机号。多数情况返回为null或为单条数据
     * @author fzm_mhw
     * @data 2018/6/22 10:33
     */
    User getByTel(String userTel);
}