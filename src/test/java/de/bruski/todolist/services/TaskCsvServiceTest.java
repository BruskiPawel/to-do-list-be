package de.bruski.todolist.services;

import de.bruski.todolist.models.TaskCSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

class TaskCsvServiceTest {

    TaskCsvServiceI taskCsvServiceI = new TaskCsvService();

    @Test
    void convertCSV() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvdata/task.csv");

        List<TaskCSVRecord> recs = taskCsvServiceI.convertCSV(file);

        System.out.println(recs);
    }
}