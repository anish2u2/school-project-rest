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

import school.contracts.ClassesService;
import school.contracts.SchoolService;
import school.contracts.StudentService;
import school.contracts.TeachersService;

@Controller
@CrossOrigin
public class SchoolController {

	@Autowired
	@Qualifier("schoolService")
	private SchoolService schoolService;

	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;

	@Autowired
	@Qualifier("teacherService")
	private TeachersService teacherService;

	@Autowired
	@Qualifier("classesService")
	private ClassesService classesService;

	@RequestMapping(value = "/fetchListOfSchools.json", method = RequestMethod.GET)
	public @ResponseBody Object getAllSchools(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseObject = new HashMap<String, Object>(1);
		try {

			responseObject.put("schools", schoolService.getAll());

		} catch (Exception ex) {
			responseObject.put("error", "Internal Exception..");
		}
		return responseObject;
	}

	@RequestMapping(value = "/fetchListOfTeachers.json", method = RequestMethod.GET)
	public @ResponseBody Object getListOfTeachers(@RequestParam(name = "schoolId") Long schoolId,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseObject = new HashMap<String, Object>(1);
		try {

			responseObject.put("teachers", teacherService.fetchDataOnSchoolId(schoolId));

		} catch (Exception ex) {
			responseObject.put("error", "Internal Exception..");
		}
		return responseObject;
	}

	@RequestMapping(value = "/fetchListOfClasses.json", method = RequestMethod.GET)
	public @ResponseBody Object getListOfClasses(@RequestParam(name = "schoolId") Long schoolId,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseObject = new HashMap<String, Object>(1);
		try {

			responseObject.put("classes", classesService.fetchDataOnSchoolId(schoolId));

		} catch (Exception ex) {
			responseObject.put("error", "Internal Exception..");
		}
		return responseObject;
	}

	@RequestMapping(value = "/fetchListOfStudents.json", method = RequestMethod.GET)
	public @ResponseBody Object getListOfStudents(@RequestParam(name = "schoolId") Long schoolId,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseObject = new HashMap<String, Object>(1);
		try {

			responseObject.put("students", studentService.fetchDataOnSchoolId(schoolId));

		} catch (Exception ex) {
			responseObject.put("error", "Internal Exception..");
		}
		return responseObject;
	}
}
