// setup
let options = {
    method: '',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': ''
    },
    body: ''
};
function setApi(api, options, callback) {
    fetch(api, options)
        .then(function(response) {
            return response.json();
        })
        .then(callback);
}
// call
function callApi(api, method, jwt, data, callback) {
    options['method'] = method;
    options['headers']['Authorization'] = 'Bearer ' + jwt;
    if(data === '') delete options['body'];
    else options['body'] = JSON.stringify(data);
    setApi(api, options, callback);
}
