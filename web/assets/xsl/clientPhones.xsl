<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : clientPhones.xsl
    Created on : March 23, 2020, 3:30 PM
    Author     : thien
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/phones">
        <table>
            <thead>
                <tr>
                    <th id="imgHeader">Image</th>
                    <th >Name</th>
                    <th id="priceHeader">Price</th>
                    <th >Website</th>
                    <th>Link to details</th>
                </tr>
            </thead>
            <tbody>
                <xsl:for-each select="//phone">
                    <tr>
                        <td>
                            <img>
                                <xsl:attribute name="src">
                                    <xsl:value-of select="img"/>
                                </xsl:attribute>
                            </img>
                        </td>
                        <td><xsl:value-of select="name"></xsl:value-of></td>
                        <td>
                            <xsl:variable name="intPrice" select="price"/>
                            <xsl:variable name="n" select="string-length($intPrice)"/>
                            <xsl:choose>
                                <xsl:when test="$n > 6">
                                    <xsl:variable name="part_1" select="substring($intPrice,1,($n)-6)"/>
                                    <xsl:variable name="part_2" select="substring($intPrice,($n)-5,3)"/>
                                    <xsl:variable name="part_3" select="substring($intPrice,($n)-2,3)"/>
                                    <xsl:value-of select="concat($part_1, '.', $part_2, '.', $part_3, ' ₫')"/>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:variable name="part_1" select="substring($intPrice,1,($n)-3)"/>
                                    <xsl:variable name="part_2" select="substring($intPrice,($n)-2,3)"/>
                                    <xsl:value-of select="concat($part_1, '.', $part_2, ' ₫')"/>
                                </xsl:otherwise>
                            </xsl:choose>
                        </td>
                        <td><xsl:value-of select="website"></xsl:value-of></td>
                        <td>
                            <a target="_blank">
                                <xsl:attribute name="href">
                                    <xsl:value-of select="link"/>
                                </xsl:attribute>
                                Link
                            </a>
                        </td>
                        
                    </tr>
                </xsl:for-each>
            </tbody>
        </table>
    </xsl:template>
    <xsl:template match="/names">
        <xsl:for-each select="//name">
            <h3><xsl:value-of select="."></xsl:value-of></h3>
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>
