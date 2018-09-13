package com.mhw.boot.controller;

import com.mhw.boot.commons.bean.FileBean;
import com.mhw.boot.commons.bean.ResResult;
import com.mhw.boot.commons.util.FileUtil;
import com.mhw.boot.commons.util.ResResultUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * 上传文件Controller层
 *
 * @Author mhw_mhw
 * @Date 2018/6/20 10:07
 * @Version
 */
@RestController
public class FileController {

    /**
     * 上传单个文件
     *
     * @param file
     * @param request
     * @return
     * @author mhw_mhw
     * @data 2018/6/17 16:18
     */
    @PostMapping(value = "upload")
    public ResResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        FileBean fileVo = FileUtil.uploadFile(file, request);
        return ResResultUtil.success(fileVo);
    }

    @PostMapping(value = "uploadFiles")
    public ResResult uploadFiles(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
        List<FileBean> list = FileUtil.uploadFiles(files, request);
        return ResResultUtil.success(list);
    }

    @PostMapping(value = "uploadFilesAndParams")
    public ResResult uploadFiles(@RequestParam("file") MultipartFile[] files, @RequestParam("id") String id,
                                 @RequestParam("username") String username, HttpServletRequest request) {
        List<FileBean> list = FileUtil.uploadFiles(files, request);
        List<String> params = new ArrayList<>();
        params.add(id);
        params.add(username);

        Map<String, List> map = new HashMap<>(2);
        map.put("files", list);
        map.put("formation", params);
        return ResResultUtil.success(map);
    }
}
