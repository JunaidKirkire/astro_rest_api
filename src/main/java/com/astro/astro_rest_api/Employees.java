package com.astro.astro_rest_api;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employees {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int employee_id;

	private String employee_name;

	public int getEmployeeId() {
		return this.employee_id;
	}
	public String getEmployeeName() {
		return employee_name;
	}

	public void setEmployeeName(String employee_name) {
		this.employee_name = employee_name;
	}
}
