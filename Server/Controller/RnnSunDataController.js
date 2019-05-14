const RnnValue = require("../Models/rnnValue")
const request = require("request");
const Solar = require('../Models/Solar');

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
                    if(err) reject(err)
                    else resolve(arduinoData);
                });
            });
            return promise;
        }
        getData()
        .then((arduinoData) => {console.log(arduinoData); res.send(arduinoData)});
    },

    insertSolarValue : function(req,res){
        let solarData = [{value:"12.4",date:"2019-05-03"},{value:"10.7",date:"2019-05-04"},
        {value:"11.1",date:"2019-05-05"},{value:"12.8",date:"2019-05-06"},{value:"12.9",date:"2019-05-07"},
        {value:"12.2",date:"2019-05-08"},{value:"12.3",date:"2019-05-09"},{value:"13.7",date:"2019-05-10"}]
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
        let solarValue = Solar.find()
        solarValue.exec().then(solarValue=>{res.send(solarValue); console.log(solarValue);})
        .catch(err => res.send(err));
    }
}