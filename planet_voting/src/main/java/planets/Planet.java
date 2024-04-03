//package planets;
//
//public class Planet {
//    private final String name;
//    private final String environment;
//    private final String resources;
//
//    public Planet(String name, String environment, String resources) {
//        this.name = name;
//        this.environment = environment;
//        this.resources = resources;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getEnvironment() {
//        return environment;
//    }
//
//    public String getResources() {
//        return resources;
//    }
//
//    // You might also add toString method for easy printing of planet details
//    @Override
//    public String toString() {
//        return name + " (Environment: " + environment + ", Resources: " + resources + ")";
//    }
//}
//



package planets;

public class Planet {
    private String name;
    private String environment;
    private String description;

    public Planet(String name, String environment, String description) {
        this.name = name;
        this.environment = environment;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getDescription() {
        return description;
    }

    // You might want to override toString for easier printing
    @Override
    public String toString() {
        return name + " - Environment: " + environment + ". " + description;
    }
}
