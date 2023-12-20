auth0.createAuth0Client({
    domain: "dev-hel5bs2ionmhr2gk.us.auth0.com",
    clientId: "3z9Wr1FfoK4kkdquJZHYYUEoJs7C8YV7"
}).then(async (auth0Client) => {
    await auth0Client.handleRedirectCallback();
    var userInfo = await auth0Client.getUser()
    console.log(userInfo);
    console.log("NICKNAME: "+userInfo.nickname);
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username: userInfo.nickname})
    };
    fetch('http://localhost:8080/apiPesada/loginOauth',requestOptions)
        .then(response => { response.json().then(r => {
            console.log(r.sesionId);
            localStorage.username = userInfo.nickname;
            localStorage.sesionId = r.sesionId;
            if (this.sesionId === ""){
                alert("Datos incorrectos");
            }
            else{
                window.location.href = '/home';
            }
        })
        })
        .catch(error => console.error('Error:', error));
});