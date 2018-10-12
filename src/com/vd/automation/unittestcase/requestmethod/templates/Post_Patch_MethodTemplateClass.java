package com.vd.automation.unittestcase.requestmethod.templates;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.vd.automation.unittestcase.AbstractClass;
import com.vd.automation.unittestcase.objects.MethodsObject;
import com.vd.automation.unittestcase.objects.MethodReturnTypeFieldsObject;
import com.vd.automation.unittestcase.objects.ParameterObject;
import com.vd.automation.unittestcase.objects.RequestBodyFieldsObject;
import com.vd.automation.unittestcase.randomvaluesgenrator.RandomValuesGeneratorClass;

public class Post_Patch_MethodTemplateClass extends AbstractClass {

	RequestBodyFieldsObject requestBodyFieldsObject;

	public Post_Patch_MethodTemplateClass() {
		requestBodyFieldsObject = new RequestBodyFieldsObject();
	}

	public void post_patch_MethodTemplate(String baseURL, MethodsObject methodObject, String className) {

		String methodName = methodObject.getMethodName();
		String requestMethod = methodObject.getRequestMethod();
		String url = baseURL + methodObject.getUrl();
		String methodReturnType = methodObject.getMethodReturnType();
		List<ParameterObject> parameterObjectList = methodObject.getParameterObject();

		for (int i = 0; i < parameterObjectList.size(); i++) {
			ParameterObject paramObject = parameterObjectList.get(i);
			String paramAnnotatedClass = paramObject.getMethodParamAnnotation();
			if (paramAnnotatedClass.contains(".") && !paramAnnotatedClass.equals("")) {
				String paramBodyType = getClassNameFromClassPath(paramAnnotatedClass);
				if (paramBodyType.equals("RequestBody")) {
					String requestBodyClassName = paramObject.getMethodParamDataType();
					requestBodyFieldsObject = getRequestBodyFieldsObject(requestBodyClassName);
				}
			}
		}

		List<MethodReturnTypeFieldsObject> methodReturnTypeFieldsList = getMethodReturnTypeFieldsList(methodReturnType);
		writePost_Patch_MethodUnitTest(methodName, requestMethod, url, requestBodyFieldsObject, className, "",
				methodReturnTypeFieldsList);
		String responseBodyNullCheck = "_RespBodyNullCheck";
		writePost_Patch_MethodUnitTest(methodName, requestMethod, url, requestBodyFieldsObject, className,
				responseBodyNullCheck, methodReturnTypeFieldsList);

	}

	private void writePost_Patch_MethodUnitTest(String methodName, String requestMethod, String url,
			RequestBodyFieldsObject requestBodyFieldsObject, String className, String responseBodyNullCheck,
			List<MethodReturnTypeFieldsObject> methodReturnTypeFieldsList) {

		totalTestCasesCounter++;

		String resBodyNull = "";
		if (responseBodyNullCheck.equals("_RespBodyNullCheck")) {
			methodName = methodName + responseBodyNullCheck;
			resBodyNull = "Assert.assertNull(responseBody);";
			// This will write Assert.assertNull(responseBody) to check
			// responseBody is null
		}

		String testAnnotation = "\n\n @Test";
		String methodDefinition = "\npublic void Test" + methodName + "(){";
		String httpRequest = "RequestSpecification httpRequest= RestAssured.given().header(\"Content-Type\",\"application/json\");";
		String jsonObjectrequest = "JSONObject requestParams = new JSONObject();";
		String json = "String json =" + requestBodyFieldsObject.getJsonObject();
		String classInstance = requestBodyFieldsObject.getClassInstance();
		String requestBody = "httpRequest.body(json);";

		// String requestBody =
		// "httpRequest.body(objectMapper.writeValueAsString("
		// + requestBodyFieldsObject.getClassInstanceName() + ") );";

		String response = "Response response=httpRequest.request(Method." + requestMethod + ", BASE_URL + \"" + url
				+ "\");";
		String responseBody = "String responseBody=response.getBody().asString();";
		String soutResponseBody = "System.out.println(\"Response Body is =>  \" + responseBody);";
		String assertCheck = "Assert.assertEquals(response.getStatusCode(), 200);";
		String endTestCase = "System.out.println(\"--------------------End -------------------\");\n\n}";

		// System.out.println(methodDefinition);
		// System.out.println("\t" + httpRequest + "\n" + "\t" +
		// jsonObjectrequest + "\n" + "\t" + json + "\n" + "\t"
		// + requestBody + "\n" + "\t" + response + "\n" + "\t" + responseBody +
		// "\n" + "\t" + soutResponseBody
		// + "\n" + "\t" + assertCheck + "\t" + booking_Journey_ID + "\n" + "\t"
		// + soutID + "\n" + "\t"
		// + endTestCase + "\n");

		try (FileWriter fw = new FileWriter(unitTestCasesFolderPath + className + ".java", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println();
			out.println("\t" + testAnnotation + "\n" + "\t" + methodDefinition + "\n\n\t\t" + httpRequest + "\n\t\t"
					+ jsonObjectrequest + "\n\t\t"
					+ json/* + "\n\t\t" + classInstance */);
			// out.println("\t\t" + classInstance);
			// out.println();
			//
			// List<String> classSetterMethod =
			// requestBodyFieldsObject.getClassSettterCalls();
			// for (int i = 0; i < classSetterMethod.size(); i++) {
			// out.println("\t\t" + classSetterMethod.get(i));
			// }
			out.println("\t\t" + requestBody + "\n\t\t" + response + "\n\t\t" + responseBody + "\n\t\t" + resBodyNull
					+ "\n\t\t" + soutResponseBody + "\n\t\t" + assertCheck + "\n");
			if (responseBodyNullCheck.equals("")) {

				for (int i = 0; i < methodReturnTypeFieldsList.size(); i++) {
					String fieldName = methodReturnTypeFieldsList.get(i).getFieldName();
					String fieldType = methodReturnTypeFieldsList.get(i).getFieldType();
					String CapitalizeFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

					out.println("\t\t" + fieldType + " " + CapitalizeFieldName + "= response.jsonPath().get(\""
							+ fieldName + "\");");
					out.println("\t\t" + "System.out.println(\"The Booking ID created is  =>  \" +"
							+ CapitalizeFieldName + ");" + "\n");

				}
			}
			out.println("\t\t" + endTestCase + "\n");
		} catch (IOException e) {
		}

	}

	/**
	 * @param methodReturnTypeClass
	 * @return
	 */
	private List<MethodReturnTypeFieldsObject> getMethodReturnTypeFieldsList(String methodReturnTypeClass) {
		List<MethodReturnTypeFieldsObject> methodReturnTypeFieldsList = new ArrayList<>();
		MethodReturnTypeFieldsObject methodReturnTypeFieldsObject;

		Class<?> extractedClassNameFromPackage = getClassNameFromPackageInClassType(methodReturnTypeClass);

		Field[] fieldsOfClass = extractedClassNameFromPackage.getDeclaredFields();
		for (Field field : fieldsOfClass) {
			String fieldName = field.getName();
			String fieldType = getClassNameFromClassPath(field.getGenericType().getTypeName());
			// System.out.println("Field Name : " + fieldName + " Field Return
			// Type : " + fieldType);
			methodReturnTypeFieldsObject = new MethodReturnTypeFieldsObject();
			methodReturnTypeFieldsObject.setFieldName(fieldName);
			methodReturnTypeFieldsObject.setFieldType(fieldType);
			methodReturnTypeFieldsList.add(methodReturnTypeFieldsObject);

		}
		return methodReturnTypeFieldsList;
	}

	private RequestBodyFieldsObject getRequestBodyFieldsObject(String requestBodyClassName) {

		RequestBodyFieldsObject requestBodyFieldsObject = new RequestBodyFieldsObject();
		Class<?> extractedClassFromPackage = getClassNameFromPackageInClassType(requestBodyClassName);
		RandomValuesGeneratorClass randomValuesGeneratorClass = new RandomValuesGeneratorClass();
		List<String> listOfClassSetters = new ArrayList<>();
		String jSonObject = "\"{";
		String className = getClassNameFromClassPath(requestBodyClassName);
		// convert ClassName to lowercase to be used as its instance Name;
		String classInstanceName = className.substring(0, 1).toLowerCase() + className.substring(1);
		Field[] fieldsOfClass = extractedClassFromPackage.getDeclaredFields();
		for (int i = 0; i < fieldsOfClass.length; i++) {

			String setFieldValue = "";
			String fieldName = fieldsOfClass[i].getName();
			// convert ClassName to lowercase to be used as its instance Name;
			String CapitalizeFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			String fieldType = fieldsOfClass[i].getGenericType().getTypeName();
			// System.out.println("Field Name : " + fieldName + " Field Type : "
			// + fieldType);

			if (fieldType.equals("java.lang.String")) {
				setFieldValue = randomValuesGeneratorClass.getStringRandomValue();
			} else if (fieldType.equals("java.lang.Long")) {
				setFieldValue = randomValuesGeneratorClass.getLongRandomValue();
			} else if (fieldType.equals("java.lang.Double")) {
				setFieldValue = randomValuesGeneratorClass.getDoubleRandomValue();
			} else if (fieldType.equals("java.lang.Integer")) {
				setFieldValue = randomValuesGeneratorClass.getIntegerRandomValue();
			} else if (fieldType.equals("java.lang.Float")) {
				setFieldValue = randomValuesGeneratorClass.getFloatRandomValue();
			} else if (fieldType.equals("java.lang.Date")) {
				setFieldValue = randomValuesGeneratorClass.getDateRandomValue();
			} else
				setFieldValue = "null";

			// Creating jSon Object
			jSonObject += "\\\"" + fieldName + "\\\": " + setFieldValue;
			if (i != fieldsOfClass.length - 1)
				jSonObject += " ,";

			// Creating setter methods for ParamterClass (i.e- RequestBody)
			String setterMethodOfClass = classInstanceName + ".set" + CapitalizeFieldName + "(" + setFieldValue + ")";
			listOfClassSetters.add(setterMethodOfClass);
		}
		jSonObject += "}\";";

		requestBodyFieldsObject.setJsonObject(jSonObject);
		requestBodyFieldsObject.setClassInstance(className + " " + classInstanceName + " = new " + className + "();");
		requestBodyFieldsObject.setClassInstanceName(classInstanceName);
		requestBodyFieldsObject.setClassSettterCalls(listOfClassSetters);

		return requestBodyFieldsObject;

	}

}
