<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : bachlong.xsl
    Created on : March 14, 2020, 4:59 PM
    Author     : thien
    Description:
        Purpose of transformation follows.
-->

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
