package Voters;

public class Voter {
    private final String id; // Unique identifier for each voter
    private final SocialClass socialClass;

    public Voter(String id, SocialClass socialClass) {
        this.id = id;
        this.socialClass = socialClass;
    }

    public String getId() {
        return id;
    }

    public SocialClass getSocialClass() {
        return socialClass;
    }

    public enum SocialClass {
        HIGH(3), MIDDLE(2), LOWER(1);

        private final int weight;

        SocialClass(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }

    // You might also add toString method for easy printing of voter details
    @Override
    public String toString() {
        return "gamePlanet.Voter ID: " + id + ", Social Class: " + socialClass + " (Weight: " + socialClass.getWeight() + ")";
    }
}

