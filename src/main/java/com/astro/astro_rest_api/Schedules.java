package com.astro.astro_rest_api;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Schedules {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int schedule_id;
	
	private Timestamp schedule_start_time;
	
	private Timestamp schedule_end_time;

	public Schedules() {}
	
	public Schedules(int schedule_id, Timestamp schedule_start_time, Timestamp schedule_end_time) {
		this.schedule_id = schedule_id;
		this.schedule_start_time = schedule_start_time;
		this.schedule_end_time = schedule_end_time;
	}
	
	public int getScheduleId() {
		return this.schedule_id;
	}
	
	public void setScheduleId(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	
	public Timestamp getScheduleStartTime() {
		return this.schedule_start_time;
	}

	public void setScheduleStartTime(Timestamp schedule_start_time) {
		this.schedule_start_time = schedule_start_time;
	}

	public Timestamp getScheduleEndTime() {
		return this.schedule_end_time;
	}

	public void setScheduleEndTime(Timestamp schedule_end_time) {
		this.schedule_end_time = schedule_end_time;
	}
}