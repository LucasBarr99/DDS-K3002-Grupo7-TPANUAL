var app = new Vue({
    el: "#vueValidadorSesion",
    data:{},
    created: function(){
        this.validarSesion()
    },
    methods: {
        validarSesion : function(){
            console.log("sesionId "+localStorage.sesionId)
            //Fetch a la api para validar si el sesionId es valido
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: localStorage.sesionId
            };

            fetch('http://localhost:8080/apiPesada/validarSesionId',requestOptions)
                .then(response => { response.json().then(r => {
                    console.log(r.esValido);
                    if(r.esValido){
                        const texto = document.getElementById("textoBienvenida");
                        texto.innerHTML = "Bienvenido, "+localStorage.username+" !";
                    }
                    else{
                        window.location.href = '/login';
                    }
                })
                })
                .catch(error => console.error('Error:', error));
        }
    }
})