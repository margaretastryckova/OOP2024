package GamePlanet;

import planets.Planet;
import Voters.Voter;

import java.util.*;

public class VotingSystem {
    private final Map<Planet, Integer> votes = new HashMap<>();
    private final Set<String> votedIds = new HashSet<>();

    /**
     * Registers a vote for a given planet by a voter.
     * Ensures that each voter votes only once.
     *
     * @param voter  the voter casting the vote
     * @param planet the planet being voted for
     */
    public void registerVote(Voter voter, Planet planet) {
        if (votedIds.contains(voter.getId())) {
            System.out.println("This voter has already voted. Voting integrity maintained.");
            return;
        }

        votes.merge(planet, voter.getSocialClass().getWeight(), Integer::sum);
        votedIds.add(voter.getId());
        System.out.println("Vote successfully registered for " + planet.getName() + " by voter ID " + voter.getId());
    }

    /**
     * Calculates and displays the winning planet.
     * The planet with the highest total weight from votes is declared the winner.
     */
    public void displayWinningPlanet() {
        if (votes.isEmpty()) {
            System.out.println("No votes have been registered yet.");
            return;
        }

        Planet winningPlanet = Collections.max(votes.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("The winning planet for colonization is " + winningPlanet.getName() + " with " + votes.get(winningPlanet) + " weighted votes.");
    }

    // Add any other methods you need for managing the voting system
}
