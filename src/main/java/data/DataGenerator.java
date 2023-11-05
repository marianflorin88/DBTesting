package data;

import java.util.Random;

public class DataGenerator {
//     name, emailAddress, job, avatar, location
    private static final Random RANDOM = new Random();

    public static String generateRandomName() {
        String[] names =  {  "Ion",
                "David",
                "Luca",
                "Matei",
                "Marcu",
                "Anton",
                "Iezechil",
                "Nectarie",
                "Ilie"
        };

        String name = names[RANDOM.nextInt(names.length)];

        return name;
    }

    public static String generateEmailAddress(String name) {
        String[] domains = {
                "gmail.com",
                "yahoo.com",
                "yopmail.com"
        };

        return name.toLowerCase() + "@" + domains[RANDOM.nextInt(domains.length)];
    }

    public static String generateJob() {
        String[] jobs = {
          "Evanghelist",
          "Proroc",
          "Mare Preot",
          "Voievod",
          "Apostol",
          "Presedinte",
          "Postelnic",
          "Invatator",
          "Staroste"
        };

        return jobs[RANDOM.nextInt(jobs.length)];
    }

    public static String generateAvatar() {
        return "https://via.placeholder.com/150";
    }

    public static String generateLocation() {
        String[] locations = {
                "Aiud",
                "Constanta",
                "Deva",
                "Husi",
                "Iasi",
                "Pocreaca",
                "Poplaca",
                "Berevoiesti",
                "Gastesti",
                "Bai Tovarase"
        };

        return locations[RANDOM.nextInt(locations.length)];
    }
}
