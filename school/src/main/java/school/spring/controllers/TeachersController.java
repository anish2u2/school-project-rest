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

import school.contracts.TeachersService;

@Controller
@CrossOrigin
@RequestMapping(value = "/teachers.json")
public class TeachersController {

	@Autowired
	@Qualifier("teacherService")
	private TeachersService teacherService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Object fetchTeacherInfo(@RequestParam(name = "teacherId") Long teacherId,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseObject = new HashMap<String, Object>(1);
		try {

			responseObject.put("teachers", teacherService.fetchTeacherDetails(teacherId));

		} catch (Exception ex) {
			responseObject.put("error", "Internal Exception..");
		}
		return responseObject;
	}

}
