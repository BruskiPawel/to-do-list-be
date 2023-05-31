package de.bruski.todolist.models;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCSVRecord {

    @CsvBindByName
    private UUID id;
    @CsvBindByName
    private String date;
    @CsvBindByName
    private String time;
    @CsvBindByName
    private String content;
}
