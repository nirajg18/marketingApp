package com.marketingapp.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.marketingapp.dto.LeadData;
import com.marketingapp.entities.Lead;
import com.marketingapp.services.LeadService;
@Controller
public class LeadController {
	@Autowired
	private LeadService leadService;
	@RequestMapping("/create")
	public String viewCreateLeadPage() {
		return "create_new_lead";
	}
	@RequestMapping("/saveLead")
	public String saveOneLead(@ModelAttribute("lead")Lead lead, ModelMap model) {
		leadService.saveLead(lead);
		model.addAttribute("saveMsg", "Record is saved");
		return "create_new_lead";
	}
	@RequestMapping("/listleads")
	public String getAllLeads(ModelMap model) {
		List <Lead> leads = leadService.listAll();
		model.addAttribute("leads", leads);
		return "search_result";
	}
	@RequestMapping("/delete")
	public String deleteOneLead(@RequestParam("id") long id, ModelMap model) {
		leadService.deleteLeadById(id);
		
		List<Lead> leads = leadService.listAll();
		model.addAttribute("leads", leads);
		return "search_result";
	}
	@RequestMapping("/update")
	public String updateOneLead(@RequestParam("id") long id, ModelMap model) {
		Lead lead = leadService.findOneLeadById(id);
		if(lead!=null) {
			model.addAttribute("lead", lead);
			return "update_lead";
		}else {
			model.addAttribute("error","No record found");
			return "error_page";
		}
	}	
	@RequestMapping("/updateLead")
	public String updateLead(LeadData data, ModelMap model) {
		Lead l=new Lead();
		l.setId(data.getId());
		l.setFirstName(data.getFirstName());
		l.setLastName(data.getLastName());
		l.setEmail(data.getEmail());
		l.setMobile(data.getMobile());
		leadService.saveLead(l);
		List<Lead> leads = leadService.listAll();
		model.addAttribute("leads", leads);
		return "search_result";
	}
}

