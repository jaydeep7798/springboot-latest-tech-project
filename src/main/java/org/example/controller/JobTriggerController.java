package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")

public class JobTriggerController {
    @Autowired
    private final JobService jobService;

    public JobTriggerController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/run")
    //http://localhost:8080/jobs/run?filePath=C:/Users/JAYDEEP/IdeaProjects/springboot-latest-tech-project/src/main/resources/customers.csv use this URL
    //Note : Change your URL according to the path.
    public String runJob(@RequestParam String filePath) {
        filePath = filePath.trim();
        jobService.runJob(filePath);
        return "Job started!";
    }
}

