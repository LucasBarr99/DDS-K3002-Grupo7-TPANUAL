auth0.createAuth0Client({
    domain: "dev-hel5bs2ionmhr2gk.us.auth0.com",
    clientId: "3z9Wr1FfoK4kkdquJZHYYUEoJs7C8YV7"
}).then(async (auth0Client) => {
    // Assumes a button with id "login" in the DOM
    const loginButton = document.getElementById('authLogin');

    loginButton.addEventListener("click", (e) => {
        e.preventDefault();
        auth0Client.loginWithRedirect({
            authorizationParams: {
                redirect_uri: 'http://localhost:8080/home'
            }
        })
    });
});

var app = new Vue({
    el: "#vueLoginForm",
    data:{
        username: "",
        password: "",
        sesionId:""
    },
    methods: {
        saludar : function(){
            console.log("HOLA MUNDO")
        },
        postLogin : function(e){
            //console.log("HOLA "+this.username)
            e.preventDefault()
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username: this.username, password:this.password })
            };
            fetch('http://localhost:8080/apiPesada/login',requestOptions)
                .then(response => { response.json().then(r => {
                        this.sesionId = r.sesionId;
                        console.log(r.sesionId);
                        localStorage.username = this.username;
                        localStorage.sesionId = this.sesionId;
                        if (this.sesionId === ""){
                            alert("Datos incorrectos");
                        }
                        else{
                            window.location.href = '/home';
                        }
                    })
                })
                .catch(error => console.error('Error:', error));


        }
    }
})