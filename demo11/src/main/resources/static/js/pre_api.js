let jwt = localStorage.getItem('Bearer');
// login
function preLogin() {
    document.querySelector('.btn-login').onclick = function () {
        let username = document.querySelector('#username');
        let password = document.querySelector('#password');
        // check
        if (username.value === "" || password.value === "") {
            username.value = ""; username.style.borderColor = 'red';
            password.value = ""; password.style.borderColor = 'red';
            document.querySelector('#login-error').style.display = 'block';
        } else {
            let request = {
                username: username.value,
                password: password.value
            }
            callApi(userLoginApi, 'POST', '', request, handleLogin);
        }
    }
}
function preCheck() {
    callApi(userCheckApi, 'POST', jwt, '', handleCheck);
}
// user
function preShowUser() {
    document.querySelector('.card').style.display = 'block';
    callApi(userShowApi, 'GET', jwt, '', handleShowUser);
}
function preUpdateUser() {
    // setup
    document.querySelector('.form').style.display = 'block';
    document.querySelector('#form-user-update').style.display = 'block';
    let inputs = document.querySelectorAll('#form-user-update input');
    for(let i = 1; i < inputs.length; ++i) {
        inputs[i].classList.remove('color');
    }
    document.querySelector('#user-error').style.display = 'none';
    let username = document.querySelector('#username');
    let name = document.querySelector('#user-name');
    let male = document.querySelector('#user-male');
    let female = document.querySelector('#user-female');
    let address = document.querySelector('#user-address');
    // set value
    let infos = document.getElementsByClassName('td-user-info')
    username.value = infos[0].textContent;
    name.value = infos[1].textContent;
    if(male.value === infos[2].textContent) male.checked = true;
    else female.checked = true;
    address.value = infos[3].textContent;
    // call api
    document.querySelector('.btn-user-submit').onclick = function () {
        let gender = male;
        if(male.checked === false) gender = female;
        // check
        if(name.value === '' || address.value ===  '') {
            for(let i = 1; i < inputs.length; ++i) {
                inputs[i].classList.add('color');
            }
            document.querySelector('#user-error').style.display = 'block';
        } else {
            let data = {
                username: username.value,
                name: name.value,
                gender: gender.value,
                address: address.value
            }
            callApi(userUpdateApi, 'PUT', jwt, data, handleUpdateUser);
        }
    }
}
function preChangePassword() {
    // setup
    document.querySelector('.form').style.display = 'block';
    document.querySelector('#form-user-password').style.display = 'block';
    document.querySelector('#password-error').style.display = 'none';
    let inputs = document.querySelectorAll('#form-user-password input');
    for(let i = 1; i < inputs.length; ++i) {
        inputs[i].classList.remove('color');
    }
    let username = document.querySelector('#username1');
    let password = document.querySelector('#user-password');
    let confirm = document.querySelector('#user-password-new');
    username.value = document.getElementsByClassName('td-user-info')[0].textContent;
    // call api
    document.querySelector('.btn-password-submit').onclick = function () {
        if(password.value !== confirm.value || password.value === '' || confirm.value === '') {
            for(let i = 1; i < inputs.length; ++i) {
                inputs[i].classList.add('color');
            }
            document.querySelector('#password-error').style.display = 'block';
        } else {
            let data = {
                username: username.value,
                password: password.value,
            }
            callApi(userPasswordApi, 'PUT', jwt, data, handleChangePassword);
        }
    }
}
// home
function preChart() {
    callApi(homeStudentApi, 'GET', jwt, '', handleMajorStudent);
    callApi(homeLecturerApi, 'GET', jwt, '', handleMajorLecturer);
    callApi(homeLevelApi, 'GET', jwt, '', handleLevel);
    callApi(homeGpaApi, 'GET', jwt, '', handleGpa);
}
// student lecturer
function preShow(api) {
    callApi(api + '?page=1&limit=5', 'GET', jwt, '', handleShow);
}
function prePage(blockPage, api, apiSearch, page) {
    let search = document.querySelector('#search');
    let value = search.getAttribute('search');
    let method = 'GET';
    let data = '';
    let api0 = api;
    if(value !== '') {
        data = {
            search: value
        }
        method = 'POST';
        api0 = apiSearch;
    }
    if (blockPage.classList.contains('disabled') === false) {
        api0 = api0 + "?page=" + (page) + "&limit=5";
        callApi(api0, method, jwt, data, handleShow);
    }
}
function preSearch(api) {
    let search = document.querySelector('#search');
    let value = search.value;
    search.setAttribute('search', value);
    let data = {
        search: value
    }
    callApi(api, 'POST', jwt, data, handleShow);
}
function preAdd(api, check) {
    // setup
    document.querySelector('.form').style.display = 'block';
    document.querySelector('#form-add-update').style.display = 'block';
    let fields = document.querySelectorAll('#form-add-update .add-up-field');
    for(let i = 0; i < fields.length; ++i)
        fields[i].classList.remove('color');
    document.querySelector('#error').style.display = 'none';
    // set value
    let name = document.querySelector('#name');
    let genders = document.getElementsByName('gender');
    let birth = document.querySelector('#birth');
    let address = document.querySelector('#address');
    let major = document.querySelector('#major');
    let other;
    if(check === 'student') {
        other = document.querySelector('#gpa');
        other.value = '';
    }
    if(check === 'lecturer'){
        other = document.querySelector('#level');
        other.value = 'Non';
    }
    name.value = ''; genders[0].checked = false; genders[1].checked = false; birth.value = '';
    address.value = ''; major.value = 'Non';
    // call api
    document.querySelector('.btn-submit').onclick = function () {
        let gender = genders[0];
        if(gender.checked === false) gender = genders[1];
        if(name.value === '' || gender.value === '' || birth.value === ''
        || address.value === '' || major.value === 'Non' || other.value === '' || other.value === 'Non') {
            let fields = document.querySelectorAll('#form-add-update .add-up-field');
            for(let i = 0; i < fields.length; ++i)
                fields[i].classList.add('color');
            let error = document.querySelector('#error');
            error.style.display = 'block';
        } else {
            let data = {
                name: name.value,
                gender: gender.value,
                birth: birth.value,
                address: address.value,
                major: major.value,
            }
            if(check === 'student') {
                data['gpa'] = parseFloat(other.value);
            }
            if(check === 'lecturer') {
                data['level'] = other.value;
            }
            callApi(api, 'POST', jwt, data, handleAddUp);
        }
    }
}
function preUpdate(id) {
    // setup
    document.querySelector('.form').style.display = 'block';
    document.querySelector('#form-add-update').style.display = 'block';
    let fields = document.querySelectorAll('#form-add-update .add-up-field');
    for(let i = 0; i < fields.length; ++i)
        fields[i].classList.remove('color');
    document.querySelector('#error').style.display = 'none';
    // set value
    let name = document.querySelector('#name');
    let genders = document.getElementsByName('gender');
    let birth = document.querySelector('#birth');
    let address = document.querySelector('#address');
    let major = document.querySelector('#major');
    let other;
    if(location.href === studentApi) {
        other = document.querySelector('#gpa');
    }
    let page = parseInt(document.querySelector('.a-now').innerHTML);
    let api = studentUpdateApi + '/' + id + '?page=' + page + '&limit=5';
    if(location.href === lecturerApi){
        other = document.querySelector('#level');
        api = lecturerUpdateApi + '/' + id + '?page=' + page + '&limit=5';
    }
    let cols = document.getElementById(id).childNodes;
    name.value = cols[3].textContent;
    if(cols[5].textContent === 'nam') genders[0].checked = true;
    else genders[1].checked = true;
    birth.value = cols[7].textContent;
    address.value = cols[9].textContent;
    major.value = cols[11].textContent;
    other.value = cols[13].textContent;
    // call api
    document.querySelector('.btn-submit').onclick = function () {
        let gender = genders[0];
        if(genders[1].checked === true) gender = genders[1];
        if(name.value === '' || gender.checked === false || birth.value === ''
            || address.value === '' || major.value === 'Non' || other.value === '' || other.value === 'Non') {
            let fields = document.querySelectorAll('#form-add-update .add-up-field');
            for(let i = 0; i < fields.length; ++i)
                fields[i].classList.add('color');
            let error = document.querySelector('#error');
            error.style.display = 'block';
        } else {
            let data = {
                name: name.value,
                gender: gender.value,
                birth: birth.value,
                address: address.value,
                major: major.value,
            }
            if(location.href === studentApi) {
                data['gpa'] = parseFloat(other.value);
            }
            if(location.href === lecturerApi) {
                data['level'] = other.value;
            }
            callApi(api, 'PUT', jwt, data, handleAddUp);
        }
    }
}
function preDelete(id) {
    let page = parseInt(document.querySelector('.a-now').innerHTML);
    let api = studentDeleteApi + '/' + id + '?page=' + page + '&limit=5';
    if(location.href === lecturerApi)
        api = lecturerDeleteApi + '/' + id + '?page=' + page + '&limit=5';
    callApi(api, 'DELETE', jwt, '', handleDelete);
}
// major level gpa
function preShowMLG(api, data) {
    if(location.href === majorSApi || location.href === majorLApi)
        callApi(api + '?page=1&limit=5', 'POST', jwt, data, handleShowMajor);
    else if(location.href === levelApi)
        callApi(api + '?page=1&limit=5', 'POST', jwt, data, handleShowLG);
    else callApi(api + '?page=1&limit=5', 'GET', jwt, data, handleShowLG);
}
function prePageMLG(blockPage, api, page, check) {
    let value;
    if(check === 'major' || check === 'level')
        value = document.querySelector('#search').value;
    let data = '';
    let api0 = api;
    if(check === 'major') {
        data = {
            major: value
        }
    }
    if(check === 'level') {
        data = {
            level: value
        }
    }
    let value0 = document.querySelector('#search').getAttribute('search');
    if(value0 === 'def') api0 = gpaShowApi;
    if(value0 === 'inc') api0 = gpaIncreaseApi;
    if(value0 === 'dec') api0 = gpaDecreaseApi;
    if (blockPage.classList.contains('disabled') === false) {
        api0 = api0 + "?page=" + (page) + "&limit=5";
        if(check === 'major') callApi(api0, 'POST', jwt, data, handleShowMajor);
        else if(check === 'level') callApi(api0, 'POST', jwt, data, handleShowLG);
        else callApi(api0, 'GET', jwt, '', handleShowLG);
    }
}
function preSearchMLG(api, check, gpa) {
    let search = document.querySelector('#search');
    let value;
    if(gpa === ''){
        value = search.value;
    }
    search.setAttribute('search', gpa);
    let data = '';
    if(check === 'major') {
        data = {
            major: value
        }
    }
    if(check === 'level') {
        data = {
            level: value
        }
    }
    if(check === 'major') callApi(api, 'POST', jwt, data, handleShowMajor);
    else if(check === 'level') callApi(api, 'POST', jwt, data, handleShowLG);
    else callApi(api, 'GET', jwt, data, handleShowLG);
}
// level gpa
function preUp(id) {
    // setup
    document.querySelector('.form').style.display = 'block';
    document.querySelector('#form-add-update').style.display = 'block';
    let fields = document.querySelectorAll('#form-add-update .add-up-field');
    for(let i = 0; i < fields.length; ++i)
        fields[i].classList.remove('color');
    document.querySelector('#error').style.display = 'none';
    // set value
    let name = document.querySelector('#name');
    let other;
    if(location.href === gpaApi) {
        other = document.querySelector('#gpa');
    }
    let page = parseInt(document.querySelector('.a-now').innerHTML);
    let api = gpaUpdateApi + '/' + id + '?page=' + page + '&limit=5';
    if(location.href === levelApi){
        other = document.querySelector('#level');
        api = levelUpdateApi + '/' + id + '?page=' + page + '&limit=5';
    }
    let cols = document.getElementById(id).childNodes;
    name.value = cols[3].textContent;
    other.value = cols[5].textContent;
    // call api
    document.querySelector('.btn-submit').onclick = function () {
        if(other.value === '' || other.value === 'Non') {
            let fields = document.querySelectorAll('#form-add-update .add-up-field');
            for(let i = 0; i < fields.length; ++i)
                fields[i].classList.add('color');
            let error = document.querySelector('#error');
            error.style.display = 'block';
        } else {
            let data = {
            }
            if(location.href === gpaApi) {
                data['gpa'] = parseFloat(other.value);
            }
            if(location.href === levelApi) {
                data['level'] = other.value;
            }
            callApi(api, 'PUT', jwt, data, handleUp);
        }
    }
}



























