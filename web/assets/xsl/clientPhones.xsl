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
                    <th>Name</th>
                    <th>Price</th>
                    <th>Link</th>
                </tr>
            </thead>
            <tbody>
                <xsl:for-each select="//phone">
                    <tr>
                        <td><xsl:value-of select="name"></xsl:value-of></td>
                        <td><xsl:value-of select="price"></xsl:value-of></td>
                        <td>
                            <a>
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
