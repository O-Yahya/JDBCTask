import java.util.ArrayList;

public class Team {
    //Data members
    private String name;
    private int id;
    private ArrayList<Player> players;
    int wins;
    int draws;
    int losses;
    int points;
    private Coach coach;

    //Constructors
    Team(){};
    Team(String name, int id, ArrayList<Player> players, int wins, int draws, int losses){
        this.name = name;
        this.id = id;
        this.players = players;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
    }

    //Setters and getters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getPoints(){
        return points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCoach(Coach coach){
        this.coach = coach;
    }
    public Coach getCoach(){
        return coach;
    }

    //Method to calculate point total
    public void calcPoints(){
        points = wins * 3 + draws;
    }


}
