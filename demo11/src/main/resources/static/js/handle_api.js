// login
function handleLogin(request) {
    let code = request['code'];
    if(code === '111') {
        let username = document.querySelector('#username');
        let password = document.querySelector('#password');
        username.value = ""; username.style.borderColor = 'red';
        password.value = ""; password.style.borderColor = 'red';
        document.querySelector('#login-error').style.display = 'block';
    }
    else {
        let data = request['data'];
        localStorage.setItem('Bearer', data);
        window.location = "http://localhost:8082/home";
    }
}
function handleCheck(request) {
    let code = request['code'];
    if(code === '111') window.location = "http://localhost:8082/login";
}
// user
function handleShowUser(request) {
    let data = request['data'];
    let htmls = `
        <table>
            <tr class="text-info small">
                <th>Tên đăng nhập:</th>
                <td class="pl-1 td-user-info">${data['username']}</td>
            </tr>
            <tr class="text-info small">
                <th>Tên:</th>
                <td class="pl-1 td-user-info">${data['name']}</td>
            </tr>
            <tr class="text-info small">
                <th>Giới tính:</th>
                <td class="pl-1 td-user-info">${data['gender']}</td>
            </tr>
            <tr class="text-info small">
                <th>Địa chỉ:</th>
                <td class="pl-1 td-user-info">${data['address']}</td>
            </tr>
        </table>
    `;
    let cardBody = document.querySelector('.card-body');
    cardBody.innerHTML = htmls;
}
function handleUpdateUser(request) {
    document.querySelector('#form-user-update').style.display = 'none';
    document.querySelector('.form').style.display = 'none';
    preShowUser();
}
function handleChangePassword(request) {
    document.querySelector('#form-user-password').style.display = 'none';
    document.querySelector('.form').style.display = 'none';
}
// home
function handleChart(id, type, xValues, yValues, barColors, text, scales) {
    new Chart(id, {
        type: type,
        data: {
            labels: xValues,
            datasets: [{
                backgroundColor: barColors,
                data: yValues
            }]
        },
        options: {
            legend: {display: false},
            title: {
                display: true,
                text: text
            },
            scales: scales
        }
    });
}
function handleMajorStudent(response) {
    var xValues = ["IT", "Kinh Tế", "Ngôn Ngữ"];
    var yValues = response['data'];
    var barColors = [
        "#80ffaa",
        "#ffff80",
        "#ff6699"
    ];
    handleChart('pieStudent', 'pie', xValues, yValues, barColors, 'Chuyên Ngành Sinh Viên:', '');
}
function handleMajorLecturer(response) {
    var xValues = ["IT", "Kinh Tế", "Ngôn Ngữ"];
    var yValues = response['data'];
    var barColors = [
        "#80ffaa",
        "#ffff80",
        "#ff6699"
    ];
    handleChart('pieLecturer', 'pie', xValues, yValues, barColors, 'Chuyên Ngành Giảng Viên:', '');
}
function handleLevel(response) {
    var xValues = ["Giáo Sư", "Tiến Sĩ", "Thạc Sĩ"];
    var yValues = response['data'];
    var barColors = [
        "#80ffff",
        "#ff5c33",
        "#8cff66"
    ];
    handleChart('barLecturer', 'bar', xValues, yValues, barColors, 'Trình Độ:', {yAxes: [{ticks: {min: 0}}]})
}
function handleGpa(response) {
    var xValues = ["0.00-1.00", "1.01-2.00", "2.01-3.00", "3.01-4.00"];
    var yValues = response['data'];
    var barColors = [
        "#80ffff",
        "#ff5c33",
        "#8cff66",
        "#66ffff"
    ];
    handleChart('barStudent', 'bar', xValues, yValues, barColors, 'Điểm:', {yAxes: [{ticks: {min: 0}}]})
}
// student lecturer
function handlePage(data) {
    // setup
    let page = data[0]['page'];
    let totalPages = data[0]['totalPages'];
    let previous = document.querySelector('.previous');
    let now = document.querySelector('.now');
    let then = document.querySelector('.then');
    let next = document.querySelector('.next');
    document.querySelector('.a-now').innerHTML = page;
    document.querySelector('.a-then').innerHTML = page + 1;
    now.classList.add('active');
    // check
    if(page === 1) previous.classList.add('disabled');
    else previous.classList.remove('disabled');
    if(page === totalPages) {
        then.classList.add('disabled');
        next.classList.add('disabled');
    } else {
        then.classList.remove('disabled');
        next.classList.remove('disabled');
    }
}
function handleShow(response) {
    let data = response['data'];
    let tBody = document.querySelector("#tbody");
    let html = "";
    for (let index in data) {
        let other = data[index]['level'];
        if(other === undefined) other = data[index]['gpa'];
        html += `
            <tr id="${data[index]['id']}">
                <td>${data[index]['id']}</td>
                <td>${data[index]['name']}</td>
                <td>${data[index]['gender']}</td>
                <td>${data[index]['birth']}</td>
                <td>${data[index]['address']}</td>
                <td>${data[index]['major']}</td>
                <td>${other}</td>
                <td>
                    <button type="button" class="btn btn-warning mr-2 update" style="padding:3px 10px;"
                    onclick="preUpdate(${data[index]['id']})">
                    Sửa</button>
                    <button type="button" class="btn btn-danger delete" style="padding:3px 10px;"
                    onclick="preDelete(${data[index]['id']})">
                    Xóa</button>
                </td>
            </tr>   
        `;
    }
    tBody.innerHTML = html;
    handlePage(data);
}
function handleAddUp() {
    document.querySelector('.form').style.display = 'none';
    document.querySelector('#form-add-update').style.display = 'none';
    let page = parseInt(document.querySelector('.a-now').innerHTML);
    if(location.href === studentApi)
        prePage(document.querySelector('.now'), studentShowApi, studentSearchApi, page)
    else if(location.href === lecturerApi)
        prePage(document.querySelector('.now'), lecturerShowApi, lecturerSearchApi, page)

}
function handleDelete() {
    let page = parseInt(document.querySelector('.a-now').innerHTML);
    if(location.href === studentApi)
        prePage(document.querySelector('.now'), studentShowApi, studentSearchApi, page)
    else if(location.href === lecturerApi)
        prePage(document.querySelector('.now'), lecturerShowApi, lecturerSearchApi, page)
}
// major
function handleShowMajor(response) {
    let data = response['data'];
    let tBody = document.querySelector("#tbody");
    let html = "";
    for (let index in data) {
        html += `
            <tr id="${data[index]['id']}">
                <td>${data[index]['id']}</td>
                <td>${data[index]['name']}</td>
                <td>${data[index]['major']}</td>
            </tr>   
        `;
    }
    tBody.innerHTML = html;
    handlePage(data);
}
// level gpa
function handleShowLG(response) {
    let data = response['data'];
    let tBody = document.querySelector("#tbody");
    let html = "";
    for (let index in data) {
        let other = data[index]['level'];
        if(other === undefined) other = data[index]['gpa'];
        html += `
            <tr id="${data[index]['id']}">
                <td>${data[index]['id']}</td>
                <td>${data[index]['name']}</td>
                <td>${other}</td>
                <td>
                    <button type="button" class="btn btn-warning mr-2 update" style="padding:3px 10px;"
                    onclick="preUp(${data[index]['id']})">
                    Sửa</button>
                </td>
            </tr>   
        `;
    }
    tBody.innerHTML = html;
    handlePage(data);
}
function handleUp() {
    document.querySelector('#form-add-update').style.display = 'none';
    document.querySelector('.form').style.display = 'none';
    let page = parseInt(document.querySelector('.a-now').innerHTML);
    if(location.href === levelApi)
        prePageMLG(document.querySelector('.now'), levelShowApi, page, 'level')
    else if(location.href === gpaApi)
        prePageMLG(document.querySelector('.now'), gpaShowApi, page, '')
}



















