package com.x.y.z.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

/**
 * Created by 1234qwer on 2018/3/30.
 */
public class ZipUtil {

    private static Log LOG = LogFactory.getLog(ZipUtil.class);

    public static void main(String[] args) throws ZipException, IOException {
        //阿里对账户单压缩包解压
        Map<String,ByteArrayOutputStream> map = unZip(new FileInputStream(new File("D:\\usr\\20885113257556640156_20180328.csv.zip")));
        for (String name : map.keySet()) {
            System.out.println("=========name========："+name);
            ByteArrayOutputStream bout = map.get(name);
            System.out.println(new String (bout.toByteArray(),"GBK"));
        }


        ByteArrayOutputStream map2 = unGZip(new FileInputStream(new File("D:\\usr\\aaaaa.gz")));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(map2.toByteArray())));
        String line = null;
        while ( (line = reader.readLine()) != null) {
            System.out.println(line);
        }


    }


    /**
     * 返回解压后的输入流
     * @param in 调用该方法后inputstream会被关闭
     * @return 返回文件名字以及解压后的文件流
     */
    public static Map<String,ByteArrayOutputStream> unZip(InputStream in) {

        Map<String,ByteArrayOutputStream> re = new HashMap<String,ByteArrayOutputStream>();

        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(in, Charset.forName("GBK"));
            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {

                ByteArrayOutputStream bout = new ByteArrayOutputStream();

                //以字节形式读取会把压缩包的一些压缩信息读出来
//                byte[] bytes = new byte[1024];
//                int i;
//                while ((i = bufferedInputStream.read(bytes))!=-1){
//                    bout.write(bytes);
//                    bout.flush();
//                }

                //所以只能使用BufferReader
                BufferedReader reader = new BufferedReader(new InputStreamReader(zis,"GBK"));
                String s = null;
                while((s =reader.readLine() )!= null) {
                    bout.write(s.getBytes("GBK"));
                    bout.write("\n".getBytes());
                }


                re.put(entry.getName(), bout);
            }
        } catch (Exception e) {
            LOG.error("",e);
        }finally {
            if(zis != null) {
                try {
                    zis.close();
                } catch (IOException e) {
                    LOG.error("",e);
                }
            }
        }

        return re;
    }




    /**
     * 返回解压后的输入流
     * @param in 调用该方法后inputstream会被关闭
     * @return 返回文件名字以及解压后的文件流
     */
    public static ByteArrayOutputStream unGZip(InputStream in) {

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        GZIPInputStream zis = null;
        try {
            zis = new GZIPInputStream(in);

            BufferedReader reader = new BufferedReader(new InputStreamReader(zis));
            String s = null;
            while((s =reader.readLine() )!= null) {
                bout.write(s.getBytes());
                bout.write("\n".getBytes());
            }

        } catch (Exception e) {
            LOG.error("",e);
        }finally {
            if(zis != null) {
                try {
                    zis.close();
                } catch (IOException e) {
                    LOG.error("",e);
                }
            }
        }

        return bout;
    }







}
