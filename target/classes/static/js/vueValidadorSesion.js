var app = new Vue({
    el: "#vueValidadorSesion",
    data:{
        sesionId :localStorage.sesionId
    },
    created: function(){
        this.validarSesion()
    },
    methods: {
        irAUrl :function(){
            window.location.href = '/aperturaIncidentes?sesion='+localStorage.sesionId;
        },
        validarSesion: function () {
            console.log("sesionId " + localStorage.sesionId)
            var partesUrl = document.URL.split("/");
            var ultimoElemento = partesUrl[partesUrl.length - 1];
            console.log("url " + ultimoElemento)
            //Fetch a la api para validar si el sesionId es valido
            const requestOptions = {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({sessionId: localStorage.sesionId, url: ultimoElemento})
            };
            // Validar que el SessionId es correcto
            fetch('http://localhost:8080/apiPesada/validarSesionId', requestOptions)
                .then(response => {
                    response.json().then(r => {
                        console.log(r.esValido);
                        if (r.esValido) {
                            document.getElementById("textoBienvenida").textContent = "Bienvenido, " + localStorage.username + " !";
                            console.log(r.tienePermiso);
                            if (!r.tienePermiso) {
                                window.location.href = '/errorPermisos';
                            }
                        } else {
                            window.location.href = '/login';
                        }
                    })
                })
                .catch(error => console.error('Error:', error));
        }
    }
});