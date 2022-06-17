package web;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

import dao.AdminDaoImpl;
import dao.DaoExceptions;
import dao.IProduitDao;
import dao.ProduitDaoImpl;
import dao.UserDaoImpl;
import metier.entities.Produit;
import metier.entities.User;
@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
IProduitDao metier;
UserDaoImpl user;
HttpSession session,adminsession;
AdminDaoImpl admin;
@Override
public void init() throws ServletException {
metier = new ProduitDaoImpl();
user = new UserDaoImpl();
admin = new AdminDaoImpl();

}
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
				String path=request.getServletPath();
				// chargement de la page de connexion pour l'admin.		
						 if(path.equals("/admin.do")) {
							request.getRequestDispatcher("admin.jsp").forward(request,response);
						}		
						
				
				
						 //vérification des cordonnées de l'admin.
						else if (path.equals("/accessadmin.do")) 
						{
							String adresse=request.getParameter("adresse");
							String pwd = request.getParameter("pwd");
							
							
							metier.entities.admin a = admin.getAdmin(adresse);
							
							String npwd=a.getPwd();
							
							if(npwd != null && npwd.equals(pwd)) 
							{
								HttpSession session=request.getSession();//ouverture de session de l'admin.
								session.setAttribute("adrs", adresse);
								session.setAttribute("pwd", npwd);
								this.adminsession=session;
								this.session=session;
								response.sendRedirect("/CRUD/user/index.do");
								
								
							}
							else if(npwd== null) //si le mot de passe est null veut dire que la methode getAdmin(adresse) n'a pas trouver une résultat dans la base de données.
							{
								request.setAttribute("error", "vérifier votre adresse ou mot de passe !");
								request.getRequestDispatcher("admin.jsp").forward(request,response);
							}
							else {
								request.getRequestDispatcher("admin.jsp").forward(request,response);
								request.setAttribute("error", "vérifier votre adresse ou mot de passe !");
								
							}
							
						}
						 
						else if (path.equals("/user/index.do")&&(adminsession.getAttribute("adrs")!=null))//si la connexion est établie avec succès (la session contient les données de l'admin.) /la deuxiéme condition et pour éviter le passage à travers l’url.‎
						{
							response.sendRedirect("chercheruser.do?name=");
						}
						//recherche d'un uitlisateur
						else if (path.equals("/user/chercheruser.do")&&(adminsession.getAttribute("adrs")!=null))//seul l'admin à le droit de la gestion des utilisateurs 
						{
						
						String name=request.getParameter("name");
						UserModel modele= new UserModel();
				 		modele.setName(name);
						List<User> users = user.userParName(name);
						modele.setUsers(users);
						request.setAttribute("modele", modele);
						request.getRequestDispatcher("users.jsp").forward(request,response);
						
						}
						//l'ajout d'un utilisateur
						else if (path.equals("/user/adduser.do") &&(adminsession.getAttribute("adrs")!=null))
						{
							request.getRequestDispatcher("adduser.jsp").forward(request,response);
						}
						//enregistrer un utilisateur 
						else if (path.equals("/user/saveuser.do") && request.getMethod().equals("POST")&&(adminsession.getAttribute("adrs")!=null))
						{
						User u= new User();
						try {
							 String nom=request.getParameter("nom");
							 String adresse=request.getParameter("adresse");
							 String pwd=request.getParameter("pwd");
							u = user.save(new User(nom,adresse,pwd));
							request.setAttribute("user", u);
							request.getRequestDispatcher("confirm.jsp").forward(request,response);
						} catch (DaoExceptions e) {
							//enlever un erreure si le mot de passe inferieur à 8 caractére
							request.setAttribute("error", e.getMessage());
							request.getRequestDispatcher("adduser.jsp").forward(request,response);
						}
						 
						}//la suppression d'un utilisateur
						else if (path.equals("/user/supprimeruser.do")&&(adminsession.getAttribute("adrs")!=null))
						{
						 int id= Integer.parseInt(request.getParameter("id"));
						 user.deleteUser(id);
						 response.sendRedirect("chercheruser.do?name=");//retour au menu d'affichage aprés la suppression
						}
						//modification d'un utilisateur
						else if (path.equals("/user/editeruser.do")&&(adminsession.getAttribute("adrs")!=null) )
						{
						String adresse= request.getParameter("adresse");
						 User u = user.getUser(adresse);
						 request.setAttribute("user", u);
						request.getRequestDispatcher("edituser.jsp").forward(request,response);
						}
						//mettre à jour un utilisateur
						else if (path.equals("/user/updateuser.do")&&(adminsession.getAttribute("adrs")!=null) )
						{
						int id = Integer.parseInt(request.getParameter("id"));
						String nom=request.getParameter("nom");
						String adresse=request.getParameter("adresse");
						String pwd=request.getParameter("pwd");
						
						User u = new User();
						u.setId(id);
						u.setName(nom);
						//une erreur va étre engendrer en cas ou le mot de passe aprés la modification set inferieur à 8 caractéres.
						try {
							u.setAdresse(adresse);
							u.setPwd(pwd);
						} catch (DaoExceptions e) {
							// TODO Auto-generated catch block
							request.setAttribute("error", e.getMessage());
							
						}
						user.updateuser(u);
						request.setAttribute("user", u);
						request.getRequestDispatcher("confirm.jsp").forward(request,response);
						}
						
						//déconnection de l'admin.
						else if (path.equals("/user/logoutuser.do")) {
							adminsession.invalidate();
							response.sendRedirect("/CRUD/admin.do");
							
						}
						 
						 
		//chargement de la page de connexion d'un utilisateur.
		else if (path.equals("/index.do"))
		{
			
			request.getRequestDispatcher("authentification.jsp").forward(request,response);
				
		}
		//vérification des cordonnées de l'utilisateur.
		else if (path.equals("/access.do")) 
			{
				String adresse=request.getParameter("adresse");
				String pwd = request.getParameter("pwd");
				
				
				User u = user.getUser(adresse);
				
				String npwd=u.getPwd();
				
				if(npwd!=null && npwd.equals(pwd)) 
				{
					HttpSession session=request.getSession();//ouverture de la session qui va le suivie tout au long de sa navigation
					session.setAttribute("adresse", adresse);
					session.setAttribute("pwd", npwd);
					this.session=session;
					response.sendRedirect("/CRUD/produit/index.do");
					
					
				}
				
				else {
					request.setAttribute("error", "vérifier votre adresse ou mot de passe !");
					request.getRequestDispatcher("authentification.jsp").forward(request,response);
					
					//retour à la page de connexion si les cordonnées sont fausses.
					
				}
				
			}
		
		else if ((path.equals("/produit/index.do"))&&((session.getAttribute("adresse")!=null)||session.getAttribute("adrs")!=null))
				{
					response.sendRedirect("chercher.do?motCle=");
				}
				//la recherche d'un produit.
		else if (path.equals("/produit/chercher.do")&&(((session.getAttribute("adresse")!=null))||session.getAttribute("adrs")!=null))//la deuxiéme condition est pour éviter l’accès à travers l'url.
				{
				String motCle=request.getParameter("motCle");
				ProduitModele model= new ProduitModele();
				model.setMotCle(motCle);
				List<Produit> prods = metier.produitsParMC(motCle);
				model.setProduits(prods);
				request.setAttribute("model", model);
				request.getRequestDispatcher("produits.jsp").forward(request,response);
				}
				//l'ajout d'un produit.
		else if (path.equals("/produit/saisie.do")&&(((session.getAttribute("adresse")!=null) )||session.getAttribute("adrs")!=null))
				{
					request.getRequestDispatcher("saisieProduit.jsp").forward(request,response);
				}
				//l'enregistrement du produit.
		else if (path.equals("/produit/save.do") && request.getMethod().equals("POST")&&(((session.getAttribute("adresse")!=null))||session.getAttribute("adrs")!=null))
				{
				 String nom=request.getParameter("nom");
				double prix = Double.parseDouble(request.getParameter("prix"));
				Produit p = metier.save(new Produit(nom,prix));
				request.setAttribute("produit", p);
				request.getRequestDispatcher("confirmation.jsp").forward(request,response);
				}
				//la suppression d'un produit.
		else if (path.equals("/produit/supprimer.do")&&(((session.getAttribute("adresse")!=null))||session.getAttribute("adrs")!=null))
				{
				 Long id= Long.parseLong(request.getParameter("id"));
				 metier.deleteProduit(id);
				 response.sendRedirect("chercher.do?motCle=");
				}
				//la modification d'un produit.
		else if (path.equals("/produit/editer.do") &&(((session.getAttribute("adresse")!=null))||session.getAttribute("adrs")!=null))
				{
				Long id= Long.parseLong(request.getParameter("id"));
				 Produit p = metier.getProduit(id);
				 request.setAttribute("produit", p);
				 request.getRequestDispatcher("editerProduit.jsp").forward(request,response);
				}
				//l'enregistrement des modifications.
		else if (path.equals("/produit/update.do") &&(((session.getAttribute("adresse")!=null))||session.getAttribute("adrs")!=null))
				{
				Long id = Long.parseLong(request.getParameter("id"));
				String nom=request.getParameter("nom");
				double prix = Double.parseDouble(request.getParameter("prix"));
				Produit p = new Produit();
				p.setIdProduit(id);
				p.setNomProduit(nom);
				p.setPrix(prix);
				metier.updateProduit(p);
				request.setAttribute("produit", p);
				request.getRequestDispatcher("confirmation.jsp").forward(request,response);
				}
				//déconnexion des utilisateurs 
		else if (path.equals("/produit/logout.do"))
		{
			if(session.getAttribute("adresse")!=null) {
			//fermeture de session
			session.invalidate();
			response.sendRedirect("/CRUD/index.do");//retour à la page d'index .
			}
			else {
				response.sendRedirect("/CRUD/index.do");
			}
			
		}	
		
				
				//si aucun des url n'est pas engendrer un 404 error va étre engendrer.
				else
				{
				response.sendError(Response.SC_NOT_FOUND);
				}
			}
@Override
protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
doGet(request,response);
}
}