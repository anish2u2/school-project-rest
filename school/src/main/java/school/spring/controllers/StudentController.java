package school.spring.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import school.contracts.StudentService;

@Controller
@CrossOrigin
@RequestMapping(value = "/student.json")
public class StudentController {

	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Object fetchStudentInfo(@RequestParam(name = "studentId") Long studentId,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseObject = new HashMap<String, Object>(1);
		try {

			responseObject.put("students", studentService.fetchStudent(studentId));

		} catch (Exception ex) {
			responseObject.put("error", "Internal Exception..");
		}
		return responseObject;
	}

}
