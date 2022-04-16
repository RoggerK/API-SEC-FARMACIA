
//Creando eventos con JQuery
$(document).on("click", "#btnagregarproveedor", function(){
	$("#txtnomproveedor").val("");
	$("#txtrucproveedor").val("");
	$("#txttlfproveedor").val("");
	$("#txtdirproveedor").val("");
	$("#cboestado").val("-1");	
	$("#hddidproveedor").val("0");
	
	$("#modalproveedor").modal("show");
});

$(document).on("click", ".btnactualizarproveedor", function(){
	$("#txtnomproveedor").val($(this).attr("data-nombre"));
	$("#txtrucproveedor").val($(this).attr("data-ruc"));
	$("#txttlfproveedor").val($(this).attr("data-telefono"));
	$("#txtdirproveedor").val($(this).attr("data-direccion"));
	$("#cboestado").val($(this).attr("data-estado"));	
	$("#hddidproveedor").val($(this).attr("data-id"));
	
	$("#modalproveedor").modal("show");
});

$(document).on("click", ".btnmodaleliminarproveedor", function(){
	//$("#hddproveedoreliminar").val("0");
	Swal.fire({
		title: "¿Está seguro de eliminar el proveedor: "+ $(this).attr("data-nombre")+" ?",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Si,eliminar'
	  }).then((result) => {
		if (result.isConfirmed) {
			EliminarProveedor($(this).attr("data-id"))
		}
	  })
	//$("#mensajeeliminar").text("¿Está seguro de eliminar el proveedor: "+ $(this).attr("data-nombre")+" ?");
	//$("#hddproveedoreliminar").val($(this).attr("data-id"));
	
	//$("#modaleliminarproveedor").modal("show");
});

$(document).on("click", "#btnregistrarproveedor", function(){
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: '/Proveedor/registrarProveedor',
		data: JSON.stringify({
			idproveedor: $("#hddidproveedor").val(),
			nombreproveedor: $("#txtnomproveedor").val(),
			rucproveedor: $("#txtrucproveedor").val(),
			telefonoproveedor: $("#txttlfproveedor").val(),
			direccionproveedor: $("#txtdirproveedor").val(),
			isactive: $("#cboestado").val()
		}),
		success: function(resultado){
			if(resultado.respuesta){
				//alert(resultado.mensaje);
				swal.fire({
					position: 'center',
					icon: 'success',
					title: resultado.mensaje,
					showConfirmButton: false,
					timer: 1500
				  })
				ListarProveedores();
			}else{
				swal.fire({
					position: 'center',
					icon: 'error',
					title: resultado.mensaje,
					showConfirmButton: false,
					timer: 1500
				  })
				//alert(resultado.mensaje);
			}
		}		
	});
	$("#modalproveedor").modal("hide");
});


function EliminarProveedor(id) {
	// console.log(id)
	$.ajax({
		type: 'DELETE',
		contentType: 'application/json',
		url: '/Proveedor/eliminarProveedor',
		data: JSON.stringify({
			idproveedor: id
		}),
		success: function(resultado){
			if(resultado.respuesta){
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: resultado.mensaje,
					showConfirmButton: false,
					timer: 1500
				  })
				// alert(resultado.mensaje);
				ListarProveedores();
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

function ListarProveedores(){
	$.ajax({
		type: 'GET',
		url: '/Proveedor/listarProveedor',
		dataType: 'json',
		success: function(resultado){
			$("#tblproveedor > tbody").html("");
			$.each(resultado, function(index, value){
				$("#tblproveedor > tbody").append(`
					<tr>
						<td>${value.idproveedor}</td>
						<td>${value.nombreproveedor}</td>
						<td>${value.rucproveedor}</td>
						<td>${value.telefonoproveedor}</td>
						<td>${value.direccionproveedor}</td>
						<td>${value.isactive === 1 ? 'Activo' : 'Inactivo'}</td>
						<td>
							<div class="d-flex">
								<button type="button" class="btn btn-success rounded-circle me-2 btnactualizarproveedor"
									data-id="${value.idproveedor}" 
									data-nombre="${value.nombreproveedor}"
									data-ruc="${value.rucproveedor}"
									data-telefono="${value.telefonoproveedor}"
									data-direccion="${value.direccionproveedor}"
									data-estado="${value.isactive}">
									<img src="/assets/img/pencil.svg" alt="Actualizar" width="20" height="20">
								</button>
								<button type="button" class="btn btn-danger rounded-circle btnmodaleliminarproveedor"
									data-id="${value.idproveedor}" 
									data-nombre="${value.nombreproveedor}">
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