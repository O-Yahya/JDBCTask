public class Match {
    int id;
    //Data members
    private Team home;
    private Team away;
    private String time;
    private String date;
    private Result result;

    //Constructors
    Match(){};
    Match(Team home, Team away, String time, String date){
        this.home = home;
        this.away = away;
        this.time = time;
        this.date = date;
        result = new Result(this);
        result.generate();
    }

    //Setters and getters
    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String toString(){
        return result.toString();
    }

    public String getDate() {
        return date;
    }

    public Result getResult(){return result;}
}
