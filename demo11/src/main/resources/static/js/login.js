loginStart();
function loginStart() {
    // setup
    document.querySelector('#login-error').style.display = 'none';
    document.querySelector('#username').style.borderColor = '#ced4da';
    document.querySelector('#password').style.borderColor = '#ced4da';
    localStorage.clear();
    // handle
    preLogin();
}