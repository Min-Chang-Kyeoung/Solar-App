var express = require('express');
var fs = require('fs');
var app = express();
module.exports = () => {
    app.get('/panel/:id',(req,res) => {
        let imgName = req.params.id
        
        fs.exists('C://Users/Chang/Desktop/AR_app/Solar-Energy/Server/imgPanel/'+imgName, (exists)=>{
            if(exists){
                fs.readFile('C://Users/Chang/Desktop/AR_app/Solar-Energy/Server/imgPanel/'+imgName, (err,img) =>{
                    res.end(img)
                })
            }else{
                res.send({message:'dont exists'})
            }
        })
    })
    return app;
}