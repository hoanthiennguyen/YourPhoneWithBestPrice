<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : httpswwwmainguyenvndienthoaifilter.xsl
    Created on : March 21, 2020, 8:27 AM
    Author     : thien
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <phones>
            <xsl:for-each select="//div[contains(@class, 'col-md-3 col-sm-3 col-xs-3 item-prod')]">
                <phone>
                    <name>
                        <xsl:value-of select="div//*[contains(@class, 'prod_item_name')]"></xsl:value-of>
                    </name>
                    <price>
                        <xsl:variable name="textPrice" select="div//*[contains(@class, 'prod_item_price')]"></xsl:variable>
                        <xsl:variable name="removedDotPrice" select="translate($textPrice, '.', '')"></xsl:variable>
                        <xsl:variable name="addUnit" select="concat($removedDotPrice, 'đ')"></xsl:variable>
                        <xsl:variable name="removeUnit" select="substring-before($addUnit, 'đ')"></xsl:variable>
                        <xsl:variable name="trimPrice" select="normalize-space($removeUnit)"></xsl:variable>
                        <xsl:value-of select="$trimPrice"></xsl:value-of>
                    </price>
                    <link>
                        <xsl:value-of select="concat('https://www.mainguyen.vn',div/a/@href)"></xsl:value-of>
                    </link>
                    <img>
                        <xsl:value-of select="concat('https://www.mainguyen.vn',div//img/@data-original)"/>
                    </img>
                </phone>
            </xsl:for-each>
        </phones>
    </xsl:template>

</xsl:stylesheet>
