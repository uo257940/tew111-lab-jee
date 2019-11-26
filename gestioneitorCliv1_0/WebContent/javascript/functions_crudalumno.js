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
//Eliminación un alumno existente
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


$(function() {
//	Creamos el modelo con los datos y la conexión al servicio web.
	var model = new Model();
	model.load();
});


