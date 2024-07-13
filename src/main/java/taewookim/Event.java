package taewookim;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Event implements Listener {

    @EventHandler
    public void dig(BlockBreakEvent e) {
        Block b = e.getBlock();
        Interaction inter = (Interaction) b.getWorld().spawnEntity(b.getLocation().add(0.5,0.5,0.5), EntityType.INTERACTION);
        inter.setInteractionHeight(1.1f);
        inter.setInteractionWidth(1.1f);
        Data.add(e.getPlayer(), new SubData(inter, 20));
        Player p = e.getPlayer();
        SubData data = Data.get(p);
        p.sendTitle(data.count + "/" + data.maxcount, "");
        e.setCancelled(true);
    }

    @EventHandler
    public void inter(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
        SubData data = Data.get(p);
        if(data==null) {
            return;
        }
        if(data.interaction.equals(e.getRightClicked())) {
            data.count--;
            if(data.count==0) {
                data.interaction.getLocation().getBlock().breakNaturally();
                Data.remove(p);
            }else {
                p.sendTitle(data.count + "/" + data.maxcount, "", 0, 5, 0);
            }
        }
    }

    @EventHandler
    public void quit(PlayerQuitEvent e) {
        Data.remove(e.getPlayer());
    }

    @EventHandler
    public void move(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        SubData data = Data.get(p);
        if(data==null) {
            return;
        }
        if(data.interaction.getLocation().distance(p.getLocation())>4) {
            Data.remove(p);
        }
    }

}
