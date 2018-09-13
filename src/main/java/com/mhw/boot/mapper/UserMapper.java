package com.mhw.boot.mapper;

import com.mhw.boot.model.po.User;
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
     * @author mhw_mhw
     * @data 2018/6/22 10:33
     */
    User getByTel(String userTel);

    /**
     * 定时任务：更新modify_time
     *
     * @param CurrentTime
     * @return
     * @author mhw_mhw
     * @date 2018/8/20 11:14
     */
    void updateTime(Long CurrentTime);

    /**
     * 批量插入多条数据
     *
     * @param
     * @return
     * @author mhw_mhw
     * @date 2018/8/23 14:42
     */
    void insertUserList(List<User> userList);
}