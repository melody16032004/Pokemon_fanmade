
package main;

import java.util.Random;


public class ListPokemon {
    private int no;
    private String name;
    private String type;
    private int height;
    private int weight;

    private String evYield;
    private int baseExp;
    private String growthRate;

    private int hp;
    private int atk;
    private int def;
    private int spAtk;
    private int spDef;
    private int speed;

    private int[] ivs;
    private int[] evs;

    private int levelToNextRank;
    private int condition;
    private Tuple<Integer,Integer> narute;
    private int level;
    private double num1;
    private double num2;
    private int num3;

    public ListPokemon(int no, String name, String type, int height, int weight,
                       String evYield, int baseExp, String growthRate, 
                       int hp, int atk, int def, int spAtk, int spDef, int speed,int iv,int ev,int lv , Tuple<Integer,Integer> nature) {
        this.no = no;
        this.name = name;
        this.type = type;
        this.height = height;
        this.weight = weight;
        this.evYield = evYield;
        this.baseExp = baseExp;
        this.growthRate = growthRate;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spAtk = spAtk;
        this.spDef = spDef;
        this.speed = speed;

        this.ivs = new int{iv,iv,iv,iv,iv,iv};
        this.evs = new int[]{ev,ev,ev,ev,ev,ev};
        this.level = lv;
        this nature = nature;

        this.levelToNextRank = 0;
        this.setCondition(0);

        this.num1 = 0;
        this.num2 = 0;
        this.num3 = 0;
    }
    public ListPokemon(

    // Getters and Setters
    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public String getEvYield() { return evYield; }
    public void setEvYield(String evYield) { this.evYield = evYield; }

    public int getBaseExp() { return baseExp; }
    public void setBaseExp(int baseExp) { this.baseExp = baseExp; }

    public String getGrowthRate() { return growthRate; }
    public void setGrowthRate(String growthRate) { this.growthRate = growthRate; }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getAtk() { return atk; }
    public void setAtk(int atk) { this.atk = atk; }

    public int getDef() { return def; }
    public void setDef(int def) { this.def = def; }

    public int getSpAtk() { return spAtk; }
    public void setSpAtk(int spAtk) { this.spAtk = spAtk; }

    public int getSpDef() { return spDef; }
    public void setSpDef(int spDef) { this.spDef = spDef; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public int getLevelToNextRank() { return levelToNextRank; }
    public void setLevelToNextRank(int levelToNextRank) { this.levelToNextRank = levelToNextRank; }

    public int getCondition() { return condition; }
    public final void setCondition(int condition) {
        if (condition >= 0 && condition <= 2) {
            this.condition = condition;
        } else {
            this.condition = 0; // Default to 0 if invalid value
        }
    }

    public int[] getIvs() { return ivs; }
    public void setIvs(int[] ivs) { this.ivs = ivs; }

    public int[] getEvs() { return evs; }
    public void setEvs(int[] evs) { 
        int totalEVs = getTotalEVs();
        if (totalEVs > 510) {
            int excess = totalEVs - 510;
            for (int i = 0; i < evs.length && excess > 0; i++) {
                int reduce = Math.min(evs[i], excess);
                evs[i] -= reduce;
                excess -= reduce;
            }
        }
        this.evs = evs; 
    }

    public int getTotal() {
        return hp + atk + def + spAtk + spDef + speed;
    }

    public int getTotalEVs() {
        int totalEVs = 0;
        for (int ev : evs) {
            totalEVs += ev;
        }
        return totalEVs;
    }

    public String getConditionDescription(int condition) {
        return switch (condition) {
            case 0 -> "levelUP";
            case 1 -> "give_item";
            case 2 -> "learn_move";
            default -> "unknown";
        };
    }

    public int[] getGrowthStat(int[] evs) {
        int[] growth = new int[6];
        for (int i = 0; i < evs.length; i++) {
            growth[i] = evs[i] / 4; // +1 stat for every 4 EVs
        }
        return growth;
    }
}

