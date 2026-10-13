package sportspulse.model;

public class Player {
    private final int id;
    private final String name, team, position;
    private final double performance;
    private final int fitness;

    public Player(int id, String name, String team, String position, double performance, int fitness) {
        this.id=id; this.name=name; this.team=team; this.position=position;
        this.performance=performance; this.fitness=fitness;
    }
    public int getId(){ return id; }
    public String getName(){ return name; }
    public String getTeam(){ return team; }
    public String getPosition(){ return position; }
    public double getPerformance(){ return performance; }
    public int getFitness(){ return fitness; }
    @Override public String toString() {
        return "ID="+id+" | "+name+" | Team="+team+" | Position="+position+
               " | Performance="+performance+" | Fitness="+fitness+"/100";
    }
}
