package com.fzm.boot.commons.util;

import com.alibaba.fastjson.JSON;
import com.fzm.boot.commons.bean.FileBean;
import com.fzm.boot.commons.constant.ResponseEnum;
import com.fzm.boot.commons.exception.ThirdPartyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



/**
 * 文件工具类
 *
 * @Author fzm_mhw
 * @Date 2018/6/15 14:34
 * @Version
 */
public class FileUtil {

    private static final String SLASH = "/";
    private static final String WHIPPLETREE = "-";
    private static final String DOT = ".";

    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 上传单个文件，用UUID代替文件名
     *
     * @param file
     * @param request
     * @return String 上传结果
     * @author fzm_mhw
     * @data 2018/6/15 14:31
     */
    public static FileBean uploadFile(MultipartFile file, HttpServletRequest request) {
        if(file.isEmpty()) {
            throw new ThirdPartyException(ResponseEnum.FILE_EMPTY_ERROR.getCode(), ResponseEnum.FILE_EMPTY_ERROR.getMsg());
        }

        //获取文件名(带后缀名)和后缀名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为 ：" + fileName);
        int pointLocation = fileName.lastIndexOf(DOT);
        String suffixName = fileName.substring(pointLocation + 1);
        //文件上传后的路径
        String filePath = request.getSession().getServletContext().getRealPath(SLASH);
        logger.info("filePath : " + filePath);

        //解决中文问题，linux下中文路径，图片显示问题。
        //文件名 = uuid + "." + 后缀名。
        String uuid = UUID.randomUUID().toString().replace(WHIPPLETREE, "");
        String fileUUIDName = uuid.concat(DOT).concat(suffixName);
        //目录 = 路径 + "\" + （文件夹名 + "\" +）文件名
        File dest = new File(filePath + "\\" + fileUUIDName);
        //检测是否存在目录，不存在则新建目录
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        //上传文件
        try {
            file.transferTo(dest);
            //封装文件信息(文件名，后缀名，UUID，URL),并返回
            FileBean fileBean = new FileBean();
            fileBean.setFileName(fileName.substring(0, pointLocation));
            fileBean.setFileSuffixName(suffixName);
            fileBean.setFileUUID(uuid);
            //此处有两个斜杠，需要修改
            fileBean.setUrl(filePath.concat(fileUUIDName).replaceAll("\\\\\\\\", "\\\\"));
            logger.info("文件信息 ： " + JSON.toJSONString(fileBean));
            return fileBean;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new ThirdPartyException(ResponseEnum.FILE_UPLOAD_ERROR.getCode(), ResponseEnum.FILE_UPLOAD_ERROR.getMsg());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ThirdPartyException(ResponseEnum.FILE_UPLOAD_ERROR.getCode(), ResponseEnum.FILE_UPLOAD_ERROR.getMsg());
        }
    }

    /**
     * 上传多个文件，用UUID代替文件名
     *
     * @param
     * @return
     * @author fzm_mhw
     * @data 2018/6/15 20:44
     */
    public static List<FileBean> uploadFiles(MultipartFile[] files, HttpServletRequest request) {
        //for循环遍历MultipartFile数组，将结果放到list里返回
        List<FileBean> list = new ArrayList<>();
        for(MultipartFile file : files) {
            FileBean fileBean = uploadFile(file, request);
            list.add(fileBean);
        }
        return list;
    }
}
