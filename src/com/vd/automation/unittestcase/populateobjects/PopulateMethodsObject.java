package com.vd.automation.unittestcase.populateobjects;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vd.automation.unittestcase.objects.MethodsObject;
import com.vd.automation.unittestcase.objects.ParameterObject;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class PopulateMethodsObject {

	/**
	 * @param cls
	 * @return
	 */
	public List<MethodsObject> populateMethodsObject(Class<?> cls) {
		List<MethodsObject> methodObjectList = new ArrayList<>();
		List<ParameterObject> paramObjectList;

		String className = cls.getName();
		// System.out.println("Fetched Class Name :" + className);
		// getting all methods of class
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			System.out.println("Fetched Method Name : " + methodName);

			Class<?> methodType = method.getReturnType();
			String methodReturnType = methodType.getName();
			// System.out.println("Method Return Type
			// ::::::::::::::::::::::::::: " + methodReturnType);

			String requestMethod = "";
			String methodURL = "";
			String[] methodURLarray = null;

			String methodAnnotationName = "";
			for (Annotation annotaion : method.getAnnotations()) {
				Class<? extends Annotation> type = annotaion.annotationType();
				methodAnnotationName = type.getName();
				System.out.println("Method Annotation Name : " + methodAnnotationName);
				if (methodAnnotationName.equals("org.springframework.web.bind.annotation.GetMapping")) {
					requestMethod = "GET";
					methodURLarray = method.getAnnotation(GetMapping.class).value();
				} else if (methodAnnotationName.equals("org.springframework.web.bind.annotation.PatchMapping")) {
					requestMethod = "PATCH";
					methodURLarray = method.getAnnotation(PatchMapping.class).value();
				} else if (methodAnnotationName.equals("org.springframework.web.bind.annotation.PostMapping")) {
					requestMethod = "POST";
					methodURLarray = method.getAnnotation(PostMapping.class).value();
				} else if (methodAnnotationName.equals("org.springframework.web.bind.annotation.PutMapping")) {
					requestMethod = "PUT";
					methodURLarray = method.getAnnotation(PutMapping.class).value();
				} else if (methodAnnotationName.equals("org.springframework.web.bind.annotation.DeleteMapping")) {
					requestMethod = "DELETE";
					methodURLarray = method.getAnnotation(DeleteMapping.class).value();
				} else if (methodAnnotationName.equals("org.springframework.web.bind.annotation.RequestMapping")) {
					RequestMethod[] requestMethodarray = method.getAnnotation(RequestMapping.class).method();
					requestMethod = requestMethodarray[0].toString();
					methodURLarray = method.getAnnotation(RequestMapping.class).value();
				}
				methodURL = methodURLarray[0];
				System.out.println("Request Method : " + requestMethod);
				System.out.println("Method URL : " + methodURL);

			}

			MethodsObject methodObject = new MethodsObject();

			// Read method Parameters
			Parameter[] parameters = method.getParameters();
			// System.out.println("\tParam Name\t\tParam Type");
			paramObjectList = new ArrayList<>(); // Initialize Parameter List
			for (Parameter param : parameters) {
				ParameterObject paramObject = new ParameterObject();
				String paramDataType = param.getType().getName();
				String annotationName = "null";
				// System.out.println("\t" + param.getName() + "\t" +
				// paramDataType);
				for (Annotation annotaion : param.getAnnotations()) {
					Class<? extends Annotation> type = annotaion.annotationType();
					annotationName = type.getName();
					// System.out.println("Annotation Name : " +
					// annotationName);
				}
				paramObject.setMethodParamDataType(paramDataType);
				paramObject.setMethodParamAnnotation(annotationName);
				paramObjectList.add(paramObject);
			}
			System.out.println("\n");
			methodObject.setUrl(methodURL);
			methodObject.setRequestMethod(requestMethod);
			methodObject.setMethodName(methodName);
			methodObject.setParameterObject(paramObjectList);
			methodObject.setMethodReturnType(methodReturnType);

			// adding in method list;
			methodObjectList.add(methodObject);

		}
		return methodObjectList;
	}

}
