<%@page import="java.util.Calendar"%>
<%@page import="model.ejb.TimerEJB"%>
<%@page import="model.entidad.Dia"%>
<%@page import="model.ejb.UsuarioEJB"%>
<%@page import="model.ejb.DiaEJB"%>
<%@page import="model.entidad.Restaurante"%>
<%@page import="model.ejb.RolEJB"%>
<%@page import="model.entidad.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.entidad.Rol"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	DiaEJB d = new DiaEJB();
	UsuarioEJB us = new UsuarioEJB();
	TimerEJB timerEJB = new TimerEJB();

	//recupero el usuario de la sesion
	HttpSession sesion = request.getSession(true);
	Usuario userNav = (Usuario) sesion.getAttribute("user");

	//comprueba que este en modo trabajo
	int modoTrabajo;
	try {
		modoTrabajo = (int) sesion.getAttribute("modoTrabajo");

	} catch (Exception e) {
		modoTrabajo = 0;
	}

	//comprueba que el usuario sea valido
	if (userNav == null || userNav.getId() == 0 && userNav.getRol() == 0) {
		response.sendRedirect("Main");
	} else {

		// Si el modo trabajo es 1 es que el modo trabajo esta activado y redirige al
		// jsp del modo trabajo
		if (modoTrabajo == 1) {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexTrabajo.jsp");
			rs.forward(request, response);
		} else {

			if (userNav.getRol() == 1) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexUsuario.jsp");
				rs.forward(request, response);
			} else if (userNav.getRol() == 2 || userNav.getRol() == 3) {
				ArrayList<Integer> arrT = new ArrayList<Integer>();
				Integer mesFecha = 3;
				Integer anyoFecha = 2018;
				Integer id = 1;

				//saca los parametros
				try {
					mesFecha = Integer.parseInt(sesion.getAttribute("mes").toString());
					anyoFecha = Integer.parseInt(sesion.getAttribute("anyo").toString());
					id = Integer.parseInt(sesion.getAttribute("idUsuario").toString());
				} catch (Exception e) {

				}

				//busca el usuario
				Usuario user = us.UsuarioPorId(id);

				//saca los dias maximos del mes
				Calendar c = new Calendar.Builder().setCalendarType("iso8601")
						.setDate(anyoFecha, mesFecha - 1, 1).build();

				int diaMaximo = c.getActualMaximum(Calendar.DATE);

				//saca una array con los dias del horario del usuario segun el mes, año y usuario
				ArrayList<Dia> arrD = d.horarioUsuario(user, mesFecha, anyoFecha);
				sesion.setAttribute("usuarioHorario", user);
				sesion.setAttribute("mesHorario", mesFecha);
				sesion.setAttribute("anyoHorario", anyoFecha);
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
<title>Edita Horario</title>
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
							</div> Home</a> <a class="nav-link" href="ModoTrabajo"><div
								class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Activar Modo Trabajo</a> <a class="nav-link" href="Login?logout=1"><div
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
					<h1 class="mt-4">Horario</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item"><a href="Main">Home</a></li>
						<li class="breadcrumb-item active">Horario></li>
					</ol>
					<div class="card mb-4">
						<div class="card-body">
							Horario del mes
							<%
							out.print(mesFecha);
						%>
							y el año
							<%
							out.print(anyoFecha);
						%>

						</div>
					</div>
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table mr-1"></i>Usuarios del restaurante
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<form action="EditaHorario" method="POST">
									<table class="table table-bordered" id="dataTable" width="100%"
										cellspacing="0">
										<thead>
											<tr>
												<th>Trabajador</th>
												<%
													String htmlDia = "";

																//Muestra los dias segun el dia maximo

																for (int i = 1; i <= diaMaximo; i++) {

																	htmlDia += "<th colspan='4'>" + d.NombreDia(anyoFecha + "-" + mesFecha + "-" + i) + "</th>";

																}

																out.print(htmlDia);
												%>

											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>Trabajador</th>
												<%
													out.print(htmlDia);
												%>
											</tr>
										</tfoot>
										<tbody>
											<%
												htmlDia = "<tr>";
															//muestra las entradas y salidas
															htmlDia += "<td></td>";

															for (Dia dia : arrD) {
																htmlDia += "<td>ENT</td>";
																htmlDia += "<td>SAL</td>";
																htmlDia += "<td>ENT</td>";
																htmlDia += "<td>SAL</td>";

															}

															htmlDia += "</tr>";

															out.print(htmlDia);
											%>
											<%
												String html = "";

															html += "<tr>";
															html += "<td>" + user.getNombre() + " " + user.getApellido() + "</td>";

															//si la array esta vacia crea inputs vacios si tiene algo creara inputs con el valor
															if (arrD.isEmpty()) {
																for (int i = 1; i <= diaMaximo; i++) {

																	html += "<td><input type='time' name='entrada1" + String.format("%02d", i) + "'></td>";

																	html += "<td><input type='time' name='salida1" + String.format("%02d", i) + "'></td>";

																	html += "<td><input type='time' name='entrada2" + String.format("%02d", i) + "'></td>";

																	html += "<td><input type='time' name='salida2" + String.format("%02d", i) + "'></td>";
																}
															} else {
																for (Dia dia : arrD) {

																	//si no es un dia valido crea un input vacio si no con los datos
																	if (dia.getEntrada_1() == null && dia.getEntrada_2() == null) {
																		html += "<td><input type='time' name='entrada1" + d.getDia(dia.getFecha())
																				+ "'></td>";

																		html += "<td><input type='time' name='salida1" + d.getDia(dia.getFecha())
																				+ "'></td>";

																		html += "<td><input type='time' name='entrada2" + d.getDia(dia.getFecha())
																				+ "'></td>";

																		html += "<td><input type='time' name='salida2" + d.getDia(dia.getFecha())
																				+ "'></td>";
																	} else {

																		arrT = timerEJB.getHMS(timerEJB.getSeconds(dia.getEntrada_1()));
																		html += "<td><input type='time' name='entrada1" + d.getDia(dia.getFecha())
																				+ "' value='" + String.format("%02d", arrT.get(0)) + ":"
																				+ String.format("%02d", arrT.get(1)) + "'></td>";

																		arrT = timerEJB.getHMS(timerEJB.getSeconds(dia.getSalida_1()));
																		html += "<td><input type='time' name='salida1" + d.getDia(dia.getFecha())
																				+ "' value='" + String.format("%02d", arrT.get(0)) + ":"
																				+ String.format("%02d", arrT.get(1)) + "'></td>";

																		arrT = timerEJB.getHMS(timerEJB.getSeconds(dia.getEntrada_2()));
																		html += "<td><input type='time' name='entrada2" + d.getDia(dia.getFecha())
																				+ "' value='" + String.format("%02d", arrT.get(0)) + ":"
																				+ String.format("%02d", arrT.get(1)) + "'></td>";

																		arrT = timerEJB.getHMS(timerEJB.getSeconds(dia.getSalida_1()));
																		html += "<td><input type='time' name='salida2" + d.getDia(dia.getFecha())
																				+ "' value='" + String.format("%02d", arrT.get(0)) + ":"
																				+ String.format("%02d", arrT.get(1)) + "'></td>";
																	}

																}
															}

															html += "</tr>";

															out.print(html);
											%>

										</tbody>
									</table>
									<input class="btn btn-primary" type="submit" value="Editar">
								</form>
							</div>
						</div>
					</div>
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
		}
	}
%>
