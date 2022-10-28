gpaStart();
function gpaStart() {
    // setup
    preCheck();
    document.querySelector('.card').style.display = 'none';
    document.querySelector('.form').style.display = 'none';
    document.querySelector('#form-user-update').style.display = 'none';
    document.querySelector('#form-user-password').style.display = 'none';
    document.querySelector('#form-add-update').style.display = 'none';
    document.querySelector('#search').setAttribute('search', 'def');
    // pre api
    preShowMLG(gpaShowApi, '');
}