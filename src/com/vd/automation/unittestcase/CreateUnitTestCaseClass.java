package com.vd.automation.unittestcase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import com.vd.automation.unittestcase.objects.MethodsObject;
import com.vd.automation.unittestcase.objects.ClassesObject;
import com.vd.automation.unittestcase.requestmethod.templates.GetMethodsTemplateClass;
import com.vd.automation.unittestcase.requestmethod.templates.Post_Patch_MethodTemplateClass;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class CreateUnitTestCaseClass extends AbstractClass{

	GetMethodsTemplateClass getMethodsTemplate;
	Post_Patch_MethodTemplateClass post_patch_MethodsTemplate;

	public CreateUnitTestCaseClass() {
		getMethodsTemplate = new GetMethodsTemplateClass();
		post_patch_MethodsTemplate = new Post_Patch_MethodTemplateClass();
	}

	/**
	 * @param readClassObjectList
	 * @throws Exception
	 */
	public void UnitTestWriterFunction(List<ClassesObject> readClassObjectList) throws Exception {

		for (int i = 0; i < readClassObjectList.size(); i++) {
			ClassesObject readClassObject = readClassObjectList.get(i);
			String className = readClassObject.getClassName();

			CreateNewFile(className); // Created new
										// File to write
										// Unit
										// TestCases in
										// it
			List<MethodsObject> methodObjectsList = readClassObject.getMethodObject();
			String baseURL = readClassObject.getBaseUrl();

			for (int j = 0; j < methodObjectsList.size(); j++) {
				MethodsObject methodObject = methodObjectsList.get(j);
				if (methodObject.getRequestMethod().equals("GET")) {
					getMethodsTemplate.GetMethodTemplate(baseURL, methodObject, className);
				}

				else if (methodObject.getRequestMethod().equals("POST")
						|| methodObject.getRequestMethod().equals("PATCH")) {
					post_patch_MethodsTemplate.post_patch_MethodTemplate(baseURL, methodObject, className);
				}
			}

			putCloseBracketOfClassInFile(className);
		}
	}

	private void putCloseBracketOfClassInFile(String className) throws Exception {
		try (FileWriter fw = new FileWriter(unitTestCasesFolderPath + className + ".java", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println();
			out.println("}");
			out.println();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void CreateNewFile(String className) throws Exception {
		File old_file = new File(unitTestCasesFolderPath + className + ".java");
		old_file.delete();
		// File temp=new File("../playlist/temp.txt");
		File new_file = new File(unitTestCasesFolderPath + className + ".java");

		try (FileWriter fw = new FileWriter(new_file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println();
			out.println("public class Test" + className + "  {");
			out.println();
			out.println();
			out.println("\t" + "String BASE_URL;");
			out.println("\t" + "ObjectMapper objectMapper;");
			out.println("\t" + "//Add this jackson library in your pom.xml file ");
			out.println("\t" + "//<dependency>" + "\n" + "\t" + "//<groupId>com.fasterxml.jackson.core</groupId>" + "\n"
					+ "\t" + "//<artifactId>jackson-databind</artifactId>" + "\n" + "\t" + "//<version>2.6.3</version>"
					+ "\n" + "\t" + "//</dependency>");
			out.println("\t" + "public Test" + className + "()  {");
			out.println("\t" + "\t" + "BASE_URL=\"WRITE BASE URL HERE\"");
			out.println("\t" + "\t" + "objectMapper=new ObjectMapper();");
			out.println("\t" + "}");
			out.println();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
