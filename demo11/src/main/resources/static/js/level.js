levelStart();
function levelStart() {
    // setup
    preCheck();
    document.querySelector('.card').style.display = 'none';
    document.querySelector('.form').style.display = 'none';
    document.querySelector('#form-user-update').style.display = 'none';
    document.querySelector('#form-user-password').style.display = 'none';
    document.querySelector('#form-add-update').style.display = 'none';
    // pre api
    let search = document.querySelector('#search').value;
    let data = {
        level: search
    }
    preShowMLG(levelShowApi, data);
}