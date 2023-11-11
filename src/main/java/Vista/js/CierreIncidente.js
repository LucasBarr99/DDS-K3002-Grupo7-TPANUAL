function cerrarDetalleIncidente()
{
    if(document.getElementById("Estado").innerHTML == "Estado: CERRADO"){
        alert("Este incidente ya esta cerrado")
    }
    else{
        var opcion = confirm("Esta seguro que quiere cerrar este incidente?");
        if (opcion == true) {
            document.getElementById("Estado").innerHTML = "Estado: CERRADO";
        }
    }


}

function cerrarListaIncidente(id){
    if(document.getElementById("Estado"+id).innerHTML == "Cerrado"){
        alert("Este incidente ya esta cerrado")
    }
    else{
        var opcion = confirm("Esta seguro que quiere cerrar este incidente?");
        if (opcion == true) {
            document.getElementById("Estado"+id).innerHTML = "Cerrado";
            document.getElementById("Estado"+id).className = "badge badge-pill bg-success";
        }
    }
}