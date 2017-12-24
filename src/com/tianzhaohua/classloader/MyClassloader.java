package com.tianzhaohua.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义Java类加载器实现Java的热加载
 */
public class MyClassloader extends ClassLoader {

    private String classpath;

    public MyClassloader(String classpath) {
        super(ClassLoader.getSystemClassLoader());
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] data = this.loadClassData(name);
        return this.defineClass(name,data,0,data.length);


    }

    private byte[] loadClassData(String name) {
        try {
            name = name.replace(".","//");
            FileInputStream inputStream = new FileInputStream(new File(classpath+name+".class"));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int b =0;
            while ((b=inputStream.read())!=-1){
                byteArrayOutputStream.write(b);
            }
            inputStream.close();
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){


        }
        return null;
    }
}
