package demo1.model;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Job {

    private String title;
    private String company;
    private String city;
    private String state;
    private String country;
    private String snippet;
    private String date;

    public Job() {}

    // no error handling
    public Job(String csv) {
        String[] a = csv.substring(1, csv.length() - 1)
                        .split("\",\"");
        title = a[0];
        company = a[1];
        city = a[2];
        state = a[3];
        country = a[4];
        snippet = a[5];
        date = a[6];
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getDate() {
        return date;
    }

    public String getCaption() {
        return String.format("%s is looking for a %s in %s",
                             getCompany(), getTitle(), getCity());
    }

    public String toCsvString() {
        return Stream.of(title, company, city, state, country, snippet, date)
                     .map(s -> s.replace("\"", "\"\""))
                     .collect(Collectors.joining("\",\"", "\"", "\""));
    }

    @Override
    public String toString() {
        return "Job{" +
               "title='" + title + '\'' +
               ", company='" + company + '\'' +
               ", city='" + city + '\'' +
               ", state='" + state + '\'' +
               ", country='" + country + '\'' +
               ", date='" + date + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;

        Job job = (Job) o;

        if (!title.equals(job.title)) return false;
        if (!company.equals(job.company)) return false;
        if (!Objects.equals(city, job.city)) return false;
        if (!Objects.equals(state, job.state)) return false;
        if (!Objects.equals(country, job.country)) return false;
        if (!Objects.equals(snippet, job.snippet)) return false;
        return Objects.equals(date, job.date);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + company.hashCode();
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (snippet != null ? snippet.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
