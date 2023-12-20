var app = new Vue({
    el: "#vueAdministrarRoles",
    data:{
        miembros:[],
        idComunidad:""
    },
    created : function(){
        // Obtén la URL actual
        var url = window.location.href;
        // Utiliza una expresión regular para encontrar el número después de "/comunidades/"
        var match = url.match(/\/comunidades\/(\d+)\/usuarios/);
        // Comprueba si se encontró una coincidencia y extrae el número
        if (match) {
            var numero = match[1];
            console.log(numero);
            this.idComunidad = numero;
        } else {
            console.log("No se encontró un número en la URL");
        }
        this.buscarMiembros();
    },
    methods: {
        buscarMiembros : function(){

            fetch('/apiPesada/comunidades/'+this.idComunidad+'/usuarios')
                .then(response => { response.json()
                    .then(r => {
                        console.log(r);
                        this.miembros = r.miembros;
                    }).then(r => {this.marcarChecks()})
                })
                .catch(error => console.error('Error:', error));
        },
        marcarChecks : function(){
            this.miembros.forEach(m => {
                var rol = "";
                if(m.tipo === 'DE_SERVICIO'){
                    rol = "rol1";

                }
                else if(m.tipo === 'OBSERVADOR'){
                    rol = "rol2";
                }
                var idElemento =  rol+m.id;
                console.log(idElemento);
                const elem =document.getElementById(idElemento);
                console.log(elem);
                elem.checked = true;
            })
        },
        updateRol:function(miembro,nuevoRol){
            var m = miembro.miembro;
            console.log("Rol de miembro: "+m.id+" cambia a: "+nuevoRol);
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ miembroId: m.id, nuevoRol:nuevoRol })
            };
            fetch('/apiPesada/comunidades/' + this.idComunidad + '/usuarios', requestOptions).then (r =>console.log(r));
        }

    }
})