package com.fzm.boot;

import com.fzm.boot.commons.util.HttpRequestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTests {

    private final static Logger logger = LoggerFactory.getLogger(BootApplicationTests.class);

	@Test
	public void contextLoads() {
	}

	@Test
    public void testHttpPost() {
	    String url = "http://localhost:8080/say";
	    String sentence = "你好";
        Map<String, String> map = new HashMap<>();
        map.put("sentence", sentence);
        String result = HttpRequestUtil.sendPost(url, map);
        System.out.println(result);
    }

    @Test
    public void testHttpRequest() throws IOException {
	    //url
	    String url = "http://localhost:8080/uploadFilesAndParams";

	    //文件map
	    Map<String, File[]> files = new HashMap<>();
	    File[] file = new File[2];
        file[0] = new File("C:\\Users\\mahui\\Desktop\\周报\\3.26-3.30.docx");
        file[1] = new File("C:\\Users\\mahui\\Desktop\\周报\\4.8-4.13.docx");
        files.put("file", file);

        //参数map
        Map<String, String> formation = new HashMap<>();
        String id = "14061320";
        String username = "mhw";
        formation.put("id", id);
        formation.put("username", username);

        String result = HttpRequestUtil.sendPost(url, null, formation, files);
        logger.info(result);
    }

    @Test
    public void testBackSlash() {
	    String filePath = "C:\\Users\\mahui\\Desktop\\周报\\3.26-3.30.docx";
        filePath.replaceAll("\\\\\\\\", "\\\\");
        File file = new File(filePath);
        System.out.println(file.exists());
    }
}
