package com.shopping.common.spring;

import com.shopping.common.annotation.AnnotationUtils;
import com.shopping.common.annotation.ClassPathClassScanner;
import com.shopping.common.type.ClassUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class ControllerParser {

    Set<Class> requestMappingAnnotations;

    public ControllerParser() {
        requestMappingAnnotations =new HashSet<>();
        requestMappingAnnotations.add(GetMapping.class);
        requestMappingAnnotations.add(PutMapping.class);
        requestMappingAnnotations.add(DeleteMapping.class);
        requestMappingAnnotations.add(PatchMapping.class);
        requestMappingAnnotations.add(PostMapping.class);

    }

    public void process(){
        String[] basePackages = SpringUtils.getBasePackages();
        Set<Class> controllers = new ClassPathClassScanner().scanClassesWithAnnotation(basePackages, Controller.class);
        for(Class controller:controllers){
            String controllerRequestMappingUrl=getControllerRequestMappingUrl(controller);
            RequestMethod classRequestMethod=getControllerRequestMethod(controller);
            Method[] methods= ClassUtils.getDeclaredMethods(controller);
            for(Method method:methods){
                //requestUrl
                String methodRequestMappingUrl=getMethodRequestMappingUrl(controllerRequestMappingUrl,method);
                //requestMethod
                RequestMethod methodRequestMethod=getMethodRequestMethod(classRequestMethod,method);
                //参数
                Object[] parameterInfos = getParameterInfo(method);
                System.out.println("hehe");
            }
        }
    }

    public String getControllerRequestMappingUrl(Class controller){
        return getRequestMappingUrl(controller);
    }

    public String getMethodRequestMappingUrl(String classRequestMappingUrl, Method method){
        return classRequestMappingUrl+getRequestMappingUrl(method);
    }

    public RequestMethod getControllerRequestMethod(Class controller){
        return getRequestMethod(controller);
    }

    public RequestMethod getMethodRequestMethod(RequestMethod classRequestMethod, Method method){
        RequestMethod methodRequestMethod= getRequestMethod(method);
        return methodRequestMethod!=null?methodRequestMethod:classRequestMethod;
    }

    /**
     *
     * @param method
     * @return
     */
    public Object[] getParameterInfo(Method method){
        Class[] parameterTypes = method.getParameterTypes();
        if(parameterTypes.length==0){
            return null;
        }
        Object[] parameterInfos=new Object[parameterTypes.length];
        Annotation[][] annotations = method.getParameterAnnotations();
        for(int i=0;i<parameterTypes.length;i++){
            Object[] parameterInfo=new Object[3];
            parameterInfo[0]=parameterTypes[i];
            Annotation[] parameterAnnotations=annotations[i];
            String name="parameter"+i;
            Boolean required=false;
            for (Annotation parameterAnnotation : parameterAnnotations) {
                if (parameterAnnotation instanceof RequestParam) {
                    name = ((RequestParam) parameterAnnotation).value();
                    required = ((RequestParam) parameterAnnotation).required();
                    break;
                }
            }
            parameterInfo[1]=name;
            parameterInfo[2]=required;
            parameterInfos[i]=parameterInfo;
        }
        return parameterInfos;
    }


    private String getRequestMappingUrl(Object target){
        String url="";
        Annotation annotation=null;
        if(target instanceof Method){
            annotation=getConcreteRequestMappingAnnotation((Method) target);
        } else if(target instanceof Class){
            annotation= AnnotationUtils.getAnnotation((Class) target, RequestMapping.class);
        }
        if(annotation!=null){
            String[] urls;
            urls= (String[]) getRequestMappingMethodAndUrls(annotation)[1];
            if(urls!=null&&urls.length>0){
                url=urls[0];
                if(url!=null&&!url.startsWith("/")){
                    url+="/";
                }
            }

        }
        return url;
    }

    private RequestMethod getRequestMethod(Object target){
        RequestMethod method = null;
        Annotation annotation=null;
        if(target instanceof Method){
            annotation=getConcreteRequestMappingAnnotation((Method) target);
        } else if(target instanceof Class){
            annotation=AnnotationUtils.getAnnotation((Class) target, RequestMapping.class);
        }
        if(annotation!=null){
            RequestMethod[] requestMethods;
            requestMethods = (RequestMethod[]) getRequestMappingMethodAndUrls(annotation)[0];
            if(requestMethods.length>0) {
                method=requestMethods[0];
            }
        }
        return method;
    }



    /**
     * 获取注解的urls
     * @param annotation
     * @return
     */
    private Object[] getRequestMappingMethodAndUrls(Annotation annotation){
        Object[] methodAndUrls=new Object[2];
        if(annotation instanceof GetMapping){
            methodAndUrls[0]=new RequestMethod[]{RequestMethod.GET};
            //todo requestMapping中url是path而不是value
            methodAndUrls[1]=((GetMapping) annotation).value();
        } else if(annotation instanceof PutMapping){
            methodAndUrls[0]=new RequestMethod[]{RequestMethod.PUT};
            methodAndUrls[1]=((PutMapping) annotation).value();
        } else if(annotation instanceof DeleteMapping){
            methodAndUrls[0]=new RequestMethod[]{RequestMethod.DELETE};
            methodAndUrls[1]=((DeleteMapping) annotation).value();
        } else if(annotation instanceof PatchMapping){
            methodAndUrls[0]=new RequestMethod[]{RequestMethod.PATCH};
            methodAndUrls[1]=((PatchMapping) annotation).value();
        } else if(annotation instanceof PostMapping){
            methodAndUrls[0]=new RequestMethod[]{RequestMethod.POST};
            methodAndUrls[1]=((PostMapping) annotation).value();
        } else if(annotation instanceof RequestMapping){
            methodAndUrls[0]=((RequestMapping) annotation).method();
            methodAndUrls[1]=((RequestMapping) annotation).value();
        }
        return methodAndUrls;
    }

    public Annotation getConcreteRequestMappingAnnotation(Method method){
        Annotation requestMapping=null;
        for(Class requestMappingAnnotation:requestMappingAnnotations){
            requestMapping=AnnotationUtils.getAnnotation(method,requestMappingAnnotation);
            if(requestMapping!=null){
                break;
            }
        }
        if(requestMapping==null){
            requestMapping=AnnotationUtils.getAnnotation(method, RequestMapping.class);
        }
        return requestMapping;
    }

}
