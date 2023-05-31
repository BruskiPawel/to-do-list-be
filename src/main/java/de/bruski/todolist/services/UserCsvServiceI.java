package de.bruski.todolist.services;

import de.bruski.todolist.models.UserCsvRecord;

import java.io.File;
import java.util.List;

public interface UserCsvServiceI {
    List<UserCsvRecord> convertCSV(File csvFile);
}
