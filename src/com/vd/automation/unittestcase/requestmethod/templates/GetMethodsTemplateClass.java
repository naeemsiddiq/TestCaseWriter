/**
 * 
 */
package com.vd.automation.unittestcase.requestmethod.templates;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.vd.automation.unittestcase.AbstractClass;
import com.vd.automation.unittestcase.objects.MethodsObject;
import com.vd.automation.unittestcase.objects.ParameterObject;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class GetMethodsTemplateClass extends AbstractClass {

	public void GetMethodTemplate(String baseURL, MethodsObject methodObject, String className) {

		String methodName = methodObject.getMethodName();
		String requestMethod = methodObject.getRequestMethod();
		String url = baseURL + methodObject.getUrl();
		List<ParameterObject> parameterObjectList = methodObject.getParameterObject();

		String responseBodyNullCheck = "";

		writeGetMethodUnitTest(className, methodName, requestMethod, url, parameterObjectList, responseBodyNullCheck);
		// to check GET request Method response by giving POST
		writeGetMethodUnitTest(className, methodName + "_POST_Req_inGET_API", "POST", url, parameterObjectList,
				responseBodyNullCheck);
		responseBodyNullCheck = "_RespBodyNullCheck";
		writeGetMethodUnitTest(className, methodName, requestMethod, url, parameterObjectList, responseBodyNullCheck);

	}

	private void writeGetMethodUnitTest(String className, String methodName, String requestMethod, String url,
			List<ParameterObject> parameterObjectList, String responseBodyNullCheck) {

		totalTestCasesCounter++;

		for (int i = 0; i < parameterObjectList.size(); i++) {
			ParameterObject parameterObject = parameterObjectList.get(i);
			String paramDataType = parameterObject.getMethodParamDataType();
			if (!paramDataType.equals("")) {
				String constant = "/CONSTANT";
			}
		}

		String resBodyNull = "";
		if (responseBodyNullCheck.equals("_RespBodyNullCheck")) {
			methodName = methodName + responseBodyNullCheck;
			resBodyNull = "Assert.assertNull(responseBody);";
		}

		String testAnnotation = "\n\n @Test";
		String methodDefinition = "\n public void Test" + methodName + "(){";
		String httpRequest = "RequestSpecification httpRequest= RestAssured.given().header(\"Content-Type\",\"application/json\");";
		String response = "Response response=httpRequest.request(Method." + requestMethod + ", BASE_URL + \"" + url
				+ "\");";
		String responseBody = "String responseBody=response.getBody().asString();";
		String soutResponseBody = "System.out.println(\"Response Body is =>  \" + responseBody);";
		String assertCheck = "Assert.assertEquals(response.getStatusCode(), 200);\n\n}";
		// System.out.println(methodDefinition);
		// System.out.println("\t" + httpRequest + "\n" + "\t" + response + "\n"
		// + "\t" + responseBody + "\n" + "\t"
		// + soutResponseBody + "\n" + "\t" + assertCheck);

		try (FileWriter fw = new FileWriter(unitTestCasesFolderPath + className + ".java", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println();
			out.println("\t" + testAnnotation);
			out.println("\t\t" + methodDefinition);
			out.println();
			out.println("\t\t" + httpRequest);
			out.println("\t\t" + response);
			out.println("\t\t" + responseBody);
			out.println("\t\t" + resBodyNull); // This will write
			// Assert.assertNull(responseBody)
			// when responseBody is null
			// else print ""
			out.println();
			out.println("\t\t" + soutResponseBody);
			out.println();
			out.println("\t\t" + assertCheck);
			out.println();
		} catch (IOException e) {
		}

	}
}
