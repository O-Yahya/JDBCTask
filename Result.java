import java.util.ArrayList;
import java.util.Random;

//Class containing methods for generating results for a match
public class Result {
    int id;
    //Data members
    Match match;
    Team winner;
    Team loser;
    String score;
    ArrayList<Player> scorers = new ArrayList<>();

    Result(Match match){
        this.match = match;
    }

    //Generate method to simulate match randomly
    public void generate(){
        Random r = new Random();
        //Generating random number of goals for each team and assigning winner and loser accordingly
        int homeGoals = r.nextInt(10);
        int awayGoals = r.nextInt(10);
        if (homeGoals == awayGoals){
            winner = match.getHome();
            loser = match.getAway();
            winner.draws += 1;
            loser.draws += 1;
        }
        else if (homeGoals > awayGoals){
            winner = match.getHome();
            loser = match.getAway();
            winner.wins += 1;
            loser.losses += 1;
        }
        else{
            winner = match.getAway();
            loser = match.getHome();
            winner.wins += 1;
            loser.losses += 1;
        }

        //Calculating new point totals after match
        winner.calcPoints();
        loser.calcPoints();

        //Randomly choosing goal scorers for each team according to winner and storing in scorers array list
        int x = 0;
        if (winner.equals(match.getHome())){
            score = String.valueOf(homeGoals) + "-" + String.valueOf(awayGoals);
            for (int i = 0; i<homeGoals; i++){
                x = r.nextInt(winner.getPlayers().size());
                Player scorer = winner.getPlayers().get(x);
                scorers.add(scorer);
            }
            for (int i = 0; i<awayGoals; i++){
                x = r.nextInt(loser.getPlayers().size());
                Player scorer = loser.getPlayers().get(x);
                scorers.add(scorer);
            }
        }
        else{
            for (int i = 0; i<awayGoals; i++){
                x = r.nextInt(winner.getPlayers().size());
                Player scorer = winner.getPlayers().get(x);
                scorers.add(scorer);
            }
            for (int i = 0; i<homeGoals; i++){
                x = r.nextInt(loser.getPlayers().size());
                Player scorer = loser.getPlayers().get(x);
                scorers.add(scorer);
            }
            score = String.valueOf(awayGoals) + "-" + String.valueOf(homeGoals);
        }


    }

    //Method to show match result details
    public String toString(){
        StringBuilder s = new StringBuilder("\n" + match.getHome().getName() + " V " + match.getAway().getName() + "\n" + match.getTime() + "\n" + match.getDate() + "\n" + score + "\n" + "Winner: " + winner.getName() + "\nScorers: ");
        for (Player scorer : scorers){
            s.append("\n").append(scorer.getName());
        }
        return String.valueOf(s);
    }
}
