package de.bruski.todolist.bootstrap;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.models.TaskCSVRecord;
import de.bruski.todolist.repositories.TaskRepository;
import de.bruski.todolist.services.TaskCsvServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final TaskCsvServiceI taskCsvServiceI;
    private final TaskRepository taskRepository;



    @Transactional
    @Override
    public void run(String... args) throws Exception {
        taskRepository.deleteAll();
        loadTaskCSVData();
    }

    public void loadTaskCSVData() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvdata/task.csv");

        List<TaskCSVRecord> recs = taskCsvServiceI.convertCSV(file);
        recs.forEach(rec -> {
            Task save = taskRepository.save(Task.builder()
                    .content(rec.getContent())
                    .id(rec.getId())
                    .time(LocalTime.parse(rec.getTime()))
                    .date(LocalDate.parse(rec.getDate()))
                    .build());
            System.out.println(save);
        });

    }

//    public void loadUserCSVData() throws FileNotFoundException {
//        File file = ResourceUtils.getFile("classpath:csvdata/user.csv");
//
//        List<UserCsvRecord> recs = userCsvServiceI.convertCSV(file);
//        recs.forEach(rec -> {
//            User save = userRepository.save(User.builder()
//                    .email(rec.getEmail())
//                    .password(rec.getPassword())
//                    .username(rec.getUsername())
//                    .id(rec.getId())
//                    .build());
//            System.out.println(save);
//        });
//
//    }
}
