<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <phones>
            <xsl:for-each select="//ul[@class='homeproduct']/li">
                <phone>
                    <name>
                        <xsl:value-of select="a/h3"></xsl:value-of>
                    </name>
                    <price>
                        <xsl:variable name="rawText" select="a/div/strong"/>
                        <xsl:variable name="textPrice" select="substring-after($rawText,':')"/>
                        <xsl:variable name="removedDotPrice" select="translate($textPrice, '.', '')"></xsl:variable>
                        <xsl:variable name="removeUnit" select="substring($removedDotPrice, 1, string-length($removedDotPrice)-1)"/>
                        <xsl:variable name="trimPrice" select="normalize-space($removeUnit)"></xsl:variable>
                        <xsl:value-of select="$trimPrice"></xsl:value-of>
                    </price>
                    <link>
                        <xsl:value-of select="concat('https://www.dienthoaisieure.com', a/@href)"></xsl:value-of>
                    </link>
                    <img>
                        <xsl:value-of select="a/img/@data-original"/>
                    </img>
                </phone>
            </xsl:for-each>
        </phones>
    </xsl:template>

</xsl:stylesheet>
                