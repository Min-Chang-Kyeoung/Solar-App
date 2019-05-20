var RnnSunDataController = require('../Controller/RnnSunDataController');
var express = require('express');
var app = express();

module.exports = () =>{
    app.get('/getRnnValue',RnnSunDataController.getRnnData);
    app.get('/getValue',RnnSunDataController.getHardwareData);
    app.get('/insertSolar',RnnSunDataController.insertSolarValue);
    //app.get('/getSolarValue',RnnSunDataController.getSolarValue);
    app.get('/test',RnnSunDataController.test);
    return app;
}