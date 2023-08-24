public class Player {
    //Data members
    private String name;
    private int age;
    private int kitNum;

    //Constructors
    Player(){};
    Player(String name, int age, int kitNum){
        this.name = name;
        this.age = age;
        this.kitNum = kitNum;
    }

    //Setters and getters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getKitNum() {
        return kitNum;
    }
    public void setKitNum(int kitNum) {
        this.kitNum = kitNum;
    }
}
