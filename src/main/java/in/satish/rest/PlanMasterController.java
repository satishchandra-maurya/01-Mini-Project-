package in.satish.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.satish.entity.PlanMaster;
import in.satish.service.PlanService;

@RestController
public class PlanMasterController {

	@Autowired
	private PlanService planService;
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> getCategories(){
		Map<Integer, String> planCategories = planService.getPlanCategory();
		return new ResponseEntity<> (planCategories, HttpStatus.OK);
	}
	
	@PostMapping("/plan")
	public ResponseEntity<String> savePlanMaster(@RequestBody PlanMaster planMaster){
		String msg;
		boolean savePlan = planService.savePlan(planMaster);
		if(savePlan)
			msg = "Plan Saved Successfully";
		else {
			msg = "Plan Not Saved";
		}
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<PlanMaster>> getPlans(){
		List<PlanMaster> allPlan = planService.getAllPlan();
		return new ResponseEntity<> (allPlan, HttpStatus.OK);
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<PlanMaster> editPlan(@PathVariable Integer planId){
		PlanMaster planMaster = planService.getPlanById(planId);
		return new ResponseEntity<> (planMaster, HttpStatus.OK);
		
	}
	
	@PutMapping("/plan")
	public ResponseEntity<String> updatePlanMaster(@RequestBody PlanMaster planMaster){
		String msg;
		boolean updatePlan = planService.updatePlan(planMaster);
		if(updatePlan)
		{
			msg = "Plan Master updated successfully !..";
		}
		else {
			msg = "Not Updated Plan Master";
		}
		
		return new ResponseEntity<> (msg, HttpStatus.OK);
	}
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlanMaster(@PathVariable Integer planId){
		String msg;
		boolean deletePlan = planService.deletePlan(planId);
		if(deletePlan) {
			msg = "Plan Deleted Successfully !...";
		}else {
			msg = "Plan Not Deeleted !...";
		}
		
		return new ResponseEntity<> (msg, HttpStatus.OK);
	}
	@PutMapping("/plan/{planId}/{status}")
	public ResponseEntity<String> changPlanMasterStatus(@PathVariable Integer planId, @PathVariable String status){
		String msg;
		boolean changePlanStatus = planService.changePlanStatus(planId, status);
		if(changePlanStatus) {
			msg = "Activated this Plan Status !..";
		}else {
			msg = "Not Activated this Plan Status !..";
		}
		return new ResponseEntity<> (msg, HttpStatus.OK);
		
	}
}
