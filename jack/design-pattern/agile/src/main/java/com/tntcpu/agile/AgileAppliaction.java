package com.tntcpu.agile;

import com.tntcpu.agile.chap19.InMemoryPayrollDatabase;
import com.tntcpu.agile.chap19.PayrollDatabase;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-12-31
 **/
@SpringBootApplication
public class AgileAppliaction {
//    private static PayrollDatabase database;

    public static void main(String[] args) {
        SpringApplication.run(AgileAppliaction.class, args);
    }

//    public static PayrollDatabase getDatabase() {
//        return database;
//    }


//    @Override
//    public void run(ApplicationArguments args) {
//        database = new InMemoryPayrollDatabase();
//    }
}
