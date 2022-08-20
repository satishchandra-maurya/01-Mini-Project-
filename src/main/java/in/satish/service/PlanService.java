package in.satish.service;

import java.util.List;
import java.util.Map;

import in.satish.entity.PlanMaster;

public interface PlanService {

	public Map<Integer, String> getPlanCategory();
	
	public boolean savePlan(PlanMaster planMaster);
	
	public boolean updatePlan(PlanMaster planMaster);
	
	public boolean deletePlan(Integer PlanId);
	
	public List<PlanMaster> getAllPlan();
	
	public PlanMaster getPlanById(Integer planId);
	
	public boolean changePlanStatus(Integer planId, String status);
	
	
}
