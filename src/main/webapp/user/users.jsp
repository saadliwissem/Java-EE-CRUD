<%@ page language="java" contentType="text/html; charset=windows-1256"
 pageEncoding="windows-1256"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
            <title>users</title>
            <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        </head>

        <body>
            <%@include file="header.jsp" %>
                <p></p>
                <div class="container">
                    <div class="card">
                        <div class="card-header">
                            Recherche des utilisateurs
                        </div>
                        <div class="card-body">
                            <form action="chercheruser.do" method="get">
                                <label>nom </label>
                                <input type="text" name="name" value="${modele.name}" />
                                <button type="submit" class="btn btn-primary">Chercher</button>
                            </form>
                            <table class="table table-striped">
                                <tr>
                                    <th>ID</th>
                                    <th>Nom d'utilisateur</th>
                                    <th>adresse</th>
                                    <th>mot de passe</th>
                                </tr>
                                <c:forEach items="${modele.users}" var="u">
                                    <tr>
                                        <td>${u.id}</td>
                                        <td>${u.name}</td>
                                        <td>${u.adresse }</td>
                                        <td>${u.pwd }</td>
                                        <td><a onclick="return confirm('Etes-vous sûr ?')" href="supprimeruser.do?id=${u.id }">Supprimer</a></td>
                                        <td><a href="editeruser.do?adresse=${u.adresse}">modifier</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
        </body>

        </html>