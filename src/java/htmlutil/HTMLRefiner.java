/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmlutil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.EscapseHTMLUtils;
import util.FileUtil;

/**
 *
 * @author thien
 */
public class HTMLRefiner {

    public static String refineHtml(String src) {

        src = getBody(src);
        src = removeMiscellaneousTags(src);
        HTMLWellformer xmlSyntaxChecker = new HTMLWellformer();
        src = xmlSyntaxChecker.check(src);
        src = getBody(src);

        src = "<root>" + src + "</root>";
        return src;
    }

    private static String getBody(String src) {
        String result = src;
        String expression = "<body.*?</body>";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            result = matcher.group(0);
        }
        return result;

    }

    private static String removeMiscellaneousTags(String src) {
        String result = src;
        String expression = "<script.*?</script>";
        result = result.replaceAll(expression, "");

        expression = "<!--.*?-->";
        result = result.replaceAll(expression, "");

        expression = "&nbsp;?";
        result = result.replaceAll(expression, "");

        expression = "<br>";
        result = result.replaceAll(expression, "");
        return result;

    }

    public static String getXMLString(String urlString) throws IOException {
        URLConnection connection = setupConnection(urlString);
        String html = getExpectedString(connection.getInputStream());
        String unicodeFixed = EscapseHTMLUtils.encodeHtml(html);
        return refineHtml(unicodeFixed);

    }

    private static URLConnection setupConnection(String urlString) throws IOException {

        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setReadTimeout(20 * 1000);
        connection.setConnectTimeout(8 * 1000);
        connection.addRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        return connection;

    }

    private static String getExpectedString(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        try {
            String src = getXMLString("https://bachlongmobile.com/dien-thoai.html");
            FileUtil.writeToFile(src, "bachlong.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
