
var app = new Vue({
    el: "#vueSugerenciaIncidente",
    data:{
        idIncidente:"",
        entidad:"",
        servicio:"",
        descripcion:"",
        estado:"",
        fechaApertura:"",
        fechaCierre:""
    },
    created: function(){
        this.cargarIncidente()
    },
    methods: {
        cargarIncidente : function(){
            fetch('http://localhost:8080/apiPesada/sugerenciaIncidente?sessionId='+localStorage.sesionId)
            .then(response => { response.json()
                .then(r => {
                    console.log(r);
                    this.idIncidente = r.idIncidente;
                    this.descripcion = r.descripcion;
                    this.servicio = r.servicioAfectado;
                    this.estado = r.estado;
                    this.fechaCierre = r.fechaCierre;
                    this.fechaApertura = r.fechaApertura;
                    this.entidad = r.entidadServicio;
                })
            })
            .catch(error => console.error('Error:', error));
        },
        cerrarIncidente : function(){
            var opcion = confirm("Esta seguro que quiere cerrar este incidente?");
            if (opcion) {
                const requestOptions = {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: ''
                }
                fetch('http://localhost:8080/incidentes/'+this.idIncidente+'/cerrar',requestOptions)
            }

        }
    }
})