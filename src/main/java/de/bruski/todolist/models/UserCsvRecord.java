package de.bruski.todolist.models;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCsvRecord {
    @CsvBindByName
    private UUID id;
    @CsvBindByName
    private String username;
    @CsvBindByName
    private String email;
    @CsvBindByName
    private String password;
}
