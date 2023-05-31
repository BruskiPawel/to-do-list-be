package de.bruski.todolist.services;

import com.opencsv.bean.CsvToBeanBuilder;
import de.bruski.todolist.models.TaskCSVRecord;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class TaskCsvService implements TaskCsvServiceI {

    @Override
    public List<TaskCSVRecord> convertCSV(File csvFile) {
        try {
            List<TaskCSVRecord> beerCSVRecords = new CsvToBeanBuilder<TaskCSVRecord>(new FileReader(csvFile))
                    .withType(TaskCSVRecord.class)
                    .build().parse();
            return beerCSVRecords;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
