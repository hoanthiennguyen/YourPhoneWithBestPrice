<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <phones>
            <xsl:for-each select="//div[@class='motsanpham  ']">
                <phone>
                    <name>
                        <xsl:value-of select="div/div[@class='tieude-sanpham']/a"/>
                    </name>
                    <price>
                        <xsl:variable name="textPrice" select="div/div[@class='gia-sanpham']/span"/>
                        <xsl:variable name="removedDotPrice" select="translate($textPrice, '.', '')"/>
                        <xsl:variable name="removeUnit" select="substring-before($removedDotPrice,'#')"/>
                        <xsl:variable name="removeUnit2" select="substring($removeUnit,1, string-length($removeUnit) - 1)"/>
                        <xsl:variable name="trimPrice" select="normalize-space($removeUnit2)"/>
                        <xsl:value-of select="$trimPrice"/>
                    </price>
                    <link>
                        <xsl:value-of select="a/@href"/>
                    </link>
                    <img>
                        <xsl:variable name="style" select="div/div[@class='anhsanpham']/img/@style"/>
                        <xsl:variable name="urlWith" select="substring-after($style,'url(')"/>
                        <xsl:value-of select="substring-before($urlWith, ')')"/>
                    </img>
                </phone>
            </xsl:for-each>
        </phones>
    </xsl:template>

</xsl:stylesheet>