package de.bruski.todolist.bootstrap;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.entities.User;
import de.bruski.todolist.models.TaskCSVRecord;
import de.bruski.todolist.models.UserCsvRecord;
import de.bruski.todolist.repositories.TaskRepository;
import de.bruski.todolist.repositories.UserRepository;
import de.bruski.todolist.services.TaskCsvServiceI;
import de.bruski.todolist.services.UserCsvServiceI;
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
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final TaskCsvServiceI taskCsvServiceI;
    private final TaskRepository taskRepository;
    private final UserCsvServiceI userCsvServiceI;
    private final UserRepository userRepository;



    @Transactional
    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        loadUserCSVData();
        System.out.println("fdsfsdfsdfsfsdfsdfsdf");
        System.out.println(userRepository.findAll().get(0).getUsername());
        System.out.println("fdsfsdfsdfsfsdfsdfsdf");
        taskRepository.deleteAll();
        loadTaskCSVData();
    }

    public void loadTaskCSVData() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvdata/task.csv");

        List<TaskCSVRecord> recs = taskCsvServiceI.convertCSV(file);
        recs.forEach(rec -> {
            Optional<User> user = Optional.ofNullable(userRepository.findAll().get(0));
            user.ifPresent(u -> {
                Task save = taskRepository.saveAndFlush(Task.builder()
                        .content(rec.getContent())
                        .id(rec.getId())
                        .time(LocalTime.parse(rec.getTime()))
                        .date(LocalDate.parse(rec.getDate()))
                        .user(u)
                        .build());
            });
        });

    }

    public void loadUserCSVData() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvdata/user.csv");

        List<UserCsvRecord> recs = userCsvServiceI.convertCSV(file);
        recs.forEach(rec -> {
            User save = userRepository.saveAndFlush(User.builder()
                    .email(rec.getEmail())
                    .password(rec.getPassword())
                    .username(rec.getUsername())
                    .id(rec.getId())
                    .build());
            System.out.println(save);
        });

    }
}
