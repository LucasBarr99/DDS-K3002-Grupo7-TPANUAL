var app = new Vue({
    el: "#vueServiciosComunidad",
    data:{
        comunidadSeleccionada:null
    },
    methods: {
        irAUrl :function(e){
            e.preventDefault()
            window.location.href = '/aperturaIncidentes?sesion='+localStorage.sesionId;
        },
        redireccionar : function(){
            console.log(this.comunidadSeleccionada);
            window.location.href = '/aperturaIncidentes?sesion='+localStorage.sesionId+'&comunidad='+this.comunidadSeleccionada;
        }
    }
});