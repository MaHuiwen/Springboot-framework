package com.fzm.boot.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujincheng on 17/11/7.
 */
public class BeanCopyUtil {

    private  final static Logger logger = LoggerFactory.getLogger(BeanCopyUtil.class);

    /**
     *
     * @param srcList 源List
     * @param targetClass 目标List的Class
     * @return 包装了targetClass类型的List
     */
    public static List listCopy(List srcList,Class targetClass){
        List list  = new ArrayList<Object>();
        for(Object srcObj : srcList){
            list.add(beanCopy(srcObj,targetClass));
        }
        return list;
    }

    /**
     *
     * @param src 源对象
     * @param targetClass 目标对象的类型
     * @return 目标对象 
     */
    public static Object beanCopy(Object src,Class targetClass){
        Object targetObj = null;
        try{
            targetObj = targetClass.newInstance();
            BeanUtils.copyProperties(src,targetObj);
        }catch (IllegalAccessException e){
            logger.error("BeanCopyUtil error {}",e);
        }catch (InstantiationException e){
            logger.error("BeanCopyUtil error {}",e);
        }
        return targetObj;

    }

}
