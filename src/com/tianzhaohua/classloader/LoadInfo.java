package com.tianzhaohua.classloader;

public class LoadInfo {
    //自定义的类加载器
    private MyClassloader myClassloader;
    //记录要加载的类的时间戳-->加载的时间
    private long loadTime;
    private BaseManager manager;

    public LoadInfo(MyClassloader myClassloader, long loadTime) {
        super();
        this.myClassloader = myClassloader;
        this.loadTime = loadTime;
    }

    public MyClassloader getMyClassloader() {
        return myClassloader;
    }

    public void setMyClassloader(MyClassloader myClassloader) {
        this.myClassloader = myClassloader;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public BaseManager getManager() {
        return manager;
    }

    public void setManager(BaseManager manager) {
        this.manager = manager;
    }
}
