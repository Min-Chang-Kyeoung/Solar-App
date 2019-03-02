var express = require('express');
var panelcontroller = require('../Controller/PanelController');
var crawl = require('../MiddleWares/crawling');
var app = express();

module.exports = () =>{
    app.use('/Getpanel',crawl);
    app.get('/getallpanel',panelcontroller.getAllPanelData);
    app.post('/postdetailpanel',panelcontroller.postDetailPanelData);
    return app;
}