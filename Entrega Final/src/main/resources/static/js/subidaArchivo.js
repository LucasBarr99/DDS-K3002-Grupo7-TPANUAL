
const form = document.getElementById("idForm");
const myFile = document.getElementById("myFile");

form.addEventListener("submit", ev => {
    ev.preventDefault();
    console.log("Carga variables")
    const ruta = "http://localhost:8080/apiPesada/cargaMasiva";
    const formData = new FormData();

    formData.append("myFile", myFile.files[0]);

    const requestOptions = {
        method: 'POST',
        body: formData
    };
    console.log('FormData' + formData);

    fetch(ruta, requestOptions)
        .then(response => { response.json().then(r => {this.respuesta = r.respuesta;
            console.log(r.respuesta);
            if (this.respuesta === "Error"){
                alert("Datos incorrectos");
            }
            else{
                alert("Archivo cargado con exito!");
        }
    })
    })


    })
    .catch(error => console.error('Error:', error));


