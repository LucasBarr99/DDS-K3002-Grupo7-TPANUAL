var app = new Vue({
    el: "#vueRankings",
    data:{
        criterios:[],
        criterioSeleccionado:"",
        entidades:[]
    },
    created: function(){
        this.buscarCriterios()
    },
    methods: {
        buscarCriterios : function(){

            fetch('http://localhost:8080/apiPesada/criterios')
                .then(response => { response.json()
                    .then(r => {
                        console.log(r);
                        this.criterios = r.criterios;
                    })
                })
                .catch(error => console.error('Error:', error));
        },
        buscarInforme : function (){
            /*var e = document.getElementById("criterio_ranking");
            var value = e.value;*/
            console.log("Carga ranking")
            const ruta = "http://localhost:8080/apiPesada/informes?idCriterio=" + this.criterioSeleccionado;

            console.log(ruta)
            fetch(ruta)
                .then(response => { response.json().then(r => {
                    console.log(r);
                    this.entidades = r.entidades;
                })
                })
                .catch(error => console.error('Error:', error));
        }
    }
})