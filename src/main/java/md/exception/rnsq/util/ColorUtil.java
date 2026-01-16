package md.exception.rnsq.util;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Exception_
 * @since 15 янв. 2026 г.
 */
public final class ColorUtil {

    private ColorUtil(){}

    public static String color(String text) {
        if (text == null) return "";

        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

        for(Matcher matcher = pattern.matcher(text); matcher.find(); matcher = pattern.matcher(text)) {
            String hexCode = text.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');
            StringBuilder builder = new StringBuilder();
            replaceSharp.chars().forEach((c) -> {
                builder.append("&").append((char)c);
            });
            text = text.replace(hexCode, builder.toString());
        }

        return ChatColor.translateAlternateColorCodes('&', text).replace("&", "");
    }
}

