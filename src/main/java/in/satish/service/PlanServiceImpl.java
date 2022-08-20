package in.satish.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.satish.entity.PlanCategory;
import in.satish.entity.PlanMaster;
import in.satish.repo.PlanCategoryRepo;
import in.satish.repo.PlanMasterRepo;

@Service
public class PlanServiceImpl implements PlanService{

	@Autowired
	private PlanMasterRepo pmr;
	
	@Autowired
	private PlanCategoryRepo pcr;
	
	@Override
	public Map<Integer, String> getPlanCategory() {
		List<PlanCategory> categories = pcr.findAll();
		Map<Integer, String> categoryMap = new HashMap<>();
		
		categories.forEach(category ->{
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		
		return categoryMap;
	}

	@Override
	public boolean savePlan(PlanMaster planMaster) {
		PlanMaster save = pmr.save(planMaster);
		return save.getPlanId()!=null;
	}

	@Override
	public boolean updatePlan(PlanMaster planMaster) {
		pmr.save(planMaster);
		return planMaster.getPlanId()!=null;
	}

	@Override
	public boolean deletePlan(Integer PlanId) {
		boolean status = false;
		try {
		pmr.deleteById(PlanId);
		status = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<PlanMaster> getAllPlan() {
		//List<PlanMaster> findAll = 
		return pmr.findAll();
	}

	@Override
	public PlanMaster getPlanById(Integer planId) {
		Optional<PlanMaster> findById = pmr.findById(planId);
		if(findById.isPresent())
		{
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean changePlanStatus(Integer planId, String status) {
		Optional<PlanMaster> findById = pmr.findById(planId);
		
		if(findById.isPresent()) {
			PlanMaster planMaster = findById.get();
			planMaster.setPlanActiveSw(status);
			pmr.save(planMaster);
			return true;
		}
		return false;
	}

}
