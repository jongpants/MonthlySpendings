package com.example.MonthlySpendings.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.MonthlySpendings.domain.FrequencyRepository;
import com.example.MonthlySpendings.domain.Spending;
import com.example.MonthlySpendings.domain.SpendingRepository;
import com.example.MonthlySpendings.domain.TypeRepository;

@Controller
public class SpendingController {
	@Autowired
	private SpendingRepository sRepository;
	
	@Autowired
	private TypeRepository tRepository;
	
	@Autowired
	private FrequencyRepository fRepository;
	
    @RequestMapping(value = "/spendinglist")
    public String spendingList(Model model) {	
        model.addAttribute("spendings", sRepository.findAll());
        return "spendinglist";
    }
    
    @RequestMapping(value="/spendings", method = RequestMethod.GET)
    public @ResponseBody List<Spending> spendingListRest() {	
        return (List<Spending>) sRepository.findAll();
    }    

    @RequestMapping(value="/spending/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Spending> findSpendingRest(@PathVariable("id") Long spendingId) {	
    	return sRepository.findById(spendingId);
    }
  
    @RequestMapping(value = "/add")
    public String addSpending(Model model){
    	model.addAttribute("spending", new Spending());
    	model.addAttribute("types", tRepository.findAll());
    	model.addAttribute("frequencies", fRepository.findAll());
        return "addSpending";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSpending(Spending spending){
        sRepository.save(spending);
        return "redirect:spendinglist";
    }    
    
    @RequestMapping(value = "/edit/{id}")
    public String editSpending(@PathVariable("id") Long spendingId, Model model){
    	model.addAttribute("spending", sRepository.findById(spendingId));
    	model.addAttribute("types", tRepository.findAll());
    	model.addAttribute("frequencies", fRepository.findAll());
        return "editspending";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteSpending(@PathVariable("id") Long spendingId, Model model) {
    	sRepository.deleteById(spendingId);
        return "redirect:../spendinglist";
    }
}