<%-- 
    Document   : addNewWebsite
    Created on : Mar 31, 2020, 8:13:24 PM
    Author     : thien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Best price</title>
        <link href="./assets/css/addNewWebsite.css" rel="stylesheet" type="text/css">
        <link rel="icon" href="./assets/img/icon.png" type="image/x-icon">
        <script src="./assets/js/addNewWebsite.js"></script>
    </head>
    <body>
        <a href="admin.jsp">Back to crawl page</a>
        <div>
            <h1>Add new website</h1>
            <label>Website</label> <input id="website"/> <br/>
            <label>Subpage</label> <textarea id="subpages" rows="10" cols="30" placeholder="Subpages separate by enter">
            
            </textarea>
        </div>
        <div id="view">
            <div id="xslContainer">
                XSL <br/>
                <textarea id="xsl" placeholder="Remember, be nice!" cols="120" rows="30">
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <phones>
            <xsl:for-each select="//li[contains(@class, 'item')]">
                <phone>
                    <name>
                        <xsl:value-of select="a/*[@class='product-name']"></xsl:value-of>
                    </name>
                    <price>
                        <xsl:variable name="textPrice" select="a//*[@class='price']"></xsl:variable>
                        <xsl:variable name="removedDotPrice" select="translate($textPrice, '.', '')"></xsl:variable>
                        <xsl:variable name="removeUnit" select="substring($removedDotPrice, 0, string-length($removedDotPrice) - 1)"></xsl:variable>
                        <xsl:variable name="trimPrice" select="normalize-space($removeUnit)"></xsl:variable>
                        <xsl:value-of select="$trimPrice"></xsl:value-of>
                    </price>
                    <link>
                        <xsl:value-of select="a/@href"></xsl:value-of>
                    </link>
                    <img>
                        <xsl:choose>
                            <xsl:when test="a/img/@src">
                                <xsl:value-of select="a/img/@src"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:value-of select="a/img/@data-original"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </img>
                </phone>
            </xsl:for-each>
        </phones>
    </xsl:template>

</xsl:stylesheet>
                </textarea><br/>

                <button onclick="onTestTransform();">Test transform</button>
                <button onclick="onSave();">Save</button>
                <button onclick="onReset();">Reset</button>
            </div>
            <div id="xmlContainer">
                XML transform <br/>
                <textarea id="xml" disabled="disabled" rows="30" cols="60"></textarea>
            </div>

        </div>
    </body>
</html>
