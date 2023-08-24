import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class League {
    //Data members
    int id;
    ArrayList<Team> teams = new ArrayList<>();
    String name;

    //Constructors
    League(String name, int id){
        this.name = name;
        this.id = id;
    }
    League(String name, ArrayList<Team> teams){
        this.name = name;
        this.teams = teams;
    }

    //Method to represent league standings after matches
    public String toString(){
        StringBuilder s = new StringBuilder("\n" + this.name + " Table");
        teams.sort(Comparator.comparing(Team::getPoints));

        int i = 1;
        for (int j = teams.size()-1; j>=0; j--){
            s.append("\n").append(String.valueOf(i)).append(" - ").append(teams.get(j).getName()).append(" ").append(teams.get(j).points).append(" pts");
            i++;
        }
        return String.valueOf(s);
    }

    //Method to randomly select 2 teams from league
    public Team [] selectTeams(){
        Random r = new Random();
        int x = r.nextInt(teams.size());
        int y = r.nextInt(teams.size());
        while (x == y){
            y = r.nextInt(teams.size());
        }
        return new Team [] {teams.get(x), teams.get(y)};
    }
}
