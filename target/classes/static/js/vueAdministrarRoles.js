var app = new Vue({
    el: "#vueAdministrarRoles",
    data:{
        miembros:[],
        idComunidad:""
    },
    async created(){
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
    mounted() {
        this.loadMiembros();
    },

    methods: {
        async loadMiembros() {
            await this.buscarMiembros();
            this.marcarChecks();
        },
    methods: {
        buscarMiembros : function(){

            fetch('/apiPesada/comunidades/'+this.idComunidad+'/usuarios')
                .then(response => { response.json()
                    .then(r => {
                        console.log(r);
                        this.miembros = r.miembros;
                    })
                })
                .catch(error => console.error('Error:', error));
        },
        marcarChecks : function (){
            console.log("HOLAAAAAAAAA");
            this.miembros.forEach(m => {
                if(m.tipo === 'DE_SERVICIO'){
                    console.log("rol1"+m.id);
                    var id =  "rol1"+m.id;
                    const elem =document.getElementById(id);
                    console.log(elem);
                    if (elem) {
                        elem.checked = true;
                    }
                }
                else if(m.tipo === 'OBSERVADOR'){
                    console.log("rol2"+m.id);
                    var id =  "rol2"+m.id;
                    const elem =document.getElementById(id);
                    console.log(elem);
                    if (elem) {
                        elem.checked = true;
                    }
                }

            })
        }
    }
})