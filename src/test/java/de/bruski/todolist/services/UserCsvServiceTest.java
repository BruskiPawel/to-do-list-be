package de.bruski.todolist.services;

import de.bruski.todolist.models.UserCsvRecord;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserCsvServiceTest {
    UserCsvServiceI userCsvService = new UserCsvService();

    @Test
    void convertCSV() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvdata/task.csv");

        List<UserCsvRecord> recs = userCsvService.convertCSV(file);

        System.out.println(recs.size());
    }

}