
package com.example.planet_voting;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class VotingAppGUI extends Application {
    private static final Set<String> votedIds = new HashSet<>();
    private static final Map<String, Integer> votes = new HashMap<>();
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        primaryStage.setTitle("Intergalactic Voting System");
        new StartWindow(primaryStage).show();
    }

    public static boolean addVotedId(String id) {
        return votedIds.add(id);
    }

//    public static void recordVote(String planet) {
//        votes.merge(planet, 1, Integer::sum);
//        System.out.println("Votes: " + votes);
//    }

    public static void recordVote(String planet, int voteWeight) {
        votes.merge(planet, voteWeight, Integer::sum);
        System.out.println("Votes: " + votes);
    }
    public static boolean hasVoted(String voterId) {
        return votedIds.contains(voterId);
    }

    public static String getLeadingPlanet() {
        return votes.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No votes yet");
    }

    // Method to get a summary of current votes
    public static String getVotesSummary() {
        if (votes.isEmpty()) {
            return "No votes have been cast yet.";
        }
        return votes.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue() + " vote(s)")
                .collect(Collectors.joining("\n"));
    }

    // Method to get final results with percentage
    public static Map<String, Double> getFinalResults() {
        Map<String, Double> finalResults = new HashMap<>();
        int totalVotes = votes.values().stream().mapToInt(Integer::intValue).sum();

        if (totalVotes > 0) {
            votes.forEach((planet, count) -> {
                double percentage = (count * 100.0) / totalVotes;
                finalResults.put(planet, percentage);
            });
        }

        return finalResults;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
