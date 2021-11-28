package com.example.MonthlySpendings.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
    @RequestMapping(value = "/spendinglist")
    public String spendingList(Model model) {	
        model.addAttribute("spendings", sRepository.findAll());
        
 //						-----------------------
 //						-- Calculation START --
 //						-----------------------
        
 /*		Week into month calculation
 		1 = *30		6 = /3
        2 = *12		7 = /6
        3 = *4		8 = /12
        4 = *1.33	9 = /24
        5 = *1		10 = /36
        
        Type definition
        11 = Mandatory
        12 = Useful
        13 = Useless
 */

        double overallCalc = 0;
        double noUselessCalc = 0;
        double minimumCalc = 0;
        double uselessCalc = 0;
        double usefulCalc = 0;
        double yearlyCalc = 0;
        
        for (Spending spending : sRepository.findAll()) {
        	if (spending.getFrequency().getFrequencyId() != 9) {
            	if (spending.getFrequency().getFrequencyId() == 1) {
            		overallCalc += spending.getCost()*30;
            		if (spending.getType().getTypeId() == 13) {
            			uselessCalc += spending.getCost()*30;
            		}
            		if (spending.getType().getTypeId() == 12) {
            			usefulCalc += spending.getCost()*30;
            		}
            	}
            	if (spending.getFrequency().getFrequencyId() == 2) {
            		overallCalc += spending.getCost()*12;
            		if (spending.getType().getTypeId() == 13) {
            			uselessCalc += spending.getCost()*12;
            		}
            		if (spending.getType().getTypeId() == 12) {
            			usefulCalc += spending.getCost()*12;
            		}
            	}
            	if (spending.getFrequency().getFrequencyId() == 3) {
            		overallCalc += spending.getCost()*4;
            		if (spending.getType().getTypeId() == 13) {
            			uselessCalc += spending.getCost()*4;
            		}
            		if (spending.getType().getTypeId() == 12) {
            			usefulCalc += spending.getCost()*4;
            		}
            	}
            	if (spending.getFrequency().getFrequencyId() == 4) {
            		overallCalc += spending.getCost()*1.33;
            		if (spending.getType().getTypeId() == 13) {
            			uselessCalc += spending.getCost()*1.33;
            		}
            		if (spending.getType().getTypeId() == 12) {
            			usefulCalc += spending.getCost()*1.33;
            		}
            	}
            	if (spending.getFrequency().getFrequencyId() == 5) {
            		overallCalc += spending.getCost();
            		if (spending.getType().getTypeId() == 13) {
            			uselessCalc += spending.getCost();
            		}
            		if (spending.getType().getTypeId() == 12) {
            			usefulCalc += spending.getCost();
            		}
            	}
            	if (spending.getFrequency().getFrequencyId() == 6) {
            		overallCalc += spending.getCost()/3;
            		if (spending.getType().getTypeId() == 13) {
            			uselessCalc += spending.getCost()/3;
            		}
            		if (spending.getType().getTypeId() == 12) {
            			usefulCalc += spending.getCost()/3;
            		}
            	}
            	if (spending.getFrequency().getFrequencyId() == 7) {
            		overallCalc += spending.getCost()/6;
            		if (spending.getType().getTypeId() == 13) {
            			uselessCalc += spending.getCost()/6;
            		}
            		if (spending.getType().getTypeId() == 12) {
            			usefulCalc += spending.getCost()/6;
            		}
            	}
            	if (spending.getFrequency().getFrequencyId() == 8) {
            		overallCalc += spending.getCost()/12;
            		if (spending.getType().getTypeId() == 13) {
            			uselessCalc += spending.getCost()/12;
            		}
            		if (spending.getType().getTypeId() == 12) {
            			usefulCalc += spending.getCost()/12;
            		}
            	}
            	if (spending.getFrequency().getFrequencyId() == 9) {
            		overallCalc += spending.getCost()/24;
            		if (spending.getType().getTypeId() == 13) {
            			uselessCalc += spending.getCost()/24;
            		}
            		if (spending.getType().getTypeId() == 12) {
            			usefulCalc += spending.getCost()/24;
            		}
            	} 
            	if (spending.getFrequency().getFrequencyId() == 10) {
            		overallCalc += spending.getCost()/36;
            		if (spending.getType().getTypeId() == 13) {
            			uselessCalc += spending.getCost()/36;
            		}
            		if (spending.getType().getTypeId() == 12) {
            			usefulCalc += spending.getCost()/36;
            		}
            	} 
        	}	
        }
        
        noUselessCalc = overallCalc - uselessCalc;
        minimumCalc = overallCalc - uselessCalc - usefulCalc;
        yearlyCalc = overallCalc * 12;
        
 //						-- ROUNDING TO 2 DECIMALS --
        overallCalc = Math.round(overallCalc*100.0)/100.0;
        uselessCalc = Math.round(uselessCalc*100.0)/100.0;
        noUselessCalc = Math.round(noUselessCalc*100.0)/100.0;
        usefulCalc = Math.round(usefulCalc*100.0)/100.0;
        minimumCalc = Math.round(minimumCalc*100.0)/100.0;
        yearlyCalc = Math.round(yearlyCalc*100.0)/100.0;
        
 //		-- MAKING STRINGS FOR OUTPUT BECAUSE PUTTING TEXT IN THE HTML VARIABLE CALLS WAS DIFFICULT --
        String spendingString1 = "Total spendings: " +overallCalc+ " | Yearly spending: " +yearlyCalc;
        String spendingString2 = "Without useless: " +noUselessCalc+ " | Useless spendings: " +uselessCalc;
        String spendingString3 = "Without useless and usefuls: " +minimumCalc+ " | Useful spendings: " +usefulCalc;       
        model.addAttribute("spendingString1", spendingString1);
        model.addAttribute("spendingString2", spendingString2);
        model.addAttribute("spendingString3", spendingString3);
        
 //					-- CALCULATION TESTING IN CONSOLE --
        System.out.println("\noverallCalc: " +overallCalc);
        System.out.println("uselessCalc: " +uselessCalc);
        System.out.println("noUselessCalc: " +noUselessCalc);
        System.out.println("usefulCalc: " +usefulCalc);
        System.out.println("minimumCalc: " +minimumCalc);
        System.out.println("yearlyCalc: " +yearlyCalc);
        
 //       				---------------------
 //						-- Calculation END --
 //       				---------------------
        
        return "spendinglist";
    }
    
 // 					-- RESTFUL STUFF --
 //			Only accessible by users with the ADMIN role
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/spendings", method = RequestMethod.GET)
    public @ResponseBody List<Spending> spendingListRest() {	
        return (List<Spending>) sRepository.findAll();
    }    
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/spending/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Spending> findSpendingRest(@PathVariable("id") Long spendingId) {	
    	return sRepository.findById(spendingId);
    }
 //						-- RESTFUL END -- 
    
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