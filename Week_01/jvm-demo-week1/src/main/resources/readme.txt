1、自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内
容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
答案：HelloClassLoader

2、画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的
关系。
答案：JVM内存.png

3、本机使用 G1 GC 启动一个程序，仿照课上案例分析一下 JVM 情况 可以使用gateway-server-0.0.1-SNAPSHOT.jar 注意关闭自适应参数：-XX:-UseAdaptiveSizePolicy

答案：采用G1垃圾回收器.docx