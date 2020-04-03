<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <phones>
            <xsl:for-each select="//div[contains(@class, 'product ')]">
                <phone>
                    <name>
                        <xsl:value-of select="div//*[@class='name']/h3"></xsl:value-of>
                    </name>
                    <price>
                        <xsl:variable name="textPrice" select="div//span[@class='price']"></xsl:variable>
                        <xsl:variable name="removedDotPrice" select="translate($textPrice, '.', '')"></xsl:variable>
                        <xsl:variable name="removeUnit" select="substring($removedDotPrice, 0, string-length($removedDotPrice))"></xsl:variable>
                        <xsl:variable name="trimPrice" select="normalize-space($removeUnit)"></xsl:variable>
                        <xsl:value-of select="$trimPrice"></xsl:value-of>
                    </price>
                    <link>
                        <xsl:value-of select="div/a/@href"></xsl:value-of>
                    </link>
                    <img>
                        <xsl:choose>
                            <xsl:when test="div//img/@src">
                                <xsl:value-of select="div//img/@src"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:value-of select="div//img/@data-src"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </img>
                </phone>
            </xsl:for-each>
        </phones>
    </xsl:template>

</xsl:stylesheet>