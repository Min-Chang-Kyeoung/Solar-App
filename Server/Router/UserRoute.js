var usercontroller = require('../Controller/UserController');
var express = require('express');
var auth = require('../MiddleWares/auth');
var app = express();

module.exports = () =>{
    app.post('/SignUp',usercontroller.insertUser);
    app.use('/LogIn',auth);
    app.post('/LogIn',usercontroller.loginUser);
    return app;
}