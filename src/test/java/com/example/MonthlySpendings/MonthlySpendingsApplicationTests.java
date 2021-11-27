package com.example.MonthlySpendings;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.MonthlySpendings.domain.Frequency;
import com.example.MonthlySpendings.domain.FrequencyRepository;
import com.example.MonthlySpendings.domain.Spending;
import com.example.MonthlySpendings.domain.SpendingRepository;
import com.example.MonthlySpendings.domain.Type;
import com.example.MonthlySpendings.domain.TypeRepository;
import com.example.MonthlySpendings.domain.User;
import com.example.MonthlySpendings.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MonthlySpendingsApplicationTests {

    @Autowired
    private SpendingRepository sRepository;
    @Autowired
    private TypeRepository tRepository;
    @Autowired
    private FrequencyRepository fRepository;
    @Autowired
    private UserRepository uRepository;
    
 /*		--ID IN ORDER--
     
			  Frequencies
     	1		- - -		Everyday
		2		- - - 		2 - 4 times a weeK
		3		- - - 		Once a week
		4		- - - 		2 - 3 times a month
		5		- - - 		Once a month
		6		- - - 		Every 3 months
		7		- - - 		Once a half-year
		8		- - - 		Once a year
		9		- - - 		Once every 2 years
		10		- - - 		Once every 3 years
		
				Types
		11		- - - 		Mandatory
		12		- - - 		Useful
		13		- - - 		Useless
		
			  Spendings		(Name, Type, Cost, Frequency)
		14		- - - 		Tax stuff, Mandatory, 1000, Once a month
		15		- - - 		Food shopping, Mandatory, 25, Once a week
		16		- - - 		Tobacco, Useless, 9.90, 2 - 4 times a week
		17		- - - 		New Phone, Useful, 1000.00, Once every 3 years
		
				Users		(Username, Password, Role)
		none	- - - 		user, $2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6, USER
		none	- - - 		admin, $2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C, ADMIN
 */
    
 //			-- TESTING OF ALL REPOSITORIES --
    @Test
    public void findSpendingWithName() {
        List<Spending> spendings = sRepository.findByName("Tobacco");
        
        assertThat(spendings).hasSize(1);
        assertThat(spendings.get(0).getName()).isEqualTo("Tobacco");
    }
    
    @Test
    public void findTypeIdWithName() {
        List<Type> types = tRepository.findByName("Useful");
        
        assertThat(types.get(0).getTypeId()).isEqualTo(12);
    }
    
    @Test
    public void findFrequencyIdWithName() {
        List<Frequency> frequencies = fRepository.findByName("Every 3 months");
        
        assertThat(frequencies.get(0).getFrequencyId()).isEqualTo(6);
    }
    
    @Test
    public void findRoleWithUsername() {
        User user = uRepository.findByUsername("admin");  
        
        assertThat(user.getRole()).isEqualTo("ADMIN");
    }
 // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    
 //			-- TESTING OF FUNCTIONALITIES --
    @Test
    public void createNewSpending() {
    	Spending spending = new Spending("New Game", tRepository.findByName("Useful").get(0), 60, fRepository.findByName("Once a month").get(0));
    	sRepository.save(spending);
    	assertThat(spending.getId()).isNotNull();
    }
    
 // I've tried to learn how to do the delete testing and found something about MOCK but with many hours of testing..
 // I was not able to get the MOCK thing to work either
    @Test
    public void deleteSpending() {
    	Spending spendingDelete = new Spending("Mouse", tRepository.findByName("Useful").get(0), 100, fRepository.findByName("Once every 3 years").get(0));

    	sRepository.delete(spendingDelete);
    	
    	assertThat(spendingDelete.getName()).isEqualTo(22);
    }
    
 // Same thing with this also, probably something about MOCK but I lack the knowledge
    @Test
    public void editSpending() {
    	
    }  
}