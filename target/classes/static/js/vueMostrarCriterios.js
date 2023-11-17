var app = new Vue({
    el: "#vueMostrarRanking",
    data:{
        criterios:[],
        criterioSeleccionado:""
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
        }
    }
})