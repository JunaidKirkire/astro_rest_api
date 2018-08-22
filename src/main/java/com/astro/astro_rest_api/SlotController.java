package com.astro.astro_rest_api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SlotController {

	@Autowired
	private SlotsRepository slotsRepository;
	
	@Autowired
	private EmployeesRepository employeesRepository;
	
	@GetMapping("/slots")
	public @ResponseBody ArrayList<Slots> slots(@RequestParam("schedule_id") int scheduleId) {
		return slotsRepository.findSlotsByScheduleId(scheduleId);
	}
	
	@GetMapping("/assign_employee")
	public @ResponseBody Iterable<Slots> assignEmployee(@RequestParam("slot_id") int slotId, @RequestParam("schedule_id") int scheduleId) {
		Iterable<Employees> lstAllEmployees = employeesRepository.findAll();
		
		ArrayList<Employees> arrListAllEmployees = new ArrayList<Employees>();
		lstAllEmployees.forEach(arrListAllEmployees::add);
				
		ArrayList<Slots> lstAllSlots = slotsRepository.findSlotsByScheduleId(scheduleId);
		ArrayList<Slots> lstBookedEmployees = slotsRepository.findBookedEmployees(scheduleId);
		
		//Delete all those employees who already have been booked for two slots
		Iterator<Employees> itrAllEmployees = arrListAllEmployees.iterator();
		while(itrAllEmployees.hasNext()) {
			Employees emp = itrAllEmployees.next();
			for(Slots slot : lstBookedEmployees) {
				if (slot.getEmployeeId() == emp.getEmployeeId()) {
					itrAllEmployees.remove();
				}
			}
		}
		
		//Delete all those employees who have already been booked in the previous slot
		itrAllEmployees = arrListAllEmployees.iterator();
		while(itrAllEmployees.hasNext()) {
			Employees emp = itrAllEmployees.next();
			for(int index = 0; index < lstAllSlots.size(); index++) {
				if(lstAllSlots.get(index).getSlotId() == slotId) {
					if((index - 1) >= 0) {
						if(lstAllSlots.get(index - 1).getEmployeeId() == emp.getEmployeeId()) {
							itrAllEmployees.remove();
						}
					}
				}
			}
		}
		
		//Randomize the employee returned
		Random rand = new Random();
		System.out.println("JUNAID " + arrListAllEmployees.size());
		Employees emp = arrListAllEmployees.get(rand.nextInt(arrListAllEmployees.size()));
		slotsRepository.assignEmployee(emp.getEmployeeId(), slotId);
		return slotsRepository.findSlotsByScheduleId(scheduleId);
	}
}
