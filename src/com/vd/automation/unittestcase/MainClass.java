package com.vd.automation.unittestcase;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vd.automation.unittestcase.objects.MethodsObject;
import com.vd.automation.unittestcase.objects.ClassesObject;
import com.vd.automation.unittestcase.populateobjects.PopulateClassesObject;
import com.vd.automation.unittestcase.populateobjects.PopulateMethodsObject;

/**
 * @author Naeem Siddiq
 * 
 *         ASE
 */
public class MainClass extends AbstractClass {

	public static void main(String[] args) throws Exception {

		String className = "";
		// To get all controller classes from given package
		List<Class<?>> classes = ReadControllerClassesFromPackages.find(contrlollersPackagePath);
		PopulateMethodsObject populateMethodsObject = new PopulateMethodsObject();
		List<MethodsObject> methodsObjectList;
		PopulateClassesObject populateClassesObject = new PopulateClassesObject();
		List<ClassesObject> readClassObjectList = new ArrayList<>();

		for (Class<?> cls : classes) {
			className = cls.getName().trim();
			className = getClassNameFromClassPath(className);

			String[] path = cls.getAnnotation(RequestMapping.class).value();
			String baseURL = "";
			for (int i = 0; i < path.length; i++) {
				baseURL += path[i];
				System.out.println("Class URL : " + path[i]);
			}
			methodsObjectList = new ArrayList<>();
			methodsObjectList = populateMethodsObject.populateMethodsObject(cls);
			// System.out.println("Method Object List : " + methodsObjectList);
			readClassObjectList.add(populateClassesObject.PopulateClassObject(className, methodsObjectList, baseURL));
		}
		// System.out.println("Class Object List : " + readClassObjectList);
		CreateUnitTestCaseClass unitTestCases = new CreateUnitTestCaseClass();
		unitTestCases.UnitTestWriterFunction(readClassObjectList);

		System.out.println("Total TestCases Written are : " + totalTestCasesCounter);
	}
}
