package com.shopping.common.mybatis;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.google.common.collect.Lists;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author capthua
 */
public class JpaAnnotationUtils {

    private static final ByteBuddy byteBuddy=new ByteBuddy();

    static {
        ByteBuddyAgent.install();
    }

    private static final String MODEL_PACKAGE_REGEX="^com.shopping.+.model.+";

    private static final Logger logger=LoggerFactory.getLogger(JpaAnnotationUtils.class);

    private static Converter<String,String> lc2LuConverter=
            CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
    private static Converter<String,String> uc2LuConverter=
            CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);

    public static void addAnnotation(Class clazz){
        if(!clazz.getName().matches(MODEL_PACKAGE_REGEX)){
            return;
        }
        addAnnotation(Lists.newArrayList(clazz),byteBuddy);
    }

    private static void addAnnotation(List<Class> classes, ByteBuddy byteBuddy){
        for(Class clazz:classes){
            boolean redefine=false;
            DynamicType.Builder builder=byteBuddy.redefine(clazz);
            if(!containsAnnotation(clazz, Table.class)){
                AnnotationDescription.Builder tableAnnotationBuilder= AnnotationDescription.Builder.ofType(Table.class);
                String tableName=uc2LuConverter.convert(clazz.getSimpleName());
                builder = builder.annotateType(tableAnnotationBuilder.define("name","t_"+tableName).build());
                logger.info("给类:{}添加table注解",clazz.getName());
                redefine=true;
            }
            Field[] fields = clazz.getDeclaredFields();
            for(Field field:fields){
                Class annotationType;
                if(field.getName().equals("id")){
                    annotationType= Id.class;
                } else {
                    annotationType= Column.class;
                }
                if(!containsAnnotation(field,annotationType)){
                    AnnotationDescription.Builder columnAnnotationBuilder= AnnotationDescription.Builder.ofType(annotationType);
                    if(!field.getName().equals("id")){
                        columnAnnotationBuilder= columnAnnotationBuilder.define("name", lc2LuConverter.convert(field.getName()));
                    }
                    builder = builder.field((ElementMatcher<FieldDescription>) target -> target.getName().equals(field.getName()))
                            .annotateField(columnAnnotationBuilder.build());
                    redefine=true;
                    logger.info("给类{}的{}添加注解{}",clazz.getName(),field.getName(),annotationType.getName());
                }
            }
            if(redefine){
                builder.make().load(Thread.currentThread().getContextClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
            }
        }

    }

    private static boolean containsAnnotation(Field field,Class annotationType){
        return containsAnnotation(field.getAnnotations(),annotationType);
    }
    private static boolean containsAnnotation(Class clazz,Class annotationType){
        return containsAnnotation(clazz.getAnnotations(),annotationType);
    }
    private static boolean containsAnnotation(Annotation[] srcAnnotations, Class annotationType){
        boolean result=false;
        for(Annotation srcAnnotation:srcAnnotations){
            if(annotationType.equals(srcAnnotation.annotationType())){
                result=true;
                break;
            }
        }
        return result;
    }

    private static void printAnnotations(Class clazz){
        List<Field> fields= Arrays.asList(clazz.getDeclaredFields());
        fields.forEach(field -> {
            System.out.println(field.getName()+" annotations:");
            Annotation[] fieldAnnotations = field.getAnnotations();
            for (Annotation annotation : fieldAnnotations) {
                System.out.println(annotation);
            }
        });
        System.out.println("class annotations:");
        System.out.println(Arrays.asList(clazz.getAnnotations()));
    }

    public static void addAnnotation(ClassLoader classLoader) {
        Field f= null;
        try {
            f = ClassLoader.class.getDeclaredField("classes");
            f.setAccessible(true);
            Vector<Class> classes = (Vector<Class>)f.get(classLoader);
            List<Class> models = classes.stream().filter(clazz->clazz.getName().matches(MODEL_PACKAGE_REGEX)).collect(Collectors.toList());
            ByteBuddy byteBuddy=new ByteBuddy();
            ByteBuddyAgent.install();
            addAnnotation(models,byteBuddy);
            models = classes.stream().filter(clazz->clazz.getName().matches(MODEL_PACKAGE_REGEX)).collect(Collectors.toList());
            printAnnotations(models.get(0));
            System.out.println("hehe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
