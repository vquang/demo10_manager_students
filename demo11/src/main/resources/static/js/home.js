homeStart();
function homeStart() {
    // setup
    preCheck();
    document.querySelector('.card').style.display = 'none';
    document.querySelector('.form').style.display = 'none';
    document.querySelector('#form-user-update').style.display = 'none';
    document.querySelector('#form-user-password').style.display = 'none';
    // pre api
    preChart();
}