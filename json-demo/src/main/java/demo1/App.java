package demo1;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo1.model.Job;
import demo1.model.JobsContainer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    private static final String JSON_JOBS = "./src/main/resources/jobs.json";
    private static final String CSV_JOBS = "./src/main/resources/jobs.csv";

    public static void main(String[] args) throws IOException {
        List<Job> jobs = loadJobs();
        System.out.printf("Total Jobs:  %d %n %n", jobs.size());

        explore(jobs);
    }

    private static void explore(List<Job> jobs) {
        printFirstFiveJobs(jobs);
    }

    private static void printFirstFiveJobs(List<Job> jobs) {
        jobs.stream()
            .limit(5)
            .forEach(System.out::println);
    }

    /**
     * @return list of jobs
     * @throws IOException
     */
    private static List<Job> loadJobs() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JobsContainer container = mapper
                .readValue(Files.newInputStream(Paths.get(JSON_JOBS)), JobsContainer.class);
        return container.getJobs();
    }

    private static void saveJobs(List<Job> jobs) throws IOException {
        JobsContainer container = new JobsContainer();
        container.setJobs(jobs);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Files.newOutputStream(Paths.get(JSON_JOBS)), container);
    }

    /**
     * @return list of jobs
     * @throws IOException
     */
    private static List<Job> loadJobsCsv() throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get(CSV_JOBS));
        List<Job> jobs = reader.lines()
                               .map(Job::new)
                               .collect(Collectors.toList());
        reader.close();
        return jobs;
    }

    private static void saveJobsCsv(List<Job> jobs) throws IOException {
        List<String> csv = jobs.stream()
                               .map(Job::toCsvString)
                               .collect(Collectors.toList());

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_JOBS));
        for (String line : csv) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}
