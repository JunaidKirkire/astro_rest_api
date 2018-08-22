package com.astro.astro_rest_api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SlotsRepository extends CrudRepository<Slots, Integer> {
	@Query(value="SELECT s.* FROM `slots` s WHERE s.schedule_schedule_id = :schedule_id", nativeQuery=true)
	ArrayList<Slots> findSlotsByScheduleId(@Param("schedule_id") int schedule_id);
	
	@Query(value="select s.* from `slots` s where s.schedule_schedule_id = :schedule_id group by s.employee_id having count(*) >= 2", nativeQuery=true)
	ArrayList<Slots> findBookedEmployees(@Param("schedule_id") int schedule_id);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update `slots` set employee_id = :employee_id where slot_id = :slot_id", nativeQuery=true)
	void assignEmployee(@Param("employee_id") int employee_id, @Param("slot_id") int slot_id);
}
