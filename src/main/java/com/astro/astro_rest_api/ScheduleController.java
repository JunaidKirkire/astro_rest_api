package com.astro.astro_rest_api;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

	@Autowired
	private SchedulesRepository schedulesRepository;

	@Autowired
	private SlotsRepository slotsRepository;

	@GetMapping("/schedules")
	public @ResponseBody Iterable<Schedules> index() {
		return schedulesRepository.findAll();
	}

	public ArrayList<Timestamp> getWorkingDaysBetweenTwoDates(Timestamp startDate, Timestamp endDate) {
		ArrayList<Timestamp> lstWorkingDays = new ArrayList<Timestamp>();
		Timestamp ts = null;
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		// Return 0 if start and end are the same
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return lstWorkingDays;
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		lstWorkingDays.add(startDate);

		do {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				lstWorkingDays.add(new Timestamp(startCal.getTime().getTime()));
			}
		} while (startCal.getTimeInMillis() < endCal.getTimeInMillis());

		return lstWorkingDays;
	}

	@PostMapping("/add")
	public @ResponseBody String addSchedule(@RequestBody Schedules schedule) {
		Schedules objSchedule = new Schedules();
		objSchedule.setScheduleStartTime(schedule.getScheduleStartTime());
		objSchedule.setScheduleEndTime(schedule.getScheduleEndTime());
		schedulesRepository.save(objSchedule);

		ArrayList<Timestamp> lstOfWorkingDays = getWorkingDaysBetweenTwoDates(schedule.getScheduleStartTime(),
				schedule.getScheduleEndTime());

		for (int counter = 0; counter < lstOfWorkingDays.size(); counter++) {

			for (int counter2 = 0; counter2 < 2; counter2++) {
				Slots firstSlot = new Slots();
				firstSlot.setSchedule(objSchedule);
				firstSlot.setScheduleDate(lstOfWorkingDays.get(counter));
				slotsRepository.save(firstSlot);
			}
		}

		return "Saved!";
	}
}
