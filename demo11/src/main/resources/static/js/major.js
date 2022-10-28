majorStart();
function majorStart() {
    // setup
    preCheck();
    document.querySelector('.card').style.display = 'none';
    document.querySelector('.form').style.display = 'none';
    document.querySelector('#form-user-update').style.display = 'none';
    document.querySelector('#form-user-password').style.display = 'none';
    // pre api
    let search = document.querySelector('#search').value;
    let data = {
        major: search
    }
    if(location.href === majorSApi) preShowMLG(majorStudentApi, data);
    else preShowMLG(majorLecturerApi, data)
}