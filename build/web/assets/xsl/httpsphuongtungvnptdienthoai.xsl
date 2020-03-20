<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : httpsphuongtungvnptdienthoai.xsl
    Created on : March 20, 2020, 2:59 PM
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
            <xsl:for-each select="//div[contains(@class, 'ty-column3')]">
                <phone>
                    <name>
                        <xsl:value-of select="a//div[@class = 'product-title']"></xsl:value-of>
                    </name>
                    <price>
                        <xsl:variable name="textPrice" select="a//span[@class='ty-price-num']"></xsl:variable>
                        <xsl:variable name="removedDotPrice" select="translate($textPrice, '.', '')"></xsl:variable>
                        <xsl:variable name="removeUnit" select="substring($removedDotPrice, 0, string-length($removedDotPrice) - 1)"></xsl:variable>
                        <xsl:variable name="trimPrice" select="normalize-space($removeUnit)"></xsl:variable>
                        <xsl:value-of select="$trimPrice"></xsl:value-of>
                    </price>
                    <link>
                        <xsl:value-of select="a/@href"></xsl:value-of>
                    </link>
                </phone>
            </xsl:for-each>
        </phones>
    </xsl:template>

</xsl:stylesheet>
