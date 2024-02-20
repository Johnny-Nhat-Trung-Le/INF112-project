package inf112.skeleton.app.model;

public record Durability(int remaining, int maximum) {
    @Override
    public String toString(){
        return remaining + "/" + maximum;
    }
}
