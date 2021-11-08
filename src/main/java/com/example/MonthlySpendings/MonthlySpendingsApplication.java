package com.example.MonthlySpendings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.MonthlySpendings.domain.Frequency;
import com.example.MonthlySpendings.domain.FrequencyRepository;
import com.example.MonthlySpendings.domain.Spending;
import com.example.MonthlySpendings.domain.SpendingRepository;
import com.example.MonthlySpendings.domain.Type;
import com.example.MonthlySpendings.domain.TypeRepository;

@SpringBootApplication
public class MonthlySpendingsApplication {
	private static final Logger log = LoggerFactory.getLogger(MonthlySpendingsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MonthlySpendingsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner spendingDemo(SpendingRepository sRepository, TypeRepository tRepository, FrequencyRepository fRepository) {
		return (args) -> {
			log.info("generate all preset content");
			fRepository.save(new Frequency("Everyday"));
			fRepository.save(new Frequency("2 - 4 times a week"));
			fRepository.save(new Frequency("Once a week"));
			fRepository.save(new Frequency("2 - 3 times a month"));
			fRepository.save(new Frequency("Once a month"));
			fRepository.save(new Frequency("Every 3 months"));
			fRepository.save(new Frequency("Once a half-year"));
			fRepository.save(new Frequency("Once a year"));
			fRepository.save(new Frequency("Once every 2 years"));
			fRepository.save(new Frequency("Once every 3 years"));
			
			tRepository.save(new Type("Mandatory"));
			tRepository.save(new Type("Useful"));
			tRepository.save(new Type("Useless"));
			
			sRepository.save(new Spending("Tax stuff", tRepository.findByName("Mandatory").get(0), 1000, fRepository.findByName("Once a month").get(0)));
			sRepository.save(new Spending("Food shopping", tRepository.findByName("Mandatory").get(0), 25, fRepository.findByName("Once a week").get(0)));
			sRepository.save(new Spending("Tobacco", tRepository.findByName("Useless").get(0), 9.90, fRepository.findByName("2 - 4 times a week").get(0)));
			sRepository.save(new Spending("New Phone", tRepository.findByName("Useful").get(0), 1000.00, fRepository.findByName("Once every 3 years").get(0)));
			
			log.info("fetch all spendings");
			for (Spending spending : sRepository.findAll()) {
				log.info(spending.toString());
			}
		};
	}
}