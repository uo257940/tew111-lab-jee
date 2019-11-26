function Model(){
//	Lista de alumnos.
	this.tbAlumnos = null;
};
//Carga los datos del servicio sobreescribiendo el dato this.tbAlumnos.
this.load = function() {
	this.tbAlumnos = AlumnosServicesRs.getAlumnos();
}
//Llamamos al servicio de alta de alumno
this.add = function(alumno) {
//	Llamamos al servicio de alta de alumno
	AlumnosServicesRs.saveAlumno({
		$entity : alumno,
		$contentType : "application/json"
	});
//	Recargamos la lista de alumnos.
	this.load();
}
//Actualización de un alumno existente: PENDIENTE DE IMPLEMENTAR
this.edit = function(alumno) {
	AlumnosServicesRS.updateAlumno({
		$entity : alumno,	
		$contentType: "application/json"
	});
	this.load();

}
//Eliminación un alumno exist	ente
this.remove = function(id_alumno) {
//	Llamamos al servicio de borrado de alumno
	AlumnosServicesRs.deleteAlumno({
		id : id_alumno
	});
//	Recargamos la lista de alumnos.
	this.load();
}
this.find = function(id_alumno) {
	function checkAlumno(obj) {
		return obj.id == id_alumno;
	}
//	Buscamos los datos del alumno cuyo id_alumno sea el mismo que el
//	seleccionado
	var alumno = this.tbAlumnos.find(checkAlumno);
	return alumno;
}

function View(){
	this.list = function (lista) {} // PENDIENTE DE IMPLEMENTAR
	this.loadAlumnoFromForm = function () {} // PENDIENTE DE IMPLEMENTAR
	this.loadAlumnoInForm = function (alumno) {} // PENDIENTE DE IMPLEMENTAR
	this.getIdAlumno = function(celda) {} // PENDIENTE DE IMPLEMENTAR
};

this.list = function(lista) {
	$("#tblList").html("");
	$("#tblList").html( "<thead>" + "<tr>" + "<th></th>"
			+ "<th>ID</th>" + "<th>IDUser</th>" + "<th>Nombre</th>"
			+ "<th>Apellidos</th>" + "<th>Email</th>" + "</tr>"
			+ "</thead>" + "<tbody>" + "</tbody>");
	for ( var i in lista) {
		var alumno = lista[i];
		$("#tblList tbody").append("<tr> <td>"
				+ "<img src='icons/edit.png' class='btnEdit'/>"
				+ "<img src='icons/delete.png' class='btnDelete'/> </td>"
				+ "<td>" + alumno.id + "</td>" + "<td>" + alumno.iduser + "</td>"
				+ "<td>" + alumno.nombre + "</td>" + "<td>" + alumno.apellidos + "</td>"
				+ "<td>" + alumno.email + "</td></tr>");
	}
}

this.loadAlumnoFromForm = function() {
	// Cogemos el alumno nuevo del formulario.
	var alumno = {
			id : $("#id").val(),
			iduser : $("#iduser").val(),
			nombre : $("#nombre").val(),
			apellidos : $("#apellidos").val(),
			email : $("#email").val()
	};
	return alumno;
}

this.loadAlumnoInForm = function(alumno) {
	// Pintamos los datos alumnos sobre el formularios de alta/edición
	$("#id").val(alumno.id);
	$("#iduser").val(alumno.iduser);
	$("#nombre").val(alumno.nombre);
	$("#apellidos").val(alumno.apellidos);
	$("#email").val(alumno.email);
	$("#iduser").focus(); // Ponemos el foco en el campo Nombre.
}

This.getIdAlumno = function(celda) {
	// Accedemos a la fila que está por encima de esta celda
	// (closest('tr'))y despues obtenemos todas las celdas de esa fila
	// (find('tr')) y
	// nos quedamos con la segunda (get(1)) que es la contiene el "id" del
	// alumno.
	var id_alumno = parseInt(celda.closest('tr').find('td').get(1).innerHTML);
	return id_alumno;
}

$(function() {
//	Creamos el modelo con los datos y la conexión al servicio web.
	var model = new Model();
	model.load();
});


