<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <phones>
            <xsl:for-each select="//div[@class='col-lg-25 col-xs-6 product']">
                <phone>
                    <name>
                        <xsl:value-of select="div[@class='hidden-md-down product-head-info']//span[@class='name truncate']"/>
                    </name>
                    <price>
                        <xsl:variable name="textPrice" select="div[@class='hidden-md-down product-head-info']//span[@class='price']"/>
                        <xsl:variable name="removedDotPrice" select="translate($textPrice, ',', '')"/>
                        <xsl:variable name="removeUnit" select="substring-before($removedDotPrice, '₫')"/>
                        <xsl:variable name="trimPrice" select="normalize-space($removeUnit)"/>
                        <xsl:value-of select="$trimPrice"/>
                    </price>
                    <link>
                        <xsl:value-of select="concat('https://mrbachkhoa.com', a/@href)"></xsl:value-of>
                    </link>
                    <img>
                        <xsl:value-of select="concat('http:', a//img/@src)"/>
                    </img>
                </phone>
            </xsl:for-each>
        </phones>
    </xsl:template>

</xsl:stylesheet>