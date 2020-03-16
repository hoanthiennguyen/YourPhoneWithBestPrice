/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PhuCV
 */
public class EscapseHTMLUtils {

    private static final HashMap<String, String> htmlEncodeChars = new HashMap<>();

    static {
        // https://hocwebchuan.com/reference/tag/symbols.php
        htmlEncodeChars.put("&euro;", "€");
        htmlEncodeChars.put("&nbsp;", " ");
        htmlEncodeChars.put("&#193;", "Á");
        htmlEncodeChars.put("&#225;", "á");
        htmlEncodeChars.put("&#194;", "Â");
        htmlEncodeChars.put("&#226;", "â");
        htmlEncodeChars.put("&#180;", "´");
        htmlEncodeChars.put("&AElig;", "Æ");
        htmlEncodeChars.put("&aelig;", "æ");
        htmlEncodeChars.put("&#192;", "À");
        htmlEncodeChars.put("&#224;", "à");
        htmlEncodeChars.put("&#197;", "Å");
        htmlEncodeChars.put("&#195;", "Ã");
        htmlEncodeChars.put("&#229;", "å");
        htmlEncodeChars.put("&#227;", "ã");
        htmlEncodeChars.put("&#196;", "Ä");
        htmlEncodeChars.put("&#228;", "ä");
        htmlEncodeChars.put("&brvbar;", "¦");
        htmlEncodeChars.put("&Ccedil;", "Ç");
        htmlEncodeChars.put("&ccedil;", "ç");
        htmlEncodeChars.put("&cedil;", "¸");
        htmlEncodeChars.put("&cent;", "¢");
        htmlEncodeChars.put("&circ;", "ˆ");
        htmlEncodeChars.put("&copy;", "©");
        htmlEncodeChars.put("&curren;", "¤");
        htmlEncodeChars.put("&deg;", "°");
        htmlEncodeChars.put("&divide;", "÷");
        htmlEncodeChars.put("&#201;", "É");
        htmlEncodeChars.put("&#233;", "é");
        htmlEncodeChars.put("&#202;", "Ê");
        htmlEncodeChars.put("&#234;", "ê");
        htmlEncodeChars.put("&#200;", "È");
        htmlEncodeChars.put("&#200;", "è");
        htmlEncodeChars.put("&#208;", "Ð");
        htmlEncodeChars.put("&#208;", "ð");
        htmlEncodeChars.put("&#235;", "Ë");
        htmlEncodeChars.put("&#235;", "ë");
        htmlEncodeChars.put("&euro;", "€");
        htmlEncodeChars.put("&fnof;", "ƒ");
        htmlEncodeChars.put("&frac12;", "½");
        htmlEncodeChars.put("&frac14;", "¼");
        htmlEncodeChars.put("&frac34;", "¾");
        htmlEncodeChars.put("&#205;", "Í");
        htmlEncodeChars.put("&#237;", "í");
        htmlEncodeChars.put("&Icirc;", "Î");
        htmlEncodeChars.put("&icirc;", "î");
        htmlEncodeChars.put("&#161;", "¡");
        htmlEncodeChars.put("&#204;", "Ì");
        htmlEncodeChars.put("&#236;", "ì");
        htmlEncodeChars.put("&iquest;", "¿");
        htmlEncodeChars.put("&Iuml;", "Ï");
        htmlEncodeChars.put("&iuml;", "ï");
        htmlEncodeChars.put("&laquo;", "«");
        htmlEncodeChars.put("&macr;", "¯");
        htmlEncodeChars.put("&micro;", "µ");
        htmlEncodeChars.put("&middot;", "¬");
        htmlEncodeChars.put("&Ntilde;", "Ñ");
        htmlEncodeChars.put("&ntilde;", "ñ");
        htmlEncodeChars.put("&#211;", "Ó");
        htmlEncodeChars.put("&#243;", "ó");
        htmlEncodeChars.put("&#212;", "Ô");
        htmlEncodeChars.put("&#244;", "ô");
        htmlEncodeChars.put("&OElig;", "Œ");
        htmlEncodeChars.put("&oelig;", "œ");
        htmlEncodeChars.put("&#210;", "Ò");
        htmlEncodeChars.put("&#242;", "ò");
        htmlEncodeChars.put("&ordf;", "ª");
        htmlEncodeChars.put("&ordm;", "º");
        htmlEncodeChars.put("&Oslash;", "Ø");
        htmlEncodeChars.put("&oslash;", "ø");
        htmlEncodeChars.put("&Otilde;", "Õ");
        htmlEncodeChars.put("&otilde;", "õ");
        htmlEncodeChars.put("&Ouml;", "Ö");
        htmlEncodeChars.put("&ouml;", "ö");
        htmlEncodeChars.put("&para;", "¶");
        htmlEncodeChars.put("&plusmn;", "±");
        htmlEncodeChars.put("&pound;", "£");
        htmlEncodeChars.put("&raquo;", "»");
        htmlEncodeChars.put("&reg;", "®");
        htmlEncodeChars.put("&Scaron;", "Š");
        htmlEncodeChars.put("&scaron;", "š");
        htmlEncodeChars.put("&sect;", "§");
        htmlEncodeChars.put("&sup1;", "¹");
        htmlEncodeChars.put("&sup2;", "²");
        htmlEncodeChars.put("&sup3;", "³");
        htmlEncodeChars.put("&szlig;", "ß");
        htmlEncodeChars.put("&THORN;", "Þ");
        htmlEncodeChars.put("&thorn;", "þ");
        htmlEncodeChars.put("&tilde;", "˜");
        htmlEncodeChars.put("&times;", "×");
        htmlEncodeChars.put("&#218;", "Ú");
        htmlEncodeChars.put("&#250;", "ú");
        htmlEncodeChars.put("&Ucirc;", "Û");
        htmlEncodeChars.put("&ucirc;", "û");
        htmlEncodeChars.put("&#217;", "Ù");
        htmlEncodeChars.put("&#249;", "ù");
        htmlEncodeChars.put("&uml;", "¨");
        htmlEncodeChars.put("&#221;", "Ý");
        htmlEncodeChars.put("&#253;", "ý");
        htmlEncodeChars.put("&yen;", "¥");
        htmlEncodeChars.put("&ndash;", "–");
        htmlEncodeChars.put("&mdash;", "—");
        htmlEncodeChars.put("&lsquo;", "‘");
        htmlEncodeChars.put("&rsquo;", "’");
        htmlEncodeChars.put("&sbquo;", "‚");
        htmlEncodeChars.put("&ldquo;", "“");
        htmlEncodeChars.put("&rdquo;", "”");
        htmlEncodeChars.put("&lsaquo;", "‹");
        htmlEncodeChars.put("&rsaquo;", "›");
        htmlEncodeChars.put("&bull;", "•");
        htmlEncodeChars.put("&hellip;", "…");
        htmlEncodeChars.put("&oline;", "‾");
        htmlEncodeChars.put("&frasl;", "⁄");

    }

    private EscapseHTMLUtils() {
    }

    public static String encodeHtml(String source) {
        return encode(source, htmlEncodeChars);
    }

    private static String encode(String source, HashMap<String, String> encodingTable) {
        if (null == source) {
            return null;
        }

        if (null == encodingTable) {
            return source;
        }
        return recursive(source, encodingTable);
    }

    private static String recursive(String source, HashMap<String, String> encodingTable) {
        boolean check = false;
        for (Map.Entry<String, String> entry : encodingTable.entrySet()) {
            String key = entry.getKey();
            if (source.contains(key)) {
                check = true;
                source = source.replace(entry.getKey(), entry.getValue());
            }
        }
        if (!check) {
            return source;
        }
        return recursive(source, encodingTable);
    }
    public static void main(String[] args) {
        System.out.println(encodeHtml("Realme 5 Pro - R8/128GB - Ch&#237;nh h&#227;ng Blue - TBH- 222 Trần Ph&#250;, Thanh H&#243;a"));
    }
}
