$(document).on('click','.btnagregarmedicamento', function(){
    $("#txtnombre").val($(this).attr("data-nombre"))
    $("#precioUnitario").val($(this).attr("data-preciounitario"))
	$('#cantidad').val('0')
	$('#resultado').val('0')
    $("#hiddenproducto").val( $(this).attr("data-id"))
    $('#modalAgregarProducto').modal("show")
})

function calcular(){
	var can = document.getElementById("cantidad").value;
	var precunidad = document.getElementById("precioUnitario").value;
	var res;
	if(can <= 0){
		alert("Se informa que la cantidad no puede ser negativa o igual a cero");
		document.getElementById("cantidad").value = 0;
		document.getElementById("resultado").value = 0;
	}else{
		res = parseFloat(can) * parseFloat(precunidad);
		document.getElementById("resultado").value = res.toFixed(2);
	}
}