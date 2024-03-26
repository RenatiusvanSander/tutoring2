package edu.remad.tutoring2.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.remad.tutoring2.appconstants.TemplateAppConstants;
import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.Car;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Version;

@Controller
@RequestMapping("/templates/cars")
public class TemplateController {

	private static List<Car> carList = new ArrayList<>();

	static {
		carList.add(new Car("Honda", "Civic"));
		carList.add(new Car("Toyota", "Camry"));
		carList.add(new Car("Nissan", "Altima"));
	}

	@PostMapping(value = "/add")
	public String addCar(@ModelAttribute("car") Car car) {
		if (null != car && null != car.getMake() && null != car.getModel() && !car.getMake().isEmpty()
				&& !car.getModel().isEmpty()) {
			carList.add(car);
		}

		return "redirect:/templates/cars/cars";
	}

	@GetMapping(value = "/cars")
	public String init(@ModelAttribute("model") ModelMap model) {
		model.addAttribute("carList", carList);

		return "cars";
	}

	@GetMapping(value = "/commons")
	public String showCommonsPage(Model model) {
		model.addAttribute("statuses", Arrays.asList("200 OK", "404 Not Found", "500 Internal Server Error"));
		model.addAttribute("random", new Random());
		model.addAttribute("statics", new DefaultObjectWrapperBuilder(new Version("2.3.28")).build().getStaticModels());

		return "commons";
	}

	@GetMapping(value = "/spring-messages")
	public String showSpringMessage(@ModelAttribute("model") ModelMap model) {
		return "spring-messages";
	}
	
	@GetMapping(value = TemplateAppConstants.REMINDER_SERVICE_EMAIL_SEND_TASK_PATH)
	public String showReminderServiceEmailSendTask(Model model) {
		String date = LocalDateTime.now().format(TimeAppConstants.DATE_FORMATTER);
		String subject = TemplateAppConstants.REMINDER_EMAIL_VALUE_SUBJECT_TEXT + date;
		
		model.addAttribute(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_SUBJECT, subject);
		model.addAttribute(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_USERNAME, "JohnDoe");
		model.addAttribute(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_TUTORING_DATE, date);
		model.addAttribute(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_START_TIME, "19:00");
		model.addAttribute(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_END_TIME, "20:00");
		
		return TemplateAppConstants.TEMPLATE_NAME_REMINDER_SERVICE_EMAIL_SEND_TASK;
	}
}
