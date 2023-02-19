package com.codecool.hogwarts_potions;

import com.codecool.hogwarts_potions.model.*;
import com.codecool.hogwarts_potions.repository.RoomRepository;
import com.codecool.hogwarts_potions.repository.StudentRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class HogwartsPotionsApplication {

    private Gson gson = new Gson();
    private static final Logger log = LoggerFactory.getLogger(HogwartsPotionsApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(HogwartsPotionsApplication.class, args);


    }

}


