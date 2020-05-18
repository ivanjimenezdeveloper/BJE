<%@page import="model.entidad.Timer"%>
<%@page import="model.ejb.TimerEJB"%>
<%@page import="model.entidad.Categoria"%>
<%@page import="model.entidad.Alimento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ejb.AlimentoEJB"%>
<%@page import="model.ejb.CategoriaEJB"%>
<%@page import="model.entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//recupero el usuario de la sesion
	HttpSession sesion = request.getSession(true);
	CategoriaEJB categoriaEJB = new CategoriaEJB();
	AlimentoEJB alimentoEJB = new AlimentoEJB();
	TimerEJB timerEJB = new TimerEJB();

	ArrayList<Alimento> arrA = alimentoEJB.busquedaAlimentos();
	ArrayList<Categoria> arrC = categoriaEJB.busquedaCategorias();
	ArrayList<Timer> arrT = timerEJB.timersActivos();

	Usuario userNav = (Usuario) sesion.getAttribute("user");
	if (userNav == null || userNav.getId() == 0 && userNav.getRol() == 0) {
		response.sendRedirect("Main");
	} else if (userNav.getRol() == 2 || userNav.getRol() == 3) {
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexUsuario.jsp");
		rs.forward(request, response);
	} else if (userNav.getRol() == 1) {

		ArrayList<Integer> timers = new ArrayList<Integer>();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Home</title>
<link href="dist/css/styles.css" rel="stylesheet" />
<link
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css"
	rel="stylesheet" crossorigin="anonymous" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<a class="navbar-brand" href="index.html">Better Job Environment</a>
		<button class="btn btn-link btn-sm order-1 order-lg-0"
			id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar Search-->
		<form
			class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
		</form>
		<!-- Navbar-->
		<ul class="navbar-nav ml-auto ml-md-0">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="userDropdown" href="#"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="userDropdown">
					<a class="dropdown-item" href="Perfil">Perfil</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="Login?logout=1">Logout</a>
				</div></li>
		</ul>
	</nav>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<!-- INICIO DE LA BARRA LATERAL -->
						<div class="sb-sidenav-menu-heading">Inicio</div>

						<a class="nav-link" href="Main"><div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Home</a> <a class="nav-link" href="Login?logout=1"><div
								class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Log Out</a>
					</div>
				</div>
				<!-- INICIO DE LA INFORMACION DEL USUARIO -->

				<div class="sb-sidenav-footer">
					<div class="small">
						Logged in as:
						<%
						//Muestro el nombre del usuario o en caso contrario Muestro el nombre estandar
							if (userNav == null) {
								out.print("Usuario");
							} else {
								out.print(userNav.getNombre());

							}
					%>
					</div>
				</div>
			</nav>
		</div>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid">


					<%
						String html = "";
							for (Categoria c : arrC) {

								html += "<div class='row'>";
								html += "<h1>" + c.getNombre() + "</h1>";
								html += "</div>"; // row nombre categoria
								html += "<div class='row'>";

								for (Alimento a : arrA) {

									if (a.getCategoria() == c.getId()) {

										for (Timer t : arrT) {

											if (t.getIdAlimento() == a.getId()) {

												timers = timerEJB.getHMS( t.getTiempo_restante());
												html += "<div class='col-xl-3 col-md-3'>"; // i1
												html += "<a class='small text-white stretched-link' href='#''>";
												html += "<div class='card bg-warning text-white mb-4'>"; // i2
												html += "<div class='card-body d-flex align-items-center justify-content-center'>"; // in 3
												html += "<h1>" + a.getNombre() + "</h1>";
												html += "<div class='card-footer d-flex align-items-center justify-content-center'>"; // in 4
												html += timers.get(0) + ":" + timers.get(1) + ":" + timers.get(2);
												html += "<div class='small text-white'></div>";

												html += "</div>"; // fin 4
												html += "</div>"; // fin 3
												html += "</div>"; // fin 2
												html += "</a>";
												html += "</div>"; // fin 1
											}
										}
									}

								}
								html += "</div>"; // fin row de tarjetas
							}

							out.print(html);
					%>

					<!-- Fin container fluid -->
				</div>
			</main>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; Better Job
							Environment 2020</div>
						<div>
							<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
								&amp; Conditions</a>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="dist/js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script src="dist/assets/demo/chart-area-demo.js"></script>
	<script src="dist/assets/demo/chart-bar-demo.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"
		crossorigin="anonymous"></script>
	<script src="dist/assets/demo/datatables-demo.js"></script>
</body>
</html>

<%
	} else {
		response.sendRedirect("Main");
	}
%>
