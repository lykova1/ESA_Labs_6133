<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Books</title>
                <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
                      rel="stylesheet"/>

                <style>
                    body { background-color: #f8f9fa; }
                    .navbar { margin-bottom: 20px; }
                    h1 { margin-bottom: 20px; }
                </style>
            </head>

            <body>
                <!-- Навбар -->
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                    <div class="container">
                        <a class="navbar-brand" href="#">Library</a>
                        <div class="navbar-nav">
                            <a class="nav-link active" href="/api/books">Books</a>
                            <a class="nav-link" href="/api/authors">Authors</a>
                        </div>
                    </div>
                </nav>

                <div class="container">

                    <h1>Books</h1>

                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Title</th>
                                <th>Author</th>
                                <th>Genre</th>
                                <th>Publish year</th>
                                <th>Pages</th>
                                <th>ISBN</th>
                            </tr>
                        </thead>

                        <tbody>
                            <xsl:for-each select="*/item">
                                <tr>
                                    <td><xsl:value-of select="id"/></td>
                                    <td><xsl:value-of select="title"/></td>
                                    <td><xsl:value-of select="authorFullName"/></td>
                                    <td><xsl:value-of select="genre"/></td>
                                    <td><xsl:value-of select="publishYear"/></td>
                                    <td><xsl:value-of select="pages"/></td>
                                    <td><xsl:value-of select="isbn"/></td>
                                </tr>
                            </xsl:for-each>
                        </tbody>
                    </table>
                </div>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
