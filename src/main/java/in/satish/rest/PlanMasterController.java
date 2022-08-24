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

import in.satish.constants.AppConstants;
import in.satish.entity.PlanMaster;
import in.satish.props.AppProperties;
import in.satish.service.PlanService;

@RestController
public class PlanMasterController {

	private PlanService planService;
	
	private Map<String, String> messages;
	
	public PlanMasterController(PlanService planService, AppProperties appProp) {
		this.planService = planService;
		this.messages = appProp.getMessages();
	}
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> getCategories(){
		Map<Integer, String> planCategories = planService.getPlanCategory();
		return new ResponseEntity<> (planCategories, HttpStatus.OK);
	}
	
	@PostMapping("/plan")
	public ResponseEntity<String> savePlanMaster(@RequestBody PlanMaster planMaster){
		String msg = AppConstants.EMPTY_STR;
		
		boolean savePlan = planService.savePlan(planMaster);
		if(savePlan)
			msg = messages.get(AppConstants.SAVE_SUCC);
		else {
			msg = messages.get(AppConstants.SAVE_FAIL);
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
		String msg=AppConstants.EMPTY_STR;
		boolean updatePlan = planService.updatePlan(planMaster);
		if(updatePlan)
		{
			msg = messages.get(AppConstants.UPDATE_SUCC);
		}
		else {
			msg = messages.get(AppConstants.UPDATE_FAIL);
		}
		
		return new ResponseEntity<> (msg, HttpStatus.OK);
	}
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlanMaster(@PathVariable Integer planId){
		String msg=AppConstants.EMPTY_STR;
		boolean deletePlan = planService.deletePlan(planId);
		if(deletePlan) {
			msg = messages.get(AppConstants.DELETE_SUCC);
		}else {
			msg = messages.get(AppConstants.DELETE_FAIL);
		}
		
		return new ResponseEntity<> (msg, HttpStatus.OK);
	}
	@PutMapping("/plan/{planId}/{status}")
	public ResponseEntity<String> changPlanMasterStatus(@PathVariable Integer planId, @PathVariable String status){
		String msg=AppConstants.EMPTY_STR;
		boolean changePlanStatus = planService.changePlanStatus(planId, status);
		if(changePlanStatus) {
			msg = messages.get(AppConstants.CHANGE_SUCC);
		}else {
			msg = messages.get(AppConstants.CHANGE_FAIL);
		}
		return new ResponseEntity<> (msg, HttpStatus.OK);
		
	}
}
