<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <c:if test="${ !empty sessionScope.adresse }">

                    <li class="nav-item active">
                        <a class="nav-link" href="index.do">acceuil</a>
                    </li>
                    <li class="nav-item active">
                        <pre>  </pre>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="saisie.do">ajouter</a>
                    </li>
                    <li class="nav-item active">
                        <pre>  </pre>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout.do">déconnexion</a>
                    </li>

                </c:if>
                <c:if test="${ !empty sessionScope.adrs && empty sessionScope.adresse}">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.do">Gestion des utilisateurs</a>
                    </li>
                    <li class="nav-item active">
                        <pre>  </pre>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../user/adduser.do">Ajouter un utilisateur</a>
                    </li>
                    <li class="nav-item active">
                        <pre>  </pre>
                    </li>

                    <li class="nav-item ">
                        <a class="nav-link" href="index.do">Gestion des produits</a>
                    </li>
                    <li class="nav-item active">
                        <pre>  </pre>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/CRUD/produit/saisie.do">Ajouter un produit</a>
                    </li>
                    <li class="nav-item active">
                        <pre>  </pre>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../change.jsp">change login</a>
                    </li>
                    <li class="nav-item active">
                        <pre>  </pre>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/CRUD/user/logoutuser.do">Déconnexion</a>
                    </li>

                </c:if>
            </ul>
        </div>
    </nav>