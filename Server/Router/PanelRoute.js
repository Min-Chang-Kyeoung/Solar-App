var express = require('express');
var crawl = require('../MiddleWares/crawling');
var app = express();

module.exports = () =>{
    app.use('/Getpanel',crawl);
    return app;
}