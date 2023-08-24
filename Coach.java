public class Coach {
    //Data members
    private String name;
    private int id;
    private Team team;

    //Constructor
    Coach(String name, int id){
        this.name = name;
        this.id = id;
    }

    //Setters and getters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
}
