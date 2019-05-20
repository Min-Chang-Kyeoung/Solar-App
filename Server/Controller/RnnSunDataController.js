const RnnValue = require("../Models/rnnValue")
const request = require("request");
const Solar = require('../Models/Solar');
const date_utils = require('date-utils');

module.exports = {
    getRnnData : function(req,res){
        let rnnValue = RnnValue.find()
        rnnValue.exec()
        .then(rnnValue =>{res.send(rnnValue); console.log(rnnValue);
        } )
        .catch(err => {res.send(err)})
    },

    getHardwareData : function(req,res){
        let url = "http://192.168.1.107:3001/test";
        id = req.userid;
        const getData = () => {
            const promise = new Promise((resolve,reject) =>{
                request(url , (err, res, body)=>{
                    arduinoData = null;
                    arduinoData = JSON.stringify(JSON.parse(body));

                    ////////////// Solar Data ///////////////
                    let newDate = new Date();
                    let time = newDate.toFormat('YYYY-MM-DD');
                    let solarValue = Solar.findOne({date:time.toString()})
                    solarValue.exec().then(solarValue => {
                        if (err) reject(err)
                        else {
                            data = {solar:solarValue,hardware:arduinoData};
                            resolve(data);
                        }
                    });
                });
            });
            return promise;
        }
        getData()
        .then((data) => {res.json(data)});
    },

    insertSolarValue : function(req,res){
        let solarData = [{value:"15.3",date:"2019-05-21"}]
        for(let i=0; i<solarData.length;i++){
            let solorData = new Solar({
                value:solarData[i].value,
                date:solarData[i].date
            })
            solorData.save().then(console.log(solarData));
        }
        res.send("success");
    },

    getSolarValue : function(req,res){
        let newDate = new Date();
        let time = newDate.toFormat('YYYY-MM-DD');
        let solarValue = Solar.findOne({date:time.toString()})
        solarValue.exec().then(solarValue => {res.send(solarValue); 
            console.log(solarValue);})
        .catch(err => res.send(err));
    },

    test : function(req,res){
        id = req.query.id;
        console.log(id);
        res.send(id);
    }
}