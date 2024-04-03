package GamePlanet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import planets.Minerva;
import planets.Orestes;
import planets.Somnus;

import planets.Planet;
import Voters.Voter;


public class MainApp {
    public static void main(String[] args) {
        List<Planet> planets = new ArrayList<>();
        planets.add(new Minerva());
        planets.add(new Orestes());
        planets.add(new Somnus());
        VotingSystem votingSystem = new VotingSystem();
        Scanner scanner = new Scanner(System.in);



        while (true) {
            System.out.println("Welcome to the Intergalactic Colonization Voting System!");
            System.out.println("Please enter your gamePlanet.Voter ID or type 'exit' to quit:");
            String voterId = scanner.nextLine();

            if ("exit".equalsIgnoreCase(voterId)) {
                break;
            }

            System.out.println("Select your Social Class (1 - Lower, 2 - Middle, 3 - High):");
            int classSelection = scanner.nextInt();
            Voter.SocialClass socialClass;
            switch (classSelection) {
                case 1:
                    socialClass = Voter.SocialClass.LOWER;
                    break;
                case 2:
                    socialClass = Voter.SocialClass.MIDDLE;
                    break;
                case 3:
                    socialClass = Voter.SocialClass.HIGH;
                    break;
                default:
                    System.out.println("Invalid selection, defaulting to Lower class.");
                    socialClass = Voter.SocialClass.LOWER;
            }

            System.out.println("Choose a planet to vote for:");
            for (int i = 0; i < planets.size(); i++) {
                System.out.println((i + 1) + " - " + planets.get(i).getName());
            }
            int planetSelection = scanner.nextInt() - 1;

            // Consume the leftover newline
            scanner.nextLine();

            if (planetSelection >= 0 && planetSelection < planets.size()) {
                Voter voter = new Voter(voterId, socialClass);
                votingSystem.registerVote(voter, planets.get(planetSelection));
            } else {
                System.out.println("Invalid planet selection.");
            }

            System.out.println("Do you want to display the current winning planet? (yes/no)");
            String displayWinning = scanner.nextLine();

            if ("yes".equalsIgnoreCase(displayWinning)) {
                votingSystem.displayWinningPlanet();
            }
        }

        scanner.close();
    }
}
