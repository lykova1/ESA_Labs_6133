<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Authors</title>
                <link rel="stylesheet"
                      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
            </head>
            <body>
                <!-- Навбар -->
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
                    <div class="container">
                        <a class="navbar-brand" href="#">Library</a>

                        <div class="navbar-nav">
                            <a class="nav-link" href="/api/books">
                                Books
                            </a>
                            <a class="nav-link active" href="/api/authors">
                                Authors
                            </a>
                        </div>
                    </div>
                </nav>

                <div class="container mt-4">
                    <h1 class="mb-4">Authors</h1>

                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Birth year</th>
                                <th>Country</th>
                            </tr>
                        </thead>

                        <tbody>
                            <xsl:for-each select="*/item">
                                <tr>
                                    <td><xsl:value-of select="id"/></td>
                                    <td><xsl:value-of select="firstName"/></td>
                                    <td><xsl:value-of select="lastName"/></td>
                                    <td><xsl:value-of select="birthYear"/></td>
                                    <td><xsl:value-of select="country"/></td>
                                </tr>
                            </xsl:for-each>
                        </tbody>
                    </table>
                </div>

                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
