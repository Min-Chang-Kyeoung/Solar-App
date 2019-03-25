var RnnSunDataController = require('../Controller/RnnSunDataController');
var express = require('express');
var app = express();

module.exports = () =>{
    app.get('/getRnnValue',RnnSunDataController.getRnnData);
    return app;
}