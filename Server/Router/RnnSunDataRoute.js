var RnnSunDataController = require('../Controller/RnnSunDataController');
var express = require('express');
var app = express();

module.exports = () =>{
    app.get('/getRnnValue',RnnSunDataController.getRnnData);
    app.get('/getHardwareValue',RnnSunDataController.getHardwareData);
    app.get('/insertSolar',RnnSunDataController.insertSolarValue);
    app.get('/getSolarValue',RnnSunDataController.getSolarValue);
    return app;
}