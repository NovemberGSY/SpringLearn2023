package com.nov.spring;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;

public class NovApplicationContext {
    private Class configClass;

    public NovApplicationContext(Class configClass) {
        this.configClass = configClass;

        //扫描
        if (configClass.isAnnotationPresent(ComponentScan.class)){

            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
             //扫描路径 com.nov.service,实际上是扫描其对应的class文件
            String path = componentScanAnnotation.value();

            //相对路径
            path = path.replace(".","/");

            ClassLoader classLoader = NovApplicationContext.class.getClassLoader();

            URL resource = classLoader.getResource(path);

            File file = new File(resource.getFile());

            //判断是否时文件夹
            if (file.isDirectory()){
                File[] files = file.listFiles();
                for (File f:files) {
                    String fileName = f.getAbsolutePath();
                    if (fileName.endsWith(".class")){
                        String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                        className = className.replace("/",".");

                        try {
                            Class<?> clazz = classLoader.loadClass(className);
                            if (clazz.isAnnotationPresent(Component.class)) {
                                //如果存在这样的注解，则它就是一个Bean
                            }
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }

            }


        }
    }

    public Object getBean(String beanName){
        return null;
    }
}
