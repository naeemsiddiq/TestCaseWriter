/**
 * 
 */
package com.vd.automation.unittestcase;

import java.util.List;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class AbstractClass {

	protected static int totalTestCasesCounter = 0;

	// write the folder path here
	protected static String unitTestCasesFolderPath = "C:\\Users\\Sakhi\\Desktop\\Unit Test Cases\\TestCases";

	// Write the package path here
	protected static String contrlollersPackagePath = "com.cmt.journeyplanner.interfaces.controller";

	protected static Class<?> getClassNameFromPackageInClassType(String classNamewithPackage) {

		String classPackagePath = getClassPackageFromClassStringName(classNamewithPackage);

		Class<?> requiredClass = null;
		List<Class<?>> classes = ReadControllerClassesFromPackages.find(classPackagePath);
		for (Class<?> cls : classes) {
			if (cls.getName().equals(classNamewithPackage)) {
				requiredClass = cls;
				break;
			}
		}
		return requiredClass;
	}

	protected static String getClassPackageFromClassStringName(String classNamewithPackage) {
		boolean check = true;
		String classPackagePath = "";

		for (int i = classNamewithPackage.length() - 1; i >= 0; i--) {

			if (classNamewithPackage.charAt(i) == '.' && check == true)
				check = false;
			else if (check == true)
				System.out.println();
			else
				classPackagePath = classNamewithPackage.charAt(i) + classPackagePath;
		}

		return classPackagePath;
	}

	protected static String getClassNameFromClassPath(String classNamewithPackage) {
		String className = "";
		for (int i = classNamewithPackage.length() - 1; i >= 0; i--) {

			if (classNamewithPackage.charAt(i) == '.')
				break;
			else
				className = classNamewithPackage.charAt(i) + className;
		}
		return className;
	}

}
