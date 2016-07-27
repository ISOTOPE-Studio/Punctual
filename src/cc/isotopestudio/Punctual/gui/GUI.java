package cc.isotopestudio.Punctual.gui;

import cc.isotopestudio.Punctual.Punctual;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;

public abstract class GUI implements Listener {

    // From: https://bukkit.org/threads/icon-menu.108342

    final String name;
    final int size;
    private Plugin plugin;
    String[] optionNames;
    ItemStack[] optionIcons;
    private final boolean willDestory;

    GUI(String name, int size) {
        this.name = name + getPassword();
        this.size = size;
        this.plugin = Punctual.plugin;
        this.optionNames = new String[size];
        this.optionIcons = new ItemStack[size];
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        willDestory = true;
    }

    GUI setOption(int position, ItemStack icon) {
        optionNames[position] = icon.getItemMeta().getDisplayName();
        optionIcons[position] = setItemNameAndLore(icon, icon.getItemMeta().getDisplayName(), icon.getItemMeta().getLore());
        return this;
    }

    public void open(Player player) {
        Inventory inventory = Bukkit.createInventory(player, size, name);
        for (int i = 0; i < optionIcons.length; i++) {
            if (optionIcons[i] != null) {
                inventory.setItem(i, optionIcons[i]);
            }
        }
        player.openInventory(inventory);
    }

    private void Destory() {
        HandlerList.unregisterAll(this);
        plugin = null;
        optionNames = null;
        optionIcons = null;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent event) {

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getTitle().equals(name)) {
            if (willDestory) {
                Destory();
            }
        }
    }

    private ItemStack setItemNameAndLore(ItemStack item, String name, List<String> lore) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        im.setLore(lore);
        item.setItemMeta(im);
        return item;
    }

    private static String getPassword() {
        String result = "";
        for (int i = 0; i < 5; i++) {
            result += ChatColor.values()[(int) (Math.random() * 20)];
        }
        return result;
    }
}