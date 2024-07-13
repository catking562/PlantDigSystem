package taewookim;

import org.bukkit.entity.Interaction;

public class SubData {

    public SubData(Interaction interaction, int count) {
        this.interaction = interaction;
        this.count = count;
        this.maxcount = count;
    }

    public Interaction interaction;
    public int count;
    public final int maxcount;

}
