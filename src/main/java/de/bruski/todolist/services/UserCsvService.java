package de.bruski.todolist.services;

import com.opencsv.bean.CsvToBeanBuilder;
import de.bruski.todolist.models.UserCsvRecord;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
@Service
public class UserCsvService implements UserCsvServiceI{


    @Override
    public List<UserCsvRecord> convertCSV(File csvFile) {
        try {
            List<UserCsvRecord> userCsvRecords = new CsvToBeanBuilder<UserCsvRecord>(new FileReader(csvFile))
                    .withType(UserCsvRecord.class)
                    .build().parse();
            return userCsvRecords;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
