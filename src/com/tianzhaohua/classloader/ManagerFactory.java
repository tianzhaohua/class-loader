package com.tianzhaohua.classloader;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载manager的工厂
 */
public class ManagerFactory {
    //记录类加载类的加载信息
    private static final Map<String,LoadInfo> loadInfoMap = new HashMap<String,LoadInfo>();
    //要加载的类的classpath
    public static final String CLASS_PATH="/Users/zhaohuatian/Documents/project/hot/out/production/hot";
    //实现热加载的类的全名称
    public static final String MY_MANAGER = "com.tianzhaohua.classloader.MyManager";

    public static BaseManager getManager(String className){
        File loadFile = new File(CLASS_PATH+className.replace("\\.","/")+".class");
        long lastModified = loadFile.lastModified();

        if (loadInfoMap.get(className)==null){
            load(className,lastModified);
        }else if (loadInfoMap.get(className).getLoadTime()!=lastModified){
            load(className,lastModified);
        }
        return loadInfoMap.get(className).getManager();
    }
    private static void load(String className,long lastModified){
        MyClassloader myClassloader = new MyClassloader(CLASS_PATH);
        Class<?> loadClass=null;
        try {
            loadClass = myClassloader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        BaseManager manager = NewInstance(loadClass);
        LoadInfo loadInfo = new LoadInfo(myClassloader,lastModified);
        loadInfo.setManager(manager);
        loadInfoMap.put(className,loadInfo);
    }

    //以反射的方式创建BaseManager的子类对象
    private static BaseManager NewInstance(Class<?> loadClass) {

        try {
            return (BaseManager) loadClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
