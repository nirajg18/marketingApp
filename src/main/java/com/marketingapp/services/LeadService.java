package com.marketingapp.services;
import java.util.List;
import org.springframework.stereotype.Service;
import com.marketingapp.entities.Lead;
@Service
public interface LeadService {
	public void saveLead(Lead lead);
	public List<Lead> listAll();
	public void deleteLeadById(long id);
	public Lead findOneLeadById(long id);
}
