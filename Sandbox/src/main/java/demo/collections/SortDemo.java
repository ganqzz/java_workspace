package demo.collections;

import java.util.Arrays;
import java.util.Comparator;

public class SortDemo {

    public static void main(String[] args) {
        User[] users = {
                new User(3, "Bob"),
                new User(5, "Joe"),
                new User(1, "Lisa"),
        };

        // Comparable
        System.out.println("Comparable");
        Arrays.sort(users); // Sort using Comparable method. compareTo()

        for (User user : users) {
            System.out.println(user);
        }

        // Comparator
        System.out.println("\n\nComparator");
        Arrays.sort(users, new UserSortByName());

        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * User Comparable
     */
    private static class User implements Comparable<User> {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User(int id, String name) {
            super();
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User [id=" + id + ", name=" + name + "]";
        }

        /**
         * Compare current user with specified user return zero if id for both user is same return
         * negative if current id is less than specified one return positive if specified id is
         * greater
         * than specified one
         */
        @Override
        public int compareTo(User u) {
            // return this.id - u.id; // this can cause overflow
            //return (this.id < u.id) ? -1 : (this.id > u.id) ? 1 : 0;
            return Integer.compare(this.id, u.id); // Java7
        }
    }

    /**
     * User Comparator by name
     */
    private static class UserSortByName implements Comparator<User> {

        @Override
        public int compare(User u1, User u2) {
            String userName1 = u1.getName().toUpperCase();
            String userName2 = u2.getName().toUpperCase();

            // ascending
            return userName1.compareTo(userName2);

            // descending
            // return userName2.compareTo(userName1);
        }
    }
}
