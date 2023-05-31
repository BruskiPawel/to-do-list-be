package de.bruski.todolist.services;

import de.bruski.todolist.models.TaskCSVRecord;

import java.io.File;
import java.util.List;


public interface TaskCsvServiceI {
    List<TaskCSVRecord> convertCSV(File csvFile);
}
