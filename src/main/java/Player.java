public class Player {
    private String name;
    private int age;
    private int worldRanking;
    private String description;

    /**
     * Player constructor.
     *
     * @param pName name of the player e.g. "Kosoric Luka"
     * @param pAge player age.
     * @param pRanking player ranking.
     * @param pDescription player description.
     */
    public Player(String pName, int pAge, int pRanking, String pDescription) {
        this.name = pName;
        this.age = pAge;
        this.worldRanking = pRanking;
        this.description = pDescription;
    }

    public int getAge () {
        return this.age;
    }

    public int getWorldRanking () {
        return this.worldRanking;
    }

    public String getDescription () {
        return this.description;
    }

    public String getName () {
        return this.name;
    }
}
