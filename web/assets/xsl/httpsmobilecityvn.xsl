<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : httpsmobilecityvn.xsl
    Created on : March 21, 2020, 2:34 PM
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
            <xsl:for-each select="//div[contains(@class, 'product-list-item')]">
                <phone>
                    <name>
                        <xsl:value-of select="div[@class='product-item-info']//p[contains(@class,'name')]/a"></xsl:value-of>
                    </name>
                    <price>
                        <xsl:variable name="textPrice" select="div[@class='product-item-info']//p[contains(@class, 'price')]"/>
                        <xsl:variable name="removedDotPrice" select="translate($textPrice, '.', '')"/>
                        <xsl:variable name="removeUnit" select="substring($removedDotPrice, 1, string-length($removedDotPrice) - 2)"/>
                        <xsl:variable name="trimPrice" select="normalize-space($removeUnit)"/>
                        <xsl:value-of select="number($trimPrice)"/>
                    </price>
                    <link>
                        <xsl:value-of select="div[@class='product-item-info']//p[contains(@class,'name')]/a/@href"></xsl:value-of>
                    </link>
                    <img>
                        <xsl:value-of select="div[@class='product-item-image']//img/@data-original"/>
                    </img>
                </phone>
            </xsl:for-each>
        </phones>
    </xsl:template>

</xsl:stylesheet>
