function limpiarFormulario() {
    $("#txtimagen").val("")
    $("#txtnombre").val("")
    $("#cboprescripcion").val("VENTA LIBRE")
    $("#cbopresentacion").val("POTE")
    $("#precio").val("")
    $("#precioUnitario").val("")
    $("#stock").val("")
    $("#cbocategoria").val("0")
    $("#cbomarca").val("0")
    $("#cboestado").val("-1")
    $("#hiddenproducto").val("0")
}

function EliminarProducto(id) {
    // console.log(id)
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: '/Producto/eliminarProducto',
        data: JSON.stringify({
            id_producto: id
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
                ListarProductos();
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

function ListarProductos(){
    $.ajax({
        type: 'GET',
        url: '/Producto/listarProducto',
        dataType: 'json',
        success: function(resultado){
            $("#tblProducto > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblProducto > tbody").append(`
                    <tr>
                        <td>${value.id_producto}</td>
                        <td><img src="${value.imagen_producto}" alt="Imagen" width="100"></td>
                        <td>${value.nombre_producto}</td>
                        <td>${value.prescripcion}</td>
                        <td>${value.presentacion}</td>
                        <td>${value.precio}</td>
                        <td>${value.precio_unitario}</td>
                        <td>${value.stock}</td>
                        <td>${value.nombrecategoria}</td>
                        <td>${value.nombremarca}</td>
                        <td>${(value.is_active == 1 ) ? 'Activo' : 'Inactivo' }</td>
                        <td>
                            <div class="d-flex">
                                <button type="button" class="btn btn-success rounded-circle me-2 actualizarProducto"
                                data-id="${value.id_producto}" 
                                data-imagen="${value.imagen_producto}"
                                data-nombre="${value.nombre_producto}"
                                data-prescripcion="${value.prescripcion}"
                                data-presentacion="${value.presentacion}"
                                data-precio="${value.precio}"
                                data-preciounitario="${value.precio_unitario}"
                                data-stock="${value.stock}"
                                data-idcategoria="${value.id_categoria_producto}"
                                data-idmarca="${value.id_marca_producto}"
                                data-estado="${value.is_active}"
                                > <img
                                        src="/assets/img/pencil.svg" alt="Actualizar" width="20" height="20">
                                </button> 
                                <button type="button" class="btn btn-danger rounded-circle eliminarProducto"
                                    data-id="${value.id_producto}" data-nombre="${value.nombre_producto}"
                                > <img src="/assets/img/trash.svg"
                                        alt="Eliminar" width="20" height="20">
                                </button>
                            </div>
                        </td>
                    </tr>
                `)
            });
        }		
    });	
}

$(document).on('click','#agregarProducto', function(){
    limpiarFormulario();
    $('#modalProducto').modal('show')
})

$(document).on('click','#registrarProducto', function(){
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: '/Producto/registrarProducto',
        data: JSON.stringify({
            id_producto: $("#hiddenproducto").val(),
            imagenproducto: $("#txtimagen").val(),
            nombreproducto: $("#txtnombre").val(),
            prescripcion: $("#cboprescripcion").val(),
            presentacion: $("#cbopresentacion").val(),
            precio: $("#precio").val(),
            preciounitario: $("#precioUnitario").val(),
            stock: $("#stock").val(),
            idcategoria: $("#cbocategoria").val(),
            idmarca: $("#cbomarca").val(),
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
                ListarProductos();
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
    $("#modalProducto").modal("hide");
})


$(document).on('click','.actualizarProducto', function(){
    $("#txtimagen").val( $(this).attr("data-imagen") );
    $("#txtnombre").val($(this).attr("data-nombre"))
    $("#cboprescripcion").val($(this).attr("data-prescripcion"))
    $("#cbopresentacion").val($(this).attr("data-presentacion"))
    $("#precio").val($(this).attr("data-precio"))
    $("#precioUnitario").val($(this).attr("data-preciounitario"))
    $("#stock").val($(this).attr("data-stock"))
    $("#cbocategoria").val($(this).attr("data-idcategoria"))
    $("#cbomarca").val($(this).attr("data-idmarca"))
    $("#cboestado").val($(this).attr("data-estado"))
    $("#hiddenproducto").val( $(this).attr("data-id") )

    $('#modalProducto').modal("show")
})

$(document).on('click','.eliminarProducto', function(){
    Swal.fire({
        title: "¿Está seguro de eliminar el producto: "+ $(this).attr("data-nombre")+" ?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si,eliminar'
      }).then((result) => {
        if (result.isConfirmed) {
            EliminarProducto($(this).attr("data-id"))
        }
      })
})