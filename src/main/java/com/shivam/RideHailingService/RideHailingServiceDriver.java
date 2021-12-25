package com.shivam.RideHailingService;

import com.shivam.RideHailingService.InMemoryDataBase.CabDB;
import com.shivam.RideHailingService.Services.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RideHailingServiceDriver implements CommandLineRunner {

    @Autowired
    CabService cabService;


    //As it was not working properly so I have added everything in testcases
    public static void main(String[] args) {
        System.out.println("Check");


        SpringApplication.run(RideHailingServiceDriver.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        cabService.introduceNewCabInSystem("123", "Abc");
    }
}
