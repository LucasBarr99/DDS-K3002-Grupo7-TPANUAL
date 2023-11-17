changelistener = document.getElementById("criterio_ranking").onchange;

function changeListener(){
    var e = document.getElementById("criterio_ranking");
    var value = e.value;
    console.log("Carga ranking")
    const ruta = "http://localhost:8080/apiPesada/informes?idCriterio=" + value;

    console.log(ruta)
    fetch(ruta)
        .then(response => { response.json().then(r => {
            console.log(r);
            this.entidades = r.entidades;
        })
        })
        .catch(error => console.error('Error:', error));
}
