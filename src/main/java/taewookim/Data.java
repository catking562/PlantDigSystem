package taewookim;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Data {

    private static Map<Player, SubData> datas = new HashMap<>();

    public static void add(Player p, SubData subdata) {
        datas.put(p, subdata);
    }

    public static SubData get(Player p) {
        return datas.get(p);
    }

    public static void remove(Player p) {
        SubData data = get(p);
        data.interaction.remove();
        datas.remove(p);
    }

}
