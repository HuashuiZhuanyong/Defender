package hszy.ydy.defender;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MyListener implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event)// 监听实体攻击实体事件
    {
        if(event.getEntity() instanceof Player)//是玩家
        {
            //检测手里盾的附魔
            if((((Player) event.getEntity()).getInventory().getItemInMainHand().getType() == Material.SHIELD)||(((Player) event.getEntity()).getInventory().getItemInOffHand().getType() == Material.SHIELD))
            {
                if((((Player) event.getEntity()).getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 4)||(((Player) event.getEntity()).getInventory().getItemInOffHand().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 4))
                {
                    //举盾完成
                    if(((Player) event.getEntity()).isBlocking() == true)
                    {
                        //必须挡住伤害
                        if (event.getFinalDamage() == 0) {
                            //反弹伤害
                            ((Damageable) event.getDamager()).damage(event.getOriginalDamage(EntityDamageEvent.DamageModifier.BLOCKING) * Defender.dmg, event.getEntity());
                            //破盾伤害
                            if (Defender.undfd != 0)
                                event.setDamage(event.getOriginalDamage(EntityDamageEvent.DamageModifier.BLOCKING) * Defender.undfd);
                        }
                    }
                }
            }
        }
    }
}
