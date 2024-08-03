package com.example.runner;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.*;
import java.util.Calendar;


@Slf4j
@Component
public class VisitorRunner implements ApplicationRunner {

    public static final String INPUt_FILE = "src/main/resources/visitors.csv";

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        val jobParameters = new JobParametersBuilder()
                .addDate("timestamp", Calendar.getInstance().getTime())
                .addString("inputFile", INPUt_FILE)
                .toJobParameters();
        val jobExecution = jobLauncher.run(job, jobParameters);
        while (jobExecution.isRunning()) {
            log.info("..................");
        }
    }


    @Scheduled(fixedRate = 2000)
    public void run(String inputFile) throws Exception {
        val jobParameters = new JobParametersBuilder()
                .addDate("timestamp", Calendar.getInstance().getTime())
                .addString("inputFile", inputFile)
                .toJobParameters();
        jobLauncher.run(job, jobParameters);
    }

    public void runJob() {

        val path = Paths.get("/home/user/Vikrant"); // to be changed to your directory.
        WatchKey key;
        WatchService watchService = null;
        try {
            watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            System.out.println("watchService.take() " +watchService.take());
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {

                    log.info("Event kind:{}. File affected: {}.", event.kind(), event.context());
                    if (event.kind().name().equals("ENTRY_CREATE")) {
                        run(path + "/" + event.context().toString()); // Add the file name pattern validation here
                    }
                }
                key.reset();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
