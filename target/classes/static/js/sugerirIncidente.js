
var app = new Vue({
    el: "#vueSugerenciaIncidente",
    data:{
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

        fetch('http://localhost:8080/apiPesada/sugerenciaIncidente')
            .then(response => { response.json()
                .then(r => {
                    console.log(r);
                    this.descripcion = r.descripcion;
                    this.servicio = r.servicioAfectado;
                    this.estado = r.estado;
                    this.fechaCierre = r.fechaCierre;
                    this.fechaApertura = r.fechaApertura;
                    this.entidad = r.entidadServicio;
                })
            })
            .catch(error => console.error('Error:', error));
            }
    }
})