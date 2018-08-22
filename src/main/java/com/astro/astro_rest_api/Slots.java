package com.astro.astro_rest_api;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Slots {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int slot_id;
	
	private int employee_id;

	private Timestamp scheduleDate;
	
	@ManyToOne
	private Schedules schedule;
	
	public Slots() {}
	
	public int getSlotId() {
		return slot_id;
	}
	
	public int getEmployeeId() {
		return employee_id;
	}

	public void setEmployeeId(int employee_id) {
		this.employee_id = employee_id;
	}
	
	public Schedules getSchedule() {
		return schedule;
	}
	
	public void setSchedule(Schedules schedule) {
		this.schedule = schedule;
	}

	public Timestamp getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Timestamp scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

}
