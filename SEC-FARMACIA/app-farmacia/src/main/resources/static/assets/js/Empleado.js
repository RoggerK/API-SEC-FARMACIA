//Creando eventos con JQuery
$(document).on("click", "#btnagregarempleado", function(){
	$("#hddidempleado").val("0");
	$("#txtnomempleado").val("");
	$("#txtapeempleado").val("");
	$("#cbosexo").val("N");
	$("#txttelefono").val("");
	$("#cbocargo").val("0");
	$("#cboestado").val("-1");	
	
	$("#modalempleado").modal("show");
});

$(document).on("click", ".btnactualizarempleado", function(){
	$("#txtnomempleado").val($(this).attr("data-nombre"));
	$("#txtapeempleado").val($(this).attr("data-apellido"));
	$("#cbosexo").val($(this).attr("data-sexo"));
	$("#txttelefono").val($(this).attr("data-telefono"));
	$("#cbocargo").val($(this).attr("data-idcargo"));	
	$("#hddidempleado").val($(this).attr("data-id"));
	
	$("#modalempleado").modal("show");
});

$(document).on("click", ".btnmodaleliminarempleado", function(){
	// $("#hddempleadoeliminar").val("0");
	// $("#mensajeeliminar").text("¿Está seguro de eliminar el empleado: "+ $(this).attr("data-nombre")+" ?");
	Swal.fire({
		title: "¿Está seguro de eliminar el empleado: "+ $(this).attr("data-nombre")+" ?",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Si,eliminar'
	  }).then((result) => {
		if (result.isConfirmed) {
			EliminarEmpleado($(this).attr("data-id"))
		}
	  })
	// $("#hddempleadoeliminar").val($(this).attr("data-id"));
	
	// $("#modaleliminarempleado").modal("show");
});

$(document).on("click", "#btnregistrarempleado", function(){
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: '/Empleado/registrarEmpleado',
		data: JSON.stringify({
			idempleado: $("#hddidempleado").val(),
			nombre: $("#txtnomempleado").val(),
			apellido: $("#txtapeempleado").val(),
			sexo: $("#cbosexo").val(),
			telefono: $("#txttelefono").val(),
			idcargo: $("#cbocargo").val(),
			isactive: $("#cboestado").val()
		}),
		success: function(resultado){
			if(resultado.respuesta){
				// alert(resultado.mensaje);
				swal.fire({
					position: 'center',
					icon: 'success',
					title: resultado.mensaje,
					showConfirmButton: false,
					timer: 1500
				  })
				ListarEmpleados();
			}else{
				swal.fire({
					position: 'center',
					icon: 'error',
					title: resultado.mensaje,
					showConfirmButton: false,
					timer: 1500
				  })
				// alert(resultado.mensaje);
			}
		}		
	});
	$("#modalempleado").modal("hide");
});


function EliminarEmpleado(id) {
	// console.log(id)
	$.ajax({
		type: 'DELETE',
		contentType: 'application/json',
		url: '/Empleado/eliminarEmpleado',
		data: JSON.stringify({
			// idempleado: $("#hddempleadoeliminar").val()
			idempleado: id
		}),
		success: function(resultado){
			if(resultado.respuesta){
				// alert(resultado.mensaje);
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: resultado.mensaje,
					showConfirmButton: false,
					timer: 1500
				  })
				ListarEmpleados();
			}else{
				Swal.fire({
					position: 'center',
					icon: 'error',
					title: resultado.mensaje,
					showConfirmButton: false,
					timer: 1500
				  })
				// alert(resultado.mensaje);
			}
		}		
	});
}

function ListarEmpleados(){
	$.ajax({
		type: 'GET',
		url: '/Empleado/listarEmpleado',
		dataType: 'json',
		success: function(resultado){
			$("#tblempleado > tbody").html("");
			$.each(resultado, function(index, value){
				$("#tblempleado > tbody").append(`
					<tr>
						<td>${value.idempleado}</td>
						<td>${value.nombreempleado}</td>
						<td>${value.apellidoempleado}</td>
						<td>${(value.sexoempleado == 'M' ) ? 'Masculino'  : 'Femenino' }</td>
						<td>${value.telefonoempleado}</td>
						<td>${value.nombrecargo}</td>
						<td>${ (value.isactive == 1) ? 'Activo' : 'Inactivo' }</td>
						<td>
							<div class="d-flex">
								<button type="button" class="btn btn-success rounded-circle me-2 btnactualizarempleado"
									data-id="${value.idempleado}" 
									data-nombre="${value.nombreempleado}"
									data-apellido="${value.apellidoempleado}"
									data-sexo="${value.sexoempleado}"
									data-telefono="${value.telefonoempleado}"
									data-idcargo="${value.idcargoempleado}"
									data-estado="${value.isactive}"
								>
									<img src="/assets/img/pencil.svg" alt="Actualizar" width="20" height="20">
								</button>
								<button type="button" class="btn btn-danger rounded-circle btnmodaleliminarempleado"
									data-id="${value.idempleado}" data-nombre="${value.nombreempleado}">
									<img src="/assets/img/trash.svg" alt="Eliminar" width="20" height="20">
								</button>
							</div>
						</td>
					</tr>
				`)
			});
		}		
	});	
}