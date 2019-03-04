package com.runcoding.handler.type;

import com.google.common.collect.Sets;
import com.runcoding.handler.type.annotation.ColumnType;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScannerRegistrar;
import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author xukai
 * @date 2019-03-04
 * @desc:
 */
public class TypeHandlerScannerRegistrar extends MapperScannerRegistrar {

    private ResourceLoader resourceLoader;


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(MapperScan.class.getName()));
        ClassPathMapperScanner scanner = new ClassPathMapperScanner(registry);
        if (resourceLoader != null) {
            scanner.setResourceLoader(resourceLoader);
        }
        List<String> basePackages = new ArrayList<>();
        for (String pkg : annoAttrs.getStringArray("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (String pkg : annoAttrs.getStringArray("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (Class<?> clazz : annoAttrs.getClassArray("basePackageClasses")) {
            basePackages.add(ClassUtils.getPackageName(clazz));
        }
        scanner.registerFilters();
        /**扫描包并获取Mapper interface*/
        Set<BeanDefinition> mapperBeans = Sets.newHashSet();
        basePackages.forEach(basePackage->{
            Set<BeanDefinition> beanDefinitionSet = scanner.findCandidateComponents(basePackage);
            mapperBeans.addAll(beanDefinitionSet);
        });

        Set<String>  loadClass = Sets.newHashSet();

        mapperBeans.forEach(mapperBean->{
            String beanClassName = mapperBean.getBeanClassName();
            try {
                /**Mapper interface */
                Class<?> aClass  = Class.forName(beanClassName);
                Method[] methods = aClass.getMethods();
                for (Method  method : methods) {
                    /**获取返回类*/
                    Class<?> returnType = method.getReturnType();
                    if(Iterable.class.isAssignableFrom(returnType)){
                        System.out.println(returnType);
                    }

                    registrarClass(loadClass,returnType);

                    /***获取参数类*/
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    registrarClass(loadClass,parameterTypes);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void registrarClass(Set<String> loadClass, Class<?> ... aClass) {


        for (Class<?> regClass: aClass) {
            /**排除基本类型*/
            if(regClass.isPrimitive()){
                continue;
            }
            if(loadClass.contains(regClass.getName())){
               continue;
            }


            Field[] fields = regClass.getDeclaredFields();
            if(fields == null){
                continue;
            }
            for (Field field : fields) {
                field.setAccessible(true);
                ColumnType column = field.getAnnotation(ColumnType.class);
                TypeHandlerRegistrar.registrarClass(column,field.getType());
            }
            loadClass.add(regClass.getName());
        }

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


}
