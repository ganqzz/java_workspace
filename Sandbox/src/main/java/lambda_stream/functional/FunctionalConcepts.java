package lambda_stream.functional;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream API demo
 */
public class FunctionalConcepts {

    private static final String CSV_JOBS = "./src/main/resources/jobs.csv";

    public static void main(String[] args) throws IOException {
        List<Job> jobs = loadJobsCsv();
        System.out.printf("Total Jobs:  %d %n %n", jobs.size());

        // ******

        //explore(jobs);

        //functionComposition(jobs);
        //functionFactoryDemo(jobs);
        functionFactoryParallelDemo(jobs);

        //higherOrderFunction(jobs);

        //displayCompaniesMenu(jobs);
        //displayWithIndex(jobs, 10);

        //luckySearchJobOrElse(jobs);
        //luckySearchJob(jobs);

        //averageLengthOfCompanyNames(jobs);
        //longestCompanyName(jobs);

        //getSnippetWordCountsImperatively(jobs)
        //        .forEach((key, value) -> System.out.printf("%s : %d\n", key, value));
        //getSnippetWordCountsStream(jobs)
        //        .forEach((key, value) -> System.out.printf("%s : %d\n", key, value));

        //getThreeJuniorJobsStream(jobs).forEach(System.out::println);
        //getThreeJuniorJobsStream(jobs).forEach(job -> System.out.println(job.toCsvString()));

        //printPortlandJobsStream(jobs);
    }

    private static void explore(List<Job> jobs) {
    }

    // Function factory, Closure
    private static Function<String, String> createDateStringConverter(
            DateTimeFormatter inFormatter,
            DateTimeFormatter outFormatter) {
        final int[] meaningOfLife = {42};
        return dateString -> {
            meaningOfLife[0]++; // side effect
            return meaningOfLife[0] + "---"
                   + LocalDateTime.parse(dateString, inFormatter)
                                  .format(outFormatter);
        };
    }

    private static void functionFactoryParallelDemo(List<Job> jobs) {
        Function<String, String> converter = createDateStringConverter(
                DateTimeFormatter.RFC_1123_DATE_TIME,
                DateTimeFormatter.ISO_DATE_TIME
        );

        jobs.parallelStream() // 2 coreではside effectの確認はできない？
            .map(Job::getDate)
            .map(converter)
            .forEach(System.out::println);
    }

    private static void functionFactoryDemo(List<Job> jobs) {
        Function<String, String> converter = createDateStringConverter(
                DateTimeFormatter.RFC_1123_DATE_TIME,
                DateTimeFormatter.ISO_DATE_TIME
        );

        jobs.stream()
            .map(Job::getDate)
            .map(converter)
            .limit(5)
            .forEach(System.out::println);
    }

    private static void functionComposition(List<Job> jobs) {
        Function<String, LocalDateTime> dateConverter =
                dateString -> LocalDateTime.parse(dateString,
                                                  DateTimeFormatter.RFC_1123_DATE_TIME);
        // 3 / 15 / 17
        Function<LocalDateTime, String> siteDateStringConverter =
                date -> date.format(DateTimeFormatter.ofPattern("M / d / YY"));

        jobs.stream()
            .map(Job::getDate)
            .map(dateConverter.andThen(siteDateStringConverter))
            .limit(5)
            .forEach(System.out::println);
    }

    private static void higherOrderFunction(List<Job> jobs) {
        Predicate<Job> caJobChecker = job -> job.getState().equals("CA");

        Job caJob = jobs.stream()
                        .filter(caJobChecker.and(FunctionalConcepts::isJuniorJob))
                        .findFirst()
                        .orElseThrow(NullPointerException::new);
        emailIfMatches(caJob, caJobChecker);
    }

    private static void emailIfMatches(Job job, Predicate<Job> checker) {
        if (checker.test(job)) {
            System.out.println("I am sending an email about " + job);
        }
    }

    private static List<String> getCompanies(List<Job> jobs) {
        return jobs.stream()
                   .map(Job::getCompany)
                   .distinct()
                   .sorted()
                   .collect(Collectors.toList());
    }

    private static <T> void displayWithIndex(List<T> list, int count) {
        int last = (count > 0 ? Math.min(list.size(), count) : list.size()) - 1;
        IntStream.rangeClosed(0, last)
                 .mapToObj(i -> String.format("%d. %s", i, list.get(i)))
                 .forEach(System.out::println);
    }

    private static void displayCompaniesMenu(List<Job> jobs) {
        displayWithIndex(getCompanies(jobs), 10);
    }

    private static void luckySearchJobOrElse(List<Job> jobs) {
        String searchTerm = "hoge hoge";
        Optional<Job> foundJob = jobs.stream()
                                     .filter(job -> job.getTitle().contains(searchTerm))
                                     .findFirst();
        System.out.println(foundJob.map(Job::getTitle).orElse("No such jobs"));
    }

    private static void luckySearchJob(List<Job> jobs) {
        String searchTerm = "Java";
        Optional<Job> foundJob = jobs.stream()
                                     .filter(job -> job.getTitle().contains(searchTerm))
                                     .findFirst();
        foundJob.ifPresent(System.out::println);
    }

    private static void longestCompanyName(List<Job> jobs) {
        System.out.println(
                jobs.stream()
                    .map(Job::getCompany)
                    //.max((company1, company2) -> company1.length() - company2.length())
                    .max(Comparator.comparing(String::length))
        );
    }

    private static void averageLengthOfCompanyNames(List<Job> jobs) {
        System.out.println(
                jobs.stream()
                    .map(Job::getCompany)
                    .mapToInt(String::length)
                    .average()
        );
    }

    private static void ofDemo() {
        Stream.of("Hello", "this", "is", "a", "Stream")
              .forEach(System.out::println);
    }

    private static Map<String, Long> getSnippetWordCountsStream(List<Job> jobs) {
        return jobs.stream()
                   .map(Job::getSnippet)
                   .map(snippet -> snippet.split("\\W+"))
                   .flatMap(Stream::of)
                   .filter(word -> word.length() > 0)
                   .map(String::toLowerCase)
                   .collect(Collectors.groupingBy(
                           //word -> word, // key
                           Function.identity(), // key : T -> T
                           Collectors.counting() // value
                   ));
    }

    private static Map<String, Long> getSnippetWordCountsImperatively(List<Job> jobs) {
        Map<String, Long> wordCounts = new HashMap<>();
        for (Job job : jobs) {
            String[] words = job.getSnippet().split("\\W+");
            for (String word : words) {
                if (word.length() == 0) continue;
                String lword = word.toLowerCase();
                Long count = wordCounts.get(lword);
                if (count == null) count = 0L;
                wordCounts.put(lword, ++count);
            }
        }
        return wordCounts;
    }

    private static boolean isJuniorJob(Job job) {
        String title = job.getTitle().toLowerCase();
        return title.contains("junior") || title.contains("jr");
    }

    private static List<Job> getThreeJuniorJobsStreamBadExample(List<Job> jobs) {
        // with side effects
        List<Job> juniorJobs = new ArrayList<>();
        jobs.stream()
            .filter(FunctionalConcepts::isJuniorJob)
            .limit(3)
            .forEach(juniorJobs::add);
        return juniorJobs;
    }

    private static List<Job> getThreeJuniorJobsStream(List<Job> jobs) {
        return jobs.stream()
                   .filter(FunctionalConcepts::isJuniorJob)
                   .limit(3)
                   .collect(Collectors.toList());
    }

    private static List<Job> getThreeJuniorJobsImperatively(List<Job> jobs) {
        List<Job> juniorJobs = new ArrayList<>();
        for (Job job : jobs) {
            if (isJuniorJob(job)) {
                juniorJobs.add(job);
                if (juniorJobs.size() >= 3) break;
            }
        }
        return juniorJobs;
    }

    private static List<String> getCaptionsStream(List<Job> jobs) {
        return jobs.stream()
                   .filter(FunctionalConcepts::isJuniorJob)
                   .limit(3)
                   .map(Job::getCaption)
                   .collect(Collectors.toList());
    }

    private static List<String> getCaptionsImperatively(List<Job> jobs) {
        List<String> captions = new ArrayList<>();
        for (Job job : jobs) {
            if (isJuniorJob(job)) {
                captions.add(job.getCaption());
                if (captions.size() >= 3) break;
            }
        }
        return captions;
    }

    private static void printPortlandJobsStream(List<Job> jobs) {
        jobs.stream()
            .filter(job -> job.getState().equals("OR"))
            .filter(job -> job.getCity().equals("Portland"))
            .forEach(System.out::println);
    }

    private static void printPortlandJobsImperatively(List<Job> jobs) {
        for (Job job : jobs) {
            if (job.getState().equals("OR") && job.getCity().equals("Portland")) {
                System.out.println(job);
            }
        }
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
        List<String> jobCsv = jobs.stream()
                                  .map(Job::toCsvString)
                                  .collect(Collectors.toList());

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_JOBS));
        for (String s : jobCsv) {
            writer.write(s);
            writer.newLine();
        }
        writer.close();
    }
}
