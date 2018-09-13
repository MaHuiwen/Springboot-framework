package com.mhw.boot.commons.bean;

/**
 * 文件包含的信息，包括文件名、后缀名、文件UUID、上传后的URL
 *
 * @Author mhw_mhw
 * @Date 2018/6/20 10:06
 * @Version
 */
public class FileBean {
    /**文件名，不包含后缀*/
    private String fileName;
    /**文件后缀名*/
    private String fileSuffixName;
    /**文件UUID*/
    private String fileUUID;
    /**文件URL*/
    private String url;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSuffixName() {
        return fileSuffixName;
    }

    public void setFileSuffixName(String fileSuffixName) {
        this.fileSuffixName = fileSuffixName;
    }

    public String getFileUUID() {
        return fileUUID;
    }

    public void setFileUUID(String fileUUID) {
        this.fileUUID = fileUUID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FileBean{" +
                "fileName='" + fileName + '\'' +
                ", fileSuffixName='" + fileSuffixName + '\'' +
                ", fileUUID='" + fileUUID + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
