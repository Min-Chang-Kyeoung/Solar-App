var usercontroller = require('../Controller/UserController');
var express = require('express');
var app = express();

module.exports = () =>{
    app.post('/SignUp',usercontroller.insertUser);
    app.post('/LogIn',usercontroller.loginUser);

    return app;
}