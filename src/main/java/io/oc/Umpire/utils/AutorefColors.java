package io.oc.Umpire.utils;

import com.google.common.collect.ImmutableMap;
import org.bukkit.ChatColor;

import java.util.Map;

public class AutorefColors {
    public static final Map<String, ChatColor> TEAMS_OPTION_COLOR = ImmutableMap.<String, ChatColor>builder()
            .put("aqua", ChatColor.AQUA)
            .put("black", ChatColor.BLACK)
            .put("blue", ChatColor.BLUE)
            .put("bold", ChatColor.BOLD)
            .put("dark_aqua", ChatColor.DARK_AQUA)
            .put("dark_blue", ChatColor.DARK_BLUE)
            .put("dark_gray", ChatColor.DARK_GRAY)
            .put("dark_green", ChatColor.DARK_GREEN)
            .put("dark_purple", ChatColor.DARK_PURPLE)
            .put("dark_red", ChatColor.DARK_RED)
            .put("gold", ChatColor.GOLD)
            .put("gray", ChatColor.GRAY)
            .put("green", ChatColor.GREEN)
            .put("italic", ChatColor.ITALIC)
            .put("light_purple", ChatColor.LIGHT_PURPLE)
            .put("obfuscated", ChatColor.MAGIC) // This is the important line
            .put("red", ChatColor.RED)
            .put("reset", ChatColor.RESET)
            .put("strikethrough", ChatColor.STRIKETHROUGH)
            .put("underline", ChatColor.UNDERLINE)
            .put("white", ChatColor.WHITE)
            .put("yellow", ChatColor.YELLOW)
            .build();
}
