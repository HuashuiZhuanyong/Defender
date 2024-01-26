package hszy.ydy.defender;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class MyListener implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event)// 监听实体攻击实体事件
    {
        if(event.getEntity() instanceof Player)//是玩家
        {
            //受害者主手物品
            ItemStack mainHandItem = ((Player) event.getEntity()).getInventory().getItemInMainHand();
            //受害者副手物品
            ItemStack offHandItem =  ((Player) event.getEntity()).getInventory().getItemInOffHand();

            Player damagee = (Player) event.getEntity();

            //检测被打的人主副手物品有无保护四附魔盾
            if(((mainHandItem.getType() == Material.SHIELD && mainHandItem.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 4))||(offHandItem.getType() == Material.SHIELD && offHandItem.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 4))
            {
                //举盾完成
                if(damagee.isBlocking())
                {
                    //举盾的0.25秒之后才能挡住伤害
                    if (event.getFinalDamage() == 0) {
                        //打他的实体
                        Damageable damager = (Damageable) event.getDamager();
                        //反弹伤害
                        damager.damage(event.getDamage() * Defender.dmg, damagee);
                        //破盾伤害
                        if (Defender.undfd != 0) {

                            double finalHealth = (damagee.getHealth() - event.getDamage()) * Defender.undfd;
                            if (finalHealth > 0)
                                damagee.setHealth(finalHealth);
                        }
                    }
                }
            }
        }
    }
}
